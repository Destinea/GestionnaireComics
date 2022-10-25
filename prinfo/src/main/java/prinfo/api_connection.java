package prinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class api_connection {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_KEY = "53b33e3da09b63c64d3a69667f455e9076055dcc";

    /**
     * Permet de rechercher des infos sur la bdd comicvine.
     * @param recherche L'info que l'on recherche.
     * @return Resultat de la requête sous format xml.
     * @author Cyril
     * @throws IOException
     */
    public String Recherche_API(String recherche) throws IOException {
        //TODO Ajouter la clé API dans un header pas dans la requête
        String requeteURL = "https://comicvine.gamespot.com/api/search/?query=" + recherche + "&api_key=" + API_KEY;
        String reponse;

        URL obj = new URL(requeteURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        reponse = response.toString();

        return reponse;
    }
    /*
    public static void main(String[] args) throws IOException {
        api_connection test = new api_connection();
        String resultat = test.Recherche_API("wolverine");
        System.out.println(resultat);
    }
    */
}
