package fr.isep.frattesi.projetjavaedt.model;

import fr.isep.frattesi.projetjavaedt.model.enums.TypeRole;

import java.util.Date;

public class Etudiant extends Utilisateur {
    private String numeroEtudiant;

    public Etudiant(String identifiantUtilisateur, String nom, String prenom, String email, String motDePasseHash, String numeroEtudiant) {
        super(identifiantUtilisateur, nom, prenom, email, motDePasseHash, TypeRole.ETUDIANT);
        this.numeroEtudiant = numeroEtudiant;
    }

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    @Override
    public EmploiDuTemps consulterEmploiDuTemps(Date dateDebut, Date dateFin) {
        System.out.println("L'étudiant " + getPrenom() + " " + getNom() + " consulte son emploi du temps.");

        EmploiDuTemps edt = new EmploiDuTemps("edt_etudiant_" + getNumeroEtudiant(), "Emploi du temps pour " + getNom(), new Date(), new Date(), "semaine");
        return edt;
    }

    public String voirInformationsSalle(Salle salle) {
        System.out.println("L'étudiant " + getPrenom() + " " + getNom() + " consulte les informations de la salle " + salle.getNumeroSalle());
        if (salle != null) {
            return "Salle: " + salle.getNumeroSalle() + ", Bâtiment: " + salle.getBatiment() + ", Capacité: " + salle.getCapaciteMax();
        }
        return "Salle non spécifiée.";
    }

    public void recevoirNotification(Notification notification) {
        System.out.println("L'étudiant " + getPrenom() + " " + getNom() + " a reçu une notification : " + notification.getMessage());

    }

}
