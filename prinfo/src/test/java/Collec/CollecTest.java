package Collec;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CollecTest {
    @Test
    public void testlecturebdd() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Collec collection = new Collec();
            Comic_Collec.lectureBdd(stmt, collection, "tata");
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
    public void testcompareCollection() {
        Comic_Collec t1 = new Comic_Collec("Spiderman1", 1, "iconLink", "Spiderman", 1, 1, "souhait");
        Comic_Collec t2 = new Comic_Collec("Spiderman2", 2, "iconLink", "Spiderman", 1, 2, "souhait");
        Comic_Collec t3 = new Comic_Collec("Spiderman3", 3, "iconLink", "Spiderman", 1, 3, "souhait");
        Comic_Collec t4 = new Comic_Collec("Batman2", 4, "iconLink", "Batman", 2, 2, "souhait");
        Comic_Collec t5 = new Comic_Collec("Batman1", 5, "iconLink", "Batman", 2, 1, "souhait");
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
    public void testsavebdd() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Collec collection = new Collec();
            Comic_Collec.lectureBdd(stmt, collection, "tata");
            //collection.addComic(new Comic_Collec("wolverine 2", 41, "https://image.com", "wolverine", 12, 2, "possede"));
            //collection.addComic(new Comic_Collec("wolverine 3", 42, "https://image.com", "wolverine", 12, 3, "lu"));
            collection.rmComic(new Comic_Collec("wolverine 2", 41, "https://image.com", "wolverine", 12, 2, "possede"));
            collection.rmComic(new Comic_Collec("wolverine 3", 42, "https://image.com", "wolverine", 12, 3, "lu"));
            Comic_Collec.saveBdd(stmt, collection,"tata");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}