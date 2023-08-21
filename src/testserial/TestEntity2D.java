package testserial;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

// http://web.mit.edu/6.031/www/sp19/classes/23-sockets-networking/

public class TestEntity2D {

	public static void main(String[] args) {
		Entity2D ent_1 = new Entity2D("test1", 0.0f, 0.0f);
		
		ObjectOutputStream oos = null;
		
		// Writing
		try {
			FileOutputStream fichier = new FileOutputStream("donnees.ser");
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(ent_1);
			oos.flush();
			oos.close();
			fichier.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// Reading
		Entity2D deserial = null;
		ObjectInputStream ois = null;
		try {
			FileInputStream f = new FileInputStream("donnees.ser");
			ois = new ObjectInputStream(f);
			deserial = (Entity2D) ois.readObject();
			ois.close();
			f.close();
		}
		catch (final IOException ex) {
			ex.printStackTrace();
	    }
		catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
