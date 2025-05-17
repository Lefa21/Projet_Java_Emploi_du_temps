package fr.isep.frattesi.projetjavaedt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:emploidutemps.db"; // Nom du fichier de la base de données

    /**
     * Établit et retourne une connexion à la base de données SQLite.
     * @return Une connexion à la base de données.
     * @throws SQLException Si une erreur d'accès à la base de données se produit.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    /**
     * Crée la table des utilisateurs si elle n'existe pas déjà.
     * Doit être appelée une fois au démarrage de l'application.
     */
    public static void creerTableUtilisateursSiNonExistante() {
        String sql = "CREATE TABLE IF NOT EXISTS UTILISATEURS (\n"
                + "    id_utilisateur TEXT PRIMARY KEY,\n"
                + "    nom TEXT NOT NULL,\n"
                + "    prenom TEXT NOT NULL,\n"
                + "    email TEXT UNIQUE NOT NULL,\n"
                + "    mot_de_passe_hash TEXT NOT NULL,\n"
                + "    type_role TEXT NOT NULL CHECK(type_role IN ('ETUDIANT', 'ENSEIGNANT', 'ADMINISTRATEUR')),\n"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table UTILISATEURS vérifiée/créée avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la création de la table UTILISATEURS : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
    