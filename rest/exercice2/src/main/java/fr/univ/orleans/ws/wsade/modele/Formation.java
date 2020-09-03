package fr.univ.orleans.ws.wsade.modele;

import fr.univ.orleans.ws.wsade.exceptions.ModuleInexistantException;
import fr.univ.orleans.ws.wsade.exceptions.ModuleNonPresentDansFormationException;
import fr.univ.orleans.ws.wsade.modele.module.Module;
import fr.univ.orleans.ws.wsade.modele.module.Seance;

import java.util.*;

public class Formation {

    private String libelleFormation;

    private Enseignant responsable;

    private Collection<Module> semestre1;

    private Collection<Module> semestre2;


    private Map<String,Module> modules;

    private Map<Semestre,Collection<Module>> modulesParSemestre;




    private int nbGroupesTD;
    private  int nbGroupesTP;

    public static Formation creer(String nomFormation, Enseignant responsable, int nbGroupesTD, int nbGroupesTP) {
        return new Formation(nomFormation,responsable,nbGroupesTD,nbGroupesTP);
    }

    public void changerResponsable(Enseignant enseignant) {
        this.responsable = enseignant;
    }

    public  enum Semestre {SEMESTRE_1,SEMESTRE_2};

    Formation(String libelleFormation, Enseignant responsable, int nbGroupesTD, int nbGroupesTP) {
        this.libelleFormation = libelleFormation;
        this.responsable = responsable;
        this.semestre1 = new ArrayList<>();
        this.semestre2 = new ArrayList<>();
        this.modules = new HashMap<>();
        this.modulesParSemestre = new HashMap<>();
        this.modulesParSemestre.put(Semestre.SEMESTRE_1,semestre1);
        this.modulesParSemestre.put(Semestre.SEMESTRE_2,semestre2);
        this.nbGroupesTD = nbGroupesTD;
        this.nbGroupesTP = nbGroupesTP;
    }



    public void ajouterModule(Semestre semestre, Module module) {
        this.modules.put(module.getNomModule(),module);
        this.modulesParSemestre.get(semestre).add(module);

    }


    public int getNbGroupesTD() {
        return nbGroupesTD;
    }

    public void setNbGroupesTD(int nbGroupesTD) {
        this.nbGroupesTD = nbGroupesTD;
    }

    public int getNbGroupesTP() {
        return nbGroupesTP;
    }

    public void setNbGroupesTP(int nbGroupesTP) {
        this.nbGroupesTP = nbGroupesTP;
    }

    public Seance ajouterSeance(Module module, Enseignant enseignant, Seance.TypeSeance typeSeance, double duree) throws ModuleNonPresentDansFormationException {
        Module module1 = this.modules.get(module.getNomModule());
        if (Objects.isNull(module1))
            throw new ModuleNonPresentDansFormationException();

        return module1.ajouterSeance(this,typeSeance,enseignant,duree);
    }



    public String getLibelleFormation() {
        return libelleFormation;
    }

    public Enseignant getResponsable() {
        return responsable;
    }

    public Collection<Module> getSemestre1() {
        return semestre1;
    }

    public Collection<Module> getSemestre2() {
        return semestre2;
    }




    public Module getModuleById(String libelleModule) throws ModuleInexistantException {
        if (modules.containsKey(libelleModule))
            return this.modules.get(libelleModule);
        else
            throw new ModuleInexistantException();
    }
}
