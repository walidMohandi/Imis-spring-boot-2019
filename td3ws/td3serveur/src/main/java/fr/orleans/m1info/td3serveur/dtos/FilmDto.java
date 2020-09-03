package fr.orleans.m1info.td3serveur.dtos;

import fr.orleans.m1info.td3serveur.modele.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmDto {
    private int id;
    private String titre;
    private String realisateur;
    private String sortie;

    public static List<FilmDto> creerListe(List<Film> films){
        List<FilmDto> filmsdto=new ArrayList<>();
        for (Film f:films) {
            filmsdto.add(new FilmDto(f.getId(),f.getTitre(),f.getRealisateur(),f.getSortie()));
        }
        return filmsdto;
    }

    public FilmDto(int id, String titre, String realisateur, String sortie) {
        this.id = id;
        this.titre = titre;
        this.realisateur = realisateur;
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

    public String getSortie() {
        return sortie;
    }
}
