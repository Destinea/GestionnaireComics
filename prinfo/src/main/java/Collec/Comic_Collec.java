package Collec;

import API.Comic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Comic_Collec extends Comic {

    private String Etat;

    public Comic_Collec(String name, int id, String iconLink, String serieName, int serieId, int number, String etat) {
        super(name, "NULL", "comic", id, iconLink, serieName, serieId, number, "NULL");
        Etat = etat;
    }

    public static void lecture_bdd(Statement stmt, Comic c) throws SQLException {
        Collec collection=new Collec();
        String sql = "SELECT * FROM comic INNER JOIN serie on comic.id_serie = serie.id_serie INNER JOIN collection c on comic.id_comic = c.id_comic;";
        stmt.executeQuery(sql);
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            collection.addComic(new Comic_Collec(rs.getString("nom"),  rs.getInt("id_comic"), rs.getString("lien_image"), rs.getString("nom_serie"), rs.getInt("id_serie"), rs.getInt("numero"), rs.getString("etat_lecture")));
        }
    }

}
