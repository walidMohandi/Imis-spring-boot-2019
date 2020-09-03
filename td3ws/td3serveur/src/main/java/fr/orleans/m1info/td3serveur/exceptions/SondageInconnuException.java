package fr.orleans.m1info.td3serveur.exceptions;

public class SondageInconnuException extends Exception {
    public SondageInconnuException() {
        super();
    }

    public SondageInconnuException(String message) {
        super(message);
    }
}
