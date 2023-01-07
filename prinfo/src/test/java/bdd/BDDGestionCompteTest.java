package bdd;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import User.User_BDD;
import org.junit.Test;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

public class BDDGestionCompteTest {


    @Test
    public void testSuppression() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            assertTrue(User_BDD.suppression(stmt, "toto"));
        }
    }

    @Test
    public void testInsertion() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt;
            stmt = con.createStatement();
            Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
            User_BDD.suppression(stmt, "tata");
            assertTrue(User_BDD.insertion(stmt, "tata", "test2", argon2)); // true = insertion réussie, false = existe déjà
            assertFalse(User_BDD.insertion(stmt, "tata", "test2", argon2));
        }
    }

    @Test
    public void testLecture() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
            User_BDD.insertion(stmt, "tata", "test", argon2);
            assertTrue(argon2.verify(User_BDD.lecture(stmt, "tata"), "test"));
        }
    }

    @Test
    public void testComparaison() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
            User_BDD.insertion(stmt, "toto", "test", argon2);
            String password = User_BDD.lecture(stmt, "toto");
            assertFalse(User_BDD.comparaison(password, "dggf", argon2));
            assertTrue(User_BDD.comparaison(password, "test", argon2));
        }
    }

    @Test
    public void testAjouterNametag() throws SQLException {
        {
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
                Statement stmt;
                stmt = con.createStatement();
                Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
                User_BDD.insertion(stmt, "tota", "test2", argon2);
                assertTrue(User_BDD.ajouterNametag(stmt, "tota", "tata"));
                User_BDD.suppression(stmt,"tota");
            }

        }
    }
}
