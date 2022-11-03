package prinfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class api_connection {
    //TODO : Gérer l'accès aux différentes pages (pour l'instant on récupère que la première page de résultats)
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_KEY = "53b33e3da09b63c64d3a69667f455e9076055dcc";

    /**
     * Renvoie tout les resultats liés à l'entrée passée en paramètre.
     * @param recherche L'information que l'on recherche.
     * @return Resultat de la requête sous format json.
     * @author Cyril
     */
    private String getJsonResults(String recherche) throws IOException {
        String requeteURL = "https://comicvine.gamespot.com/api/search/?query=" + recherche + "&format=json&api_key=" + API_KEY;
        String reponse;

        URL objetRequeteURL = new URL(requeteURL);
        HttpURLConnection connexionURL = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        reponse = response.toString();

        return reponse;
    }

    /**
     * Instancie les résultats de la recherche.
     * @param recherche l'information que l'on recherche
     * @return Un tableau contenant les objets associés à la requête
     * @author Cyril
     */
    public JSONArray GetResults(String recherche) throws IOException {
        String jsonString = getJsonResults(recherche);
        JSONObject objetJSON = new JSONObject(jsonString);
        return obj.getJSONArray("results");
    }


    /* Pour tester
    public static void main(String[] args) throws IOException {
        api_connection test = new api_connection();
        JSONArray results = test.GetResults("wolverine");
    }
    */

}
