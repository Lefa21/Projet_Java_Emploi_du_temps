package fr.isep.frattesi.projetjavaedt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cours {
    private Date jourDate;
    private Date heureDebut;
    private Date heureFin;
    private String typeCours;
    private String commentaire;

    private Cours coursConcerne;
    private Salle salleUtilisee;
    private Enseignant enseignantResponsable;
    private String idCours; // Anciennement codeCours
    private String nomMatiere;
    private String description;

    // Relation avec Conflit (un créneau peut être impliqué dans plusieurs conflits)
    private List<Conflit> conflitsImpliques;

    public Cours(String idCours, Date jourDate, Date heureDebut, Date heureFin, String typeCours, String nomMatiere, String description) {
        this.idCours = idCours;
        this.jourDate = jourDate;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.typeCours = typeCours;
        this.conflitsImpliques = new ArrayList<>();
        this.nomMatiere = nomMatiere;
        this.description = description;
    }

    // Getters et Setters
    public String getidCours() {
        return idCours;
    }

    public void setIdentifiantCreneau(String identifiantCreneau) {
        this.idCours = identifiantCreneau;
    }

    public Date getJourDate() {
        return jourDate;
    }

    public void setJourDate(Date jourDate) {
        this.jourDate = jourDate;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public String getTypeCours() {
        return typeCours;
    }

    public void setTypeCours(String typeCours) {
        this.typeCours = typeCours;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Cours getCoursConcerne() {
        return coursConcerne;
    }

    public void setCoursConcerne(Cours coursConcerne) {
        this.coursConcerne = coursConcerne;
    }

    public Salle getSalleUtilisee() {
        return salleUtilisee;
    }

    public void setSalleUtilisee(Salle salleUtilisee) {
        this.salleUtilisee = salleUtilisee;
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


    public boolean modifierHoraire(Date nouveauJourDate, Date nouvelleHeureDebut, Date nouvelleHeureFin) {
        System.out.println("Modification de l'horaire du créneau " + idCours);
        this.setJourDate(nouveauJourDate);
        this.setHeureDebut(nouvelleHeureDebut);
        this.setHeureFin(nouvelleHeureFin);
        return true;
    }

    public boolean verifierDisponibiliteRessources() {
        System.out.println("Vérification de la disponibilité des ressources pour le créneau " + idCours);
        return true;
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

    public Enseignant getEnseignantResponsable() {
        return enseignantResponsable;
    }

    public void setEnseignantResponsable(Enseignant enseignantResponsable) {
        this.enseignantResponsable = enseignantResponsable;
    }
}

