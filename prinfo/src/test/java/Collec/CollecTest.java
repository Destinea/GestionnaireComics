package Collec;

import API.Comic;
import org.junit.Test;

import java.sql.*;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class CollecTest {
    /*@Test
    public void testLectureBdd() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Collec collection = new Collec();
            Collec.lectureBdd(stmt, collection, "tata");
            Set<Comic_Collec> liste = collection.getComics();
            for (Comic_Collec comic : liste) {
                assert !comic.getName().isEmpty();
                assert !comic.getSerieName().isEmpty();
                assert !comic.getIconLink().isEmpty();
                assert !comic.getType().isEmpty();
                System.out.println(comic.getName());
            }
        }
    }

    @Test
    public void testCompareCollection() {
        Comic_Collec t1 = new Comic_Collec("Spiderman1", 1, "iconLink", "Spiderman", 1, 1, 1);
        Comic_Collec t2 = new Comic_Collec("Spiderman2", 2, "iconLink", "Spiderman", 1, 2, 1);
        Comic_Collec t3 = new Comic_Collec("Spiderman3", 3, "iconLink", "Spiderman", 1, 3, 1);
        Comic_Collec t4 = new Comic_Collec("Batman2", 4, "iconLink", "Batman", 2, 2, 1);
        Comic_Collec t5 = new Comic_Collec("Batman1", 5, "iconLink", "Batman", 2, 1, 1);
        List<Integer> listsupp = new ArrayList<>();
        listsupp.add(5);
        Collec collection = new Collec();
        collection.addComic(t4);
        Collec collection_new = new Collec();
        collection_new.addComic(t1);
        collection_new.addComic(t2);
        collection_new.addComic(t3);
        collection_new.addComic(t4);
        Collec collection_old = new Collec();
        collection_old.addComic(t1);
        collection_old.addComic(t2);
        collection_old.addComic(t3);
        collection_old.addComic(t5);
        Comic_Collec.compareCollection(collection_new, collection_old);
        assertEquals(collection_new.getlisteSupp(), listsupp);
        assertEquals(collection_new.getlisteAjout(), collection.getComics());
    }

    @Test
    public void testsSavebddAjoutComic() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Collec collection = new Collec();
            Collec.lectureBdd(stmt, collection, "tata");
            Comic_Collec t1 = new Comic_Collec("wolverine 2", 41, "https://image.com", "wolverine", 12, 2, 2);
            Comic_Collec t2 = new Comic_Collec("wolverine 3", 42, "https://image.com", "wolverine", 12, 3, 3);

            // Etape 1 : Ajout comic
            collection.addComic(t1);
            collection.addComic(t2);

            Comic_Collec.saveBdd(stmt, collection, "tata");

            String sql = "SELECT id_comic FROM collection INNER JOIN user ON collection.id_user = user.id_user WHERE login ='tata' AND id_comic = " + t1.getId() + " OR id_comic = " + t2.getId() + ";";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            List<Integer> liste_id = new ArrayList<>();
            while (rs.next()) {
                liste_id.add(rs.getInt(1));
            }

            assert t1.getId() == liste_id.get(0) || t1.getId() == liste_id.get(1);
            assert t2.getId() == liste_id.get(0) || t2.getId() == liste_id.get(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSavebddModificationComic() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Collec collection = new Collec();
            Collec.lectureBdd(stmt, collection, "tata");
            Comic_Collec t1 = new Comic_Collec("wolverine 2", 41, "https://image.com", "wolverine", 12, 2, 3);
            Comic_Collec t2 = new Comic_Collec("wolverine 3", 42, "https://image.com", "wolverine", 12, 3, 2);

            // Etape 2 : Modification comic
            Comic_Collec comic1 = collection.searchComic(41);
            if (comic1 != null) {
                comic1.setEtat(3);
            }
            Comic_Collec comic2 = collection.searchComic(42);
            if (comic2 != null) {
                comic2.setEtat(2);
            }
            Comic_Collec.saveBdd(stmt, collection, "tata");


            String sql = "SELECT id_etat FROM collection INNER JOIN user ON collection.id_user = user.id_user WHERE login ='tata' AND id_comic = " + t1.getId() + " OR id_comic = " + t2.getId() + ";";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            List<Integer> liste_id = new ArrayList<>();
            while (rs.next()) {
                liste_id.add(rs.getInt(1));
            }

            assert Objects.equals(t1.getEtat(), liste_id.get(0)) || Objects.equals(t1.getEtat(), liste_id.get(1));
            assert Objects.equals(t2.getEtat(), liste_id.get(0)) || Objects.equals(t2.getEtat(), liste_id.get(1));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSavebddSuppressionComic() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Collec collection = new Collec();
            Collec.lectureBdd(stmt, collection, "tata");
            Comic_Collec t1 = new Comic_Collec("wolverine 2", 41, "https://image.com", "wolverine", 12, 2, 3);
            Comic_Collec t2 = new Comic_Collec("wolverine 3", 42, "https://image.com", "wolverine", 12, 3, 2);

            // Etape 3 : Suppression comic
            collection.rmComic(t1);
            collection.rmComic(t2);

            Comic_Collec.saveBdd(stmt, collection, "tata");

            String sql = "SELECT id_comic FROM collection INNER JOIN user ON collection.id_user = user.id_user WHERE login ='tata' AND id_comic = " + t1.getId() + " OR id_comic = " + t2.getId() + ";";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            List<Integer> liste_id = new ArrayList<>();
            while (rs.next()) {
                assert rs.getString(1) == null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testgetPlusLu() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            List<Comic> liste = Comic_Collec.getPlusLu(stmt);
            System.out.println("Les comics les plus lus sont : ");
            for (Comic comic : liste) {
                System.out.println(comic.getName() + " : " + comic.getId());
            }
            assertEquals(3, liste.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}