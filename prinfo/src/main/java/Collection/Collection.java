package Collection;

import java.util.Set;

import API.Comic;
import API.Serie;
public class Collection {
	
	private Set<Comic> comics;
	private Set<User_serie> series;
	
	public Collection() {
		this.comics= new Set();
		
		
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
		return 1;
	}
	public int searchSerie(Serie serie) {
		return 1;
	}
	
}
