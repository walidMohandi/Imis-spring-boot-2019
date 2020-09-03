package fr.orleans.m1info.td3serveur.services;

import fr.orleans.m1info.td3serveur.modele.Film;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class FilmsService {
    private static AtomicInteger compteur=new AtomicInteger(1);
    private List<Film> lesFilms=new ArrayList<>();
    private static FilmsService fs=null;

    public static synchronized  FilmsService getService() {
        if (fs==null) {
            fs=new FilmsService();
        }
        return fs;
    }

    private FilmsService(){

    }

    public Film creerFilm(String titre, String realisateur, String resume, Collection<String> acteurs, String sortie) {
        Film f=new Film(compteur.getAndIncrement(),titre,realisateur,resume,acteurs,sortie);
        lesFilms.add(f);
        return (f);
    }

    public Film getFilm(int idf) {
        Optional<Film> film=lesFilms.stream().filter(m->m.getId()==idf).findAny();
        if (film.isPresent()) {
            return film.get();
        } else {
            return null;
        }
    }

    public List<Film> getAllFilms() {
        return lesFilms;
    }
}
