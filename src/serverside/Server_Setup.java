package serverside;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author MaierL
 *
 */
public class Server_Setup {
	
	/**
	 * Methode f�r den Serversocket
	 * @return gibt den ServerSocket zur�ck
	 */
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
