package Collec;

public class MissingComic {
	private int serie_id;
	private int comic_nb;
	public MissingComic(int id_serie,int nb_com) {
		serie_id=id_serie;
		comic_nb=nb_com;
	}
	public int getSerieID() {
		return serie_id;
	}
	public int getNumero() {
		return comic_nb;
	}
}
