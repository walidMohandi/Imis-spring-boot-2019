package fr.univ.orleans.ws.wsade.modele.module;

import fr.univ.orleans.ws.wsade.modele.Enseignant;
import fr.univ.orleans.ws.wsade.modele.Formation;

public abstract class Seance {



    public enum TypeSeance {CM,TD,TP};

    private static long ID_SEANCES = 0;


    public Module getModule() {
        return module;
    }


    private long idSeance;
    private Enseignant enseignant;
    private Module module;
    private Formation formationConcernee;

    private double dureeEnHeure;


    public Seance(Formation formation, Module module, Enseignant enseignant, double dureeEnHeure) {
        this.idSeance = ID_SEANCES++;
        this.module = module;
        this.formationConcernee = formation;
        this.enseignant = enseignant;
        this.dureeEnHeure = dureeEnHeure;
    }


    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public double getDureeEnHeure() {
        return dureeEnHeure;
    }

    public void setDureeEnHeure(double dureeEnHeure) {
        this.dureeEnHeure = dureeEnHeure;
    }


    public Formation getFormationConcernee() {
        return formationConcernee;
    }

    public abstract double calculEqTD();


    public long getIdSeance() {
        return idSeance;
    }
}
