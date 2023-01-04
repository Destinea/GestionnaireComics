package Collec;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import API.Comic;
import API.Serie;
public class Collec {
	
	private HashSet<Comic> comics;
	private HashSet<User_serie> series;
	
	public Collec() {
		this.comics= new HashSet<Comic>();
		this.series= new HashSet<User_serie>();
		
	}
	
	public Set<Comic> getComics() {
		return comics;
	}
	public void addComic(Comic comic) {
		this.comics.add(comic);
	}
	
	public Set<User_serie> getSeries() {
		return series;
	}
	public void addSerie(User_serie series) {
		this.series.add(series);
	}
	public int searchComic(Comic comic) {
		for (Iterator<Comic> iterator = this.comics.iterator(); iterator.hasNext();) {
			Comic c = (Comic) iterator.next();
			if (c==comic) {
				return 1;
			}
			
		}
			
		return 0;
	}

	public int searchSerie(Serie serie) {
		for (Iterator<User_serie> iterator = this.series.iterator(); iterator.hasNext();) {
			User_serie s = (User_serie) iterator.next();
			if (s.getId()==serie.getId()) {
				return 1;
			}
		}
		return 0;
	}
	
}
