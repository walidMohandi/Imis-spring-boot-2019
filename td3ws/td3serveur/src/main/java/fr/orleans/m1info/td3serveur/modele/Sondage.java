package fr.orleans.m1info.td3serveur.modele;

import java.util.ArrayList;
import java.util.List;

public class Sondage {
    public enum Etat{preparation,ouvert,ferme};

    private String nomSondage;
    private Utilisateur proprietaire;
    private List<Film> films;
    private Etat statut;

    public Sondage(String nomSondage,Utilisateur proprietaire) {
        this.nomSondage = nomSondage;
        this.proprietaire = proprietaire;
        films=new ArrayList<>();
        statut=Etat.preparation;
    }

    public String getNomSondage() {
        return nomSondage;
    }

    public Utilisateur getProprietaire() {
        return proprietaire;
    }

    public List<Film> getFilms() {
        return films;
    }

    public Etat getStatut() {
        return statut;
    }
}
