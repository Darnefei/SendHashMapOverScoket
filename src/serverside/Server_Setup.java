package serverside;

import java.io.IOException;
import java.net.ServerSocket;

public class Server_Setup {
	
	public static ServerSocket startUpServer() {
		int port = 7778;
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			return serverSocket;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

}
