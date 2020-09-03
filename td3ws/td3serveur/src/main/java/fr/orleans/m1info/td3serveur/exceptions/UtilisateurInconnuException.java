package fr.orleans.m1info.td3serveur.exceptions;

public class UtilisateurInconnuException extends Exception {
    public UtilisateurInconnuException() {
        super();
    }

    public UtilisateurInconnuException(String message) {
        super(message);
    }
}
