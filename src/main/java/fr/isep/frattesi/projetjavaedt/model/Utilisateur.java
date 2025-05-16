package fr.isep.frattesi.projetjavaedt.model;

import fr.isep.frattesi.projetjavaedt.model.enums.TypeRole; // Assurez-vous que cette énumération est bien définie

import java.util.Date;

/**
 * Classe abstraite représentant un utilisateur du système.
 */
public abstract class Utilisateur {
    protected String idUtilisateur;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse; // Cet attribut stockera le mot de passe HACHÉ
    protected TypeRole typeRole;

    public Utilisateur(String idUtilisateur, String nom, String prenom, String email, String motDePasseEnClair, TypeRole typeRole) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = hashPassword(motDePasseEnClair); // Hachage lors de la création
        this.typeRole = typeRole;
    }

    // Getters
    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Récupère le hash du mot de passe.
     * @return Le mot de passe haché.
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    public TypeRole getTypeRole() {
        return typeRole;
    }

    // Setters
    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     * Le mot de passe fourni en clair sera haché avant stockage.
     * @param motDePasseEnClair Le nouveau mot de passe en clair.
     */
    public void setMotDePasse(String motDePasseEnClair) {
        this.motDePasse = hashPassword(motDePasseEnClair); // Hachage lors de la modification
    }

    public void setTypeRole(TypeRole typeRole) {
        this.typeRole = typeRole;
    }

    // Méthodes du diagramme
    /**
     * Tente d'authentifier l'utilisateur.
     * @param identifiant L'identifiant fourni.
     * @param motDePasseEnClair Le mot de passe en clair fourni.
     * @return true si l'authentification réussit, false sinon.
     */
    public boolean seConnecter(String identifiant, String motDePasseEnClair) {
        System.out.println("Tentative d'authentification pour : " + identifiant);
        // Compare l'identifiant et le hash du mot de passe fourni avec le hash stocké
        return this.idUtilisateur.equals(identifiant) && this.motDePasse.equals(hashPassword(motDePasseEnClair));
    }

    public void seDeconnecter(){
        System.out.println("Utilisateur " + this.prenom + " " + this.nom + " se déconnecte.");
        // Logique de déconnexion (ex: invalider une session, etc.)
    }

    /**
     * Méthode privée pour hacher un mot de passe.
     * ATTENTION: Ceci est une simulation TRÈS simpliste pour l'exemple.
     * NE PAS UTILISER EN PRODUCTION. Utilisez des bibliothèques de hachage robustes.
     * @param password Le mot de passe en clair.
     * @return Une version "hachée" (simulée) du mot de passe.
     */
    private String hashPassword(String password) {
        if (password == null) return null;
        return "hashed_" + password; // Simulation de hachage
    }

    /**
     * Tente de modifier le mot de passe de l'utilisateur.
     * @param ancienMdpEnClair L'ancien mot de passe en clair.
     * @param nouveauMdpEnClair Le nouveau mot de passe en clair.
     * @return true si la modification est réussie, false sinon.
     */
    public boolean modifierMotDePasse(String ancienMdpEnClair, String nouveauMdpEnClair) {
        System.out.println("Tentative de modification du mot de passe pour : " + this.idUtilisateur);
        if (this.motDePasse.equals(hashPassword(ancienMdpEnClair))) {
            this.setMotDePasse(nouveauMdpEnClair); // Le setter va hacher le nouveau mot de passe
            System.out.println("Mot de passe modifié avec succès pour " + this.idUtilisateur);
            return true;
        }
        System.out.println("Échec de la modification du mot de passe pour " + this.idUtilisateur + " : ancien mot de passe incorrect.");
        return false;
    }

    public String consulterProfil() {
        return "Profil de " + prenom + " " + nom + " (" + typeRole + ")";
    }

    public abstract EmploiDuTemps consulterEmploiDuTemps(Date dateDebut, Date dateFin);
}
