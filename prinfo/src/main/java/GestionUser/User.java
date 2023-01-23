package GestionUser;

import API.Comic;
import Collec.Collec;
import Collec.Comic_Collec;
import FileManagner.DBconfig;
import FileManagner.FileManagner;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.sql.*;

/**
 * @author alexi
 * @author Valentine
 * Implemente un utilisateur
 */
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
	public User() {
		FileManagner f= new FileManagner();
		this.username=f.read();
		this.nametag=username;
		this.collection= new Collec();
		this.suggestions= new Collec();
		f.delete();
	}
	/**
	 * sauvegarde le username pour le renseigner automatiquement la prochaine connexion
	 */
	public void save() {
		FileManagner f= new FileManagner();
		f.write(username);
	}
	
	/**
	 * @retur champ collection
	 */
	public Collec getCollection() {
		return collection;
	}
	/**
	 * @param collection
	 */
	public void setCollection(Collec collection) {
		this.collection = collection;
	}
	
	/**
	 * @return suggestions
	 */
	public Collec getSuggestions() {
		return suggestions;
	}
	
	/**
	 * @param suggestions
	 */
	public void setSuggestions(Collec suggestions) {
		this.suggestions = suggestions;
	}
	
	/**
	 * @return champ username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return champ nametag
	 */
	public String getNametag() {
		return nametag;
	}
	/**
	 * @param nouveau nametag
	 */
	public void changeNametag(String nametag) {
		this.nametag = nametag;
	}
	/**
	 * @param comic a ajouter
	 */
	public void addUserComic(Comic c) {
		this.collection.changeComicStatus(c, 1);
	}
	
	/**
	 * change l'etat du comic dans la collection de l'utilisateur
	 * @param comic, etat
	 */
	public void changeUserComicStatus(Comic c,int etat) {
		this.collection.changeComicStatus(c, etat);
	}

	/**
	 * charge le champ collection de l'utilisateur a partir des données de la Bdd
	 */
	public void chargeCollection() throws SQLException {
		try (Connection con = DriverManager.getConnection(DBconfig.getUrl(), DBconfig.getUsername(), DBconfig.getPassword())) {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM comic INNER JOIN serie on comic.id_serie = serie.id_serie INNER JOIN collection c on comic.id_comic = c.id_comic INNER JOIN user u ON u.id_user = c.id_user WHERE u.login ='" + this.username + "';";
			stmt.executeQuery(sql);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				//System.out.println("Ajout de "+rs.getString("nom"));
				changeUserComicStatus(new Comic_Collec(rs.getString("nom"), rs.getInt("id_comic"), rs.getString("lien_image"), rs.getString("nom_serie"), rs.getInt("id_serie"), rs.getInt("numero"),rs.getInt("id_etat")), rs.getInt("id_etat"));
			}
		}
	}
	
	/**
	 * change le mot de passe de l'utilisateur dans la BDD
	 * @param lien bdd, nouveau mot de passe
	 */
	public void changePassword(Statement stmt,char[] new_password) throws SQLException {
		//Appelle a la BDD pour modifier le password
                Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		User_BDD.changerMotDePasse(stmt, this.username, new_password, argon2);
	}
	
}
