package fr.isep.frattesi.projetjavaedt.service;

import fr.isep.frattesi.projetjavaedt.model.Administrateur;
import fr.isep.frattesi.projetjavaedt.model.Etudiant;
import fr.isep.frattesi.projetjavaedt.model.Enseignant;
import fr.isep.frattesi.projetjavaedt.model.Utilisateur;
import fr.isep.frattesi.projetjavaedt.dao.UtilisateurDao; // Importer le DAO

public class AuthentificationService {

    private UtilisateurDao utilisateurDAO;

    public AuthentificationService() {
        this.utilisateurDAO = new UtilisateurDao();
        // Optionnel: Insérer des utilisateurs de test si la base est vide
        // Cela devrait idéalement être fait une seule fois ou via un script de configuration.
        // initialiserDonneesDeTestSiNecessaire();
    }

    /**
     * Tente d'authentifier un utilisateur.
     * @param identifiant L'identifiant fourni.
     * @param motDePasseEnClair Le mot de passe en clair fourni.
     * @return L'objet Utilisateur si l'authentification réussit, sinon null.
     */
    public Utilisateur authentifier(String identifiant, String motDePasseEnClair) {
        Utilisateur utilisateurTrouve = utilisateurDAO.trouverParIdentifiant(identifiant);

        if (utilisateurTrouve != null) {
            // La méthode seConnecter de l'objet Utilisateur compare le mot de passe en clair (après l'avoir haché)
            // avec le motDePasseHash stocké dans l'objet (qui a été chargé depuis la DB).
            if (utilisateurTrouve.seConnecter(identifiant, motDePasseEnClair)) {
                return utilisateurTrouve; // Authentification réussie
            }
        }
        return null; // Échec de l'authentification (utilisateur non trouvé ou mot de passe incorrect)
    }

    /**
     * Méthode d'exemple pour initialiser des données de test.
     * À appeler avec précaution (par exemple, vérifier si la table est vide avant).
     */
    public void initialiserDonneesDeTestSiNecessaire() {
        // Vérifier si des utilisateurs existent déjà pour éviter les doublons
        if (utilisateurDAO.trouverParIdentifiant("admin01") == null) {
            System.out.println("Initialisation des utilisateurs de test...");
            utilisateurDAO.ajouterUtilisateur(new Administrateur("admin01", "Min", "Ada", "admin@isep.fr", "admin123"));
            utilisateurDAO.ajouterUtilisateur(new Etudiant("etu01", "Dupont", "Jean", "jean.dupont@isep.fr", "etu123", "E001"));
            utilisateurDAO.ajouterUtilisateur(new Enseignant("ens01", "Martin", "Sophie", "sophie.martin@isep.fr", "ens123", "M001"));
            System.out.println("Utilisateurs de test ajoutés.");
        } else {
            System.out.println("Les utilisateurs de test existent déjà ou l'admin01 existe.");
        }
    }
}
    