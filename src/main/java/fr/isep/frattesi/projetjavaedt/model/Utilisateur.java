package fr.isep.frattesi.projetjavaedt.model;

import fr.isep.frattesi.projetjavaedt.model.enums.TypeRole;

import java.util.Date; // Pour Date, Time, DateTime du diagramme

/**
 * Classe abstraite représentant un utilisateur du système.
 */
public abstract class Utilisateur {
    protected String idUtilisateur;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String motDePasse;
    protected TypeRole typeRole;

    public Utilisateur(String idUtilisateur, String nom, String prenom, String email, String motDePasse, TypeRole typeRole) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
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

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setTypeRole(TypeRole typeRole) {
        this.typeRole = typeRole;
    }

    // Méthodes du diagramme
    public boolean sAuthentifier(String identifiant, String motDePasse) {
        System.out.println("Tentative d'authentification pour : " + identifiant);
        return this.idUtilisateur.equals(identifiant) && this.motDePasse.equals(hashPassword(motDePasse)); // Simplifié
    }

    private String hashPassword(String password) {
        return "hashed_" + password;
    }

    public boolean modifierMotDePasse(String ancienMdp, String nouveauMdp) {
        System.out.println("Tentative de modification du mot de passe pour : " + this.idUtilisateur);
        if (this.motDePasse.equals(hashPassword(ancienMdp))) {
            this.setMotDePasse(hashPassword(nouveauMdp));
            System.out.println("Mot de passe modifié avec succès.");
            return true;
        }
        System.out.println("Échec de la modification du mot de passe : ancien mot de passe incorrect.");
        return false;
    }

    public String consulterProfil() {
        return "Profil de " + prenom + " " + nom + " (" + typeRole + ")";
    }

    public abstract EmploiDuTemps consulterEmploiDuTemps(Date dateDebut, Date dateFin);
}

