package GestionUser;

public class UserTest {
	/*
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
            assertTrue(argon2.verify(User_BDD.lectureMdp(stmt, "tata"), "test"));
        }
    }

    @Test
    public void testComparaison() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt = con.createStatement();
            Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
            User_BDD.insertion(stmt, "toto", "test", argon2);
            String password = User_BDD.lectureMdp(stmt, "toto");
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

    @Test
    public void testchangermotdepasse() throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt;
            stmt = con.createStatement();
            Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
            User_BDD.changerMotDePasse(stmt, "tota", "test1",argon2 );
            String password = User_BDD.lectureMdp(stmt, "tota");
            assertTrue(User_BDD.comparaison(password, "test1", argon2));

        }
    }
    */
}
