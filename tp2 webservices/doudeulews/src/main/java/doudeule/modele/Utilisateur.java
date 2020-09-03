package doudeule.modele;

public class Utilisateur {
    private static int IDSUTIL=1;
    private int idUtil;
    private String nom;
    private String prenom;

    public Utilisateur() {
        this.idUtil=IDSUTIL++;
    }

    public Utilisateur(String nom, String prenom) {
        this();
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getIdUtil() {
        return idUtil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
