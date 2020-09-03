package services;

import dtos.SondageDto;
import dtos.UtilDto;

import java.util.List;

public interface Sondages {
    String ajoutUtil(String nom, String prenom, String pseudo);
    String ajoutFilm(String titre, String real, List<String> acteurs, String resume, String annee);
    String créerSondage(int idUtil,String nomSondage);
    SondageDto ajoutFilmASondage(int idUtil, String nomSondage, String idFilm);
}
