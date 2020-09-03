package dtos;

public class UtilDto {
    private String nom;
    private String prenom;
    private String pseudo;

    public UtilDto(String nom, String prenom, String pseudo) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }
}
