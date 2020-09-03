package fr.univ.orleans.ws.wsade.modele;

import fr.univ.orleans.ws.wsade.modele.module.Seance;

import java.util.ArrayList;
import java.util.Collection;

public class Enseignant {



    private static long IDENTIFIANTS = 0;
    private long idEnseignant;
    private String nom;
    private String prenom;

    public Collection<Seance> getSeancesAssurees() {
        return seancesAssurees;
    }

    Collection<Seance> seancesAssurees;


    public static Enseignant creer(String nom, String prenom) {
        return new Enseignant(nom, prenom);
    }


    Enseignant(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.idEnseignant = IDENTIFIANTS++;
        this.seancesAssurees = new ArrayList<>();
    }


    public long getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(long idEnseignant) {
        this.idEnseignant = idEnseignant;
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



    public void ajouterSeance(Seance seance) {
        this.seancesAssurees.add(seance);
    }

    public void supprimerSeance(Seance seance) {
        this.seancesAssurees.remove(seance);
    }
}
