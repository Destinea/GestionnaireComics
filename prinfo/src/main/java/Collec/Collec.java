package Collec;

import java.util.*;

import API.Comic;
import API.Serie;
public class Collec {
	
	private final HashSet<Comic_Collec> comics;
	private final HashSet<User_serie> series;
	private final HashSet<Comic_Collec> listeAjout;
	private final List<Integer> listeSupp;
	
	public Collec() {
		this.comics= new HashSet<Comic_Collec>();
		this.series= new HashSet<User_serie>();
		listeAjout = new HashSet<Comic_Collec>(); // Récupère les infos des comics à ajouter à la BDD
		listeSupp = new ArrayList<>(); // Récupère les id des comics à supprimer de la BDD
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
	public void addSerie(User_serie series) {
		this.series.add(series);
	}
	public Comic_Collec searchComic(Comic comic) {
		for (Iterator<Comic_Collec> iterator = this.comics.iterator(); iterator.hasNext();) {
			Comic_Collec c = iterator.next();
			if (c.getId()==comic.getId()) {
				return c;
			}
		}
		return null ;
	}

	public int searchSerie(Serie serie) {
		for (Iterator<User_serie> iterator = this.series.iterator(); iterator.hasNext();) {
			User_serie s = iterator.next();
			if (s.getId()==serie.getId()) {
				return 1;
			}
		}
		return 0;
	}
	
}
