package fe.orleans.m1info.tp3serv.controleur;


import fe.orleans.m1info.tp3serv.Dto.FilmDto;
import fe.orleans.m1info.tp3serv.Dto.SondageDto;
import fe.orleans.m1info.tp3serv.Models.Film;
import fe.orleans.m1info.tp3serv.Models.Sondage;
import fe.orleans.m1info.tp3serv.Models.Utilisateur;
import fe.orleans.m1info.tp3serv.Service.FilmService;
import fe.orleans.m1info.tp3serv.Service.SondageService;
import fe.orleans.m1info.tp3serv.Service.UtilisateurService;
import fe.orleans.m1info.tp3serv.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("hamat")

public class sondageControleur {
    //service.Facade facade=new Facade();
    UtilisateurService us=UtilisateurService.getService();
    FilmService fs=FilmService.getService();
    SondageService ss=new SondageService();


    @PostMapping( value= "/util")
    public ResponseEntity<Utilisateur> creerUtil(@RequestBody Utilisateur u){
        Utilisateur util=us.creerUtilisateur(u);
        if(util!=null){
            URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(util.getId()).toUri();
            return ResponseEntity.created(location).body(util);
        }
        else{
            return  ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }



    @GetMapping(value = "/film",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FilmDto>> getAllFilm(){
        List<FilmDto> l= FilmDto.creerList(fs.getAllFilms());
        return ResponseEntity.ok().body(l);
    }



    @PostMapping(value="film")
    public ResponseEntity<Film> creerFilm(@RequestBody Film f){
        Film film=fs.creerFilm(f.getTitre(),f.getRealisateur(),f.getResume(),f.getActeurs_principaux(),f.getDateDeSortie());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(film.getIdFilm()).toUri();
        return ResponseEntity.created(location).body(film);
    }

    @PutMapping(value = "/film/{idF}")
    public ResponseEntity<Film> setFilm(@PathVariable int idF,@RequestBody Film film){
        Film f=fs.modifierFilm(idF,film);
        if(f!=null){
            return ResponseEntity.ok().body(f);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * creation d'un sondage
     * @param titre le titre du sondage, passe en body
     * @param idUtil l'id du createur, passé en header
     * @return un dto contenant une parie des informations du sondage (liste des films sans certains détails, absence du nom du créateur du sondage)
     */
    @PostMapping(value = "/sondage")
    public ResponseEntity<SondageDto> creerSondage(@RequestBody String titre,@RequestHeader int idUtil)  {
        System.out.println("Sondage"+titre+" idUtil "+idUtil);
        try{
            Sondage s=ss.creerSondage(titre,idUtil);
            URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(s.getNomSondage()).toUri();
            return ResponseEntity.created(location).body(SondageDto.creerSondageDto(s));
        }
        catch (DuplicationSondageException dse){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch(UtilisateurExceptions ue){
            return ResponseEntity.notFound().build();
        }

    }
    /**Ajoute un film au sondage en paramètres idfilm,idutil VARIANTE : idutil en header Code de retour :
- 201 CREATED si creation ok
- 401 UNAUTHORIZED si
l’utilisateur n’est pas le bon
- 409 CONFLICT si déjà dans
le sondage*/
    @PutMapping(value = "/sondage/{nomSondage}/film")
    public ResponseEntity<SondageDto> ajouterFilmSondage(@PathVariable String nomSondage,@RequestHeader int idFilm,@RequestHeader int idUtil){
        try{
            Sondage s=ss.ajouterFilmSondage(idFilm,idUtil,nomSondage);
            URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(nomSondage).toUri();
            return ResponseEntity.created(location).body(SondageDto.creerSondageDto(s));
        }
        catch (NonProprietaireException npe){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (SondageInconnuException sie){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (FilmInconnuException fie){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/sondage/{idF}")
    public ResponseEntity<SondageDto> getSondage(@PathVariable String idF){
        Sondage s=ss.getSondage(idF);
        if(s!=null){
            return ResponseEntity.ok().body(SondageDto.creerSondageDto(s));
        }
        return ResponseEntity.notFound().build();
    }






}
