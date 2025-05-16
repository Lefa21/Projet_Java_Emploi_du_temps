package fr.isep.frattesi.projetjavaedt.controller;

import fr.isep.frattesi.projetjavaedt.EmploiDuTempsAPP;
import fr.isep.frattesi.projetjavaedt.model.Utilisateur;
import fr.isep.frattesi.projetjavaedt.model.enums.TypeRole; // Utilisation de votre enum
import fr.isep.frattesi.projetjavaedt.service.AuthentificationService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField identifiantField;

    @FXML
    private PasswordField motDePasseField;

    @FXML
    private Button connexionButton; // Peut être retiré si non utilisé directement

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
            messageLabel.setText("Connexion réussie: " + utilisateurConnecte.getPrenom() + " " + utilisateurConnecte.getNom());
            messageLabel.setStyle("-fx-text-fill: green;");

            try {
                if (utilisateurConnecte.getTypeRole() == TypeRole.ADMINISTRATEUR) {
                    EmploiDuTempsAPP.loadScene("DashboardAdminView.fxml", "Tableau de Bord Admin", 800, 600);
                } else if (utilisateurConnecte.getTypeRole() == TypeRole.ETUDIANT) {
                    // EmploiDuTempsAPP.loadScene("DashboardEtudiantView.fxml", "Tableau de Bord Étudiant", 800, 600);
                    messageLabel.setText("Vue Étudiant à implémenter. Connecté en tant que: " + utilisateurConnecte.getPrenom());
                } else if (utilisateurConnecte.getTypeRole() == TypeRole.ENSEIGNANT) {
                    // EmploiDuTempsAPP.loadScene("DashboardEnseignantView.fxml", "Tableau de Bord Enseignant", 800, 600);
                    messageLabel.setText("Vue Enseignant à implémenter. Connecté en tant que: " + utilisateurConnecte.getPrenom());
                }
            } catch (IOException e) {
                e.printStackTrace();
                messageLabel.setText("Erreur lors du chargement de la vue suivante.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }

        } else {
            messageLabel.setText("Identifiant ou mot de passe incorrect.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void initialize() {
        messageLabel.setText("");
    }
}
