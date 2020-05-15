package clientside;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 *
 *
 *
 */
/**
 * @author MaierL
 *
 */
public class Client_ConnectToServer {

	/**
	 * 
	 */
	static Socket socket;


	/**
	 * @return true, falls connection aufgebaut wurden, sonst false
	 */
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
