package fr.univ.orleans.ws.wsade.dto;

import fr.univ.orleans.ws.wsade.modele.module.Seance;

import java.util.Objects;

public class SeanceDTO {




    private long idSeance;
    private EnseignantDTO enseignant;
    private ModuleDTO module;
    private FormationDTO formation;
    private double duree;


    public SeanceDTO() {
    }




    private SeanceDTO(Seance seance){
        this.idSeance = seance.getIdSeance();
        this.enseignant = Objects.isNull(seance.getEnseignant())?null:EnseignantDTO.creer(seance.getEnseignant());
        this.formation = Objects.isNull(seance.getFormationConcernee())?null:FormationDTO.creer(seance.getFormationConcernee());
        this.module = Objects.isNull(seance.getModule()) ? null:ModuleDTO.creer(seance.getModule());
        this.duree = seance.getDureeEnHeure();
    }



    public static SeanceDTO creer(Seance seance) {
        return new SeanceDTO(seance);
    }

    public long getIdSeance() {
        return idSeance;
    }

    public void setIdSeance(long idSeance) {
        this.idSeance = idSeance;
    }

    public EnseignantDTO getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(EnseignantDTO enseignant) {
        this.enseignant = enseignant;
    }

    public ModuleDTO getModule() {
        return module;
    }

    public void setModule(ModuleDTO module) {
        this.module = module;
    }

    public FormationDTO getFormation() {
        return formation;
    }

    public void setFormation(FormationDTO formation) {
        this.formation = formation;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }
}
