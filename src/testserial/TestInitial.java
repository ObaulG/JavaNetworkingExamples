package testserial;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class TestInitial {
	
	
	public static void testReadFileBytesBasic(String path) throws FileNotFoundException, IOException {
		FileInputStream fis;
		
		fis = new FileInputStream(new File(path));
		
		long time = System.currentTimeMillis();
		System.out.println("zzz");
		// will return -1 when reading is completed
		while(fis.read() != -1);
		System.out.println("Temps d'exécution (ms) sans buffer : " + (System.currentTimeMillis() - time) + " ms.");
		
		fis.close();
	}
	
	public static void testReadFileBytesBuffered(String path, int bufferSize) throws FileNotFoundException, IOException {
		FileInputStream fis;
		BufferedInputStream bis;

		fis = new FileInputStream(new File(path));
		bis = new BufferedInputStream(fis, bufferSize);

		long time = System.currentTimeMillis();
		//Lecture
		while(bis.read() != -1);
		System.out.println("Temps d'exécution (ms) avec un buffer de " + bufferSize + " octets : " + (System.currentTimeMillis() - time) + " ms.");
		
		fis.close();
		bis.close();
	}
	public static byte[] readFileNIO(String path) throws FileNotFoundException, IOException {
			
		FileInputStream fis;
	    BufferedInputStream bis;
	    FileChannel fc;
	    
		fis = new FileInputStream(new File(path));
		fc = fis.getChannel();
  
		//We write a buffer with the exact same size of the file.
		int size = (int)fc.size();
		ByteBuffer bBuff = ByteBuffer.allocate(size);
		long timestamp_ns = System.nanoTime();
  
		//We fill the whole buffer
		fc.read(bBuff);
		bBuff.flip();
		System.out.println("File read in " + (System.nanoTime() - timestamp_ns) + " ns.");
		byte[] tabByte = bBuff.array();
		return tabByte;
		}
	
	  public static void main(String[] args) {
	    try {
	    	testReadFileBytesBasic("output-onlinefiletools.txt");
	    	testReadFileBytesBuffered("output-onlinefiletools.txt", 1024);
	    	testReadFileBytesBuffered("output-onlinefiletools.txt", 1024*8);
	    	testReadFileBytesBuffered("output-onlinefiletools.txt", 1024*16);

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
}
