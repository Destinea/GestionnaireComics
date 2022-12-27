package User;

import Collec.Collec;

public class User {
	private String username;
	private String nametag;
	private Collec collection;
	private Collec suggestions;
	
	public User(String username,String nametag, String password) {
		this.username= username;
		this.nametag= nametag;
		this.collection= new Collec();
		this.suggestions= new Collec();
	}
	
	public Collec getCollection() {
		return collection;
	}
	public void setCollection(Collec collection) {
		this.collection = collection;
	}
	public Collec getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(Collec suggestions) {
		this.suggestions = suggestions;
	}
	public String getUsername() {
		return username;
	}
	public String getNametag() {
		return nametag;
	}
	public void changeNametag(String nametag) {
		this.nametag = nametag;
	}

	public void changePassword(String new_password) {
		//Appelle a la BDD pour modifier le password 
	}
	
}
