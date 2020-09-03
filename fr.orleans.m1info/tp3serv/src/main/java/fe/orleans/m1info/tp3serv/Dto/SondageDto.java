package fe.orleans.m1info.tp3serv.Dto;

import fe.orleans.m1info.tp3serv.Models.Sondage;

import java.util.List;

public class SondageDto {

    private String nomSondage;
    private List<FilmDto> films;
    private String vainqueur;

    public static SondageDto creerSondageDto(Sondage s) {
        String v=null;
        if (s.getStatut()==Sondage.Etat.ferme) {
            // TODO
            v="FERME";
        }
        List<FilmDto> films=FilmDto.creerList(s.getFilms());
        SondageDto sdto=new SondageDto(s.getNomSondage(),films,v);
        return sdto;
    }

    public SondageDto(String nomSondage, List<FilmDto> films, String vainqueur) {
        this.nomSondage = nomSondage;
        this.films = films;
        this.vainqueur = vainqueur;
    }

    public String getNomSondage() {
        return nomSondage;
    }

    public List<FilmDto> getFilms() {
        return films;
    }

    public String getVainqueur() {
        return vainqueur;
    }
}
