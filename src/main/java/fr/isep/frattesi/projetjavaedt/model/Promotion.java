package fr.isep.frattesi.projetjavaedt.model;

import java.util.ArrayList;
import java.util.List;

public class Promotion {
    private String identifiantPromotion;
    private String nomPromotion;
    private String anneeScolaire;
    private List<Etudiant> etudiants;

    public Promotion(String identifiantPromotion, String nomPromotion, String anneeScolaire) {
        this.identifiantPromotion = identifiantPromotion;
        this.nomPromotion = nomPromotion;
        this.anneeScolaire = anneeScolaire;
        this.etudiants = new ArrayList<>();
    }

    // Getters et Setters
    public String getIdentifiantPromotion() {
        return identifiantPromotion;
    }

    public void setIdentifiantPromotion(String identifiantPromotion) {
        this.identifiantPromotion = identifiantPromotion;
    }

    public String getNomPromotion() {
        return nomPromotion;
    }

    public void setNomPromotion(String nomPromotion) {
        this.nomPromotion = nomPromotion;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void addEtudiant(Etudiant etudiant) {
        if (etudiant != null && !this.etudiants.contains(etudiant)) {
            this.etudiants.add(etudiant);
        }
    }

    public void removeEtudiant(Etudiant etudiant) {
        this.etudiants.remove(etudiant);
    }
}
