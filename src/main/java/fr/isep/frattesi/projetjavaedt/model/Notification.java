package fr.isep.frattesi.projetjavaedt.model;

public class Notification {
    private String identifiantNotification;
    private String message;
    private Date dateEnvoi;
    private EnumTypeNotification typeNotification;
    private boolean estLue;

    // Relations
    private Utilisateur destinataire;
    private Utilisateur emetteur;

    public Notification(String identifiantNotification, String message, EnumTypeNotification typeNotification, Utilisateur destinataire) {
        this.identifiantNotification = identifiantNotification;
        this.message = message;
        this.typeNotification = typeNotification;
        this.destinataire = destinataire;
        this.dateEnvoi = new Date();
        this.estLue = false;
    }

    // Getters et Setters
    public String getIdentifiantNotification() {
        return identifiantNotification;
    }

    public void setIdentifiantNotification(String identifiantNotification) {
        this.identifiantNotification = identifiantNotification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public EnumTypeNotification getTypeNotification() {
        return typeNotification;
    }

    public void setTypeNotification(EnumTypeNotification typeNotification) {
        this.typeNotification = typeNotification;
    }

    public boolean isEstLue() {
        return estLue;
    }

    public void setEstLue(boolean estLue) {
        this.estLue = estLue;
    }

    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Utilisateur destinataire) {
        this.destinataire = destinataire;
    }

    public Utilisateur getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(Utilisateur emetteur) {
        this.emetteur = emetteur;
    }
}
