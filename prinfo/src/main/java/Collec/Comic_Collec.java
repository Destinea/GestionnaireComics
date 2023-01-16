package Collec;

import API.Comic;
import User.User;

import java.sql.*;
import java.util.*;

public class Comic_Collec extends Comic {
	
	/*
	 * 0 pas dans la collection
	 * 1 favori (=présent dans la collection)
	 * 2 lu
	 * 3 possédé
	 * 4 lu et possédé
	 **/
    private int Etat;

    public Comic_Collec(String name, int id, String iconLink, String serieName, int serieId, int number, int etat) {
        super(name,id, iconLink, serieName, serieId, number);
        Etat = etat;
    }
    public Comic_Collec(Comic c, int etat) {
        super(c);
        Etat = etat;
    }
    public int getEtat() {
        return this.Etat;
    }

    public void setEtat(int etat) { this.Etat = etat;}

    // Lecture et Enregistrement

    public static void saveBdd(User user) throws SQLException {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prinfo7", "prinfo", "prinfo")) {
            Statement stmt;
            stmt = con.createStatement();
            User old_user = new User(user.getUsername(), "");
            old_user.chargeCollection();

            System.out.println("old");
            for (Comic_Collec comic : old_user.getCollection().getComics())
            {
                System.out.println(comic.getName());
            }

            System.out.println("new");
            for (Comic_Collec comic : user.getCollection().getComics())
            {
                System.out.println(comic.getName());
            }

            compareCollection(user.getCollection(), old_user.getCollection());
            if (!user.getCollection().getlisteAjout().isEmpty()) {
                System.out.println("On va ajouter des comics");
                ajouter_bdd(stmt, user.getCollection(), old_user.getUsername());
            }
            if (!user.getCollection().getlisteSupp().isEmpty()) {
                System.out.println("On va supprimer des comics");
                supprimer_bdd(stmt, user.getCollection(), old_user.getUsername());
            }
            verificationEtatLecture(stmt, user.getCollection(), user.getUsername());
        }
    }

    // Comparaison avant après
    public static void compareCollection(Collec new_collection, Collec old_collection) {
        // Liste des comics
        Set<Comic_Collec> old_liste = old_collection.getComics();
        Set<Comic_Collec> new_liste = new_collection.getComics();

        // Liste des id
        List<Integer> old_liste_id = new ArrayList<>();
        List<Integer> new_liste_id = new ArrayList<>();

        // On récupère tous les id
        for (Comic_Collec c : old_liste)
        {
            old_liste_id.add(c.getId());
        }
        for (Comic_Collec c : new_liste)
        {
            new_liste_id.add(c.getId());
        }

        // Récupère les id de tous les comics à ajouter
        for (Comic_Collec comic : old_liste) {
            if (!new_liste_id.contains(comic.getId())) {
                new_collection.addlisteSupp(comic.getId());
            }
        }

        // Récupère les infos de tous les comics à ajouter
        for (Comic_Collec comic : new_liste) {
            if (!old_liste_id.contains(comic.getId())) {
                new_collection.addlisteAjout(comic);
            }
        }

        System.out.println("Ajout : " + new_collection.getlisteAjout());
        System.out.println("Supp : " + new_collection.getlisteSupp());
    }

    // Ajout des nouveaux comics à la bdd
    public static void ajouter_bdd(Statement stmt, Collec add_collection, String login) throws SQLException {
        HashSet<Comic_Collec> liste_ajout = add_collection.getlisteAjout();
        for (Comic_Collec comic : liste_ajout) {
            insertionSerie(stmt, comic); // Si la série n'est pas dans la BDD, on l'ajoute
            insertionComic(stmt, comic); // On insère le comic
            liaisonComicUser(stmt, comic.getId(), login); // On lie le Comic à l'utilisateur
        }
    }

    // Suppression des anciens comics
    public static void supprimer_bdd(Statement stmt, Collec supp_collection, String login) throws SQLException {
        List<Integer> liste_suppression = supp_collection.getlisteSupp();
        int id_user = getUserId(stmt, login);
        System.out.println("Liste a supprimer : " + liste_suppression);
        String sql = "DELETE FROM collection as c WHERE id_comic IN " + liste_suppression.toString().replace("[", "(").replace("]", ")") + " AND c.id_user = " + id_user;
        stmt.executeUpdate(sql);
        verificationCollection(stmt, liste_suppression);
    }

    public static void insertionComic(Statement stmt, Comic c) throws SQLException {
        System.out.println("Insertion du comic : " + c.getName() );
        if (VerificationComic(stmt, c))
        {
            System.out.println("Il faut insérer");
            String sql = "INSERT INTO comic(id_comic, nom, lien_image, id_serie, numero) VALUES (" + c.getId() + ",'" + c.getName() + "', '" + c.getIconLink() + "', " + c.getSerieId() + "," + c.getNumber() + ");";
            stmt.executeUpdate(sql);
        }
    }

    public static boolean VerificationComic(Statement stmt, Comic c) throws SQLException {
        System.out.println("Vérification de l'existence du comic " + c.getId() + " dans la bdd");
        String sql = "SELECT * FROM comic WHERE id_comic = " + c.getId() + ";";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.getResultSet();
        return !rs.next();
    }

    public static void insertionSerie(Statement stmt, Comic c) throws SQLException {
        System.out.println("Vérification de la nécessité d'inserer la série : " + c.getSerieName());
        if (VerificationSerie(stmt, c)) {
            System.out.println("Il faut insérer");
            String sql_Serie = "INSERT INTO serie(id_serie, nom_serie) VALUES (" + c.getSerieId() + ", '" + c.getSerieName() + "');";
            stmt.executeUpdate(sql_Serie);
        }
    }

    public static boolean VerificationSerie(Statement stmt, Comic c) throws SQLException {
        System.out.println("Vérification de l'existence de la série " + c.getSerieName() + " dans la bdd");
        String sql = "SELECT * FROM serie WHERE id_serie = " + c.getSerieId() + ";";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.getResultSet();
        return !rs.next();
    }

    public static void liaisonComicUser(Statement stmt, int id_comic, String login) throws SQLException {
        int id_user = getUserId(stmt, login);
        if (id_user != -1)
        {
            System.out.println("Liaison utilisateur " + id_user + " et comic " + id_comic);
            String sql_UserCollection = "INSERT INTO collection(id_user, id_comic, id_etat) VALUES ('" + id_user + "', '" + id_comic + "', 1);";
            stmt.executeUpdate(sql_UserCollection);
        }
    }

    public static int getUserId(Statement stmt, String login) throws SQLException {
        String sql_getLogin = "SELECT id_user FROM user WHERE login = '" + login + "';";
        stmt.executeQuery(sql_getLogin);
        ResultSet rs_idUser = stmt.getResultSet();
        int id_user = -1;
        while (rs_idUser.next()) {
            id_user = rs_idUser.getInt(1);
        }
        return id_user;
    }

    public static void verificationCollection(Statement stmt, List<Integer> liste_suppression) throws SQLException {
        System.out.println("On vérifie si des comics doivent être supprimés de la table comic");
        for (int id_comic : liste_suppression) {
            String sql = "SELECT * FROM Collection WHERE id_comic = " + id_comic + ";";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            if (!rs.next()) {
                nettoyageCollection(stmt, id_comic);
            }
        }
    }

    public static void nettoyageCollection(Statement stmt, int id_comic) throws SQLException {
        System.out.println("Suppression du comic de la table comic : " + id_comic);
        String sql = "DELETE FROM Comic WHERE id_comic = " + id_comic + ";";
        stmt.executeUpdate(sql);
    }

    public static void verificationEtatLecture(Statement stmt, Collec collection, String login) throws SQLException {
        System.out.println("Vérification changement etat lecture");
        int id_user = getUserId(stmt, login);
        for (Comic_Collec comic : collection.getComics()) {
            Integer int_etat = comic.getEtat();
            String sql = "SELECT id_etat FROM Collection WHERE id_comic = " + comic.getId() + " AND id_user = " + id_user + ";";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            System.out.println(rs);
            Integer res = 0;
            while (rs.next()) {
                res = rs.getInt(1);
            }
            if (!Objects.equals(res, int_etat) && res != 0)
            {
                majEtatLecture(stmt, id_user, comic.getId(), int_etat);
            }
        }
    }
    public static void majEtatLecture(Statement stmt, int id_user, int id_comic, int id_etat) throws SQLException {
        System.out.println("Maj etat lecture : " + id_comic);
        String sql = "UPDATE collection SET id_etat = " + id_etat + " WHERE id_comic = " + id_comic + " AND id_user = '" + id_user + "';";
        stmt.executeUpdate(sql);
    }


    public static List<Comic> getPlusLu(Statement stmt) throws SQLException {
        String sql = "SELECT *, count(*) as count FROM collection INNER JOIN comic ON comic.id_comic = collection.id_comic INNER JOIN serie s on comic.id_serie = s.id_serie GROUP BY collection.id_comic ORDER BY count DESC LIMIT 3";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.getResultSet();
        List<Comic> liste_comic = new ArrayList<>();
        while (rs.next())
        {
            liste_comic.add(new Comic_Collec(rs.getString("nom"), rs.getInt("id_comic"), rs.getString("lien_image"), rs.getString("nom_serie"), rs.getInt("id_serie"), rs.getInt("numero"), rs.getInt("id_etat")));
        }
        return liste_comic;
    }
}
