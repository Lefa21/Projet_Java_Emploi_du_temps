package fr.isep.frattesi.projetjavaedt.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EmploiDuTemps {
    private String identifiantEdt;
    private String titre;
    private Date dateDebutValidite;
    private Date dateFinValidite;
    private String typeAffichage;


    private List<Cours> coursInclus;
     private List<Conflit> conflitsImpliques;


    public EmploiDuTemps(String identifiantEdt, String titre, Date dateDebutValidite, Date dateFinValidite, String typeAffichage) {
        this.identifiantEdt = identifiantEdt;
        this.titre = titre;
        this.dateDebutValidite = dateDebutValidite;
        this.dateFinValidite = dateFinValidite;
        this.typeAffichage = typeAffichage;
        this.conflitsImpliques = new ArrayList<>();
        this.coursInclus = new ArrayList<>();
    }

    // Getters et Setters
    public String getIdentifiantEdt() {
        return identifiantEdt;
    }

    public void setIdentifiantEdt(String identifiantEdt) {
        this.identifiantEdt = identifiantEdt;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDateDebutValidite() {
        return dateDebutValidite;
    }

    public void setDateDebutValidite(Date dateDebutValidite) {
        this.dateDebutValidite = dateDebutValidite;
    }

    public Date getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(Date dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    public String getTypeAffichage() {
        return typeAffichage;
    }

    public void setTypeAffichage(String typeAffichage) {
        this.typeAffichage = typeAffichage;
    }

    public List<Cours> getCreneauxInclus() {
        return coursInclus;
    }

    public void addCreneau(Cours cours) {
        if (cours != null && !this.coursInclus.contains(cours)) {
            this.coursInclus.add(cours);
        }
    }

    public void removeCours(Cours cours) {
        this.coursInclus.remove(cours);
    }


    public List<Conflit> getConflitsImpliques() {
        return conflitsImpliques;
    }

    public void addConflitImplique(Conflit conflit) {
        if (conflit != null && !this.conflitsImpliques.contains(conflit)) {
            this.conflitsImpliques.add(conflit);
        }
    }
    public void removeConflitImplique(Conflit conflit) {
        this.conflitsImpliques.remove(conflit);
    }

    // Méthodes du diagramme
    public String visualiserParVue(String vue) {
        System.out.println("Visualisation de l'EDT " + identifiantEdt + " par vue: " + vue);
        // Logique pour formater et retourner l'affichage de l'EDT selon la vue
        return "Affichage de l'EDT pour la vue: " + vue + " [Contenu à implémenter]";
    }

    public List<Cours> filtrerCours(Map<String, String> criteres) {
        System.out.println("Filtrage des créneaux de l'EDT " + identifiantEdt);
        // Logique pour filtrer les créneaux basés sur les critères
        List<Cours> coursFiltres = new ArrayList<>();
        // Exemple de critère: criteres.get("jour") == "Lundi"
        // Parcourir this.creneauxInclus et ajouter à creneauxFiltres si les critères correspondent
        return coursFiltres;
    }

    public File exporter(String format) {
        System.out.println("Exportation de l'EDT " + identifiantEdt + " au format: " + format);
        return null;
    }

    public void imprimer() {
        System.out.println("Impression de l'EDT " + identifiantEdt);
    }
}
