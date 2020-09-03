package fe.orleans.m1info.tp3serv.Models;

import java.util.Date;
import java.util.List;

public class Film {

    private int idFilm;
    private String titre;
    private String realisateur;
    private String resume;
    private List<String> acteurs_principaux;
    private String dateDeSortie;

    public Film() {

    }

    public Film(int idFilm, String titre, String realisateur, String resume, List<String> acteurs_principaux, String dateDeSortie) {
        this.idFilm = idFilm;
        this.titre = titre;
        this.realisateur = realisateur;
        this.resume = resume;
        this.acteurs_principaux = acteurs_principaux;
        this.dateDeSortie = dateDeSortie;
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

    public List<String> getActeurs_principaux() {
        return acteurs_principaux;
    }

    public String getDateDeSortie() {
        return dateDeSortie;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setActeurs_principaux(List<String> acteurs_principaux) {
        this.acteurs_principaux = acteurs_principaux;
    }
}
