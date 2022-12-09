package prinfo;

import java.sql.*;

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

	public static boolean insertion(Statement stmt, String login, String psw, Argon2 argon2) throws SQLException {
		// Insertion du login et du mdp
		try {
			if (lecture(stmt, login).isEmpty())
			{
				String hash_password = argon2.hash(4, 1024 * 1024, 8, psw);
				String sql = "INSERT INTO user(login, password) VALUES ('" + login + "', '" + hash_password + "');";
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
			ResultSet rs = stmt.executeQuery(sql);
                        rs = stmt.getResultSet();
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
			if (lecture(stmt, login).isEmpty())
			{
			return true;
			}
			else { return false;}
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static boolean comparaison(String pwd, String attempt, Argon2 argon2)
	{
		try {
			boolean success = argon2.verify(pwd, attempt);
			return success;
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}	

}
