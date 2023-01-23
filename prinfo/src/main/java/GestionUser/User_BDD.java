package GestionUser;

import de.mkammerer.argon2.Argon2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class User_BDD {

    public static boolean insertion(Statement stmt, String login, char[] psw, String email, Argon2 argon2) {
        // Insertion du login et du mdp
        try {
            if (lectureMdp(stmt, login).isEmpty())
            {
                String hash_password = argon2.hash(4, 1024 * 1024, 8, psw);
                String sql = "INSERT INTO user(login, password, email) VALUES (\"" + login + "\", \"" + hash_password + "\", \"" + email + "\");";
                //System.out.print(sql);
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

    public static String lectureMdp(Statement stmt, String login) throws SQLException {
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

    public static boolean suppression(Statement stmt, String login) throws SQLException {
        //Deletion à partir du nom d'utilisateur
        try {
            String sql = "DELETE FROM user WHERE login = \"" + login + "\";";
            stmt.executeUpdate(sql);
            return lectureMdp(stmt, login).isEmpty();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean comparaison(String pwd, char[] attempt, Argon2 argon2)
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

    public static void changerMotDePasse(Statement stmt, String login, char[] password, Argon2 argon2) throws SQLException {
        String hash_password = argon2.hash(4, 1024 * 1024, 8, password);
        String sql = "UPDATE user SET password = '" + hash_password + "' WHERE login = '" + login + "';";
        stmt.executeUpdate(sql);
    }
}

