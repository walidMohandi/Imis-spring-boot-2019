package fr.univ.orleans.ws.wsade.modele;


import fr.univ.orleans.ws.wsade.exceptions.*;
import fr.univ.orleans.ws.wsade.modele.module.*;
import fr.univ.orleans.ws.wsade.modele.module.Module;


import java.util.*;
import java.util.stream.Collectors;

public class FacadeAdeImpl implements FacadeAde {


    private static final Map<Long,Enseignant> enseignants = new HashMap<>();


    private static final Map<String,Module> cataloguesModules = new HashMap<>();

    private static final Map<String,Formation> cataloguesFormations = new HashMap<>();

    private static final Map<Long,Seance> toutesLesSeances = new HashMap<>();
    public static final int NB_GROUPES_TD = 2;
    public static final int NB_GROUPES_TP = 2;


    @Override
    public long ajouterUnEnseignant(String nom, String prenom) {
        Enseignant enseignant = Enseignant.creer(nom, prenom);
        enseignants.put(enseignant.getIdEnseignant(),enseignant);
        return enseignant.getIdEnseignant();
    }


    @Override
    public String creerUnModule(String nomModule, String descriptifModule, long idProfesseur, double volumeCM, double volumeTD, double volumeTP) throws ModuleDejaExistantException, ProfesseurInexistantException {


        Enseignant responsable = this.getEnseignantById(idProfesseur);


        if (cataloguesModules.containsKey(nomModule)) {
            throw new ModuleDejaExistantException();
        }

        Module module = FabriqueModule.newModule(nomModule, descriptifModule, responsable, volumeCM, volumeTD, volumeTP);
        cataloguesModules.put(nomModule, module);
        return nomModule;

    }


    @Override
    public String creerUneFormation(String nomFormation, long idResponsable) throws ProfesseurInexistantException, FormationDejaExistanteException {
        Enseignant responsable = this.getEnseignantById(idResponsable);

        if (cataloguesFormations.containsKey(nomFormation)) {
            throw new FormationDejaExistanteException();
        }

        Formation formation = Formation.creer(nomFormation,responsable, NB_GROUPES_TD, NB_GROUPES_TP);
        cataloguesFormations.put(formation.getLibelleFormation(),formation);
        return nomFormation;

    }



    @Override
    public Collection<Enseignant> getPersonnel() {
        return enseignants.values();
    }


    @Override
    public Collection<Formation> getFormations() {
        return cataloguesFormations.values();
    }



    @Override
    public Collection<Module> getModules() {
        return cataloguesModules.values();
    }



    @Override
    public Module getModuleById(String identifiantModule) throws ModuleInexistantException {
        Module module = cataloguesModules.get(identifiantModule);
        if (Objects.isNull(module)) {
            throw new ModuleInexistantException();
        }
        return module;
    }



    @Override
    public Enseignant getEnseignantById(long identifiant) throws ProfesseurInexistantException {
        Enseignant enseignant = enseignants.get(identifiant);
        if (Objects.isNull(enseignant)) {
            throw new ProfesseurInexistantException();
        }

        return enseignant;
    }



    @Override
    public Formation getFormationById(String idFormation) throws FormationInexistanteException {
        Formation formation = cataloguesFormations.get(idFormation);
        if (Objects.isNull(formation)) {
            throw new FormationInexistanteException();
        }
        return formation;
    }



    @Override
    public Seance getSeanceById(long idSeance) throws SeanceInexistanteException {
        Seance seance = toutesLesSeances.get(idSeance);
        if (Objects.isNull(seance)) {
            throw new SeanceInexistanteException();
        }
        return seance;

    }

    @Override
    public long creerSeance(String nomFormation, String nomModule, long idEnseignant, Seance.TypeSeance typeSeance, double duree) throws ProfesseurInexistantException, ModuleInexistantException, FormationInexistanteException, ModuleNonPresentDansFormationException {

        Formation formation = this.getFormationById(nomFormation);
        Module module = this.getModuleById(nomModule);
        Enseignant enseignant = this.getEnseignantById(idEnseignant);
        Seance seance = formation.ajouterSeance(module, enseignant,typeSeance,duree);
        toutesLesSeances.put(seance.getIdSeance(),seance);
        return seance.getIdSeance();
    }


    @Override
    public Collection<CM> getTousLesCMsDunModulePourUneFormation(String identifiantModule, String idFormation) throws ModuleInexistantException, FormationInexistanteException {
        Module module = this.getModuleById(identifiantModule);
        Formation formation = this.getFormationById(idFormation);
        return module.getCms(formation);

    }



    @Override
    public Collection<TD> getTousLesTDsDunModulePourUneFormation(String identifiantModule, String idFormation) throws ModuleInexistantException, FormationInexistanteException {
        Module module = this.getModuleById(identifiantModule);
        Formation formation = this.getFormationById(idFormation);
        return module.getTds(formation);

    }


