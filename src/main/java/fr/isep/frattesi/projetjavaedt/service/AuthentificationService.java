package fr.isep.frattesi.projetjavaedt.service;

import fr.isep.frattesi.projetjavaedt.model.Administrateur;
import fr.isep.frattesi.projetjavaedt.model.Etudiant;
import fr.isep.frattesi.projetjavaedt.model.Enseignant;
import fr.isep.frattesi.projetjavaedt.model.Utilisateur;
// Pas besoin d'importer TypeRole ici si on ne l'utilise pas directement pour la création,
// car les constructeurs des sous-classes d'Utilisateur le gèrent.

import java.util.ArrayList;
import java.util.List;

public class AuthentificationService {

    private List<Utilisateur> utilisateursEnregistres;

    public AuthentificationService() {
        this.utilisateursEnregistres = new ArrayList<>();
        // Initialiser avec quelques utilisateurs de test.
        // Le mot de passe fourni au constructeur d'Utilisateur (et ses sous-classes)
        // sera maintenant haché par le constructeur d'Utilisateur lui-même.
        utilisateursEnregistres.add(new Administrateur("admin01", "Min", "Ada", "admin@isep.fr", "admin123"));
        utilisateursEnregistres.add(new Etudiant("etu01", "Dupont", "Jean", "jean.dupont@isep.fr", "etu123", "E001"));
        utilisateursEnregistres.add(new Enseignant("ens01", "Martin", "Sophie", "sophie.martin@isep.fr", "ens123", "M001"));
    }

    /**
     * Tente d'authentifier un utilisateur en utilisant la méthode seConnecter de l'objet Utilisateur.
     * @param identifiant L'identifiant fourni.
     * @param motDePasseEnClair Le mot de passe en clair fourni.
     * @return L'objet Utilisateur si l'authentification réussit, sinon null.
     */
    public Utilisateur authentifier(String identifiant, String motDePasseEnClair) {
        for (Utilisateur utilisateur : utilisateursEnregistres) {
            // Appel de la méthode seConnecter de l'instance Utilisateur
            if (utilisateur.seConnecter(identifiant, motDePasseEnClair)) {
                return utilisateur; // Authentification réussie
            }
        }
        return null; // Échec de l'authentification
    }
}
