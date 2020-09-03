package fe.orleans.m1info.tp3serv.Service;

import fe.orleans.m1info.tp3serv.Models.Utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UtilisateurService {

    private static UtilisateurService us=null;
    private static AtomicInteger compteur=new AtomicInteger(1);
    private List<Utilisateur> utils=new ArrayList<>();

    public static synchronized UtilisateurService getService(){
        if(us==null){
            us=new UtilisateurService();
        }
        return us;
    }

    public UtilisateurService(){

    }

    public Utilisateur creerUtilisateur(Utilisateur u){
        if(getUtilisateur(u.getPseudo())==null){
            Utilisateur util=new Utilisateur(compteur.getAndIncrement(),u.getNom(),u.getPrenom(),u.getPseudo());
            utils.add(util);
            return util;
        }
        return null;
    }

    public  Utilisateur getUtilisateur(String pseudo){
        Optional<Utilisateur> util =utils.stream().filter(u -> u.getPseudo().equals(pseudo)).findAny();
        if(util.isPresent()){
            return util.get();
        }
        return null;
    }
    public Utilisateur getUtilisateur(int id){
        Optional<Utilisateur> util=utils.stream().filter(u ->u.getId()==id).findAny();
        if(util.isPresent()){
            return util.get();
        }
        return null;
    }

}
