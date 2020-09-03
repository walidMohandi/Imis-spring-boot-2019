package fr.univ.orleans.webservices.liveserialisation.Dto;

import fr.univ.orleans.webservices.liveserialisation.modele.Message;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurDTO {
    private final String login;
    private final String password;
    private final boolean isAdmin;
    private final List<Long> messages = new ArrayList<>();

    public UtilisateurDTO(String login, String password, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public List<Long> getMessages() {
        return messages;
    }
}
