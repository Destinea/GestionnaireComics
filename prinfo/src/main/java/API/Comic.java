package API;

public class Comic extends Results{
    private String SerieName;
    private int SerieId;
    private double number;// Utilisation d'un double car il existe des comics n°4.5 par exemple


    public Comic(String name, String shortDescription, String type, int id, String iconLink, String serieName, int serieId, double number, String HTMLDescription) {
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

    public double getNumber() {
        return number;
    }


}
