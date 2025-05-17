package fr.isep.frattesi.projetjavaedt; // Assurez-vous que c'est le nom exact de votre package racine

import fr.isep.frattesi.projetjavaedt.dao.DatabaseManager;
import fr.isep.frattesi.projetjavaedt.model.Utilisateur; // Importer la classe Utilisateur
import fr.isep.frattesi.projetjavaedt.service.AuthentificationService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class EmploiDuTempsAPP extends Application {

    private static Stage primaryStage;
    private static Utilisateur currentUser; // Pour stocker l'utilisateur connecté globalement

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        DatabaseManager.creerTableUtilisateursSiNonExistante(); // Création de la table

        // Initialiser les données de test (à faire avec précaution, peut-être une seule fois)
        AuthentificationService tempAuthService = new AuthentificationService();
        tempAuthService.initialiserDonneesDeTestSiNecessaire();

        primaryStage.setTitle("Gestion des Emplois du Temps - Connexion");
        loadScene("LoginView.fxml", "Connexion", 600, 400);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    /**
     * Charge une nouvelle scène FXML.
     * @param fxmlFileNameOnly Nom du fichier FXML (ex: "LoginView.fxml")
     * @param title Titre de la fenêtre
     * @param width Largeur de la scène
     * @param height Hauteur de la scène
     * @throws IOException Si le FXML ne peut être chargé
     */
    public static void loadScene(String fxmlFileNameOnly, String title, int width, int height) throws IOException {
        // Construit le chemin en ajoutant le sous-dossier "view/"
        // Ce chemin est relatif à la localisation de EmploiDuTempsAPP.class dans le classpath
        // Si EmploiDuTempsAPP est dans fr.isep.frattesi.projetjavaedt
        // et vos FXML sont dans resources/fr/isep/frattesi/projetjavaedt/view/
        // alors le chemin relatif depuis la classe est "view/" + fxmlFileNameOnly
        String fxmlPath = "view/" + fxmlFileNameOnly;
        URL fxmlLocation = EmploiDuTempsAPP.class.getResource(fxmlPath);

        if (fxmlLocation == null) {
            System.err.println("ERREUR : Impossible de trouver le fichier FXML : " + fxmlPath);
            String expectedPathBase = "";
            if (EmploiDuTempsAPP.class.getPackage() != null) {
                expectedPathBase = EmploiDuTempsAPP.class.getPackage().getName().replace('.', '/');
            }
            System.err.println("Vérifiez que le fichier '" + fxmlFileNameOnly + "' est bien dans le dossier :");
            System.err.println("src/main/resources/" + expectedPathBase + "/view/");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Parent root = fxmlLoader.load();

        primaryStage.setTitle(title);
        if (primaryStage.getScene() == null) { // Première scène
            Scene scene = new Scene(root, width, height);
            // Charger un CSS global si vous en avez un
            // URL cssLocation = EmploiDuTempsAPP.class.getResource("css/style.css"); // Adaptez le chemin vers votre CSS
            // if (cssLocation != null) {
            //    scene.getStylesheets().add(cssLocation.toExternalForm());
            // } else {
            //     System.err.println("Attention: Fichier CSS principal non trouvé à css/style.css");
            // }
            primaryStage.setScene(scene);
        } else { // Remplacer la racine de la scène existante
            primaryStage.getScene().setRoot(root);
        }
        // Ajuster la taille de la fenêtre à la nouvelle scène
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.centerOnScreen(); // Centre la fenêtre
    }

    public static void setCurrentUser(Utilisateur user) {
        currentUser = user;
    }

    public static Utilisateur getCurrentUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
