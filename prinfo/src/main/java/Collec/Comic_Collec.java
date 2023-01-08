package Collec;

import API.Comic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Comic_Collec extends Comic {

    private String Etat;

    public Comic_Collec(String name, int id, String iconLink, String serieName, int serieId, int number, String etat) {
        super(name, "NULL", "comic", id, iconLink, serieName, serieId, number, "NULL");
        Etat = etat;

    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) { this.Etat = etat;}

    // Lecture et Enregistrement
    public static void lectureBdd(Statement stmt, Collec collection, String login) throws SQLException {
        String sql = "SELECT * FROM comic INNER JOIN serie on comic.id_serie = serie.id_serie INNER JOIN collection c on comic.id_comic = c.id_comic INNER JOIN user u ON u.id_user = c.id_user WHERE u.login ='" + login + "';";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            collection.addComic(new Comic_Collec(rs.getString("nom"), rs.getInt("id_comic"), rs.getString("lien_image"), rs.getString("nom_serie"), rs.getInt("id_serie"), rs.getInt("numero"), rs.getString("etat_lecture")));
        }
    }

    public static void saveBdd(Statement stmt, Collec new_collection, String login) throws SQLException {
        Collec old_collection = new Collec();
        lectureBdd(stmt, old_collection, login);
        compareCollection(new_collection, old_collection);
        if (!new_collection.getlisteAjout().isEmpty()) {
            System.out.println("On va ajouter des comics");
            ajouter_bdd(stmt, new_collection, login);
        }
        if (!new_collection.getlisteSupp().isEmpty()) {
            System.out.println("On va supprimer des comics");
            supprimer_bdd(stmt, new_collection, login);
        }
        verificationEtatLecture(stmt, new_collection, login);
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
        System.out.println("Liste a supprimer : " + liste_suppression);
        String sql = "DELETE FROM Collection WHERE id_comic IN " + liste_suppression.toString().replace("[", "(").replace("]", ")");
        stmt.executeUpdate(sql);
        verificationCollection(stmt, liste_suppression);
    }

    public static void insertionComic(Statement stmt, Comic c) throws SQLException {
        System.out.println("Insertion du comic : " + c.getName() );
        String sql = "INSERT INTO comic(id_comic, nom, lien_image, id_serie) VALUES (" + c.getId() + ",'" + c.getName() + "', '" + c.getIconLink() + "', " + c.getSerieId() + ");";
        stmt.executeUpdate(sql);
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
        int id_user = getId(stmt, login);
        if (id_user != -1)
        {
            System.out.println("Liaison utilisateur " + id_user + " et comic " + id_comic);
            String sql_UserCollection = "INSERT INTO collection(id_user, id_comic, etat_lecture) VALUES ('" + id_user + "', '" + id_comic + "', 'possede');";
            stmt.executeUpdate(sql_UserCollection);
        }
    }

    public static int getId(Statement stmt, String login) throws SQLException {
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
        int id_user = getId(stmt, login);
        for (Comic_Collec comic : collection.getComics()) {
            String etat = comic.getEtat();
            String sql = "SELECT etat_lecture FROM Collection WHERE id_comic = " + comic.getId() + " AND id_user = " + id_user + ";";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            System.out.println(rs);
            String res = "";
            while (rs.next()) {
                res = rs.getString(1);
            }
            if (!Objects.equals(res, etat) && !res.isEmpty())
            {
                majEtatLecture(stmt, id_user, comic.getId(), etat);
            }
        }
    }
    public static void majEtatLecture(Statement stmt, int id_user, int id_comic, String etat) throws SQLException {
        System.out.println("Maj etat lecture : " + id_comic);
        String sql = "UPDATE collection SET etat_lecture= '" + etat + "' WHERE id_comic = " + id_comic + " AND id_user = '" + id_user + "';";
        stmt.executeUpdate(sql);
    }


    public static List<Comic> getPlusLu(Statement stmt) throws SQLException {
        String sql = "SELECT *, count(*) as count FROM collection INNER JOIN comic ON comic.id_comic = collection.id_comic INNER JOIN serie s on comic.id_serie = s.id_serie GROUP BY collection.id_comic ORDER BY count DESC LIMIT 3";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.getResultSet();
        List<Comic> liste_comic = new ArrayList<>();
        while (rs.next())
        {
            liste_comic.add(new Comic_Collec(rs.getString("nom"), rs.getInt("id_comic"), rs.getString("lien_image"), rs.getString("nom_serie"), rs.getInt("id_serie"), rs.getInt("numero"), rs.getString("etat_lecture")));
        }
        return liste_comic;
    }
}
