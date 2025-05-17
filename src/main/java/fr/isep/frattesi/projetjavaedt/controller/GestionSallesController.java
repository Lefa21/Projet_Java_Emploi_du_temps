package fr.isep.frattesi.projetjavaedt.controller;

import fr.isep.frattesi.projetjavaedt.EmploiDuTempsAPP;
import fr.isep.frattesi.projetjavaedt.model.Administrateur;
import fr.isep.frattesi.projetjavaedt.model.Salle;
import fr.isep.frattesi.projetjavaedt.model.Utilisateur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GestionSallesController {

    @FXML
    private TableView<Salle> sallesTableView;
    @FXML
    private TableColumn<Salle, String> numeroSalleColumn;
    @FXML
    private TableColumn<Salle, String> batimentColumn;
    @FXML
    private TableColumn<Salle, String> etageColumn;
    @FXML
    private TableColumn<Salle, Integer> capaciteColumn;
    @FXML
    private TableColumn<Salle, Boolean> disponibleColumn;

    private Administrateur adminConnecte;
    private ObservableList<Salle> sallesObservableList = FXCollections.observableArrayList();

    // Utilisation d'une liste statique pour simuler la persistance des données entre les vues pour la démo
    private static List<Salle> sallesEnMemoire = new ArrayList<>();

    // Méthode pour charger des données initiales si la liste est vide (pour la démo)
    private static void chargerSallesInitialesSiVide() {
        if (sallesEnMemoire.isEmpty()) {
            sallesEnMemoire.add(new Salle("A101", "Bâtiment A", "1er", 50));
            sallesEnMemoire.add(new Salle("B203", "Bâtiment B", "2ème", 30));
            sallesEnMemoire.add(new Salle("C005", "Bâtiment C", "RDC", 100));
        }
    }

    @FXML
    public void initialize() {
        Utilisateur currentUser = EmploiDuTempsAPP.getCurrentUser();
        if (currentUser instanceof Administrateur) {
            this.adminConnecte = (Administrateur) currentUser;
        } else {
            System.err.println("Erreur critique: GestionSallesController chargé sans administrateur connecté.");
            afficherAlerte(Alert.AlertType.ERROR, "Erreur d'accès", "Accès non autorisé. Veuillez vous reconnecter.");
            try {
                EmploiDuTempsAPP.loadScene("LoginView.fxml", "Connexion", 600, 400);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        numeroSalleColumn.setCellValueFactory(new PropertyValueFactory<>("numeroSalle"));
        batimentColumn.setCellValueFactory(new PropertyValueFactory<>("batiment"));
        etageColumn.setCellValueFactory(new PropertyValueFactory<>("etage"));
        capaciteColumn.setCellValueFactory(new PropertyValueFactory<>("capaciteMax"));
        disponibleColumn.setCellValueFactory(new PropertyValueFactory<>("estDisponible"));

        sallesTableView.setItems(sallesObservableList);
        chargerSallesInitialesSiVide(); // Charger les données de démo
        mettreAjourTableauSalles();
    }

    private void mettreAjourTableauSalles() {
        sallesObservableList.clear();
        // Dans une vraie application, vous appelleriez une méthode de adminConnecte
        // pour obtenir la liste des salles. Par exemple:
        // if (adminConnecte != null) {
        //     List<Salle> toutesLesSalles = adminConnecte.listerToutesLesSalles(); // Méthode à créer
        //     sallesObservableList.addAll(toutesLesSalles);
        // }
        // Pour la démo, on utilise la liste en mémoire :
        sallesObservableList.addAll(sallesEnMemoire);
    }

    @FXML
    private void ajoutSalle() {
        if (adminConnecte == null) {
            afficherAlerte(Alert.AlertType.ERROR, "Erreur", "Administrateur non connecté.");
            return;
        }

        TextInputDialog dialogNum = new TextInputDialog("S102");
        dialogNum.setTitle("Nouvelle Salle");
        dialogNum.setHeaderText("Ajouter une nouvelle salle");
        dialogNum.setContentText("Numéro de la salle:");
        Optional<String> resultNum = dialogNum.showAndWait();

        resultNum.ifPresent(num -> {
            // Pour une vraie application, vous auriez plus de champs ou une fenêtre de dialogue dédiée
            Map<String, String> details = new HashMap<>();
            details.put("numeroSalle", num);
            details.put("batiment", "Bât. Test");
            details.put("etage", "2");
            details.put("capaciteMax", "40");
            details.put("estDisponible", "true");

            // Appel à la méthode de l'administrateur pour gérer la création
            // boolean succes = adminConnecte.gererSalle(null, "CREER", details);
            // if (succes) {
            //    // Si gererSalle ne met pas à jour la source de données elle-même,
            //    // vous devrez ajouter la nouvelle salle à votre source de données ici.
            //    // Pour la simulation avec sallesEnMemoire :
            //    sallesEnMemoire.add(new Salle(num, details.get("batiment"), details.get("etage"), Integer.parseInt(details.get("capaciteMax"))));
            //    rafraichirTableView();
            //    showAlert(Alert.AlertType.INFORMATION, "Succès", "Salle " + num + " ajoutée.");
            // } else {
            //    showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible d'ajouter la salle.");
            // }

            // Simulation directe pour la démo
            Salle nouvelleSalle = new Salle(num, details.get("batiment"), details.get("etage"), Integer.parseInt(details.get("capaciteMax")));
            sallesEnMemoire.add(nouvelleSalle);
            mettreAjourTableauSalles();
            afficherAlerte(Alert.AlertType.INFORMATION, "Succès (Simulation)", "Salle " + num + " ajoutée.");
        });
    }

    @FXML
    private void modifierSalle() {
        if (adminConnecte == null) return;
        Salle salleSelectionnee = sallesTableView.getSelectionModel().getSelectedItem();
        if (salleSelectionnee != null) {
            // Logique pour ouvrir une dialogue et modifier, puis appeler gererSalle
            // Simulation:
            salleSelectionnee.setBatiment(salleSelectionnee.getBatiment() + " (Modifié)");
            // Map<String, String> details = new HashMap<>();
            // details.put("batiment", salleSelectionnee.getBatiment());
            // adminConnecte.gererSalle(salleSelectionnee, "MODIFIER", details);
            mettreAjourTableauSalles(); // Important pour voir le changement dans la TableView
            afficherAlerte(Alert.AlertType.INFORMATION, "Info (Simulation)", "Salle modifiée.");
        } else {
            afficherAlerte(Alert.AlertType.WARNING, "Aucune sélection", "Veuillez sélectionner une salle à modifier.");
        }
    }

    @FXML
    private void supprimerSalle() {
        if (adminConnecte == null) return;
        Salle salleSelectionnee = sallesTableView.getSelectionModel().getSelectedItem();
        if (salleSelectionnee != null) {
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION,
                    "Êtes-vous sûr de vouloir supprimer la salle " + salleSelectionnee.getNumeroSalle() + "?",
                    ButtonType.YES, ButtonType.NO);
            confirmationDialog.setTitle("Confirmation de suppression");
            confirmationDialog.setHeaderText(null); // Pas d'en-tête

            Optional<ButtonType> result = confirmationDialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                // boolean succes = adminConnecte.gererSalle(salleSelectionnee, "SUPPRIMER", null);
                // if (succes) {
                //    rafraichirTableView(); // Si la source de données a été mise à jour par gererSalle
                //    showAlert(Alert.AlertType.INFORMATION, "Succès", "Salle supprimée.");
                // } else {
                //    showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de supprimer la salle.");
                // }
                sallesEnMemoire.remove(salleSelectionnee); // Simulation
                mettreAjourTableauSalles();
                afficherAlerte(Alert.AlertType.INFORMATION, "Succès (Simulation)", "Salle supprimée.");
            }
        } else {
            afficherAlerte(Alert.AlertType.WARNING, "Aucune sélection", "Veuillez sélectionner une salle à supprimer.");
        }
    }

    @FXML
    private void retourTableauDashboard() {
        try {
            EmploiDuTempsAPP.loadScene("DashboardAdminView.fxml", "Tableau de Bord Admin", 900, 700);
        } catch (IOException e) {
            e.printStackTrace();
            afficherAlerte(Alert.AlertType.ERROR, "Erreur de Navigation", "Impossible de retourner au tableau de bord.");
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
