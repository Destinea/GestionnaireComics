package User;

import java.sql.*;

import API.Comic;
import Collec.Collec;
import Collec.Comic_Collec;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class User {
	private final String username;
	private String nametag;
	private Collec collection;
	private Collec suggestions;
	
	public User(String username,String nametag) {
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
	public void addUserComic(Comic c) {
		this.collection.changeComicStatus(c, 1);
	}
	public void changeUserComicStatus(Comic c,int etat) {
		this.collection.changeComicStatus(c, etat);
	}

	public void chargeCollection() throws SQLException {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
			Statement stmt;
			stmt = con.createStatement();
			String sql = "SELECT * FROM comic INNER JOIN serie on comic.id_serie = serie.id_serie INNER JOIN collection c on comic.id_comic = c.id_comic INNER JOIN user u ON u.id_user = c.id_user WHERE u.login ='" + this.username + "';";
			stmt.executeQuery(sql);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				//System.out.println("Ajout de "+rs.getString("nom"));
				changeUserComicStatus(new Comic_Collec(rs.getString("nom"), rs.getInt("id_comic"), rs.getString("lien_image"), rs.getString("nom_serie"), rs.getInt("id_serie"), rs.getInt("numero"),rs.getInt("id_etat")), rs.getInt("id_etat"));
			}
		}
	}
	public void changePassword(Statement stmt,String new_password) throws SQLException {
		//Appelle a la BDD pour modifier le password
                Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		User_BDD.changerMotDePasse(stmt, this.username, new_password, argon2);
	}
	
}
