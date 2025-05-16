package fr.isep.frattesi.projetjavaedt.model;

import fr.isep.frattesi.projetjavaedt.model.enums.StatutConflit;
import fr.isep.frattesi.projetjavaedt.model.enums.TypeConflit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conflit {
    private String identifiantConflit;
    private TypeConflit typeConflit;
    private String descriptionDetaille;
    private StatutConflit statutConflit;
    private Date dateSignalement;

    private Administrateur adminEnCharge;
    private Utilisateur signalePar;
    private Salle salleConcernee;

    private List<Cours> creneauxImpliques;

    public Conflit(String identifiantConflit, TypeConflit typeConflit, String descriptionDetailee, Utilisateur signalePar) {
        this.identifiantConflit = identifiantConflit;
        this.typeConflit = typeConflit;
        this.descriptionDetaille = descriptionDetailee;
        this.signalePar = signalePar;
        this.statutConflit = StatutConflit.SIGNALE;
        this.dateSignalement = new Date();
        this.creneauxImpliques = new ArrayList<>();
    }

    // Getters et Setters
    public String getIdentifiantConflit() {
        return identifiantConflit;
    }

    public void setIdentifiantConflit(String identifiantConflit) {
        this.identifiantConflit = identifiantConflit;
    }

    public TypeConflit getTypeConflit() {
        return typeConflit;
    }

    public void setTypeConflit(TypeConflit typeConflit) {
        this.typeConflit = typeConflit;
    }

    public String getDescriptionDetaille() {
        return descriptionDetaille;
    }

    public void setDescriptionDetaille(String descriptionDetaille) {
        this.descriptionDetaille = descriptionDetaille;
    }

    public StatutConflit getStatutConflit() {
        return statutConflit;
    }

    public void setStatutConflit(StatutConflit statutConflit) {
        this.statutConflit = statutConflit;
    }

    public Date getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(Date dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public Administrateur getAdministrateurEnCharge() {
        return adminEnCharge;
    }

    public void setAdministrateurEnCharge(Administrateur adminEnCharge) {
        this.adminEnCharge = adminEnCharge;
    }

    public Utilisateur getSignalePar() {
        return signalePar;
    }

    public void setSignalePar(Utilisateur signalePar) {
        this.signalePar = signalePar;
    }

    public Salle getSalleConcernee() {
        return salleConcernee;
    }

    public void setSalleConcernee(Salle salleConcernee) {
        this.salleConcernee = salleConcernee;
    }

    public List<Cours> getCreneauxImpliques() {
        return creneauxImpliques;
    }

    public void addCreneauImplique(Cours cours) {
        if (cours != null && !this.creneauxImpliques.contains(cours)) {
            this.creneauxImpliques.add(cours);
        }
    }
    public void removeCreneauImplique(Cours cours) {
        this.creneauxImpliques.remove(cours);
    }
}
