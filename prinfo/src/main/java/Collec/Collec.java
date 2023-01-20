package Collec;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public void display() {
		System.out.println("Collection");
		for (Comic_Collec comic_Collec : comics) {
			System.out.println(comic_Collec.getName());
		}
		System.out.println("-------------");
	}
	public void changeComicStatus(Comic c,int etat) {
		User_serie find_serie = null;
		boolean find_comic= false;
		for (Iterator<User_serie> iterator = series.iterator(); iterator.hasNext();) {
			User_serie serie = (User_serie) iterator.next();
			if (serie.getId()==c.getSerieId()) {
				find_serie=serie;
				//modif series
				serie.changeSerieComicStatus(c, etat);
				
				
			}
		}
		//supp de la série
		if (find_serie!=null && find_serie.getUserSerie().isEmpty()) {//supp serie vide
			series.remove(find_serie);
		}
		if (etat==0) {//Supp du comic
			try {
				rmComic(c);
			} catch (Exception e) {
				System.out.println("Impossible de supprimer:"+c.getName());
			}
		}
		else {
			//modif comics
			for (Iterator<Comic_Collec> it = comics.iterator(); it.hasNext();) {
				Comic_Collec comic_Collec = (Comic_Collec) it.next();
				if (c.getId()==comic_Collec.getId()) {
					find_comic = true;
					comic_Collec.setEtat(etat);
				
				}
			}
			//Nouvelle serie
			
			if (find_serie==null)
			{
				api_connection apiConnection = new api_connection();
				try {
					User_serie new_serie=new User_serie(apiConnection.getSerie(c.getSerieId()));
					new_serie.changeSerieComicStatus(c, etat);
					addSerie(new_serie);
				} catch (IOException e) {
					System.out.println("Impossible d'ajouter la serie "+c.getSerieName());
				}
			}
			//Nouveau comic
			if (!find_comic) {
				addComic(new Comic_Collec(c, etat));
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

	public void rmComic(Comic rm_comic) {
		this.comics.removeIf(comic -> comic.getId() == rm_comic.getId());
	}
	
	public HashSet<User_serie> getSeries() {
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
