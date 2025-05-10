package fr.isep.frattesi.projetjavaedt.model;

import java.util.ArrayList;
import java.util.List;

public class Faculte {
    private String idFaculte; // Anciennement codeFaculte
    private String nomFaculte;

    // Relation avec Promotion (une faculté contient plusieurs promotions)
    private List<Promotion> promotions;

    private Administrateur responsable;

    private List<Enseignant> enseignantsRattaches;

    private List<Cours> coursDispenses;


    public Faculte(String idFaculte, String nomFaculte) {
        this.idFaculte = idFaculte;
        this.nomFaculte = nomFaculte;
        this.promotions = new ArrayList<>();
        this.enseignantsRattaches = new ArrayList<>();
        this.coursDispenses = new ArrayList<>();
    }

    // Getters et Setters
    public String getIdFaculte() {
        return idFaculte;
    }

    public void setIdFaculte(String idFaculte) {
        this.idFaculte = idFaculte;
    }

    public String getNomFaculte() {
        return nomFaculte;
    }

    public void setNomFaculte(String nomFaculte) {
        this.nomFaculte = nomFaculte;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void addPromotion(Promotion promotion) {
        if (promotion != null && !this.promotions.contains(promotion)) {
            this.promotions.add(promotion);
        }
    }
    public void removePromotion(Promotion promotion) {
        this.promotions.remove(promotion);
    }


    public Administrateur getResponsable() {
        return responsable;
    }

    public void setResponsable(Administrateur responsable) {
        this.responsable = responsable;
    }

    public List<Enseignant> getEnseignantsRattaches() {
        return enseignantsRattaches;
    }

    public void addEnseignantRattache(Enseignant enseignant) {
        if (enseignant != null && !this.enseignantsRattaches.contains(enseignant)) {
            this.enseignantsRattaches.add(enseignant);
        }
    }
    public void removeEnseignantRattache(Enseignant enseignant) {
        this.enseignantsRattaches.remove(enseignant);
    }


    public List<Cours> getCoursDispenses() {
        return coursDispenses;
    }

    public void addCoursDispense(Cours cours) {
        if (cours != null && !this.coursDispenses.contains(cours)) {
            this.coursDispenses.add(cours);
        }
    }
    public void removeCoursDispense(Cours cours) {
        this.coursDispenses.remove(cours);
    }
}
