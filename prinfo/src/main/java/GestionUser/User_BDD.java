package GestionUser;

import de.mkammerer.argon2.Argon2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class User_BDD {

    public static boolean insertion(Statement stmt, String login, char[] psw, String email, Argon2 argon2) {
        /**
         Créé un nouvel utilisateur dans la base de données avec adresse mail et mot de passe
         @param stmt Objet de type Statement pour l'exécution de requêtes SQL
         @param psw Mot de passe de l'utilisateur à insérer (non crypté)
         @param email Adresse mail de l'utilisateur à insérer
         @param argon2 : Objet Argon2 qui contient les paramètres de l'algorithme de chiffrement
         */
        try {
            if (lectureMdp(stmt, login).isEmpty()) // Si l'utilisateur n'existe pas, on l'ajoute
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
        /**
         Renvoi le mot de passe d'un utilisateur depuis la base de données
         @param stmt Objet de type Statement pour l'exécution de requêtes SQL
         @param login Login de l'utilisateur dont on veut récuperer le mot de passe
         */
        String str = "";
        try {
            String sql = "SELECT * FROM user WHERE login = \"" + login + "\";"; // Récupération des données de l'utilisateur
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                str = rs.getString("password"); // Récupération du contenu de la colonne password
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return str; // Renvoi le mdp
    }

    public static boolean suppression(Statement stmt, String login) throws SQLException {
        /**
         Supprime un utilisateur de la base de données.
         @param stmt Objet de type Statement pour l'exécution de requêtes SQL
         @param login Login de l'utilisateur qu'on veut supprimer
         */
        try {
            String sql = "DELETE FROM user WHERE login = \"" + login + "\";"; // Suppression de l'utilisateur
            stmt.executeUpdate(sql);
            return lectureMdp(stmt, login).isEmpty(); // Vérification de la suppression
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean ajouterNametag(Statement stmt, String login, String nametag) { // Fonction à implémenter
        /**
         Ajoute/Modifie le nametag d'un utilisateur dans la base de données.
         @param stmt Objet de type Statement pour l'exécution de requêtes SQL
         @param login Login de l'utilisateur à qui on veut ajouter un nametag
         @param nametag Le nametag à ajouter à l'utilisateur
         */
         try {
            String sql = "UPDATE user SET nametag = \"" + nametag + "\" WHERE login = \"" + login + "\";"; // Met à jour le nametag
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
        /**
        Permet de changer le mot de passe d'un utilisateur en utilisant Argon2 pour crypter le mot de passe
        @param stmt : Objet de type Statement qui permet d'exécuter des requêtes SQL
        @param login : Login de l'utilisateur dont on veut changer le mot de passe
        @param password : Nouveau mot de passe de l'utilisateur (non crypté)
        @param argon2 : Objet Argon2 qui contient les paramètres de l'algorithme de chiffrement
                */
        String hash_password = argon2.hash(4, 1024 * 1024, 8, password); // Hashage du mdp
        String sql = "UPDATE user SET password = \"" + hash_password + "\" WHERE login = '" + login + "';";
        stmt.executeUpdate(sql);
    }
}

