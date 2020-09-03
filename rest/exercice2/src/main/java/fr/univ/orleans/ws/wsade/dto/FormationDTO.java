package fr.univ.orleans.ws.wsade.dto;

import fr.univ.orleans.ws.wsade.modele.Formation;

import java.io.Serializable;
import java.util.Objects;

public class FormationDTO implements Serializable {


    private String nomFormation;
    private EnseignantDTO responsable;

    private int nbGroupesTD;
    private int nbGroupesTP;

    public FormationDTO(){

    }

    public static FormationDTO creer(Formation formation) {
        return new FormationDTO(formation);
    }


    private FormationDTO(Formation formation){
        this.nomFormation = formation.getLibelleFormation();
        this.responsable = Objects.isNull(formation.getResponsable())?null:EnseignantDTO.creer(formation.getResponsable());
        this.nbGroupesTD = formation.getNbGroupesTD();
        this.nbGroupesTP = formation.getNbGroupesTP();

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

    public String getNomFormation() {
        return nomFormation;
    }

    public void setNomFormation(String nomFormation) {
        this.nomFormation = nomFormation;
    }

    public EnseignantDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(EnseignantDTO responsable) {
        this.responsable = responsable;
    }
}
