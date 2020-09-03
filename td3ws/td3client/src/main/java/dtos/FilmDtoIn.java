package dtos;


/**
 * Pour récupérer les infos principales d'un film
 */

public class FilmDtoIn {
    private int id;
    private String titre;
    private String realisateur;
    private String sortie;

    public FilmDtoIn(){}

    public FilmDtoIn(int id, String titre, String realisateur, String sortie) {
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

    @Override
    public String toString() {
        return "FilmDtoIn{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", realisateur='" + realisateur + '\'' +
                ", sortie='" + sortie + '\'' +
                '}';
    }
}
