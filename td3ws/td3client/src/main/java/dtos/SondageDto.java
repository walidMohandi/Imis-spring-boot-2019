package dtos;


import java.util.ArrayList;
import java.util.List;

public class SondageDto {
    private String nomSondage;
    private List<FilmDtoIn> films;
    private String vainqueur;


    public SondageDto() {
        this.films=new ArrayList<>();
    }

    public SondageDto(String nomSondage, List<FilmDtoIn> films, String vainqueur) {
        this.nomSondage = nomSondage;
        this.films = films;
        this.vainqueur = vainqueur;
    }

    public String getNomSondage() {
        return nomSondage;
    }

    public List<FilmDtoIn> getFilms() {
        return films;
    }

    public String getVainqueur() {
        return vainqueur;
    }

    @Override
    public String toString() {
        return "SondageDto{" +
                "nomSondage='" + nomSondage + '\'' +
                ", films=" + films +
                ", vainqueur='" + vainqueur + '\'' +
                '}';
    }
}
