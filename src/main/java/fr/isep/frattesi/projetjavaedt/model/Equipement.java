package fr.isep.frattesi.projetjavaedt.model;

public class Equipement {
    private String idEquipement;
    private String nomEquipement;
    private String typeEquipement;
    private int quantite;
    private String etat;

    public Equipement(String idEquipement, String nomEquipement, String typeEquipement, int quantite, String etat) {
        this.idEquipement = idEquipement;
        this.nomEquipement = nomEquipement;
        this.typeEquipement = typeEquipement;
        this.quantite = quantite;
        this.etat = etat;
    }

    // Getters et Setters
    public String getIdEquipement() {
        return idEquipement;
    }

    public void setIdEquipement(String idEquipement) {
        this.idEquipement = idEquipement;
    }

    public String getNomEquipement() {
        return nomEquipement;
    }

    public void setNomEquipement(String nomEquipement) {
        this.nomEquipement = nomEquipement;
    }

    public String getTypeEquipement() {
        return typeEquipement;
    }

    public void setTypeEquipement(String typeEquipement) {
        this.typeEquipement = typeEquipement;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
