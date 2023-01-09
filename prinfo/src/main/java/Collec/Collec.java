package Collec;

import java.io.IOException;
import java.util.*;

import API.Comic;
import API.api_connection;
public class Collec {
	
	private final HashSet<Comic_Collec> comics;
	private final HashSet<User_serie> series;
	private final HashSet<Comic_Collec> listeAjout;
	private final ArrayList<Integer> listeSupp;
	
	public Collec() {
		this.comics= new HashSet<Comic_Collec>();
		this.series= new HashSet<User_serie>();
		this.listeAjout = new HashSet<Comic_Collec>(); // Récupère les infos des comics à ajouter à la BDD
		this.listeSupp = new ArrayList<Integer>(); // Récupère les id des comics à supprimer de la BDD
	}
	public void changeComicStatus(Comic c,int etat) {
		//Ajout aux series
		for (Iterator<User_serie> iterator = series.iterator(); iterator.hasNext();) {
			User_serie serie = (User_serie) iterator.next();
			if (serie.getId()==c.getSerieId()) {serie.changeComicStatus(c, etat);}
		}
		//Creation nouvelle série
		api_connection apiConnection = new api_connection();
		try {
			User_serie new_serie=new User_serie( apiConnection.getSerie(c.getSerieId()));
			new_serie.changeComicStatus(c, etat);
			series.add(new_serie);
		} catch (IOException e) {
			System.out.println("Impossible d'ajouter la serie "+c.getSerieName());
		}
		//Ajout aux comics
		for (Iterator<Comic_Collec> iterator = comics.iterator(); iterator.hasNext();) {
			Comic_Collec comic_Collec = (Comic_Collec) iterator.next();
			if (c.getId()==comic_Collec.getId()) {
				if(etat>0) {
					comic_Collec.setEtat(etat);
				}
				else {
					comics.remove(comic_Collec);
				}
			}
		}
	}
	
	public List<Integer> getlisteSupp()
	{
		return listeSupp;
	}

	public void addlisteSupp(int id)
	{
		this.listeSupp.add(id);
	}

	public HashSet<Comic_Collec> getlisteAjout()
	{
		return listeAjout;
	}

	public void addlisteAjout(Comic_Collec comic)
	{
		this.listeAjout.add(comic);
	}

	public Set<Comic_Collec> getComics() {
		return comics;
	}
	public void addComic(Comic_Collec comic) {
		this.comics.add(comic);
	}

	public void rmComic(Comic_Collec rm_comic) {
		this.comics.removeIf(comic -> comic.getId() == rm_comic.getId());
	}

	public Set<User_serie> getSeries() {
		return series;
	}
	public void addSerie(User_serie serie) {
		this.series.add(serie);
	}
	public Comic_Collec searchComic(int comic_id) {
		for (Iterator<Comic_Collec> iterator = this.comics.iterator(); iterator.hasNext();) {
			Comic_Collec c = iterator.next();
			if (c.getId()==comic_id) {
				return c;
			}
		}
		return null ;
	}

	public User_serie searchSerie(int serie_id) {
		for (Iterator<User_serie> iterator = this.series.iterator(); iterator.hasNext();) {
			User_serie s = iterator.next();
			if (s.getId()==serie_id) {
				return s;
			}
		}
		return null;
	}
	
}
