/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prinfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
    

/**
 *
 * @author Jujubaca
 */
public class Connexion {
    private final String FILE_CONNEXION_NAME = "tableauConnexion.csv";

    public static void CreateFile() throws IOException {
    File file = new File("src/main/resources/tableauConnexion.csv");
    if (file.createNewFile()) {
            System.out.println("File has been created.");
        } else {
            System.out.println("File already exists.");
        }
    }
}
