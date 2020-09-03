package fr.univ.orleans.ws.wsade.modele.module;

import fr.univ.orleans.ws.wsade.modele.Enseignant;
import fr.univ.orleans.ws.wsade.modele.Formation;

public class TD extends Seance {

    private static final double TAUX = 1;

    public TD(Formation formation, Module module, Enseignant enseignant, double dureeEnHeure) {
        super(formation,module, enseignant, dureeEnHeure);
    }


    @Override
    public double calculEqTD() {
        return getDureeEnHeure()*TAUX;
    }
}
