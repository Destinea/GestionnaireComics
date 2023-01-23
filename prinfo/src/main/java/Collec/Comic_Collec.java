package Collec;

import API.Comic;
import FileManagner.DBconfig;
import GestionUser.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Valentine
 * @author alexi
 * Implemente un Comic possédé par l'utilisateur 
 */
public class Comic_Collec extends Comic implements Comparable<Object>{
	
	/**
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
        /**
         Permet de sauvegarder les modifications apportées à la collection d'un utilisateur dans la base de données.
         @param user : Objet de type User qui contient les informations sur l'utilisateur et sa collection.
         */
         try (Connection con = DriverManager.getConnection(DBconfig.getUrl(), DBconfig.getUsername(), DBconfig.getPassword())) {
             // Connexion à la bdd
            Statement stmt= con.createStatement();
            // Création d'un user copie pour récupérer l'ancienne collection depuis la bdd
            User old_user = new User(user.getUsername(), "");
            old_user.chargeCollection();

            // Comparasion de la collection locale et bdd
            compareCollection(user.getCollection(), old_user.getCollection());
            if (!user.getCollection().getlisteAjout().isEmpty()) { // Si la liste d'ajout contient des comics
                ajouter_bdd(stmt, user.getCollection(), old_user.getUsername()); // On les ajoute à la bdd
            }
            if (!user.getCollection().getlisteSupp().isEmpty()) { // Si la liste de suppression contient des comics
                supprimer_bdd(stmt, user.getCollection(), old_user.getUsername()); // On les supprime de la bdd
            }
            verificationEtatLecture(stmt, user.getCollection(), user.getUsername()); // On modifie l'état de lecture si besoin
        }
    }


    public static void compareCollection(Collec new_collection, Collec old_collection) {
        /**
         Permet de comparer deux collections de comics et de récupérer les comics à ajouter ou à supprimer.
         @param new_collection : Nouvelle collection de comics à comparer.
         @param old_collection : Ancienne collection de comics à comparer.
         */
        // Liste des comics
    	ArrayList<Comic_Collec> old_liste = old_collection.getComics();
    	ArrayList<Comic_Collec> new_liste = new_collection.getComics();

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


    public static void ajouter_bdd(Statement stmt, Collec add_collection, String login) throws SQLException {
        /**
         Permet d'ajouter des comics à la base de données pour un utilisateur donné.
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL.
         @param add_collection : Objet Collec qui contient la liste des comics à ajouter.
         @param login : Login de l'utilisateur à qui on veut ajouter les comics.
         */
        ArrayList<Comic_Collec> liste_ajout = add_collection.getlisteAjout(); // Récupération de la liste de comics à ajouter
        for (Comic_Collec comic : liste_ajout) {
            insertionSerie(stmt, comic); // Si la série n'est pas dans la BDD, on l'ajoute
            insertionComic(stmt, comic); // On insère le comic
            liaisonComicUser(stmt, comic.getId(), login); // On lie le Comic à l'utilisateur
        }
    }

    public static void supprimer_bdd(Statement stmt, Collec supp_collection, String login) throws SQLException {
        /**
         Permet de supprimer un liste de comics de la collection d'un utilisateur donné dans la base de données.
         On supprime aussi les comics qui ne sont plus présent dans la table collection.
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL.
         @param supp_collection : Objet Collec qui contient la liste des comics à supprimer de la collection.
         @param login : Login de l'utilisateur pour identifier la collection à mettre à jour.
        */
         List<Integer> liste_suppression = supp_collection.getlisteSupp(); // Récupération des comics à supprimer
        int id_user = getUserId(stmt, login); // Récupération id user
        String sql = "DELETE FROM collection as c WHERE id_comic IN " + liste_suppression.toString().replace("[", "(").replace("]", ")") + " AND c.id_user = " + id_user;
        stmt.executeUpdate(sql);
        verificationCollection(stmt, liste_suppression); // Vérification si la bdd doit être nettoyée
    }

    public static void insertionComic(Statement stmt, Comic c) throws SQLException {
        /**
         Permet d'insérer un comic dans la base de données si le comic n'existe pas déjà dans la base de données
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL
         @param c : Objet Comic qui contient les informations à insérer dans la base de données
         */

        if (VerificationComic(stmt, c)) // On vérifie si le Comic n'est pas dans la bdd et on insère si c'est le cas
        {
            String sql = "INSERT INTO comic(id_comic, nom, lien_image, id_serie, numero) VALUES (" + c.getId() + ",\"" + c.getName() + "\", \"" + c.getIconLink() + "\", " + c.getSerieId() + "," + c.getNumber() + ");";
            stmt.executeUpdate(sql);
        }
    }

    public static boolean VerificationComic(Statement stmt, Comic c) throws SQLException {
        /**
         Vérifie l'existence d'un comic dans la table comic de la base de données en utilisant son ID
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL
         @param c : Objet Comic qui contient l'ID du comic à vérifier
         @return : true si le comic n'existe pas dans la base de données, false sinon.
         */
         String sql = "SELECT * FROM comic WHERE id_comic = " + c.getId() + ";";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.getResultSet();
        return !rs.next();
    }

    public static void insertionSerie(Statement stmt, Comic c) throws SQLException {
        /**
         Permet d'insérer une série dans la base de données si elle n'y est pas déjà présente
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL
         @param c : Objet Comic qui contient les informations sur la série à insérer
         */
        if (VerificationSerie(stmt, c)) { // On vérifie si la série n'existe pas
            String sql_Serie = "INSERT INTO serie(id_serie, nom_serie) VALUES (" + c.getSerieId() + ", \"" + c.getSerieName() + "\");"; // Si oui, on insère
            stmt.executeUpdate(sql_Serie);
        }
    }

    public static boolean VerificationSerie(Statement stmt, Comic c) throws SQLException {
        /**
         Vérifie si une série existe déjà dans la base de données.
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL.
         @param c : Objet Comic qui contient les informations de la série à vérifier.
         @return : true si la série n'existe pas dans la base de données, false sinon.
         */
        String sql = "SELECT * FROM serie WHERE id_serie = " + c.getSerieId() + ";";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.getResultSet();
        return !rs.next();
    }

    public static void liaisonComicUser(Statement stmt, int id_comic, String login) throws SQLException {
        /**
         Permet de lier un utilisateur et un comic dans la table collection de la base de données.
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL
         @param id_comic : ID du comic à lier à l'utilisateur
         @param login : Login de l'utilisateur à lier au comic
         */
        int id_user = getUserId(stmt, login); // Récupération de l'id de l'utilisateur
        if (id_user != -1)
        {
            String sql_UserCollection = "INSERT INTO collection(id_user, id_comic, id_etat) VALUES (\"" + id_user + "\", \"" + id_comic + "\", 1);"; //Liaison
            stmt.executeUpdate(sql_UserCollection);
        }
    }

    public static int getUserId(Statement stmt, String login) throws SQLException {
        /**
        Permet de récupérer l'identifiant d'un utilisateur en utilisant son login
        @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL
        @param login : Login de l'utilisateur dont on veut récupérer l'identifiant
        @return l'identifiant de l'utilisateur si celui-ci existe dans la base de données, -1 sinon
        */
        String sql_getLogin = "SELECT id_user FROM user WHERE login = \"" + login + "\";"; // Récupère l'id de l'utilisateur
        stmt.executeQuery(sql_getLogin);
        ResultSet rs_idUser = stmt.getResultSet();
        int id_user = -1;
        while (rs_idUser.next()) {
            id_user = rs_idUser.getInt(1);
        }
        return id_user; // Retourne l'id de l'utilisateur (-1 si introuvable)
    }

    public static void verificationCollection(Statement stmt, List<Integer> liste_suppression) throws SQLException {
        /**
         Vérifie si les comics d'une liste sont encore présent dans la table collection
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL.
         @param liste_suppression : Liste des id des comics qui doivent être supprimés de la table Collection.
         */
        for (int id_comic : liste_suppression) {
            String sql = "SELECT * FROM Collection WHERE id_comic = " + id_comic + ";";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            if (!rs.next()) {
                nettoyageCollection(stmt, id_comic); // Si le comic ne se trouve dans aucune collection, on le supprime
            }
        }
    }

    public static void nettoyageCollection(Statement stmt, int id_comic) throws SQLException {
        /**
         Permet de supprimer un comic de la table "Comic".
         @param stmt : Objet de type Statement qui permet d'exécuter des requêtes SQL.
         @param id_comic : Identifiant du comic qu'on souhaite supprimer
         */
        String sql = "DELETE FROM Comic WHERE id_comic = " + id_comic + ";";
        stmt.executeUpdate(sql);
    }

    public static void verificationEtatLecture(Statement stmt, Collec collection, String login) throws SQLException {
        /**
         Vérifie l'état de lecture des comics dans une collection pour un utilisateur donné et le met à jour si nécessaire
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL
         @param collection : Objet Collec qui contient les informations sur les comics de la collection
         @param login : Login de l'utilisateur pour lequel on vérifie l'état de lecture
        */
         int id_user = getUserId(stmt, login); // On récupère l'id de l'utilisaéteur à partir de son login
        int int_etat;
        int res;
        for (Comic_Collec comic : collection.getComics()) { // Pour chaque comic de la collection de l'utilisateur
            int_etat = comic.getEtat(); // On récupère l'état local
            String sql = "SELECT id_etat FROM Collection WHERE id_comic = " + comic.getId() + " AND id_user = " + id_user + ";"; // On récupère l'état dans la bdd
            stmt.executeQuery(sql);
            ResultSet rs = stmt.getResultSet();
            res = 0;
            while (rs.next()) {
                res = rs.getInt(1);
            }
            if (!Objects.equals(res, int_etat) && res != 0) // On compare l'état local et celui de la bdd et on met à jour si différent et pas égal à 0
            {
                majEtatLecture(stmt, id_user, comic.getId(), int_etat);
            }
        }
    }
    public static void majEtatLecture(Statement stmt, int id_user, int id_comic, int id_etat) throws SQLException {
        /**
         Permet de mettre à jour l'état de lecture d'un comic pour un utilisateur donné
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL
         @param id_user : Identifiant de l'utilisateur pour lequel on souhaite mettre à jour l'état de lecture
         @param id_comic : Identifiant du comic pour lequel on souhaite mettre à jour l'état de lecture
         @param id_etat : Nouvel état de lecture (1 = présent dans la collection, 2 = lu, 3 = possédé, 4 = lu et possédé)
         */
        String sql = "UPDATE collection SET id_etat = " + id_etat + " WHERE id_comic = " + id_comic + " AND id_user = \"" + id_user + "\";";
        stmt.executeUpdate(sql);
    }


    public static List<Comic> getPlusLu(Statement stmt) throws SQLException {
        /**
         Permet de récupérer les 3 comics les plus présentes dans les collections de la base de données.
         @param stmt : Objet Statement qui permet d'exécuter des requêtes SQL.
         @return : Liste des 3 comics les plus lues.
         */
        String sql = "SELECT nom, comic.id_comic, lien_image, nom_serie, comic.id_serie, numero, count(*) as count FROM collection INNER JOIN comic ON comic.id_comic = collection.id_comic INNER JOIN serie s on comic.id_serie = s.id_serie GROUP BY collection.id_comic ORDER BY count DESC LIMIT 3;";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.getResultSet();
        List<Comic> liste_comic = new ArrayList<>();
        while (rs.next()) //On crée 3 comics contenants les infos récupérées et on les ajoute à une liste de Comics
        {
            liste_comic.add(new Comic_Collec(rs.getString("nom"), rs.getInt("id_comic"), rs.getString("lien_image"), rs.getString("nom_serie"), rs.getInt("id_serie"), rs.getInt("numero"), 1));
        }
        return liste_comic; // On retourne la liste
    }
    
    
	@Override
	/**
	 * @param comic a comparer
	 * @return entier correspondant a la comparaison du numero de tome des 2 comics
	 */
	public int compareTo(Object o) {
		Comic_Collec c = (Comic_Collec) o; 
        return (int) ( this.getNumber()-c.getNumber());
	}
}
