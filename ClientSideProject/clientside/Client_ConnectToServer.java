package clientside;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * Sets Up the IP and PortAdress.
 * 
 */
public class Client_ConnectToServer {

	static Socket socket;
	
	
	public static boolean startConnection() {
		try {
			socket = new Socket("localhost", 7778);
			Client_Logic.activateIOStreams(socket);
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
		
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}
