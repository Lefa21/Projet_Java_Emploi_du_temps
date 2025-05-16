package fr.isep.frattesi.projetjavaedt; // Assurez-vous que c'est le nom exact de votre package racine

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL; // Important pour la gestion des ressources

public class EmploiDuTempsAPP extends Application {

    private static Stage primaryStage; // Garder une référence pour changer les scènes

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; // Affecter le stage reçu au stage principal de notre application
        primaryStage.setTitle("Gestion des Emplois du Temps - Connexion"); // Titre initial

        // Charger la vue de connexion par défaut
        // L'appel est corrigé ici pour ne pas dupliquer "view/"
        loadScene("LoginView.fxml", "Connexion", 600, 400); // Ajustez la taille si nécessaire

        primaryStage.setResizable(true); // Ou false si vous préférez
        primaryStage.show();
    }

    /**
     * Méthode utilitaire pour charger et afficher une nouvelle scène FXML.
     * @param fxmlFileNameOnly Le nom du fichier FXML (ex: "LoginView.fxml", "DashboardAdminView.fxml").
     * Ce fichier doit se trouver dans le sous-dossier 'view' de vos ressources,
     * sous la structure de package correspondante.
     * @param title Le titre de la fenêtre pour cette scène.
     * @param width La largeur préférée de la scène.
     * @param height La hauteur préférée de la scène.
     * @throws IOException Si le fichier FXML ne peut être chargé.
     */
    public static void loadScene(String fxmlFileNameOnly, String title, int width, int height) throws IOException {
        // Construit le chemin en ajoutant le sous-dossier "view/"
        String fxmlPath = "view/" + fxmlFileNameOnly;
        URL fxmlLocation = EmploiDuTempsAPP.class.getResource(fxmlPath);

        if (fxmlLocation == null) {
            System.err.println("ERREUR : Impossible de trouver le fichier FXML : " + fxmlPath);
            System.err.println("Vérifiez que le fichier '" + fxmlFileNameOnly + "' est bien dans le dossier :");
            // Construit le chemin attendu pour l'affichage de l'erreur
            String expectedPathBase = EmploiDuTempsAPP.class.getPackage().getName().replace('.', '/');
            System.err.println("src/main/resources/" + expectedPathBase + "/view/");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Parent root = fxmlLoader.load();

        // Code pour passer des données au contrôleur (si nécessaire)
        // Object controller = fxmlLoader.getController();
        // if (data != null && controller instanceof YourInitializableControllerInterface) {
        //     ((YourInitializableControllerInterface) controller).initializeData(data);
        // }


        primaryStage.setTitle(title);
        if (primaryStage.getScene() == null) { // Première scène
            Scene scene = new Scene(root, width, height);
            // Vous pouvez lier une feuille de style CSS ici si vous en avez une pour toutes les scènes
            // URL cssLocation = EmploiDuTempsAPP.class.getResource("css/style.css");
            // if (cssLocation != null) {
            //     scene.getStylesheets().add(cssLocation.toExternalForm());
            // } else {
            //     System.err.println("Attention: Fichier CSS principal non trouvé.");
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

    public static void main(String[] args) {
        launch(args);
    }
}

/*
// Optionnel: Interface à implémenter par vos contrôleurs si vous voulez leur passer des données
// lors du chargement de la scène de manière standardisée.
interface YourInitializableControllerInterface {
    void initializeData(Object data);
}
*/