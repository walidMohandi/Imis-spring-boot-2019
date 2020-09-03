package fr.univ.orleans.ws.wsade.modele.module;

import fr.univ.orleans.ws.wsade.modele.Enseignant;
import fr.univ.orleans.ws.wsade.modele.Formation;

import java.util.Objects;

public class FabriqueSeance {




    public static Seance newSeance(Formation formation,Seance.TypeSeance typeSeance, Module module, Enseignant enseignant, double duree) {

        Seance seance = null;

        switch (typeSeance) {
            case CM:{
                seance = new CM(formation,module, enseignant, duree);
                break;
            }



            case TD: {
                seance = new TD(formation,module,enseignant,duree);
                break;
            }

            case TP: {
                seance = new TP(formation,module,enseignant,duree);
                break;
            }

        }

        if (Objects.isNull(seance)) {
            throw new RuntimeException("Type inconnu");
        }
        enseignant.ajouterSeance(seance);
        return seance;
    }

}
