package User;

import Collection.Collection;

public class User {
	private String username;
	private String nametag;
	private String password;
	private Collection collection;
	private Collection suggestions;
	
	public User(String username,String nametag, String password) {
		this.username= username;
		this.nametag= nametag;
		this.password= password;
		this.collection= new Collection();
		this.suggestions= new Collection();
	}
	
	public Collection getCollection() {
		return collection;
	}
	public void setCollection(Collection collection) {
		this.collection = collection;
	}
	public Collection getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(Collection suggestions) {
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
	public String getPassword() {
		return password;
	}
	public void changePassword(String password) {
		this.password = password;
	}
	
}
