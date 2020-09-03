package fe.orleans.m1info.tp3serv.Dto;

import fe.orleans.m1info.tp3serv.Models.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmDto {

    private int idFilm;
    private String titre;
    private String realisateur;
    private String resume;

    public static List<FilmDto> creerList(List<Film> films){
        List<FilmDto> l=new ArrayList<>();
        for(Film f: films){
            l.add(new FilmDto(f.getIdFilm(),f.getTitre(),f.getRealisateur(),f.getResume()));
        }
        return l;
    }

    public FilmDto(int idFilm, String titre, String realisateur, String resume) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.realisateur = realisateur;
        this.resume = resume;
    }

    public int getIdFilm() {
        return idFilm;
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
}
