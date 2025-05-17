package fr.isep.frattesi.projetjavaedt.dao;

import fr.isep.frattesi.projetjavaedt.dao.DatabaseManager;
import fr.isep.frattesi.projetjavaedt.model.Administrateur;
import fr.isep.frattesi.projetjavaedt.model.Etudiant;
import fr.isep.frattesi.projetjavaedt.model.Enseignant;
import fr.isep.frattesi.projetjavaedt.model.Utilisateur;
import fr.isep.frattesi.projetjavaedt.model.enums.TypeRole; // Assurez-vous que cette enum existe

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDao {

    /**
     * Trouve un utilisateur par son identifiant (id_utilisateur).
     * @param identifiant L'identifiant de l'utilisateur à rechercher.
     * @return L'objet Utilisateur trouvé, ou null s'il n'existe pas.
     */
    public Utilisateur trouverParIdentifiant(String identifiant) {
        String sql = "SELECT * FROM UTILISATEURS WHERE id_utilisateur = ?";
        Utilisateur utilisateur = null;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, identifiant);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String id = rs.getString("id_utilisateur");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String motDePasseHash = rs.getString("mot_de_passe_hash");
                TypeRole role = TypeRole.valueOf(rs.getString("type_role"));
                String numeroEtudiant = rs.getString("numero_etudiant");
                String matriculeEnseignant = rs.getString("matricule_enseignant");

                // Note: Le mot de passe est déjà haché dans la DB.
                // Le constructeur d'Utilisateur attend un mot de passe en clair pour le hacher.
                // Pour reconstruire l'objet, nous devons passer le hash directement au setter ou avoir un constructeur adapté.
                // Pour simplifier ici, nous allons supposer que nous avons un moyen de définir le hash directement.
                // Idéalement, la classe Utilisateur aurait un constructeur qui prend le hash directement,
                // ou on ne stocke pas le mot de passe dans l'objet après l'avoir récupéré de la DB,
                // car on ne l'utilise que pour la comparaison lors de la connexion.

                // Pour cet exemple, nous allons créer l'objet puis définir le hash.
                // Ceci est une simplification. Normalement, on ne stockerait pas le mot de passe en clair
                // dans l'objet Utilisateur après l'avoir récupéré de la DB.
                // La méthode seConnecter de Utilisateur sera utilisée pour la vérification.

                switch (role) {
                    case ETUDIANT:
                        // Le constructeur de Etudiant attend un mdp en clair pour le hacher.
                        // Nous allons créer l'objet et ensuite "forcer" le hash.
                        Etudiant etu = new Etudiant(id, nom, prenom, email, "placeholder", numeroEtudiant); // mdp placeholder
                        etu.setMotDePasseDirectHash(motDePasseHash); // Méthode à ajouter dans Utilisateur
                        utilisateur = etu;
                        break;
                    case ENSEIGNANT:
                        Enseignant ens = new Enseignant(id, nom, prenom, email, "placeholder", matriculeEnseignant);
                        ens.setMotDePasseDirectHash(motDePasseHash); // Méthode à ajouter dans Utilisateur
                        utilisateur = ens;
                        break;
                    case ADMINISTRATEUR:
                        Administrateur admin = new Administrateur(id, nom, prenom, email, "placeholder");
                        admin.setMotDePasseDirectHash(motDePasseHash); // Méthode à ajouter dans Utilisateur
                        utilisateur = admin;
                        break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur DAO en cherchant l'utilisateur par ID : " + e.getMessage());
            e.printStackTrace();
        }
        return utilisateur;
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     * Le mot de passe fourni à l'objet Utilisateur doit déjà être haché par sa logique interne.
     * @param utilisateur L'objet Utilisateur à ajouter.
     * @return true si l'ajout est réussi, false sinon.
     */
    public boolean ajouterUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO UTILISATEURS(id_utilisateur, nom, prenom, email, mot_de_passe_hash, type_role) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, utilisateur.getIdUtilisateur());
            pstmt.setString(2, utilisateur.getNom());
            pstmt.setString(3, utilisateur.getPrenom());
            pstmt.setString(4, utilisateur.getEmail());
            pstmt.setString(5, utilisateur.getMotDePasse()); // Récupère le hash déjà stocké dans l'objet
            pstmt.setString(6, utilisateur.getTypeRole().name());

            if (utilisateur instanceof Etudiant) {
                pstmt.setNull(8, java.sql.Types.VARCHAR);
            } else if (utilisateur instanceof Enseignant) {
                pstmt.setNull(7, java.sql.Types.VARCHAR);
            } else {
                pstmt.setNull(7, java.sql.Types.VARCHAR);
                pstmt.setNull(8, java.sql.Types.VARCHAR);
            }

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Erreur DAO lors de l'ajout de l'utilisateur : " + e.getMessage());
            if (e.getErrorCode() == 19 && e.getMessage().contains("UNIQUE constraint failed: UTILISATEURS.email")) {
                System.err.println("L'email " + utilisateur.getEmail() + " existe déjà.");
            } else if (e.getErrorCode() == 19 && e.getMessage().contains("UNIQUE constraint failed: UTILISATEURS.id_utilisateur")) {
                System.err.println("L'id_utilisateur " + utilisateur.getIdUtilisateur() + " existe déjà.");
            }
            e.printStackTrace();
            return false;
        }
    }
}
    