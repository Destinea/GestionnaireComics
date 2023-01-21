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
import java.util.Random;

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
            if (!filtre.equals("Tout"))
            {
                switch (filtre) {
                    case "Personnages":
                        type = "Personnage";
                        break;
                    case "Séries":
                        type = "Volume";
                        break;
                    case "Comic":
                        type = "Comic";
                        break;
                    default:
                        type = JSONresults.getJSONObject(i).getString("resource_type");
                        break;
                }
            }
            else
                type = switch (JSONresults.getJSONObject(i).getString("resource_type")) {
                    case "character" -> "Personnage";
                    case "volume" -> "Série";
                    case "issue" -> "Comic";
                    default -> (JSONresults.getJSONObject(i).getString("resource_type"));
            };
            if(type=="Comic"&&name=="null"){
                name = JSONresults.getJSONObject(i).getJSONObject("volume").getString("name").toString() +
                        " - n°"+JSONresults.getJSONObject(i).get("issue_number").toString();
            }
            int id = JSONresults.getJSONObject(i).getInt("id");
            String iconLink = JSONresults.getJSONObject(i).getJSONObject("image").getString("icon_url");
            String bigIconLink = JSONresults.getJSONObject(i).getJSONObject("image").getString("super_url");
            String HTMLDescription = JSONresults.getJSONObject(i).get("description").toString();
            results.add(new Results(name,shortDescription,type,id, iconLink, HTMLDescription, bigIconLink));
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
        String bigIconLink = obj.getJSONObject("image").getString("super_url");
        String SerieName = obj.getJSONObject("volume").get("name").toString();
        int SerieId = obj.getJSONObject("volume").getInt("id");
        double number;
        if(name=="null"){
            name = obj.getJSONObject("volume").getString("name").toString() +
                    " - n°"+obj.get("issue_number").toString();
        }
        try{
            number = obj.getDouble("issue_number");
        }catch(Exception e){
            number = -1;
        }
        String HTMLDescription = obj.get("description").toString();
        Results res= new Results(name, shortDescription, "Comic", id, iconLink,HTMLDescription, bigIconLink);
        return new Comic( res,SerieName,SerieId,number);
    }

    public Character getCharacter(int id) throws IOException {
        String requeteURL = "https://comicvine.gamespot.com/api/characters/?filter=id:" + id +"&format=json&api_key=" + API_KEY;
        String jsonString = sendRequest(requeteURL);
        JSONObject obj = new JSONObject(jsonString).getJSONArray("results").getJSONObject(0);

        String name = obj.get("name").toString();
        String shortDescription = obj.get("deck").toString();
        String iconLink = obj.getJSONObject("image").getString("icon_url");
        String bigIconLink = obj.getJSONObject("image").getString("super_url");
        int appearances = obj.getInt("count_of_issue_appearances");
        int firstComicID = obj.getJSONObject("first_appeared_in_issue").getInt("id");
        String firstComicName = obj.getJSONObject("first_appeared_in_issue").get("name").toString();
        int gender = obj.getInt("gender");
        String realName = obj.get("real_name").toString();
        String HTMLDescription = obj.get("description").toString();


        return new Character(name,shortDescription,"Personnage",id,iconLink,appearances,firstComicID,firstComicName,gender, realName,HTMLDescription,bigIconLink);
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
            String bigIconLink = JSONComics.getJSONObject(i).getJSONObject("image").getString("super_url");
            String SerieName = JSONComics.getJSONObject(i).getJSONObject("volume").getString("name");
            int SerieId = JSONComics.getJSONObject(i).getJSONObject("volume").getInt("id");
            double number = JSONComics.getJSONObject(i).getDouble("issue_number");
            if(name=="null"){
                name = JSONComics.getJSONObject(i).getJSONObject("volume").getString("name").toString() +
                        " - n°"+JSONComics.getJSONObject(i).get("issue_number").toString();
            }
            String HTMLDescription = JSONComics.getJSONObject(i).get("description").toString();
            Results res= new Results(name, shortDescription, "Comic", id, iconLink,HTMLDescription, bigIconLink);
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
        String bigIconLink = obj.getJSONObject("image").getString("super_url");
        int numberOfComics = obj.getInt("count_of_issues");
        int startYear = obj.getInt("start_year");
        String HTMLDescription = obj.get("description").toString();
        int lastComicID = obj.getJSONObject("last_issue").getInt("id");
        String lastComicName = obj.getJSONObject("last_issue").get("name").toString();
        int firstComicID = obj.getJSONObject("first_issue").getInt("id");
        String firstComicName = obj.getJSONObject("first_issue").get("name").toString();

        Results res= new Results(name, shortDescription, "volume", id, iconLink,HTMLDescription, bigIconLink);
        return new Serie(res, numberOfComics, startYear,lastComicID,firstComicID,lastComicName,firstComicName);
    }

    public List<Comic> getRandomComics() throws IOException{
        List<Comic> randomComics = new ArrayList<>();
        String requeteMaxId = "https://comicvine.gamespot.com/api/issues/?sort=id:desc&format=json&limit=1&api_key=53b33e3da09b63c64d3a69667f455e9076055dcc";
        JSONObject objMaxId = new JSONObject(sendRequest(requeteMaxId));
        final int ID_MAX = objMaxId.getJSONArray("results").getJSONObject(0).getInt("id");
        for(int i=0;i<3;i++){
            Random rand = new Random();
            int randomId = rand.nextInt(ID_MAX + 1);
            try{
                randomComics.add(getComic(randomId));
            }catch (Exception e) {
                i--;
            }

        }
        return randomComics;
    }

    public List<Serie> getSeries(List<Integer> listID) throws IOException {
        List<Serie> serieList = new ArrayList<>();
        StringBuilder requeteURL = new StringBuilder("https://comicvine.gamespot.com/api/volumes/?filter=id:");
        for(int i = 0; i< listID.size()-1;i++){
            requeteURL.append(listID.get(i)).append("|");
        }
        requeteURL.append(listID.get(listID.size()-1)).append("&format=json&api_key=").append(API_KEY);

        String jsonString = sendRequest(requeteURL.toString());

        JSONObject obj = new JSONObject(jsonString);
        JSONArray JSONresults = obj.getJSONArray("results");
        for(int i=0;i<JSONresults.length();i++) {
            String name = JSONresults.getJSONObject(i).get("name").toString();
            String shortDescription = JSONresults.getJSONObject(i).get("deck").toString();
            int id = JSONresults.getJSONObject(i).getInt("id");
            String iconLink = JSONresults.getJSONObject(i).getJSONObject("image").getString("icon_url");
            String bigIconLink = JSONresults.getJSONObject(i).getJSONObject("image").getString("super_url");
            String HTMLDescription = JSONresults.getJSONObject(i).get("description").toString();

            int numberOfComics = JSONresults.getJSONObject(i).getInt("count_of_issues");
            int startYear = JSONresults.getJSONObject(i).getInt("start_year");
            int lastComicID = JSONresults.getJSONObject(i).getJSONObject("last_issue").getInt("id");
            String lastComicName = JSONresults.getJSONObject(i).getJSONObject("last_issue").get("name").toString();
            int firstComicID = JSONresults.getJSONObject(i).getJSONObject("first_issue").getInt("id");
            String firstComicName = JSONresults.getJSONObject(i).getJSONObject("first_issue").get("name").toString();

            serieList.add(new Serie(new Results(name, shortDescription,"volume",id,iconLink,bigIconLink,HTMLDescription)
                    ,numberOfComics, startYear,lastComicID,firstComicID,lastComicName,firstComicName));
        }
        return serieList;
    }
    
    //Exemple d'utilisation de la classe :
    /*
    public static void main(String[] args) throws IOException {
        api_connection test = new api_connection();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(69993);
        integerList.add(126840);
        List<Serie> serieList = test.getSeries(integerList);

        for (Serie serie : serieList) {
            System.out.println(serie.getName());
            System.out.println(serie.getId());
            System.out.println(serie.getIconLink());
            System.out.println(serie.getNumberOfComics());
            System.out.println(serie.getFirstComicName());
            System.out.println(serie.getLastComicName());
            System.out.println("\n");
        }
    }
     */



}
