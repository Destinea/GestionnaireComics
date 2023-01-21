package API;

public class Comic extends Results{
    private String SerieName;
    private int SerieId;
    private double number;// Utilisation d'un double car il existe des comics n°4.5 par exemple


    public Comic(Results res, String serieName, int serieId, double number) {
        super(res);
        this.SerieName = serieName;
        this.SerieId = serieId;
        this.number = number;
    }
    public Comic(Comic comic) {
    	super(comic.getResultVersion());
    	this.SerieId=comic.getSerieId();
    	this.SerieName=comic.getSerieName();
    	this.number=comic.getNumber();
    }

    public Comic(String name, int id, String iconLink, String serieName, int serieId,int number) {
    	super(name,"NULL","issue",id,iconLink,"NULL", "NULL");
    	this.SerieName = serieName;
        this.SerieId = serieId;
        this.number = number;
	}
    public Comic getComicVersion() {
    	return this;
    }
	public String getSerieName() {
        return this.SerieName;
    }

    public int getSerieId() {
        return this.SerieId;
    }

    public double getNumber() {
        return this.number;
    }


}
