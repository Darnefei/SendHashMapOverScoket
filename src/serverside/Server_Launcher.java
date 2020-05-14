package serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Launcher {
	
	static Socket socket;

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		ServerSocket ss = Server_Setup.startUpServer();
		
		while (true) {

			System.out.println("[Server] Wait for Client to connect");
			socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
			System.out.println("Connection from " + socket + "!");
			System.out.println("New client connected");

			new Server_Thread(socket).start();

		}

	}
	public static void endConnection(){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
