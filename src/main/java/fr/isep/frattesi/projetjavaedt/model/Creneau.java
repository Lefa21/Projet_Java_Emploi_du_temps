package fr.isep.frattesi.projetjavaedt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Creneau {
    private String identifiantCreneau;
    private Date jourDate;
    private Date heureDebut;
    private Date heureFin;
    private String typeCours;
    private String commentaire;

    private Cours coursConcerne;
    private Salle salleUtilisee;
    private Enseignant enseignantAssurant;

    // Relation avec Conflit (un créneau peut être impliqué dans plusieurs conflits)
    private List<Conflit> conflitsImpliques;

    public Creneau(String identifiantCreneau, Date jourDate, Date heureDebut, Date heureFin, String typeCours) {
        this.identifiantCreneau = identifiantCreneau;
        this.jourDate = jourDate;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.typeCours = typeCours;
        this.conflitsImpliques = new ArrayList<>();
    }

    // Getters et Setters
    public String getIdentifiantCreneau() {
        return identifiantCreneau;
    }

    public void setIdentifiantCreneau(String identifiantCreneau) {
        this.identifiantCreneau = identifiantCreneau;
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

    public Enseignant getEnseignantAssurant() {
        return enseignantAssurant;
    }

    public void setEnseignant(Enseignant enseignantAssurant) {
        this.enseignantAssurant = enseignantAssurant;
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
        System.out.println("Modification de l'horaire du créneau " + identifiantCreneau);
        this.setJourDate(nouveauJourDate);
        this.setHeureDebut(nouvelleHeureDebut);
        this.setHeureFin(nouvelleHeureFin);
        return true;
    }

    public boolean verifierDisponibiliteRessources() {
        System.out.println("Vérification de la disponibilité des ressources pour le créneau " + identifiantCreneau);
        return true;
    }
}

