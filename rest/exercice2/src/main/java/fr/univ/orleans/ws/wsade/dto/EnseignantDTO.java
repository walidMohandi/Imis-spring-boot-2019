package fr.univ.orleans.ws.wsade.dto;

import fr.univ.orleans.ws.wsade.modele.Enseignant;

import java.io.Serializable;

public class EnseignantDTO implements Serializable {


    private long id;
    private String nom;
    private String prenom;



    public EnseignantDTO(){

    }

    public static EnseignantDTO creer(Enseignant enseignant) {
        return new EnseignantDTO(enseignant);
    }


    private EnseignantDTO(Enseignant enseignant){
        this.id = enseignant.getIdEnseignant();
        this.nom = enseignant.getNom();
        this.prenom = enseignant.getPrenom();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
