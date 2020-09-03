package fr.orleans.m1info.td3serveur.modele;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String pseudo;

    public Utilisateur(int id, String nom, String prenom, String pseudo) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }
}
