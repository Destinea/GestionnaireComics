package BDD;

import java.sql.*;
import API.Comic;


import java.sql.SQLException;
public class BDDGestionCollection {

    public static void insertionComic(Statement stmt, Comic c){
        try {
            if (VerificationComic(stmt, c)) {
                addSerie(stmt, c);
                String sql = "INSERT INTO comic(id_comic, nom, lien_image, id_serie) VALUES ("+ c.getId() +",'" + c.getName() + "', '" + c.getIconLink() + "', " + c.getSerieId() + ");";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static boolean VerificationComic(Statement stmt, Comic c) {
        try {
            String sql = "SELECT * FROM comic WHERE nom = '" + c.getName() + "';";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            return !rs.next();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static void ComicSerie(Statement stmt, Comic c, String login) {
        try {
            ResultSet rs_idUser = getId(stmt, login);
            while (rs_idUser.next()) {
                String sql_UserCollection = "INSERT INTO collection(id_user, id_comic, etat_lecture) VALUES ('" + rs_idUser + "', '" + c.getId() + "', 'souhait');";
                stmt.executeQuery(sql_UserCollection);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void addSerie(Statement stmt, Comic c) {
        try {
            if (VerificationSerie(stmt, c)) {
                String sql_Serie = "INSERT INTO serie(id_serie, nom_serie) VALUES (" + c.getSerieId() + ", '" + c.getSerieName() + "');";
                stmt.executeUpdate(sql_Serie);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static boolean VerificationSerie(Statement stmt, Comic c) {
        try {
            String sql = "SELECT * FROM serie WHERE id_serie = " + c.getSerieId() + ";";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            return !rs.next();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean changementEtat(Statement stmt, Comic c, String etat) {
        try {
            String sql_chgmEtat = "UPDATE collection SET etat_lecture = '" + etat + "' WHERE id_comic ='" + c.getId() + "');";
            stmt.executeQuery(sql_chgmEtat);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static boolean suppressionComicCollection(Statement stmt, Comic c, String login) {
        try {
            ResultSet rs_idUser = getId(stmt, login);
            if (rs_idUser != null) {
                while (rs_idUser.next())
                {
                    String sql_supp_Collection = "DELETE FROM collection WHERE id_user = '" + rs_idUser + "';";
                    stmt.executeQuery(sql_supp_Collection);
                    if (!VerificationComic(stmt, c))
                    {
                        return suppressionComic(stmt, c);
                    }
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static ResultSet getId(Statement stmt, String login)
    {
        try {
            String sql_getLogin = "SELECT id_user FROM user WHERE login = '" + login + "';";
            stmt.executeQuery(sql_getLogin);
            return stmt.getResultSet();
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return null;
        }
    }

    public static boolean suppressionComic(Statement stmt, Comic c)
    {
        try {
            String sql_supp_Comic = "DELETE FROM comic WHERE id_comic= '" + c.getId() + "';";
            stmt.executeUpdate(sql_supp_Comic);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }


    public static boolean suppressionSerie(Statement stmt, Comic c)
    {
        try {
            String sql_supp_Comic = "DELETE FROM serie WHERE id_serie= " + c.getSerieId() + ";";
            stmt.executeUpdate(sql_supp_Comic);
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
    }

}
