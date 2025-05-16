package fr.isep.frattesi.projetjavaedt.model;

import fr.isep.frattesi.projetjavaedt.model.enums.TypeRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Enseignant extends Utilisateur {
    private String numeroEnseignant;
    private List<Cours> coursResponsable;
    private List<Cours> creneauxAssures;


    public Enseignant(String identifiantUtilisateur, String nom, String prenom, String email, String motDePasse, String numeroEnseignant) {
        super(identifiantUtilisateur, nom, prenom, email, motDePasse, TypeRole.ENSEIGNANT);
        this.numeroEnseignant = numeroEnseignant;
        this.coursResponsable = new ArrayList<>();
        this.creneauxAssures = new ArrayList<>();
    }


    public String getNumeroEnseignant() {
        return numeroEnseignant;
    }

    public void setNumeroEnseignant(String numeroEnseignant) {
        this.numeroEnseignant = numeroEnseignant;
    }

    public List<Cours> getCoursResponsable() {
        return coursResponsable;
    }

    public void addCoursResponsable(Cours cours) {
        if (cours != null && !this.coursResponsable.contains(cours)) {
            this.coursResponsable.add(cours);
        }
    }
    public void removeCoursResponsable(Cours cours) {
        this.coursResponsable.remove(cours);
    }



    public List<Cours> getCreneauxAssures() {
        return creneauxAssures;
    }
    public void addCreneauAssure(Cours cours) {
        if (cours != null && !this.creneauxAssures.contains(cours)) {
            this.creneauxAssures.add(cours);
            // Optionnel: if (creneau.getEnseignant() != this) creneau.setEnseignant(this);
        }
    }
    public void removeCreneauAssure(Cours cours) {
        this.creneauxAssures.remove(cours);
    }



    @Override
    public EmploiDuTemps consulterEmploiDuTemps(Date dateDebut, Date dateFin) {
        System.out.println("L'enseignant " + getPrenom() + " " + getNom() + " consulte son emploi du temps.");
        EmploiDuTemps edt = new EmploiDuTemps("edt_enseignant_" + super.getIdUtilisateur(), "Emploi du temps pour " + getNom(), new Date(), new Date(), "semaine");
        for (Cours cours : this.creneauxAssures) {
        }
        return edt;
    }

    public String accederInformationsCoursEnseignes(Cours cours) {
        System.out.println("L'enseignant " + getPrenom() + " " + getNom() + " accède aux informations du cours: " + cours.getNomMatiere());
        return "Informations pour le cours " + cours.getNomMatiere() + ": [Détails à implémenter]";
    }

    public void notifierAnomalieAdministrateur(String message, String typeAnomalie) {
        System.out.println("L'enseignant " + getPrenom() + " " + getNom() + " notifie une anomalie de type '" + typeAnomalie + "': " + message);
    }
}
