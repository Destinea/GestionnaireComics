package API;

import java.sql.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

public class bdd {

	// Lancer my sql workbench et taper "use prinfo7";

	public static void main(String[] args) {
		try {
			// Connexion
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "root", "root");
			Statement stmt = con.createStatement();
			System.out.println("Connected");

			insertion(stmt, "toto", "test2");
			lecture(stmt, "toto");
			deletion(stmt, "toto");

			// Fermeture de la connexion
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void insertion(Statement stmt, String login, String psw) throws SQLException {
		// Insertion du login et du mdp
		try {
			String sql = "INSERT INTO user(login, password) VALUES ('" + login + "','" + psw + "');";
			stmt.executeUpdate(sql);
			System.out.println("Element inséré.");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void lecture(Statement stmt, String login) throws SQLException {
		// Affichage du login et du mdp à partir du login
		try {
			String sql = "SELECT * FROM user WHERE login = '" + login + "';";
			ResultSet rs = stmt.executeQuery(sql);
			rs = stmt.getResultSet();
			System.out.println("Voici les résultats obtenus pour le pseudo " + login + " : ");
			while (rs.next()) {
				String str = rs.getString("password");
				System.out.println(str);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deletion(Statement stmt, String login) throws SQLException {
		//Deletion à partir du nom d'utilisateur
		try {
			String sql = "DELETE FROM user WHERE login = '" + login + "';";
			stmt.executeUpdate(sql);
			System.out.println("Element supprimé.");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	// Il faut créer une clé mais jsp comment faire
	public String encrypt(String password, String key) {
		try {
			SecretKeySpec clef = new SecretKeySpec(key.getBytes("ISO-8859-2"), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, clef);
			return new String(cipher.doFinal(password.getBytes()));
		} catch (Exception e) {
			return null;
		}
	}

	public String decrypt(String password, String key) {
		try {
			SecretKeySpec clef = new SecretKeySpec(key.getBytes("ISO-8859-2"), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, clef);
			return new String(cipher.doFinal(password.getBytes()));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
