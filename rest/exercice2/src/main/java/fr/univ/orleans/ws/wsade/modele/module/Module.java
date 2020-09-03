package fr.univ.orleans.ws.wsade.modele.module;

import fr.univ.orleans.ws.wsade.modele.Enseignant;
import fr.univ.orleans.ws.wsade.modele.Formation;
import fr.univ.orleans.ws.wsade.modele.module.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Module {

    private String nomModule;
    private String descriptifVolume;
    private double nbHeuresCM;
    private double nbHeuresTD;
    private double nbHeuresTP;


    private Enseignant responsable;

    private Collection<CM> cms;
    private Collection<TD> tds;
    private  Collection<TP> tps;
    private Map<Seance.TypeSeance,Collection> seances;

    Module(String nomModule, String descriptifVolume, double nbHeuresCM, double nbHeuresTD, double nbHeuresTP) {
        this.nomModule = nomModule;
        this.descriptifVolume = descriptifVolume;
        this.nbHeuresCM = nbHeuresCM;
        this.nbHeuresTD = nbHeuresTD;
        this.nbHeuresTP = nbHeuresTP;
        cms = new ArrayList<>();
        tds = new ArrayList<>();
        tps = new ArrayList<>();


        seances = new HashMap<Seance.TypeSeance,Collection>();
        seances.put(Seance.TypeSeance.CM,cms);
        seances.put(Seance.TypeSeance.TD,tds);
        seances.put(Seance.TypeSeance.TP,tps);

    }


    public void setResponsable(Enseignant responsable) {
        this.responsable = responsable;
    }

    public Enseignant getResponsable() {
        return responsable;
    }

    public Collection<CM> getCms(Formation formation) {

        return cms.stream().filter(s -> s.getFormationConcernee().equals(formation)).collect(Collectors.toList());
    }

    public Collection<TD> getTds(Formation formation) {
        return tds.stream().filter(s -> s.getFormationConcernee().equals(formation)).collect(Collectors.toList());
    }

    public Collection<TP> getTps(Formation formation) {
        return tps.stream().filter(s -> s.getFormationConcernee().equals(formation)).collect(Collectors.toList());
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public String getDescriptifVolume() {
        return descriptifVolume;
    }

    public void setDescriptifVolume(String descriptifVolume) {
        this.descriptifVolume = descriptifVolume;
    }

    public double getNbHeuresCM() {
        return nbHeuresCM;
    }

    public void setNbHeuresCM(long nbHeuresCM) {
        this.nbHeuresCM = nbHeuresCM;
    }

    public double getNbHeuresTD() {
        return nbHeuresTD;
    }

    public void setNbHeuresTD(long nbHeuresTD) {
        this.nbHeuresTD = nbHeuresTD;
    }

    public double getNbHeuresTP() {
        return nbHeuresTP;
    }

    public void setNbHeuresTP(long nbHeuresTP) {
        this.nbHeuresTP = nbHeuresTP;
    }




    public Seance ajouterSeance(Formation formation, Seance.TypeSeance typeSeance, Enseignant enseignant, double duree) {
        Seance seance = FabriqueSeance.newSeance(formation,typeSeance,this,enseignant,duree);
        Collection collection =this.seances.get(typeSeance);

        collection.add(seance);
        return seance;
    }

    public void supprimerSeance(Seance seance) {
        this.tps.remove(seance);
        this.tds.remove(seance);
        this.cms.remove(seance);

    }


    public Collection<TP> getAllTps() {
        return this.tps;
    }

    public Collection<TD> getAllTds() {
        return this.tds;
    }

    public Collection<CM> getAllCms() {
        return this.cms;
    }
}