    @Override
    public Collection<TP> getTousLesTPsPourUneFormation(String identifiantModule, String idFormation) throws ModuleInexistantException, FormationInexistanteException {
        Module module = this.getModuleById(identifiantModule);
        Formation formation = this.getFormationById(idFormation);
        return module.getTps(formation);

    }


    @Override
    public Collection<Seance> getToutesLesInterventionsDunEnseignant(long idProfesseur) throws ProfesseurInexistantException {
        Enseignant enseignant = this.getEnseignantById(idProfesseur);
        return enseignant.getSeancesAssurees();
    }


    @Override
    public void supprimerSeance(long idSeance) throws SeanceInexistanteException {
        Seance seance = getSeanceById(idSeance);
        Module module = seance.getModule();
        Enseignant enseignant = seance.getEnseignant();
        enseignant.supprimerSeance(seance);
        module.supprimerSeance(seance);
        toutesLesSeances.remove(idSeance);
    }



    @Override
    public Collection<Enseignant> getResponsablesModules() {
        return cataloguesModules.values().stream().map(e -> e.getResponsable()).collect(Collectors.toList());
    }



    @Override
    public Collection<Enseignant> getResponsablesFormations() {
        return cataloguesFormations.values().stream().map(e -> e.getResponsable()).collect(Collectors.toList());
    }


    @Override
    public void supprimerEnseignant(long idEnseignant) throws ProfesseurInexistantException, AEncoreUneResponsabiliteDeFormationException, AEncoreUneResponsabiliteDeModuleException {
        Enseignant enseignant = this.getEnseignantById(idEnseignant);

        for(Seance s: enseignant.getSeancesAssurees()) {
            try {
                this.supprimerSeance(s.getIdSeance());
            } catch (SeanceInexistanteException e) {

            }
        }

        if (getResponsablesFormations().contains(enseignant)) {
            throw new AEncoreUneResponsabiliteDeFormationException();
        }

        if (getResponsablesModules().contains(enseignant)) {
            throw new AEncoreUneResponsabiliteDeModuleException();
        }

        enseignants.remove(idEnseignant);
    }


    @Override
    public void supprimerUnModule(String nomModule) throws ModuleInexistantException {
        Module module = this.getModuleById(nomModule);
        module.getAllTps().stream().forEach(s -> {
            try {
                supprimerSeance(s.getIdSeance());
            } catch (SeanceInexistanteException e) {

            }
        });

        module.getAllTds().stream().forEach(s -> {
            try {
                supprimerSeance(s.getIdSeance());
            } catch (SeanceInexistanteException e) {

            }
        });

        module.getAllCms().stream().forEach(s -> {
            try {
                supprimerSeance(s.getIdSeance());
            } catch (SeanceInexistanteException e) {

            }
        });

        cataloguesModules.remove(nomModule);
    }


    @Override
    public void supprimerUneFormation(String idFormation) throws FormationInexistanteException, SeanceInexistanteException {
        Formation formation = this.getFormationById(idFormation);

        Collection<Module> allModules = new ArrayList<>(formation.getSemestre1());
        allModules.addAll(formation.getSemestre2());

        Collection<Seance> seances = new ArrayList<>();

        for(Module module : allModules) {
            seances.addAll(module.getCms(formation));
            seances.addAll(module.getTds(formation));
            seances.addAll(module.getTps(formation));
        }

        for (Seance seance : seances) {
            this.supprimerSeance(seance.getIdSeance());
        }

        cataloguesFormations.remove(formation.getLibelleFormation());
    }


    @Override
    public void changerNombreDeGroupesDeTDs(String libelleFormation, int nbGroupes) throws FormationInexistanteException {

        Formation formation = this.getFormationById(libelleFormation);
        formation.setNbGroupesTD(nbGroupes);
    }


    @Override
    public void changerNombreDeGroupesDeTPs(String libelleFormation, int nbGroupes) throws FormationInexistanteException {

        Formation formation = this.getFormationById(libelleFormation);
        formation.setNbGroupesTP(nbGroupes);
    }



    @Override
    public void changerResponsableDuneFormation(String idFormation, long idResponsable) throws FormationInexistanteException, ProfesseurInexistantException {
            Formation formation = this.getFormationById(idFormation);
            Enseignant enseignant = this.getEnseignantById(idResponsable);
            formation.changerResponsable(enseignant);

    }

    @Override
    public void ajouterModuleAFormation(Formation formationConcernee, Module module, Formation.Semestre semestre) {
        formationConcernee.ajouterModule(semestre,module);
    }

    @Override
    public Module getModuleDeFormation(String idFormation, String idModule) throws ModuleInexistantException, FormationInexistanteException {
        Formation formation = this.getFormationById(idFormation);
        return formation.getModuleById(idModule);
    }

    @Override
    public double calculServiceEqTD(long idEnseignant) throws ProfesseurInexistantException {
        Collection<Seance> all = this.getToutesLesInterventionsDunEnseignant(idEnseignant);
        double resultat = 0;

        for (Seance seance : all) {
            resultat += seance.calculEqTD();
        }
        return resultat;

    }

}
