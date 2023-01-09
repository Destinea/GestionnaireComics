package API;

public class Serie extends Results{
    private final int NumberOfComics;
    private final int StartYear;

    private final int LastComicID;
    private final int FirstComicID;
    private final String LastComicName;
    private final String FirstComicName;


    public Serie(Results res, int numberOfComics, int startYear, int lastComicID, int firstComicID, String lastComicName, String firstComicName) {
        super(res);
        NumberOfComics = numberOfComics;
        StartYear = startYear;
        LastComicID = lastComicID;
        FirstComicID = firstComicID;
        LastComicName = lastComicName;
        FirstComicName = firstComicName;
    }
    public Serie(Serie serie) {
    	super(serie.getResultVersion());
    	NumberOfComics=serie.getNumberOfComics();
        StartYear=serie.getStartYear();
        LastComicID=serie.getLastComicID();
        FirstComicID=serie.getFirstComicID();
        LastComicName=serie.getLastComicName();
        FirstComicName=serie.getFirstComicName();
    }

    public int getNumberOfComics() {
        return this.NumberOfComics;
    }

    public int getStartYear() {
        return this.StartYear;
    }

    public int getLastComicID() {
        return this.LastComicID;
    }

    public int getFirstComicID() {
        return this.FirstComicID;
    }

    public String getLastComicName() {
        return this.LastComicName;
    }

    public String getFirstComicName() {
        return this.FirstComicName;
    }
}
