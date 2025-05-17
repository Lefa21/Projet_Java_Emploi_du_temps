package fr.isep.frattesi.projetjavaedt.model;

import fr.isep.frattesi.projetjavaedt.model.enums.TypeRole; // Assurez-vous que cette énumération est bien définie

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


    public void setTypeRole(TypeRole typeRole) {
        this.typeRole = typeRole;
    }

    public void setMotDePasse(String motDePasseEnClair) {
        this.motDePasse = hashPassword(motDePasseEnClair); // Appelle la nouvelle méthode hashPassword
    }

    public boolean seConnecter(String identifiant, String motDePasseEnClair) {
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
        if (password == null) {
            return null;
        }
        try {
            // Créer une instance de MessageDigest pour SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Obtenir les bytes du mot de passe (en UTF-8)
            byte[] encodedhash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));

            // Convertir le tableau de bytes en une représentation hexadécimale
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // Cette exception ne devrait pas se produire si SHA-256 est un algorithme standard
            System.err.println("Erreur critique: Algorithme de hachage SHA-256 non trouvé.");
            e.printStackTrace();
            // En cas d'erreur, vous pourriez retourner null ou lever une RuntimeException
            // pour indiquer un problème de configuration grave.
            // Retourner le mot de passe en clair ou un hash simple serait une mauvaise idée.
            throw new RuntimeException("Impossible de hacher le mot de passe.", e);
        }
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

    public void setMotDePasseDirectHash(String motDePasseHash) {
        this.motDePasse = motDePasseHash;
    }

    public abstract EmploiDuTemps consulterEmploiDuTemps(Date dateDebut, Date dateFin);
}
