package API;

public class Comic extends Results{
    private String SerieName;
    private int SerieId;
    private int number;
    private String HTMLDescription;

    public Comic(String name, String shortDescription, String type, int id, String iconLink, String serieName, int serieId, int number, String HTMLDescription) {
        super(name, shortDescription, type, id, iconLink);
        SerieName = serieName;
        SerieId = serieId;
        this.number = number;
        this.HTMLDescription = HTMLDescription;
    }

    public String getSerieName() {
        return SerieName;
    }

    public int getSerieId() {
        return SerieId;
    }

    public int getNumber() {
        return number;
    }

    public String getHTMLDescription() {
        return HTMLDescription;
    }
}
