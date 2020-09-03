package dtos;


import java.util.List;

/**
 * Pour la création d'un film
 */
public class FilmDtoOut {
    private String titre;
    private String realisateur;
    private List<String> acteurs;
    private String resume;
    private String sortie;

    public FilmDtoOut(String titre, String realisateur, List<String> acteurs, String resume, String sortie) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.acteurs = acteurs;
        this.resume = resume;
        this.sortie = sortie;
    }

    public String getTitre() {
        return titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public List<String> getActeurs() {
        return acteurs;
    }

    public String getResume() {
        return resume;
    }

    public String getSortie() {
        return sortie;
    }
}
