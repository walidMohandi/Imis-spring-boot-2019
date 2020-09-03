package fr.orleans.m1info.td3serveur.modele;

import java.util.Collection;

public class Film {
    private int id;
    private String titre;
    private String realisateur;
    private String resume;
    private Collection<String>acteurs;
    private String sortie;

    public Film(int id, String titre, String realisateur, String resume, Collection<String> acteurs, String sortie) {
        this.id = id;
        this.titre = titre;
        this.realisateur = realisateur;
        this.resume = resume;
        this.acteurs = acteurs;
        this.sortie = sortie;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public String getResume() {
        return resume;
    }

    public Collection<String> getActeurs() {
        return acteurs;
    }

    public String getSortie() {
        return sortie;
    }

    // on ne peut mettre à jour qu'acteurs et resume

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setActeurs(Collection<String> acteurs) {
        this.acteurs = acteurs;
    }
}
