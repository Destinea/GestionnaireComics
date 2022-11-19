package API;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'effctuer des recherches sur ComicVine.
 * @author Cyril
 */
public class api_connection {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String API_KEY = "53b33e3da09b63c64d3a69667f455e9076055dcc";

    /**
     * Renvoie le JSON lié à l'entrée passée en paramètre.
     * @param recherche : Information que l'on recherche.
     * @param numPage : numéro de la page que l'on recherche (10 recherche affichées par page.
     * @return Resultat de la requête sous format json.
     * @author Cyril
     */
    private String getJsonResults(String recherche, int numPage) throws IOException {
        String requeteURL = "https://comicvine.gamespot.com/api/search/?query=" + recherche + "&page="+numPage+"&format=json&api_key=" + API_KEY;
        String reponse;

        URL obj = new URL(requeteURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
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
     * @param recherche : Information que l'on recherche
     * @param numPage : numéro de la page que l'on recherche (10 recherche affichées par page).
     * @return Liste des résultats obtenus.
     * @author Cyril
     */
    public List<Results> GetResults(String recherche, int numPage) throws IOException {
        List<Results> results = new ArrayList<Results>();

        String jsonString = getJsonResults(recherche,numPage);
        JSONObject obj = new JSONObject(jsonString);
        JSONArray JSONresults = obj.getJSONArray("results");
        for(int i=0;i<JSONresults.length();i++){
            String name = JSONresults.getJSONObject(i).getString("name");
            String shortDescription = JSONresults.getJSONObject(i).get("deck").toString();
            String type =JSONresults.getJSONObject(i).getString("resource_type");
            String publisher = JSONresults.getJSONObject(i).getJSONObject("publisher").getString("name");
            int id = JSONresults.getJSONObject(i).getInt("id");
            String iconLink = JSONresults.getJSONObject(i).getJSONObject("image").getString("icon_url");
            results.add(new Results(name,shortDescription,type,publisher,id, iconLink));
        }
        return results;
    }



    /* Exemple d'utilisation de la classe :
    public static void main(String[] args) throws IOException {
        api_connection test = new api_connection();
        List<Results> ResultatsRecherche = test.GetResults("wolverine",1);


        for (Results results : ResultatsRecherche) {
            System.out.println(results.getName() + "\n");
            System.out.println(results.getPublisher() + "\n");
            System.out.println(results.getShortDescription() + "\n");
            System.out.println(results.getType() + "\n");
            System.out.println("\n");
        }
    }
     */

}
