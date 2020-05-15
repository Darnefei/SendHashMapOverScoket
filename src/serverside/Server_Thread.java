package serverside;

import java.io.IOException;
import java.net.Socket;

/**
 * @author MaierL
 *
 *baut einen Thread für jeden Client auf, der sich verbindet
 */
public class Server_Thread extends Thread {

	/**
	 *  Der Socket der verwendet wird
	 */
	private static Socket socket;

	/**
	 * @param socket gibt den zurzeitigen Socket
	 */
	public Server_Thread(Socket socket) {
		Server_Thread.setSocket(socket);
	}

	/**
	 * @return gibt den socket zurueck
	 */
	public static Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket gibt den zurzeitigen Socket
	 */
	public static void setSocket(Socket socket) {
		Server_Thread.socket = socket;
	}

	/**
	 * oeffnet neue Threads für jeden Clienten der sich verbindet
	 */
	public void run() {
		System.out.println(socket);
		// Open the IO Streams for all methods
		Server_Logic.activateIOStreams(socket);
		
		String command;
		try {
			do {
				/*
				 * Der Client sendet als erstes ein Kommando -> Server wartet
				 * Dadurch das Client gesendet hat, wissen wir, das der Client 100% jetzt auf den Server warten kann.
				 */

				command = Server_Logic.readCommand();
				Server_Logic.executeCommand(command);

			} while (!command.equals("goodbye"));

			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
