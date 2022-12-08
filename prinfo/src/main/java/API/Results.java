package API;

/**
 * Classe permettant d'instancier le résultat d'une recherche
 * @author Cyril
 */
public class Results {
    private String name;
    private String shortDescription;
    private String type;
    private int id;
    private String iconLink;

    private String HTMLDescription;


    public Results(String name, String shortDescription, String type, int id, String iconLink, String htmlDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.type = type;
        this.id = id;
        this.iconLink = iconLink;
        HTMLDescription = htmlDescription;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getIconLink() {
        return iconLink;
    }

    public String getHTMLDescription() {
        return HTMLDescription;
    }
}
