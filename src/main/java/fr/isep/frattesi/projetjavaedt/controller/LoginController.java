package fr.isep.frattesi.projetjavaedt.controller;

import fr.isep.frattesi.projetjavaedt.EmploiDuTempsAPP;
import fr.isep.frattesi.projetjavaedt.model.Utilisateur;
import fr.isep.frattesi.projetjavaedt.model.enums.TypeRole; // Assurez-vous que cette enum existe et est correctement nommée
import fr.isep.frattesi.projetjavaedt.service.AuthentificationService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert; // Pour les messages d'alerte
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField identifiantField;
    @FXML
    private PasswordField motDePasseField;
    @FXML
    private Label messageLabel;

    private AuthentificationService authentificationService;

    public LoginController() {
        this.authentificationService = new AuthentificationService();
    }

    @FXML
    protected void seConnecter() {
        String identifiant = identifiantField.getText();
        String motDePasse = motDePasseField.getText(); // Mot de passe en clair saisi par l'utilisateur

        if (identifiant.isEmpty() || motDePasse.isEmpty()) {
            messageLabel.setText("Veuillez entrer un identifiant et un mot de passe.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Utilisateur utilisateurConnecte = authentificationService.authentifier(identifiant, motDePasse);

        if (utilisateurConnecte != null) {
            EmploiDuTempsAPP.setCurrentUser(utilisateurConnecte); // Définir l'utilisateur courant

            messageLabel.setText("Connexion réussie: " + utilisateurConnecte.getPrenom());
            messageLabel.setStyle("-fx-text-fill: green;");

            try {
                if (utilisateurConnecte.getTypeRole() == TypeRole.ADMINISTRATEUR) {
                    EmploiDuTempsAPP.loadScene("DashboardAdminView.fxml", "Tableau de Bord Admin", 900, 700);
                } else if (utilisateurConnecte.getTypeRole() == TypeRole.ETUDIANT) {
                    // EmploiDuTempsAPP.loadScene("DashboardEtudiantView.fxml", "Tableau de Bord Étudiant", 800, 600);
                    afficherAlerte(Alert.AlertType.INFORMATION, "Accès Étudiant", "Vue Étudiant à implémenter. Connecté en tant que: " + utilisateurConnecte.getPrenom());
                } else if (utilisateurConnecte.getTypeRole() == TypeRole.ENSEIGNANT) {
                    // EmploiDuTempsAPP.loadScene("DashboardEnseignantView.fxml", "Tableau de Bord Enseignant", 800, 600);
                    afficherAlerte(Alert.AlertType.INFORMATION, "Accès Enseignant", "Vue Enseignant à implémenter. Connecté en tant que: " + utilisateurConnecte.getPrenom());
                }
            } catch (IOException e) {
                e.printStackTrace();
                messageLabel.setText("Erreur lors du chargement de la vue suivante: " + e.getMessage());
                messageLabel.setStyle("-fx-text-fill: red;");
                afficherAlerte(Alert.AlertType.ERROR, "Erreur de chargement", "Impossible de charger la vue suivante.");
            }
        } else {
            EmploiDuTempsAPP.setCurrentUser(null);
            messageLabel.setText("Identifiant ou mot de passe incorrect.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void initialize() {
        messageLabel.setText("");
    }

    private void afficherAlerte(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
