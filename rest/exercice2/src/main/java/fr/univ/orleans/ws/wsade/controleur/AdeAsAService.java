package fr.univ.orleans.ws.wsade.controleur;


import fr.univ.orleans.ws.wsade.dto.EnseignantDTO;
import fr.univ.orleans.ws.wsade.dto.FormationDTO;
import fr.univ.orleans.ws.wsade.dto.ModuleDTO;
import fr.univ.orleans.ws.wsade.dto.SeanceDTO;
import fr.univ.orleans.ws.wsade.exceptions.*;
import fr.univ.orleans.ws.wsade.modele.Enseignant;
import fr.univ.orleans.ws.wsade.modele.FacadeAde;
import fr.univ.orleans.ws.wsade.modele.FacadeAdeImpl;
import fr.univ.orleans.ws.wsade.modele.Formation;
import fr.univ.orleans.ws.wsade.modele.module.Module;
import fr.univ.orleans.ws.wsade.modele.module.Seance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ade")
public class AdeAsAService {



    private static final FacadeAde facadeAde = new FacadeAdeImpl();




    @GetMapping(value = "/enseignant/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EnseignantDTO> getEnseignantById(@PathVariable("id") long id){
        try {
            Enseignant enseignant = facadeAde.getEnseignantById(id);
            return ResponseEntity.ok(EnseignantDTO.creer(enseignant));

        } catch (ProfesseurInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping(value = "/enseignant", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Collection<EnseignantDTO>> getEnseignants(){
        Collection<Enseignant> enseignants = facadeAde.getPersonnel();

            return ResponseEntity.ok(enseignants.stream().map(e -> EnseignantDTO.creer(e)).collect(Collectors.toList()));
    }



    @PostMapping(value = "/enseignant",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> creerEnseignant(@RequestBody EnseignantDTO enseignantDTO){
        long resultat = facadeAde.ajouterUnEnseignant(enseignantDTO.getNom(),enseignantDTO.getPrenom());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(resultat).toUri();
        return ResponseEntity.created(location).build();

    }



    @DeleteMapping(value = "/enseignant/{id}")
    public ResponseEntity<String> supprimerEnseignant(@PathVariable("id") long id) {

        try {
            facadeAde.supprimerEnseignant(id);
            return ResponseEntity.ok().build();
        } catch (ProfesseurInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (AEncoreUneResponsabiliteDeFormationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (AEncoreUneResponsabiliteDeModuleException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
    }





    @PostMapping(value = "/formation", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<String> creerFormation(@RequestParam String nom, @RequestParam long idEnseignant) {
        try {
            String id = facadeAde.creerUneFormation(URLDecoder.decode(nom, StandardCharsets.UTF_8.toString()),idEnseignant);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (ProfesseurInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (FormationDejaExistanteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }





    @GetMapping(value = "/formation",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Collection<FormationDTO> getAllFormations() {
        Collection<Formation> formations = facadeAde.getFormations();
        return formations.stream().map(e -> FormationDTO.creer(e)).collect(Collectors.toList());

    }



    @GetMapping(value = "/formation/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<FormationDTO> getFormationById(@PathVariable("id") String idFormation) {
        try {

            Formation formation = facadeAde.getFormationById(URLDecoder.decode(idFormation, StandardCharsets.UTF_8.toString()));
            return ResponseEntity.ok(FormationDTO.creer(formation));
        } catch (FormationInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }




    @PatchMapping(value = "/formation/{idFormation}/nbgroupestd",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})

    public ResponseEntity<String> setNbGroupesTD(@PathVariable("idFormation") String idFormation, @RequestParam int nbGroupe) {

        try {
            Formation formation = facadeAde.getFormationById(URLDecoder.decode(idFormation, StandardCharsets.UTF_8.toString()));
            formation.setNbGroupesTD(nbGroupe);
            return ResponseEntity.ok("Nb de groupes de TD mis à jour");
        } catch (FormationInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UnsupportedEncodingException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @PatchMapping(value = "/formation/{idFormation}/nbgroupestp",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})

    public ResponseEntity<String> setNbGroupesTP(@PathVariable("idFormation") String idFormation, @RequestParam int nbGroupe) {

        try {
            Formation formation = facadeAde.getFormationById(URLDecoder.decode(idFormation, StandardCharsets.UTF_8.toString()));
            formation.setNbGroupesTP(nbGroupe);
            return ResponseEntity.ok("Nb de groupes de TP mis à jour");
        } catch (FormationInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




    @DeleteMapping(value = "/formation/{idFormation}")
    public ResponseEntity<String> supprimerFormation(@PathVariable("idFormation") String idFormation) {

        try {

            facadeAde.supprimerUneFormation(URLDecoder.decode(idFormation, StandardCharsets.UTF_8.toString()));
            return ResponseEntity.ok("La formation a bien été supprimée");
        } catch (FormationInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (SeanceInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }






    @PostMapping(value = "/module",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<String> creerUnModule(@RequestParam("nomModule") String nomModule, @RequestParam String descriptifModule,
    @RequestParam long idEnseignant, @RequestParam double volumeCM, @RequestParam double volumeTD, @RequestParam double volumeTP) {
        try {


            String nomModule2 = URLDecoder.decode(nomModule,StandardCharsets.UTF_8.toString());
            descriptifModule = URLDecoder.decode(descriptifModule,StandardCharsets.UTF_8.toString());
            String module = facadeAde.creerUnModule(nomModule2, descriptifModule, idEnseignant, volumeCM, volumeTD, volumeTP);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/ade/module/{idModule}").buildAndExpand(module).toUri();
            return ResponseEntity.created(location).build();

        } catch (ModuleDejaExistantException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (ProfesseurInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


    @GetMapping(value = "/module/{idModule}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ModuleDTO> getModule(@PathVariable("idModule") String idModule) {

        try {
            idModule = URLDecoder.decode(idModule,StandardCharsets.UTF_8.toString());
            Module module = facadeAde.getModuleById(idModule);
            ModuleDTO moduleDTO = ModuleDTO.creer(module);
            return ResponseEntity.ok(moduleDTO);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ModuleInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


    @GetMapping(value = "/module", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Collection<ModuleDTO>> getModules() {
        Collection<Module> modules = facadeAde.getModules();
        Collection<ModuleDTO> moduleDTOS =modules.stream().map(e -> ModuleDTO.creer(e)).collect(Collectors.toList());
        return ResponseEntity.ok(moduleDTOS);
    }


    @DeleteMapping(value = "/module/{idModule}")
    public ResponseEntity<String> supprimerModule(@PathVariable String idModule) {
        try {
            idModule = URLDecoder.decode(idModule,StandardCharsets.UTF_8.toString());
            facadeAde.supprimerUnModule(idModule);
            return ResponseEntity.ok().build();

        } catch (ModuleInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




    @PostMapping(value = "/formation/{idFormation}/module")
    public ResponseEntity<String> ajouterModuleAUneFormation(@PathVariable("idFormation") String idFormation, @RequestParam("idModule")String idModule, @RequestParam("semestre") String semestre) {
        try {
            idFormation =  URLDecoder.decode(idFormation,StandardCharsets.UTF_8.toString());
            idModule =  URLDecoder.decode(idModule,StandardCharsets.UTF_8.toString());
            Formation formationConcernee = facadeAde.getFormationById(idFormation);
            Module module = facadeAde.getModuleById(idModule);
            Formation.Semestre semestreConcerne = Formation.Semestre.valueOf(semestre);
            facadeAde.ajouterModuleAFormation(formationConcernee,module,semestreConcerne);


            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/ade/formation/{idFormation}/module/{id}").buildAndExpand(idFormation,module.getNomModule()).toUri();
            return ResponseEntity.created(location).build();

        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (FormationInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ModuleInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }



    @GetMapping(value = "/formation/{idFormation}/module/{idModule}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ModuleDTO> ajouterModuleAUneFormation(@PathVariable("idFormation") String idFormation, @PathVariable("idModule")String idModule) {
        try {
            Module module = facadeAde.getModuleDeFormation(idFormation,idModule);
            return ResponseEntity.ok(ModuleDTO.creer(module));
        } catch (ModuleInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (FormationInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }



    @PostMapping(value = "/seance", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<String> ajouterSeance(@RequestParam String idFormation,@RequestParam String idModule,@RequestParam long idEnseignant,
                                                @RequestParam String typeSeance, @RequestParam double duree) {


        try {
            Seance.TypeSeance type = Seance.TypeSeance.valueOf(URLDecoder.decode(typeSeance,StandardCharsets.UTF_8.toString()));
            idFormation = URLDecoder.decode(idFormation,StandardCharsets.UTF_8.toString());
            idModule = URLDecoder.decode(idModule,StandardCharsets.UTF_8.toString());

            long idSeance = facadeAde.creerSeance(idFormation,idModule,idEnseignant,type,duree);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/ade/seance/{id}").buildAndExpand(idSeance).toUri();
            return ResponseEntity.created(location).build();



        } catch (ProfesseurInexistantException e ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ModuleInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (FormationInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (ModuleNonPresentDansFormationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }


    @GetMapping(value = "/seance/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<SeanceDTO> getSeanceById(@PathVariable long id) {

        try {
            return ResponseEntity.ok(SeanceDTO.creer(facadeAde.getSeanceById(id)));
        } catch (SeanceInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }




    @GetMapping(value = "/enseignant/{idEnseignant}/seance", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Collection<SeanceDTO>> getSeances(@PathVariable long idEnseignant) {
        try {
            return ResponseEntity.ok(facadeAde.getToutesLesInterventionsDunEnseignant(idEnseignant).stream().
                    map(s -> SeanceDTO.creer(s)).collect(Collectors.toList()));
        } catch (ProfesseurInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




    @GetMapping(value = "/formation/{idFormation}/module/{idModule}/seance", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Collection<SeanceDTO>> getSeancesPourModuleDansFormation(@PathVariable String idFormation, @PathVariable String idModule) {
       Collection<SeanceDTO> collection = new ArrayList<>();

        try {
            String idFormationDecode = URLDecoder.decode(idFormation,StandardCharsets.UTF_8.toString());
            String idModuleDecode = URLDecoder.decode(idModule,StandardCharsets.UTF_8.toString());

            collection.addAll(facadeAde.getTousLesCMsDunModulePourUneFormation(idModuleDecode,idFormationDecode).stream().map(e -> SeanceDTO.creer(e)).collect(Collectors.toList()));
            collection.addAll(facadeAde.getTousLesTPsPourUneFormation(idModuleDecode,idFormationDecode).stream().map(e -> SeanceDTO.creer(e)).collect(Collectors.toList()));
            collection.addAll(facadeAde.getTousLesTDsDunModulePourUneFormation(idModuleDecode,idFormationDecode).stream().map(e -> SeanceDTO.creer(e)).collect(Collectors.toList()));
            return ResponseEntity.ok(collection);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (FormationInexistanteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (ModuleInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }





    @GetMapping(value="/enseignant/{idEnseignant}/calculEQTD")
    public ResponseEntity<Double> calculEqTD(@PathVariable long idEnseignant) {
        try {
           return ResponseEntity.ok(facadeAde.calculServiceEqTD(idEnseignant));
        } catch (ProfesseurInexistantException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }



}
