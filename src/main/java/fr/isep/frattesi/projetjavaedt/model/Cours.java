package fr.isep.frattesi.projetjavaedt.model;

public class Cours {
    private String idCours; // Anciennement codeCours
    private String nomMatiere;
    private String description;
    // creditsECTS a été retiré

    // Relations
    private Faculte faculte;
    private Enseignant enseignantResponsable;


    public Cours(String idCours, String nomMatiere,String description) {
        this.idCours = idCours;
        this.nomMatiere = nomMatiere;
        this.description = description;
    }

    // Getters et Setters
    public String getIdCours() {
        return idCours;
    }

    public void setIdCours(String idCours) {
        this.idCours = idCours;
    }

    public String getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Faculte getFaculte() {
        return faculte;
    }

    public void setFaculte(Faculte faculte) {
        this.faculte = faculte;
    }

    public Enseignant getEnseignantResponsable() {
        return enseignantResponsable;
    }

    public void setEnseignantResponsable(Enseignant enseignantResponsable) {
        this.enseignantResponsable = enseignantResponsable;
    }
}
