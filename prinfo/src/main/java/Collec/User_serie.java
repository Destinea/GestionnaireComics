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
	
	public void changeSerieComicStatus(Comic c,int status) {
		if (status==0) {
			try {
				user_serie.remove(c);
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
			}
		}	
	}
	
	public HashSet<Comic_Collec> getUserSerie() {
		return this.user_serie;
	}
	
	
}
