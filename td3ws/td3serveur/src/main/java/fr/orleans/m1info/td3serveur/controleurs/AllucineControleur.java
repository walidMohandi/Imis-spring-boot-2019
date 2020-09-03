package fr.orleans.m1info.td3serveur.controleurs;

import fr.orleans.m1info.td3serveur.dtos.FilmDto;
import fr.orleans.m1info.td3serveur.dtos.SondageDto;
import fr.orleans.m1info.td3serveur.exceptions.*;
import fr.orleans.m1info.td3serveur.modele.Film;
import fr.orleans.m1info.td3serveur.modele.Sondage;
import fr.orleans.m1info.td3serveur.modele.Utilisateur;
import fr.orleans.m1info.td3serveur.services.FilmsService;
import fr.orleans.m1info.td3serveur.services.SondagesService;
import fr.orleans.m1info.td3serveur.services.UtilisateursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/allucine")
public class AllucineControleur {
    FilmsService filmsService=FilmsService.getService();
    UtilisateursService utilisateursService=UtilisateursService.getService();
    SondagesService sondagesService=new SondagesService();

    /**
     * Création d'un utilisateur
     * @param util un utilisateur passe en body. Sans l'id (géré côté serveur)
     * @return l'utilisateur
     */
    @PostMapping("/util")
    public ResponseEntity<Utilisateur> creerUtil(@RequestBody Utilisateur util) {
        Utilisateur u=utilisateursService.creerUtilisateur(util);
        if (u!=null) {
        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(u.getId()).toUri();
            return ResponseEntity.created(location).body(u);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Liste des films
     * @return une liste avec une sous partie des informations sur les films (pas le résumé ni les acteurs)
     */
    @GetMapping("/film")
    public ResponseEntity<List<FilmDto>> tousLesFilms() {
        List<FilmDto> lesFilms = FilmDto.creerListe(filmsService.getAllFilms());
        return ResponseEntity.ok().body(lesFilms);
    }


    /**
     * Création d'un film
     * @param film le film passe en body. Sans l'id (géré côté serveur)
     * @return le film
     */
    @PostMapping("/film")
    public ResponseEntity<Film> creerFilm(@RequestBody Film film) {
        Film f=filmsService.creerFilm(film.getTitre(),film.getRealisateur(),film.getResume(), film.getActeurs(), film.getSortie());

        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(f.getId()).toUri();

        return ResponseEntity.created(location).body(f);
    }


    /**
     * creation d'un sondage
     * @param titre le titre du sondage, passe en body
     * @param idUtil l'id du createur, passé en header
     * @return un dto contenant une parie des informations du sondage (liste des films sans certains détails, absence du nom du créateur du sondage)
     */
    @PostMapping("/sondage")
    public ResponseEntity<SondageDto> creerSondage(@RequestBody String titre, @RequestHeader int idUtil) {
        System.out.println("sondage "+titre+" idUtil "+idUtil);

        try {
            Sondage s = sondagesService.creerSondage(titre, idUtil);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(s.getNomSondage()).toUri();

            return ResponseEntity.created(location).body(SondageDto.creerSondageDto(s));
        } catch (DuplicationSondageException dse) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (UtilisateurInconnuException uue) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * ajout d'un film au sondage
     * @param idFilm id du film en body. Passé sous forme d'une chaîne car l'envoi d'un entier dans le body peut poser problème à l'interprétation
     * @param ids id du sondage dans le path
     * @param idUtil id du créateur du sondage en header
     * @return le sondage mis à jour (même format que pour le post d'un sondage)
     */
    @PostMapping("/sondage/{ids}/film")
    public ResponseEntity<SondageDto> ajouterFilmASondage(@RequestBody String idFilm, @PathVariable String ids, @RequestHeader int idUtil) {

        try {
            Sondage s = sondagesService.ajouterFilm(ids,Integer.parseInt(idFilm), idUtil);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(s.getNomSondage()).toUri();

            return ResponseEntity.created(location).body(SondageDto.creerSondageDto(s));
        } catch (NonProprietaireException npe) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (UtilisateurInconnuException uue) {
            return ResponseEntity.notFound().build();
        } catch (SondageInconnuException uue) {
            return ResponseEntity.notFound().build();
        } catch (FilmInconnuException uue) {
            return ResponseEntity.notFound().build();
        }
    }


}
