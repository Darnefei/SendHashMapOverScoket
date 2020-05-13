package serverside;

import java.io.IOException;
import java.net.Socket;

public class Server_Thread extends Thread {

	private static Socket socket;

	public Server_Thread(Socket socket) {
		Server_Thread.setSocket(socket);
	}

	public static Socket getSocket() {
		return socket;
	}

	public static void setSocket(Socket socket) {
		Server_Thread.socket = socket;
	}

	public void run() {
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
