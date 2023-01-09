package Collec;


import java.util.HashSet;

import API.Comic;
import API.Serie;

public class User_serie extends Serie{
	private final HashSet<Comic_Collec> user_serie;
	
	public User_serie(Serie serie) {
		super(serie);
		this.user_serie = new HashSet<Comic_Collec>();		
	}
	
	public void changeComicStatus(Comic c,int status) {
		for (Comic_Collec comic_Collec : user_serie) {//Recherche dans les comics possédés
			if (comic_Collec.getId()==c.getId()) {
				if (status>0) {
				comic_Collec.setEtat(status);
				}
				else {
					user_serie.remove(comic_Collec);//Supprimer le comic
				}
			}
		}
		//Ajout à la série
		user_serie.add(new Comic_Collec(c, status));
	}
	
	public Comic_Collec searchComic(Comic_Collec c) {
		for (Comic_Collec comic_Collec : user_serie) {
			if (comic_Collec.equals(c)) {
				return c;
			}
		}
		return null;
	}
	public HashSet<Comic_Collec> getUserSerie() {
		return this.user_serie;
	}
	
	
}
