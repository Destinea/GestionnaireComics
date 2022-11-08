package API;

/**
 * Classe permettant d'instancier le résultat d'une recherche
 * @author Cyril
 */
public class Results {
    private String name;
    private String shortDescription;
    private String type;
    private String publisher;
    private int id;
    private String iconLink;


    public Results(String name, String shortDescription, String type, String publisher, int id, String iconLink) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.type = type;
        this.publisher = publisher;
        this.id = id;
        this.iconLink = iconLink;
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

    public String getPublisher() {
        return publisher;
    }

    public int getId() {
        return id;
    }
}
