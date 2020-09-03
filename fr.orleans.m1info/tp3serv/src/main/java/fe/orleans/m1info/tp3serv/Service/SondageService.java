package fe.orleans.m1info.tp3serv.Service;

import fe.orleans.m1info.tp3serv.Dto.SondageDto;
import fe.orleans.m1info.tp3serv.Models.Film;
import fe.orleans.m1info.tp3serv.Models.Sondage;
import fe.orleans.m1info.tp3serv.Models.Utilisateur;
import fe.orleans.m1info.tp3serv.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SondageService {


    private List<Sondage> sondages=new ArrayList<>();
    private FilmService fs=FilmService.getService();
    private UtilisateurService us=UtilisateurService.getService();

    public SondageService(){

    }

    public Sondage creerSondage(String nomSondage,int idUtil) throws UtilisateurExceptions, DuplicationSondageException
    {
        if(getSondage(nomSondage)!=null) {
                throw new DuplicationSondageException();
        }
        Utilisateur u=us.getUtilisateur(idUtil);
        if(u==null){
               throw  new UtilisateurExceptions();
        }
        Sondage s=new Sondage(nomSondage,u);
        sondages.add(s);
        return s;
    }


    public Sondage getSondage(String nomSondage){
        Optional<Sondage> sond=sondages.stream().filter(s->s.getNomSondage().equals(nomSondage)).findAny();
        if(sond.isPresent()){
            return sond.get();
        }
        return null;
    }

    public Sondage ajouterFilmSondage(int idFilm, int idUtil, String nomSondage)throws FilmInconnuException, NonProprietaireException, SondageInconnuException {
        Film f=fs.getFilm(idFilm);
        if(f==null){
            throw new FilmInconnuException();
        }
        Sondage s=getSondage(nomSondage);
        if(s==null){
            throw new SondageInconnuException();
        }
        Utilisateur u=us.getUtilisateur(idUtil);
        if(!s.getProprietaire().equals(u)){
            throw new NonProprietaireException();
        }
        s.getFilms().add(f);
        return s;
    }

    /**Ajoute un film au sondage en paramètres idfilm,idutil VARIANTE : idutil en header Code de retour :
     - 201 CREATED si creation ok
     - 401 UNAUTHORIZED si
     l’utilisateur n’est pas le bon
     - 409 CONFLICT si déjà dans
     le sondage*/




}
