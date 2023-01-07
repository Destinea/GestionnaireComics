package API;

public class Comic extends Results{
    private final String SerieName;
    private final int SerieId;
    private final int number;


    public Comic(String name, String shortDescription, String type, int id, String iconLink, String serieName, int serieId, int number, String HTMLDescription) {
        super(name, shortDescription, type, id, iconLink, HTMLDescription);
        SerieName = serieName;
        SerieId = serieId;
        this.number = number;
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


}
