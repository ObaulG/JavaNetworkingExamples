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
		clientSocket = serverSocket.accept();
		System.out.println("Client connected"+clientSocket);
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EchoServer server = new EchoServer();
		try {
			server.start(8869);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
