package bdd;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import BDD.BDDGestionCompte;
import org.junit.Test;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

public class BDDGestionCompteTest {


    @Test
    public void testSuppression() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            assertTrue(BDDGestionCompte.suppression(stmt, "toto"));
        }
    }

    @Test
    public void testInsertion() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt;
            stmt = con.createStatement();
            Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
            BDDGestionCompte.suppression(stmt, "tata");
            assertTrue(BDDGestionCompte.insertion(stmt, "tata", "test2", argon2)); // true = insertion réussie, false = existe déjà
            assertFalse(BDDGestionCompte.insertion(stmt, "tata", "test2", argon2));
        }
    }

    @Test
    public void testLecture() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
            BDDGestionCompte.insertion(stmt, "tata", "test", argon2);
            assertTrue(argon2.verify(BDDGestionCompte.lecture(stmt, "tata"), "test"));
        }
    }

    @Test
    public void testComparaison() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
            BDDGestionCompte.insertion(stmt, "toto", "test", argon2);
            String password = BDDGestionCompte.lecture(stmt, "toto");
            assertFalse(BDDGestionCompte.comparaison(password, "dggf", argon2));
            assertTrue(BDDGestionCompte.comparaison(password, "test", argon2));
        }
    }

    @Test
    public void testAjouterNametag() throws SQLException {
        {
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
                Statement stmt;
                stmt = con.createStatement();
                Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
                BDDGestionCompte.insertion(stmt, "tota", "test2", argon2);
                assertTrue(BDDGestionCompte.ajouterNametag(stmt, "tota", "tata"));
                BDDGestionCompte.suppression(stmt,"tota");
            }

        }
    }
}
