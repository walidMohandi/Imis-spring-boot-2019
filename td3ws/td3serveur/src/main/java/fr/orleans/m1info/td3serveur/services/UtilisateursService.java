package fr.orleans.m1info.td3serveur.services;

import fr.orleans.m1info.td3serveur.modele.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UtilisateursService {
    private static UtilisateursService us=null;
    private static AtomicInteger compteur=new AtomicInteger(1);
    private List<Utilisateur> lesUtilisateurs=new ArrayList<>();

    public static synchronized UtilisateursService getService() {
        if (us==null) {
            us=new UtilisateursService();
        }
        return us;
    }

    private UtilisateursService() {
    }

public Utilisateur creerUtilisateur(Utilisateur util){
    if (getUtilisateur(util.getPseudo()) == null) {
        Utilisateur u = new Utilisateur(compteur.getAndIncrement(), util.getNom(), util.getPrenom(), util.getPseudo());
        lesUtilisateurs.add(u);
        return u;
    } else {
        return null;
    }

}


public Utilisateur getUtilisateur(String pseudo) {
    Optional<Utilisateur> util=lesUtilisateurs.stream().filter(u->u.getPrenom().equals(pseudo)).findAny();
    if (util.isPresent()) {
        return util.get();
    } else {
        return null;
    }
}

    public Utilisateur getUtilisateur(int id) {
        Optional<Utilisateur> util=lesUtilisateurs.stream().filter(u->u.getId()==id).findAny();
        if (util.isPresent()) {
            return util.get();
        } else {
            return null;
        }
    }


}
