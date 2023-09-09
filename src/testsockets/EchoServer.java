/**
 * 
 */
package testsockets;
import java.net.*;
import java.io.*;
/**
 * @author pinap
 *
 */
public class EchoServer {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server opened on port " + port + ", waiting for a client...");
		// Will block until a client connects.
		clientSocket = serverSocket.accept();
		System.out.println("Client connected: "+clientSocket);
		System.out.println("InetAddress: " + clientSocket.getInetAddress() + "\nPort: " + clientSocket.getPort());
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String text = in.readLine();
		out.println(text);
	}
	
	public void stop() throws IOException{
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }


}
