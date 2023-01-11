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

    private String sendRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    /**
     * Instancie les résultats de la recherche.
     * @param recherche : Information que l'on recherche
     * @param numPage : numéro de la page que l'on recherche (10 recherche affichées par page).
     * @return Liste des résultats obtenus.
     * @author Cyril
     */
    public List<Results> GetResults(String recherche, int numPage, String filtre) throws IOException {
        List<Results> results = new ArrayList<Results>();
        recherche =recherche.replace(" ","+");
        String requeteURL;
        switch (filtre) {
            case "Personnages":
                requeteURL = "https://comicvine.gamespot.com/api/search/?query=" + recherche + "&page=" + numPage +
                        "&format=json&api_key=" + API_KEY +"&resources=character";
                break;
            case "Séries":
                requeteURL = "https://comicvine.gamespot.com/api/search/?query=" + recherche + "&page=" + numPage +
                        "&format=json&api_key=" + API_KEY +"&resources=volume";
                break;
            case "Comic":
                requeteURL = "https://comicvine.gamespot.com/api/search/?query=" + recherche + "&page=" + numPage +
                        "&format=json&api_key=" + API_KEY +"&resources=issue";
                break;
            default:
                requeteURL = "https://comicvine.gamespot.com/api/search/?query=" + recherche + "&page=" + numPage +
                        "&format=json&api_key=" + API_KEY+"&resources=issue,volume,character";
                break;
        }

        String jsonString = sendRequest(requeteURL);

        JSONObject obj = new JSONObject(jsonString);
        JSONArray JSONresults = obj.getJSONArray("results");
        for(int i=0;i<JSONresults.length();i++){
            String name = JSONresults.getJSONObject(i).get("name").toString();
            String shortDescription = JSONresults.getJSONObject(i).get("deck").toString();

            String type;
            switch (filtre) {
                case "Personnages":
                    type = "character";
                    break;
                case "Séries":
                    type = "volume";
                    break;
                case "Comic":
                    type = "issue";
                    break;
                default:
                    type = JSONresults.getJSONObject(i).getString("resource_type");
                    break;
            }

            int id = JSONresults.getJSONObject(i).getInt("id");
            String iconLink = JSONresults.getJSONObject(i).getJSONObject("image").getString("icon_url");
            String HTMLDescription = JSONresults.getJSONObject(i).get("description").toString();
            results.add(new Results(name,shortDescription,type,id, iconLink, HTMLDescription));
        }
        return results;
    }

    public Comic getComic(int id) throws IOException {
        String requeteURL = "https://comicvine.gamespot.com/api/issues/?filter=id:" + id +"&format=json&api_key=" + API_KEY;
        String jsonString = sendRequest(requeteURL);
        JSONObject obj = new JSONObject(jsonString).getJSONArray("results").getJSONObject(0);

        String name = obj.get("name").toString();
        String shortDescription = obj.get("deck").toString();
        String iconLink = obj.getJSONObject("image").getString("icon_url");
        String SerieName = obj.getJSONObject("volume").getString("name");
        int SerieId = obj.getJSONObject("volume").getInt("id");
        double number = obj.getDouble("issue_number");
        String HTMLDescription = obj.get("description").toString();
        Results res= new Results(name, shortDescription, "issue", id, iconLink,HTMLDescription);
        return new Comic( res,SerieName,SerieId,number);
    }

    public Character getCharacter(int id) throws IOException {
        String requeteURL = "https://comicvine.gamespot.com/api/characters/?filter=id:" + id +"&format=json&api_key=" + API_KEY;
        String jsonString = sendRequest(requeteURL);
        JSONObject obj = new JSONObject(jsonString).getJSONArray("results").getJSONObject(0);

        String name = obj.get("name").toString();
        String shortDescription = obj.get("deck").toString();
        String iconLink = obj.getJSONObject("image").getString("icon_url");
        int appearances = obj.getInt("count_of_issue_appearances");
        int firstComicID = obj.getJSONObject("first_appeared_in_issue").getInt("id");
        String firstComicName = obj.getJSONObject("first_appeared_in_issue").get("name").toString();
        int gender = obj.getInt("gender");
        String realName = obj.get("real_name").toString();
        String HTMLDescription = obj.get("description").toString();


        return new Character(name,shortDescription,"character",id,iconLink,appearances,firstComicID,firstComicName,gender, realName,HTMLDescription);
    }

    /**
     * Retourne les 10 derniers comics mis en ligne
     * @return La liste des 10 derniers comics publiés
     * @throws IOException erreur de parsage
     */
    public List<Comic> getLastComics() throws IOException{
        List<Comic> lastComics = new ArrayList<>();
        String requeteURL = "https://comicvine.gamespot.com/api/issues/?sort=date_added:desc&format=json&limit=10&api_key=53b33e3da09b63c64d3a69667f455e9076055dcc";
        String jsonString = sendRequest(requeteURL);
        JSONObject obj = new JSONObject(jsonString);
        JSONArray JSONComics = obj.getJSONArray("results");
        for(int i=0;i<JSONComics.length();i++){
            String name = JSONComics.getJSONObject(i).get("name").toString();
            String shortDescription = JSONComics.getJSONObject(i).get("deck").toString();
            int id = JSONComics.getJSONObject(i).getInt("id");
            String iconLink = JSONComics.getJSONObject(i).getJSONObject("image").getString("icon_url");
            String SerieName = JSONComics.getJSONObject(i).getJSONObject("volume").getString("name");
            int SerieId = JSONComics.getJSONObject(i).getJSONObject("volume").getInt("id");
            double number = JSONComics.getJSONObject(i).getDouble("issue_number");
            String HTMLDescription = JSONComics.getJSONObject(i).get("description").toString();
            Results res= new Results(name, shortDescription, "issue", id, iconLink,HTMLDescription);
            Comic newComic = new Comic(res,SerieName,SerieId,number);
            lastComics.add(newComic);
        }
        return lastComics;
    }

    public Serie getSerie(int id) throws IOException{
        String requeteURL = "https://comicvine.gamespot.com/api/volumes/?filter=id:" + id +"&format=json&api_key=" + API_KEY;
        String jsonString = sendRequest(requeteURL);
        JSONObject obj = new JSONObject(jsonString).getJSONArray("results").getJSONObject(0);

        String name = obj.get("name").toString();
        String shortDescription = obj.get("deck").toString();
        String iconLink = obj.getJSONObject("image").getString("icon_url");
        int numberOfComics = obj.getInt("count_of_issues");
        int startYear = obj.getInt("start_year");
        String HTMLDescription = obj.get("description").toString();
        int lastComicID = obj.getJSONObject("last_issue").getInt("id");
        String lastComicName = obj.getJSONObject("last_issue").get("name").toString();
        int firstComicID = obj.getJSONObject("first_issue").getInt("id");
        String firstComicName = obj.getJSONObject("first_issue").get("name").toString();

        Results res= new Results(name, shortDescription, "volume", id, iconLink,HTMLDescription);
        return new Serie(res, numberOfComics, startYear,lastComicID,firstComicID,lastComicName,firstComicName);
    }

    
    //Exemple d'utilisation de la classe :
    public static void main(String[] args) throws IOException {
        api_connection test = new api_connection();

        // Recherche générale
        /*List<Comic> lastComics = test.getLastComics();

        for (Comic comics : lastComics) {
            System.out.println(comics.getName());
            System.out.println(comics.getId());
            System.out.println(comics.getIconLink());
            System.out.println(comics.getSerieName());
            System.out.println(comics.getSerieId());
            System.out.println(comics.getNumber());
            System.out.println("\n");
        }*/
    }
     


}
