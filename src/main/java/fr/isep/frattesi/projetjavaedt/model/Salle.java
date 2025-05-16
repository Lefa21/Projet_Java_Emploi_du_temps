package fr.isep.frattesi.projetjavaedt.model;

import java.util.ArrayList;
import java.util.List;

public class Salle {
    private String numeroSalle;
    private String batiment;
    private String etage;
    private int capaciteMax;
    private boolean estDisponible;


    private List<Equipement> equipements;

    public Salle(String numeroSalle, String batiment, String etage, int capaciteMax) {
        this.numeroSalle = numeroSalle;
        this.batiment = batiment;
        this.etage = etage;
        this.capaciteMax = capaciteMax;
        this.estDisponible = true;
        this.equipements = new ArrayList<>();
    }

    public String getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(String numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public List<Equipement> getEquipements() {
        return equipements;
    }

    public boolean verifierDisponibilitePourCreneau(Cours cours) {
        System.out.println("Vérification de la disponibilité de la salle " + numeroSalle + " pour le créneau " + cours.getIdentifiantCreneau());
        return this.estDisponible; // Simplifié
    }

    public void ajouterEquipementASalle(Equipement equipement) {
        System.out.println("Ajout de l'équipement " + equipement.getIdEquipement() + " à la salle " + numeroSalle);
        if (equipement != null && !this.equipements.contains(equipement)) {
            this.equipements.add(equipement);
        }
    }

    public void retirerEquipementDeSalle(Equipement equipement) {
        System.out.println("Retrait de l'équipement " + equipement.getIdEquipement() + " de la salle " + numeroSalle);
        this.equipements.remove(equipement);
    }
}
