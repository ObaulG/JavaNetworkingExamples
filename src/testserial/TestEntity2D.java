package testserial;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.time.Duration;
import java.time.Instant;

// http://web.mit.edu/6.031/www/sp19/classes/23-sockets-networking/

public class TestEntity2D {

	
	
	public static void testStandardSerialization() {
		Entity2D ent_1 = new Entity2D("test1", 0.0f, 0.0f);
		ent_1.putItem(5);
		ent_1.putItem(7);
		ent_1.putItem(-1);
		System.out.println("Avant : " + ent_1);
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
		
		// How long is the file ?
		File saved = new File("donnees.ser");
		System.out.println("Taille du fichier : " + saved.length() + " octets");

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
		System.out.println(deserial);
	}
	
	public static void testExternalizable() {
		Entity2D ent_1 = new Entity2D("test1", 0.0f, 0.0f);
		ent_1.putItem(5);
		ent_1.putItem(7);
		ent_1.putItem(-1);
		System.out.println("Avant : " + ent_1);
		ObjectOutputStream oos = null;
		
		// Writing
		try {
			FileOutputStream fichier = new FileOutputStream("donnees.ser");
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(ent_1);
			System.out.println();
			oos.flush();
			oos.close();
			fichier.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// How long is the file ?
		File saved = new File("donnees.ser");
		System.out.println("Taille du fichier : " + saved.length() + " octets");

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
		catch (IOException ex) {
			ex.printStackTrace();
	    }
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(deserial);
	}
	
	public static void testCustomSerialization() {
		Entity2D ent_1 = new Entity2D("test1", 0.0f, 0.0f);
		ent_1.putItem(5);
		ent_1.putItem(7);
		ent_1.putItem(-1);
		System.out.println("Avant : " + ent_1);
		DataOutputStream oos = null;
		
		// Writing
		try {
			FileOutputStream fichier = new FileOutputStream("donnees.ser");
			oos = new DataOutputStream(fichier);
			ent_1.to_bytes_full_data(oos);
			oos.flush();
			oos.close();
			fichier.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// How long is the file ?
		File saved = new File("donnees.ser");
		System.out.println("Taille du fichier : " + saved.length() + " octets");

		// Reading
		/*
		Entity2D deserial = null;
		ObjectInputStream ois = null;
		try {
			FileInputStream f = new FileInputStream("donnees.ser");
			ois = new ObjectInputStream(f);
			deserial = (Entity2D) ois.readObject();
			ois.close();
			f.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
	    }
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(deserial);
		*/
	}
	public static void main(String[] args) {
		testCustomSerialization();
		
	}

}
