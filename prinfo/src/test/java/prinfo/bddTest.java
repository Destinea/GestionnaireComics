package prinfo;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import de.mkammerer.argon2.Argon2Factory.Argon2Types;

public class bddTest {
	

	@Test
	public void testDeletion() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "root", "root");
		Statement stmt = con.createStatement();
		assertTrue(bdd.deletion(stmt, "toto"));
		con.close();
	}
	
	@Test
	public void testInsertion() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "root", "root");
		Statement stmt = con.createStatement();
		Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
		bdd.deletion(stmt, "toto");
		assertTrue(bdd.insertion(stmt, "toto", "test2", argon2)); // true = insertion réussie, false = existe déjà
		assertFalse(bdd.insertion(stmt, "toto", "test2", argon2));
		con.close();
	}
	
	@Test
	public void testLecture() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "root", "root");
		Statement stmt = con.createStatement();
		Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
		bdd.insertion(stmt, "toto", "test", argon2);
		assertTrue(argon2.verify(bdd.lecture(stmt, "toto"), "test"));
		con.close();
	}
	
	@Test
	public void testComparaison() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "root", "root");
		Statement stmt = con.createStatement();
		Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
		bdd.insertion(stmt, "toto", "test", argon2);
		String password = bdd.lecture(stmt, "toto");
		assertFalse(bdd.comparaison(password, "dggf", argon2));
		assertTrue(bdd.comparaison(password, "test", argon2));
		con.close();
	}

}
