package API;

public class Serie extends Results{
    private int NumberOfComics;
    private int StartYear;
    private String HTMLDescription;
    private int LastComicID;
    private int FirstComicID;
    private String LastComicName;
    private String FirstComicName;

    public Serie(String name, String shortDescription, String type, String publisher, int id, String iconLink, int numberOfComics, int startYear, String HTMLDescription, int lastComicID, int firstComicID, String lastComicName, String firstComicName) {
        super(name, shortDescription, type, publisher, id, iconLink);
        NumberOfComics = numberOfComics;
        StartYear = startYear;
        this.HTMLDescription = HTMLDescription;
        LastComicID = lastComicID;
        FirstComicID = firstComicID;
        LastComicName = lastComicName;
        FirstComicName = firstComicName;
    }

    public int getNumberOfComics() {
        return NumberOfComics;
    }

    public int getStartYear() {
        return StartYear;
    }

    public String getHTMLDescription() {
        return HTMLDescription;
    }

    public int getLastComicID() {
        return LastComicID;
    }

    public int getFirstComicID() {
        return FirstComicID;
    }

    public String getLastComicName() {
        return LastComicName;
    }

    public String getFirstComicName() {
        return FirstComicName;
    }
}
