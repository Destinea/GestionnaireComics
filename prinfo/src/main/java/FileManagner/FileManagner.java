package FileManagner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileManagner {
	public void write(String s) {
		Path chemin = Paths.get("temp_loging");
        // convertit String en un tableau d'octets
        byte[] data = s.getBytes();
 
        OutputStream output = null;
        try {
            // Un objet BufferedOutputStream est affecté à la référence OutputStream.
            output = new BufferedOutputStream(Files.newOutputStream(chemin, CREATE));
            // Ecrire dans le fichier
            output.write(data);
            // vider le tampon
            output.flush();
 
            // fermer le fichier
            output.close();
 
        } catch (Exception e) {
            System.out.println("Message " + e);
        }
	}
	public String read() {
		Path chemin = Paths.get("temp_loging");
        InputStream input = null;
        try {
            input = Files.newInputStream(chemin);
             
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String s = null;
            s = reader.readLine();
            input.close();
            return s;
        } catch (IOException e) {
            System.out.println("Message " + e);
            return null;
        }
	}
	public void delete() {
		File myObj = new File("temp_loging"); 
	    if (myObj.delete()) { 
	      //System.out.println("Deleted the file: " + myObj.getName());
	    } else {
	      //System.out.println("Failed to delete the file.");
	    } 
	}
}
