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

    private String getJsonComic(int id) throws IOException {
        String requeteURL = "https://comicvine.gamespot.com/api/issues/?filter=id:" + id +"&format=json&api_key=" + API_KEY;
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

    private String getJsonCharacter(int id) throws IOException {
        String requeteURL = "https://comicvine.gamespot.com/api/characters/?filter=id:" + id +"&format=json&api_key=" + API_KEY;
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

    private String getJsonSerie(int id) throws IOException {
        String requeteURL = "https://comicvine.gamespot.com/api/volumes/?filter=id:" + id +"&format=json&api_key=" + API_KEY;
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
            int id = JSONresults.getJSONObject(i).getInt("id");
            String iconLink = JSONresults.getJSONObject(i).getJSONObject("image").getString("icon_url");
            results.add(new Results(name,shortDescription,type,id, iconLink));
        }
        return results;
    }

    public Comic getComic(int id) throws IOException {
        String jsonString = getJsonComic(id);
        JSONObject obj = new JSONObject(jsonString).getJSONArray("results").getJSONObject(0);

        String name = obj.get("name").toString();
        String shortDescription = obj.get("deck").toString();
        String iconLink = obj.getJSONObject("image").getString("icon_url");
        String SerieName = obj.getJSONObject("volume").getString("name");
        int SerieId = obj.getJSONObject("volume").getInt("id");
        int number = obj.getInt("issue_number");
        String HTMLDescription = obj.getString("description");

        return new Comic(name,shortDescription,"issue",id,iconLink,SerieName,SerieId,number,HTMLDescription);
    }

    public Character getCharacter(int id) throws IOException {
        String jsonString = getJsonCharacter(id);
        JSONObject obj = new JSONObject(jsonString).getJSONArray("results").getJSONObject(0);

        String name = obj.get("name").toString();
        String shortDescription = obj.get("deck").toString();
        String iconLink = obj.getJSONObject("image").getString("icon_url");
        int appearances = obj.getInt("count_of_issue_appearances");
        int firstComicID = obj.getJSONObject("first_appeared_in_issue").getInt("id");
        String firstComicName = obj.getJSONObject("first_appeared_in_issue").get("name").toString();
        int gender = obj.getInt("gender");
        String realName = obj.getString("real_name");


        return new Character(name,shortDescription,"character",id,iconLink,appearances,firstComicID,firstComicName,gender, realName);
    }

    public Serie getSerie(int id) throws IOException{
        String jsonString = getJsonSerie(id);
        JSONObject obj = new JSONObject(jsonString).getJSONArray("results").getJSONObject(0);

        String name = obj.get("name").toString();
        String shortDescription = obj.get("deck").toString();
        String iconLink = obj.getJSONObject("image").getString("icon_url");
        int numberOfComics = obj.getInt("count_of_issues");
        int startYear = obj.getInt("start_year");
        String HTMLDescription = obj.getString("description");
        int lastComicID = obj.getJSONObject("last_issue").getInt("id");
        String lastComicName = obj.getJSONObject("last_issue").get("name").toString();
        int firstComicID = obj.getJSONObject("first_issue").getInt("id");
        String firstComicName = obj.getJSONObject("first_issue").get("name").toString();


        return new Serie(name,shortDescription,"character",id,iconLink, numberOfComics, startYear,HTMLDescription,
                lastComicID,firstComicID,lastComicName,firstComicName);
    }

    /*
    //Exemple d'utilisation de la classe :
    public static void main(String[] args) throws IOException {
        api_connection test = new api_connection();

        // Recherche générale
        List<Results> ResultatsRecherche = test.GetResults("wolverine",1);


        for (Results results : ResultatsRecherche) {
            System.out.println(results.getName() + "\n");
            System.out.println(results.getShortDescription() + "\n");
            System.out.println(results.getType() + "\n");
            System.out.println("\n");
        }

        // Recherche spécifique par id
        Character Wolverine = test.getCharacter(1440);
        System.out.println(Wolverine.getName() + " Vrai nom : "+Wolverine.getRealName()+ "\n"+
                "Nombre d'apparitions : " + Wolverine.getComicAppearances() + " Première appartion : " +
                Wolverine.getFirstComicAppearanceIssueName());

        Comic UnComic = test.getComic(Wolverine.getFirstComicAppearanceIssueID());
        System.out.println("Série : " + UnComic.getSerieName() + " numéro : " + UnComic.getNumber()
                +"\n" + UnComic.getHTMLDescription());

        Serie UneSerieDeComics = test.getSerie(UnComic.getSerieId());
        System.out.println(UneSerieDeComics.getName() + " " + UneSerieDeComics.getNumberOfComics() + " numéros, série débutée en "
                + UneSerieDeComics.getStartYear() +
                "\n" + "Premier numéro : " + UneSerieDeComics.getFirstComicName() + "\n" +
                "Dernier numéro : " + UneSerieDeComics.getLastComicName());

    }
     */


}
