package prinfo;

import java.sql.*;

// recommandée par l'Organisation de normalisation de l'Internet
// meilleure fonction de hachage de mot de passe disponible
import de.mkammerer.argon2.Argon2;


public class bdd {

	// Lancer my sql workbench et taper "use prinfo7";
/*
	public static void main(String[] args) {
		try {
			// Connexion
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "root", "root");
			Statement stmt = con.createStatement();
			Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
			
			boolean etat1_insert = insertion(stmt, "toto", "test2", argon2); // true = insertion réussie, false = existe déjà
			boolean etat2_insert = insertion(stmt, "toto", "test2", argon2);
			String password = lecture(stmt, "toto");
			boolean etat_comp = comparaison( password, "test2", argon2);
			boolean etat2_comp = comparaison(password, "dggf", argon2);
			boolean etat_del = deletion(stmt, "toto");
			
			System.out.println(etat1_insert);
			System.out.println(etat2_insert);
			System.out.println(password);
			System.out.println(etat_comp);
			System.out.println(etat2_comp);
			System.out.println(etat_del);
			//true, false, idjnch, true, true, false
			

			// Fermeture de la connexion
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}*/

	public static boolean insertion(Statement stmt, String login, String psw, Argon2 argon2) {
		// Insertion du login et du mdp
		try {
			if (lecture(stmt, login).isEmpty())
			{
				String hash_password = argon2.hash(4, 1024 * 1024, 8, psw);
				String sql = "INSERT INTO user(login, password) VALUES ('" + login + "', '" + hash_password + "');";
				System.out.print(sql);
				stmt.executeUpdate(sql);
				return true;
			}
			else 
			{ 
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}

	}

	public static String lecture(Statement stmt, String login) throws SQLException {
		// Affichage du login et du mdp à partir du login
		String str = "";
		try {
			String sql = "SELECT * FROM user WHERE login = '" + login + "';";
			stmt.executeQuery(sql);
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				str = rs.getString("password");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return str;
	}

	public static boolean deletion(Statement stmt, String login) throws SQLException {
		//Deletion à partir du nom d'utilisateur
		try {
			String sql = "DELETE FROM user WHERE login = '" + login + "';";
			stmt.executeUpdate(sql);
			return lecture(stmt, login).isEmpty();
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static boolean comparaison(String pwd, String attempt, Argon2 argon2)
	{
		try {
			return argon2.verify(pwd, attempt);
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public static boolean ajouterNametag(Statement stmt, String login, String nametag) {
		try {
			String sql = "UPDATE user SET nametag = '" + nametag + "' WHERE login = '" + login + "';";
			stmt.executeUpdate(sql);
			return true;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
}

