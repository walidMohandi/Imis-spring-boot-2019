package fr.univ.orleans.ws.wsade.dto;

import fr.univ.orleans.ws.wsade.modele.Enseignant;
import fr.univ.orleans.ws.wsade.modele.Formation;
import fr.univ.orleans.ws.wsade.modele.module.Module;

import java.util.Objects;


public class ModuleDTO {

    private String nomModule;
    private String descriptifVolume;
    private double nbHeuresCM;
    private double nbHeuresTD;
    private double nbHeuresTP;


    private EnseignantDTO responsable;


    public ModuleDTO(){}

    ModuleDTO(Module module) {
        this.nomModule = module.getNomModule();
        this.descriptifVolume = module.getDescriptifVolume();
        this.nbHeuresCM = module.getNbHeuresCM();
        this.nbHeuresTD = module.getNbHeuresTD();
        this.nbHeuresTP = module.getNbHeuresTP();
        this.responsable = Objects.isNull(module.getResponsable())?null:EnseignantDTO.creer(module.getResponsable());
    }



    public static ModuleDTO creer(Module module) {
        return new ModuleDTO(module);
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

    public void setNbHeuresCM(double nbHeuresCM) {
        this.nbHeuresCM = nbHeuresCM;
    }

    public double getNbHeuresTD() {
        return nbHeuresTD;
    }

    public void setNbHeuresTD(double nbHeuresTD) {
        this.nbHeuresTD = nbHeuresTD;
    }

    public double getNbHeuresTP() {
        return nbHeuresTP;
    }

    public void setNbHeuresTP(double nbHeuresTP) {
        this.nbHeuresTP = nbHeuresTP;
    }

    public EnseignantDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(EnseignantDTO responsable) {
        this.responsable = responsable;
    }
}
