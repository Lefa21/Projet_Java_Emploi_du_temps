package fr.isep.frattesi.projetjavaedt.controller;


import fr.isep.frattesi.projetjavaedt.EmploiDuTempsAPP;
import fr.isep.frattesi.projetjavaedt.model.Utilisateur;
import fr.isep.frattesi.projetjavaedt.model.Administrateur; // Importer Administrateur

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import java.io.IOException;

public class DashboardAdministrateurController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Button btnGestionSalles;
    @FXML
    private Button btnGestionEquipements;
    @FXML
    private Button btnGestionUtilisateurs;
    @FXML
    private Button btnGestionEmploisDuTemps;
    @FXML
    private Button btnGestionCours; // Assurez-vous que ce fx:id existe dans votre FXML
    @FXML
    private Button btnGestionConflits;
    @FXML
    private Button btnVoirStatistiques;

    @FXML
    private MenuItem menuItemDeconnexion; // Assurez-vous que ce fx:id existe dans votre FXML

    private Administrateur adminConnecte; // Stocker l'admin connecté

    @FXML
    private void seDeconnecter() {
        System.out.println("Déconnexion demandée par l'admin.");
        if (this.adminConnecte != null) {
            this.adminConnecte.seDeconnecter();
        }
        EmploiDuTempsAPP.setCurrentUser(null);
        try {
            EmploiDuTempsAPP.loadScene("LoginView.fxml", "Connexion", 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
            afficherAlerte(Alert.AlertType.ERROR,"Erreur de déconnexion", "Impossible de retourner à l'écran de connexion.");
        }
    }

    @FXML
    private void gererSalles() {
        System.out.println("Navigation vers la gestion des salles.");
        try {
            EmploiDuTempsAPP.loadScene("GestionSallesView.fxml", "Gestion des Salles", 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
            afficherAlerte(Alert.AlertType.ERROR,"Erreur de Navigation", "Impossible de charger la vue de gestion des salles.");
        }
    }

    @FXML
    private void gererEquipements() {
        afficherAlerte(Alert.AlertType.INFORMATION,"Fonctionnalité en cours", "La gestion des équipements sera bientôt disponible.");
    }

    @FXML
    private void gererUtilisateurs() {
        afficherAlerte(Alert.AlertType.INFORMATION,"Fonctionnalité en cours", "La gestion des utilisateurs sera bientôt disponible.");
    }

    @FXML
    private void gererEmploisDuTemps() {
        afficherAlerte(Alert.AlertType.INFORMATION,"Fonctionnalité en cours", "La gestion des emplois du temps sera bientôt disponible.");
    }

    @FXML
    private void gererCours() {
        afficherAlerte(Alert.AlertType.INFORMATION,"Fonctionnalité en cours", "La gestion des cours sera bientôt disponible.");
    }

    @FXML
    private void gererGestionConflits() {
        afficherAlerte(Alert.AlertType.INFORMATION,"Fonctionnalité en cours", "La gestion des conflits sera bientôt disponible.");
    }

    @FXML
    private void gererStatistiques() {
        afficherAlerte(Alert.AlertType.INFORMATION,"Fonctionnalité en cours", "La vue des statistiques sera bientôt disponible.");
    }

    @FXML
    public void initialize() {
        Utilisateur currentUser = EmploiDuTempsAPP.getCurrentUser();

        if (currentUser instanceof Administrateur) {
            this.adminConnecte = (Administrateur) currentUser;
            welcomeLabel.setText("Bienvenue, " + adminConnecte.getPrenom() + " " + adminConnecte.getNom() + " (Admin)!");
        } else {
            welcomeLabel.setText("Erreur : Accès non autorisé ou utilisateur non identifié.");
            System.err.println("DashboardAdminController: Utilisateur non admin ou non connecté.");
            // Optionnel: rediriger vers la page de connexion si aucun admin n'est connecté
            // try {
            // EmploiDuTempsAPP.loadScene("LoginView.fxml", "Connexion", 600, 400);
            // } catch (IOException e) { e.printStackTrace(); }
        }
    }

    private void afficherAlerte(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
