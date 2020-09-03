package application;

import services.Sondages;
import services.SondagesProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ClientApp {
    public static void main(String[] args) {
        Sondages proxy=new SondagesProxy();

        // création d'un utilisateur
        String loc=proxy.ajoutUtil("Dugenou","Robert","bob");
        System.out.println(loc);
        // récupération de son id
        String[] locsplit=loc.split("/");
        int idUtil=Integer.parseInt(locsplit[locsplit.length-1]);
        System.out.println("idutil : "+idUtil);

        // ajout d'un film
        List<String> acteurs=new ArrayList<>();
        acteurs.add("Jim Carrey");
        acteurs.add("Kate Winslet");
        loc=proxy.ajoutFilm("Eternal Sunshine of the spotless mind","Michel Gondry",acteurs,"Erase and Rewind","2004");
        System.out.println(loc);
        // récupération de son id
        locsplit=loc.split("/");
        String idFilm=locsplit[locsplit.length-1];
        System.out.println("idfilm : "+idUtil);

        // creation d'un sondage
        System.out.println(proxy.créerSondage(idUtil,"monsondage"));

        // ajout du film au sondage

        System.out.println(proxy.ajoutFilmASondage(idUtil,"monsondage",idFilm));
    }
}
