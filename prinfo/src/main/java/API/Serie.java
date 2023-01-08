package API;

public class Serie extends Results{
    private final int NumberOfComics;
    private final int StartYear;

    private final int LastComicID;
    private final int FirstComicID;
    private final String LastComicName;
    private final String FirstComicName;


    public Serie(String name, String shortDescription, String type, int id, String iconLink, int numberOfComics, int startYear, String HTMLDescription, int lastComicID, int firstComicID, String lastComicName, String firstComicName) {
        super(name, shortDescription, type, id, iconLink, HTMLDescription);
        NumberOfComics = numberOfComics;
        StartYear = startYear;
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
