package fe.orleans.webservices.livedemosecurity.models;

public class Message {
    private final Long id;
    private final String texte;

    public Message(Long id,String texte){
        this.id=id;
        this.texte=texte;
    }

    public Long getId() {
        return id;
    }

    public String getTexte() {
        return texte;
    }
}
