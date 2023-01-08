package Collec;


import java.util.Iterator;

import API.Comic;
import API.Serie;

public class User_serie extends Serie{
	private final Comic[] full_serie;
	private final Integer[] user_serie;
	
	public User_serie(Comic[] full_serie,String name, String shortDescription, String type, int id, String iconLink, int numberOfComics, int startYear, String HTMLDescription, int lastComicID, int firstComicID, String lastComicName, String firstComicName) {
		super(name, shortDescription, type, id, iconLink, numberOfComics, startYear, HTMLDescription, lastComicID, firstComicID, lastComicName, firstComicName);
		this.full_serie =full_serie;
		this.user_serie=new Integer[full_serie.length];
		for (int i = 0; i < full_serie.length; i++) {
			user_serie[i]=0;			
		}
	}
	
	public Comic[] getUserComics() {
		int nbcomics=0;
		for (int i = 0; i < this.user_serie.length; i++) {
			if (this.user_serie[i]>0) {
				nbcomics++;
			}
			
		}
		
		Comic[] user_comics= new Comic[nbcomics];
		for (int i = 0; i < this.user_serie.length; i++) {
			if (this.user_serie[i]>0) {
				user_comics[i]=this.full_serie[i];
			}
					
		}
		return user_comics;
	}
	
	public void addUserComic(Comic c,int status) {
		for (int i = 0; i < this.full_serie.length; i++) {
			if (c==this.full_serie[i]) {
				this.user_serie[i]=status;
				break;
			}	
		}
		
	}
	
	public int searchComic(Comic c) {
		for (int i = 0; i < this.full_serie.length; i++) {
			if (c==this.full_serie[i]) {
				return this.user_serie[i];
			}
		}
		return -1;
	}
	public Integer[] getUserSerie() {
		return this.user_serie;
	}
	public Comic[] getFullSerie() {
		return this.full_serie;
		
	}
	
	
}
