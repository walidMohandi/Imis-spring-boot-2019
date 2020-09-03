package fr.orleans.m1info.td3serveur.exceptions;

public class DuplicationSondageException extends Exception{
    public DuplicationSondageException() {
        super();
    }

    public DuplicationSondageException(String message) {
        super(message);
    }
}
