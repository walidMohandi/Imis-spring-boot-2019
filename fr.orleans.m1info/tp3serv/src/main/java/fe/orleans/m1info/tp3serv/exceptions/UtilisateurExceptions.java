package fe.orleans.m1info.tp3serv.exceptions;

import fe.orleans.m1info.tp3serv.Models.Utilisateur;

public class UtilisateurExceptions extends Exception {

    public UtilisateurExceptions(){
        super();
    }

    public UtilisateurExceptions(String message){
        super(message);
    }

}
