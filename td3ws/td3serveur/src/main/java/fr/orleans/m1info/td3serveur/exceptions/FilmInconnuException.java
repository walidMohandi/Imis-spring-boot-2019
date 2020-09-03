package fr.orleans.m1info.td3serveur.exceptions;

public class FilmInconnuException extends Exception {
    public FilmInconnuException() {
        super();
    }

    public FilmInconnuException(String message) {
        super(message);
    }
}
