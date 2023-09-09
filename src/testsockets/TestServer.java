package testsockets;

import java.io.IOException;

public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EchoServer server = new EchoServer();
		try {
			server.start(8870);
			server.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
