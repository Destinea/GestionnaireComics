package Collec;


import API.Comic;
import API.Serie;
import API.api_connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author alexi
 * classe implementant les comics d'une meme serie possédes
 * par l'utilisateur
 * 
 */
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
	/**
	 * tri de la serie par numero de comic
	 * */
	public void sort() {
		Collections.sort(this.user_serie);
	}
	
	/**
	 * Supprime le comic de la serie
	 * @param comic
	 */
	public void rmSerieComic(Comic rm_comic) {
		this.user_serie.removeIf(comic -> comic.getId() == rm_comic.getId());
	}
	
	/**
	 * @return maximum 3 comics manquants a la serie
	 */
	public ArrayList<Comic> getSerieMissingComics() {
		api_connection con=new api_connection();
		ArrayList<Comic> m_c= new ArrayList<>();
		int echecs=0;
		for (int i = 0; i < series.size(); i++) {
			for(int j = 0; j < series.get(i).getNumberOfComics(); j++) {
				if (!searchComicNb(series.get(i).getId(), j)) {
					try {
						//System.out.println("essai de recuperation du numéro:"+j);
						Comic c= con.getComic(j, series.get(i).getId());
						m_c.add(c);
						//System.out.println("Ajout de "+c.getName());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						if (echecs>4) {
							return m_c;
						} else {
							echecs++;
						}
						System.out.println("Impossible d'ajouter le num "+j+" de la serie "+series.get(i).getName());
					}
					
				}
				//Si 3 suggestions c'est ok
				if (m_c.size()==3) {
					return m_c;
				}
			}
		}
		return m_c;
	}
	
	/**
	 * @return champ total (nb total de comics avec ce nom de serie)
	 */
	public int getNumberOfComics() {
		return total;
	}
	
	/**
	 * change l'etat du comic dans la serie
	 * @param comic, status
	 */
	public void changeSerieComicStatus(Comic c,int status) {
		if (status==0) {
			try {
				//System.out.println("Suppression "+c.getName());
				rmSerieComic(c);
				//System.out.println("Suppression de "+c.getName());
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
				//System.out.println("Ajout de "+c.getName());
				this.user_serie.add(new Comic_Collec(c, status));
				//System.out.println(searchComicNb(c.getSerieId(), (int) c.getNumber()));
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
			}
		}	
	}
	
	/**
	 * @param comic
	 * @return id de la serie du comic (possiblement null si non trouvé)
	 */
	public Integer searchSerieID(Comic c) {
		for (Iterator<Serie> iterator = series.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next().getId();
			if (c.getSerieId()==integer) {
				return integer;
			}
		}
		return null;
	}
	
	/**
	 * @return champ user_serie
	 */
	public ArrayList<Comic_Collec> getUserSerie() {
		return this.user_serie;
	}
	
	/**
	 * @param id de la serie,numero comic
	 * @return true or false si le numero du comic est dans la serie
	 */
	public boolean searchComicNb(int id_serie,int nb) {
		for (Comic_Collec c : user_serie) {
			if (c.getSerieId()==id_serie && nb==c.getNumber()) {
				return true;
			}
		}
		return false;
	}
	
}
