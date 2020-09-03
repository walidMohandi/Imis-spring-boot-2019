package fe.orleans.m1info.tp3serv.Service;

import fe.orleans.m1info.tp3serv.Models.Film;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class FilmService {

    private static FilmService fs=null;
    private static AtomicInteger compteur=new AtomicInteger(1);
    private List<Film> films=new ArrayList<Film>();

    public static synchronized FilmService getService(){
        if(fs==null){
            fs=new FilmService();
        }
        return fs;
    }
    public FilmService(){

    }

    public Film creerFilm(String titre, String realisateur, String resume, List<String> acteurs, String sortie){
            Film film=new Film(compteur.incrementAndGet(),titre,realisateur,resume,acteurs,sortie);
            films.add(film);
            return film;

    }

    public Film getFilm(int id){
        Optional<Film> film=films.stream().filter(f->f.getIdFilm()==id).findAny();
        if(film.isPresent()){
            return film.get();
        }
        return null;
    }



    public List<Film> getAllFilms(){
        return films;
    }

    public Film modifierFilm( int id,Film film){
        Film f=getFilm(id);
        if(f!=null){
            f.setActeurs_principaux(film.getActeurs_principaux());
            f.setResume(film.getResume());
            return f;
        }
        return null;
    }



}
