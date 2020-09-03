package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.FilmDtoOut;
import dtos.SondageDto;
import dtos.UtilDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class SondagesProxy implements Sondages {
    private static final String URI_SERVICE="http://localhost:8080/allucine";
    private static final String SONDAGE= "/sondage";

    private static final String FILM= "/film";
    private static final String UTILISATEUR = "/util";

    private HttpClient httpClient = HttpClient.newHttpClient();

    ObjectMapper objectMapper=new ObjectMapper();

    public String ajoutUtil(String nom, String prenom, String pseudo) {
        String body= null;
        UtilDto ud=new UtilDto(nom,prenom,pseudo);
        try {
            body = objectMapper.writeValueAsString(ud);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        HttpRequest requete= HttpRequest.newBuilder().
                uri(URI.create(URI_SERVICE+UTILISATEUR)).
                setHeader("Content-Type","application/json").
                POST(HttpRequest.BodyPublishers.ofString(body)).
                build();

        try {
            HttpResponse<String> reponse=
                    httpClient.send(requete, HttpResponse.BodyHandlers.ofString());

            String loc=reponse.headers().firstValue("Location").get();

            return loc;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String ajoutFilm(String titre, String real, List<String> acteurs, String resume, String annee) {
        String body= null;
        FilmDtoOut film=new FilmDtoOut(titre,real,acteurs,resume,annee);
        try {
            body = objectMapper.writeValueAsString(film);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        HttpRequest requete= HttpRequest.newBuilder().
                uri(URI.create(URI_SERVICE+FILM)).
                setHeader("Content-Type","application/json").
                POST(HttpRequest.BodyPublishers.ofString(body)).
                build();

        try {
            HttpResponse<String> reponse=
                    httpClient.send(requete, HttpResponse.BodyHandlers.ofString());

            String loc=reponse.headers().firstValue("Location").get();

            return loc;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;    }

    @Override
    public String créerSondage(int idUtil, String nomSondage) {
        String body = "monsondage";

        HttpRequest requete = HttpRequest.newBuilder().
                uri(URI.create(URI_SERVICE + SONDAGE)).
                setHeader("idUtil", "" + idUtil).
                POST(HttpRequest.BodyPublishers.ofString(body)).
                build();

        try {
            HttpResponse<String> reponse =
                    httpClient.send(requete, HttpResponse.BodyHandlers.ofString());

            String loc = reponse.headers().firstValue("Location").get();

            return loc;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public SondageDto ajoutFilmASondage(int idUtil, String nomSondage, String idFilm) {
        String body = idFilm;

        HttpRequest requete = HttpRequest.newBuilder().
                uri(URI.create(URI_SERVICE + SONDAGE+"/" + nomSondage+FILM)).
                setHeader("idUtil", "" + idUtil).
                POST(HttpRequest.BodyPublishers.ofString(body)).
                build();

        try {
            HttpResponse<String> reponse =
                    httpClient.send(requete, HttpResponse.BodyHandlers.ofString());


            return objectMapper.readValue(reponse.body(),
                    objectMapper.getTypeFactory().constructType(SondageDto.class));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}
