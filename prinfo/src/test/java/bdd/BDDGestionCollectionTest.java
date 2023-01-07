package bdd;

import API.Comic;
import BDD.BDDGestionCollection;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BDDGestionCollectionTest {

    Comic c = new Comic("Thor", "Super Comic", "comic", 36, "link", "thor", 12, 5, "Super Serie");

    @Before
    public void effacer() throws SQLException{
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            String sql = "DELETE * FROM comic WHERE nom = '" + c.getName() + "';";
            stmt.executeUpdate(sql);
        }
    }

    @Test
    public void testVerificationComic() throws SQLException{
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            assertTrue(BDDGestionCollection.VerificationComic(stmt, c));
        }
    }

    @Test
    public void testInsertionComic() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            BDDGestionCollection.insertionComic(stmt, c);
            String sql = "SELECT * FROM comic WHERE nom = '" + c.getName() + "';";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id_comic");
                String name = rs.getString("nom");
                String link = rs.getString("lien_image");
                int serieId = rs.getInt("id_serie");

                assertEquals(id, 34);
                assertEquals(name, "Wolverine");
                assertEquals(link, "link");
                assertEquals(serieId, 12);
            }
        }
    }



    @Test
    public void testComicSerie() throws SQLException{
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            ResultSet rs_idUser = BDDGestionCollection.getId(stmt, "tata");
            String sql = "SELECT * FROM collection WHERE id_user = '" + rs_idUser + "';";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String id_comic = rs.getString("id_comic");
                String etat_lecture = rs.getString("etat_lecture");

                assertEquals(id_user, 34);
                assertEquals(id_comic, "Wolverine");
                assertEquals(etat_lecture, "link");
            }
        }
    }

    @Test
    public void testChangementEtat() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            assertTrue(BDDGestionCollection.changementEtat(stmt, c, "lecture"));
        }
    }

    @Test
    public void testSuppressionComic() throws SQLException{
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            assertTrue(BDDGestionCollection.suppressionComic(stmt, c));
        }
    }

    @Test
    public void testSuppressionSerie() throws SQLException{
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            assertTrue(BDDGestionCollection.suppressionSerie(stmt, c));
        }
    }
}
