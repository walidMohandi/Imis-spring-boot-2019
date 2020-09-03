package fr.univ.orleans.ws.wsade.modele.module;

import fr.univ.orleans.ws.wsade.modele.Enseignant;
import fr.univ.orleans.ws.wsade.modele.Formation;

public class TP extends Seance {

    private final static double TAUX = 2.0/3.0;

    public TP(Formation formation, Module module, Enseignant enseignant, double dureeEnHeure) {
        super(formation,module, enseignant, dureeEnHeure);
    }


    @Override
    public double calculEqTD() {
        return getDureeEnHeure()*TAUX;
    }
}
