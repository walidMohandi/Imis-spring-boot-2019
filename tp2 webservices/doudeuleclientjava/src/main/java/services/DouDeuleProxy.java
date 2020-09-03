package services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.DTO.CreneauDTO;
import services.DTO.RdvDTO;
import services.DTO.UtilDTO;
import services.DTO.UtilisateurDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;


public class DouDeuleProxy implements DouDeuleInterface {
    private static final String URI_SERVICE="http://localhost:8080/doudeule";

    private static final String RDV= "/rdv";
    private static final String UTILISATEUR = "/utilisateur";
    private static final String CRENEAU = "/creneau";

    private HttpClient httpClient = HttpClient.newHttpClient();





    private ObjectMapper objectMapper = new ObjectMapper();



    @Override
    public List<RdvDTO> getAllRdv() {

        HttpRequest requete=HttpRequest.newBuilder()
                .uri(URI.create(URI_SERVICE.concat(RDV)))
                .setHeader("Accept","application/json")
                .GET()
                .build();

        HttpResponse response;
        try {
            response = httpClient.send(requete, HttpResponse.BodyHandlers.ofString());

            return objectMapper.readValue((JsonParser) response.body(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class,RdvDTO.class));




        }
        catch (IOException | InterruptedException e) {

        }








        return null;
    }

    @Override
    public int createRdv(RdvDTO rdv) {
        throw new UnexpectedError();
    }

    @Override
    public RdvDTO getRdv(int id) {
        return null;
    }

    @Override
    public int ajoutCreneau(int id, CreneauDTO c) {
        return 0;
    }

    @Override
    public List<CreneauDTO> getCreneaux(int id) {
        return null;
    }

    @Override
    public void ajoutDispo(int idR, int idCreneau, UtilDTO udto) {

    }

    @Override
    public List<UtilisateurDTO> getAllUtils() {
        return null;
    }

    @Override
    public int createUtil(UtilisateurDTO util)  {
        String body = "";
        try{
           body=objectMapper.writeValueAsString(util);
        }catch(JsonProcessingException e){
           e.printStackTrace();
        }

        HttpRequest requete= HttpRequest.newBuilder()
                .uri(URI.create(URI_SERVICE.concat(UTILISATEUR)))
                .setHeader("Content-Type","application/json")
                //.POST(HttpRequest.BodyPublishers.ofString("{'prenom':'"+util.getPrenom()+"\','nom':'"+util.getNom()+"\'}"))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(requete, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status: " + response.statusCode());
            System.out.println("Headers: " + response.headers());
            System.out.println("Body: " + response.body());

            String loc=response.headers().firstValue("Location").get();

            System.out.println(loc);
            //return objectMapper.readValue((JsonParser) response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class,UtilisateurDTO.class));


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return 0;
    }


    @Override
    public Collection<UtilisateurDTO> getUtilisateursDisponiblesPour(int idRdv, int idCreneau) {
        return null;
    }


}
