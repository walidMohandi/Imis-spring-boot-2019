package fr.orleans.m1info.td3serveur.services;

import fr.orleans.m1info.td3serveur.exceptions.*;
import fr.orleans.m1info.td3serveur.modele.Film;
import fr.orleans.m1info.td3serveur.modele.Sondage;
import fr.orleans.m1info.td3serveur.modele.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SondagesService {
        private List<Sondage> lesSondages=new ArrayList<>();
        private UtilisateursService us=UtilisateursService.getService();
        private FilmsService fs=FilmsService.getService();

        public Sondage creerSondage(String nomSondage,int idUtil) throws UtilisateurInconnuException, DuplicationSondageException {
            if (getSondage(nomSondage)!=null) {
                throw  new DuplicationSondageException();
            }
            Utilisateur u=us.getUtilisateur(idUtil);
            if (u==null) {
                throw new UtilisateurInconnuException();
            }
            Sondage s = new Sondage(nomSondage,u);
            lesSondages.add(s);
            return s;
        }

        public Sondage ajouterFilm(String nomSondage, int idFilm, int idUtil)throws UtilisateurInconnuException, SondageInconnuException, FilmInconnuException, NonProprietaireException {
            Sondage s =getSondage(nomSondage);
            if (s==null) {
                throw  new SondageInconnuException();
            }
            Utilisateur u=us.getUtilisateur(idUtil);
            if (u==null) {
                throw new UtilisateurInconnuException();
            }
            if (s.getProprietaire().equals(u)==false) {
                throw new NonProprietaireException();
            }
            Film f=fs.getFilm(idFilm);
            if (f==null) {
                throw new FilmInconnuException();
            }
            s.getFilms().add(f);
            return s;
        }

    public Sondage getSondage(String ids) {
        Optional<Sondage> sondage=lesSondages.stream().filter(m->m.getNomSondage().equals(ids)).findAny();
        if (sondage.isPresent()) {
            return sondage.get();
        } else {
            return null;
        }
    }

}
