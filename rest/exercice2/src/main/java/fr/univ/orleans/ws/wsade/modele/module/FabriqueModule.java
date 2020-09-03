package fr.univ.orleans.ws.wsade.modele.module;

import fr.univ.orleans.ws.wsade.modele.Enseignant;

public class FabriqueModule {


    public static Module newModule(String nomModule, String descriptif, Enseignant responsable, double volumeCM, double volumeTD, double volumeTP) {
        Module module =  new Module(nomModule,descriptif, volumeCM,volumeTD,volumeTP);
        module.setResponsable(responsable);
        return module;
    }
}
