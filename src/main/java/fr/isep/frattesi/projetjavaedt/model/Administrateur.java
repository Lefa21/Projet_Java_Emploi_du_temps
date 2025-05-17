package fr.isep.frattesi.projetjavaedt.model;

import fr.isep.frattesi.projetjavaedt.model.enums.TypeRole;

import java.util.Date;
import java.util.Map;
public class Administrateur extends Utilisateur {

    public Administrateur(String identifiantUtilisateur, String nom, String prenom, String email, String motDePasseHash) {
        super(identifiantUtilisateur, nom, prenom, email, motDePasseHash, TypeRole.ADMINISTRATEUR);
    }

    @Override
    public EmploiDuTemps consulterEmploiDuTemps(Date dateDebut, Date dateFin) {
        System.out.println("La méthode consulterEmploiDuTemps (personnel) n'est pas typiquement utilisée pour un Administrateur de cette manière.");
        return new EmploiDuTemps("edt_admin_personnel_vide", "EDT Personnel Admin (Non applicable)", new Date(), new Date(), "jour");
    }

    public Utilisateur creerUtilisateur(Map<String, String> detailsUtilisateur, TypeRole role) {
        return this;
    }

    public boolean gererUtilisateur(Utilisateur utilisateur, String action, Map<String, String> details) {
        System.out.println("Admin " + getNom() + " effectue l'action '" + action + "' sur l'utilisateur: ");
        return true;
    }

    public EmploiDuTemps creerEmploiDuTemps(Map<String, String> detailsEdt) {
        System.out.println("Admin " + getNom() + " crée un emploi du temps.");
        String id = detailsEdt.getOrDefault("id", "edt" + System.currentTimeMillis());
        String titre = detailsEdt.getOrDefault("titre", "Nouvel EDT");
        return new EmploiDuTemps(id, titre, new Date(), new Date(), "semaine");
    }

    public boolean gererEmploiDuTemps(EmploiDuTemps edt, String action, Map<String, String> modifications) {
        System.out.println("Admin " + getNom() + " effectue l'action '" + action + "' sur l'EDT: " + edt.getIdentifiantEdt());
        return true;
    }

    public boolean affecterEnseignantACours(Enseignant enseignant, Cours cours) {
        System.out.println("Admin " + getNom() + " affecte l'enseignant " + enseignant.getNom() + " au créneau " + cours.getIdCours());
        if (enseignant != null && cours != null) {
            cours.setEnseignantResponsable(enseignant);
            return true;
        }
        return false;
    }

    public boolean gererSalle(Salle salle, String action, Map<String, String> details) {
        System.out.println("Admin " + getNom() + " effectue l'action '" + action + "' sur la salle: " + (salle != null ? salle.getNumeroSalle() : "nouvelle salle"));
        return true;
    }

    public boolean gererEquipement(Equipement equipement, String action, Map<String, String> details) {
        System.out.println("Admin " + getNom() + " effectue l'action '" + action + "' sur l'équipement: " + (equipement != null ? equipement.getIdEquipement() : "nouvel équipement"));
        return true;
    }


    public boolean superviserConflit(Conflit conflit, String action, Map<String, Object> details) {
        System.out.println("Admin " + getNom() + " supervise le conflit " + conflit.getIdentifiantConflit() + " avec l'action: " + action);
        return true;
    }

    public void resoudreConflit(Conflit conflit, String solution) {
        System.out.println("Admin " + getNom() + " résout le conflit " + conflit.getIdentifiantConflit() + " avec la solution: " + solution);
        if (conflit != null) {
            conflit.setStatutConflit(fr.isep.frattesi.projetjavaedt.model.enums.StatutConflit.RESOLU);
        }
    }

    public Map<String, Object> suivreStatistiques(String typeStat, String periode) {
        System.out.println("Admin " + getNom() + " suit les statistiques de type '" + typeStat + "' pour la période '" + periode + "'.");
        return new java.util.HashMap<>();
    }
}