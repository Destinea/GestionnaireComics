package Collec;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import API.Comic;
import API.Serie;
import API.api_connection;

public class User_serie extends Serie{
	private final ArrayList<Comic_Collec> user_serie;
	private final ArrayList<Serie> series;
	private int total;
	public User_serie(Serie serie) {
		super(serie);
		this.total=serie.getNumberOfComics();
		this.user_serie = new ArrayList<Comic_Collec>();
		this.series= new ArrayList<Serie>();
		series.add(serie);
	}
	
	public void sort() {
		Collections.sort(user_serie);
	}
	
	public ArrayList<MissingComic> getSerieMissingComics() {
		ArrayList<MissingComic> m_c= new ArrayList<>();
		for (int i = 0; i < series.size(); i++) {
			for(int j = 0; j < series.get(i).getNumberOfComics(); i++) {
				//try catch
				if (m_c.size()==3) {
					return m_c;
				}
			}
		}
		return m_c;
	}
	
	public int getNumberOfComics() {
		return total;
	}
	public void changeSerieComicStatus(Comic c,int status) {
		if (status==0) {
			try {
				user_serie.remove(c);
				this.sort();
			} catch (Exception e) {
				System.out.println("Impossible de supprimer:"+c.getName());
			}
			
		}else {
			boolean find=false;
			for (Comic_Collec comic_Collec : user_serie) {//Recherche dans les comics possédés
				if (comic_Collec.getId()==c.getId()) {
					find=true;
					comic_Collec.setEtat(status);
				}
			}
			if (!find) {
				//Ajout à la série
				user_serie.add(new Comic_Collec(c, status));
				//Si l'id de la serie n'est pas déja ajouté alors on le met a jour
				//et on modifie le nb total de comics dans la série
				if (searchSerieID(c)==null) {
					api_connection apiConnection = new api_connection();
					try {
						Serie s=apiConnection.getSerie(c.getSerieId());
						this.total+=s.getNumberOfComics();
						this.series.add(s);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				}
				this.sort();
			}
		}	
	}
	public Integer searchSerieID(Comic c) {
		for (Iterator<Serie> iterator = series.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next().getId();
			if (c.getSerieId()==integer) {
				return integer;
			}
		}
		return null;
	}
	
	public ArrayList<Comic_Collec> getUserSerie() {
		return this.user_serie;
	}
	
	
}
