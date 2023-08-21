package testsockets;

import java.io.IOException;

public class TestClient {

	public static void main(String[] args) {
		
		EchoClient client = new EchoClient();
		try {
			client.startConnection("127.0.0.1", 8869);
			String response = client.sendMessage("Hello World!");
			System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
