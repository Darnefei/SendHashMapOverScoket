package clientside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Client_Logic {

	static int length;

	private static BufferedReader bufferedReader;
	private static PrintWriter printWriter;
	private static char[] buffergetStatus = new char[40];
	private static char[] getStatus = new char[5];
	private static char[] bufferInteger = new char[5];
	private static char[] bufferAppointment = new char[2000];
	private static HashMap<Integer, String> getData = new HashMap<Integer, String>();

	
	public static void sendCommand(String command) {
		printWriter.print(command);
		printWriter.flush();

		System.out.println("[Client] " + command + " was send ");
	}

	// Senden der Daten (login, appointment und register)
	public static boolean sendData(HashMap<String, String> data) {

		try {

			int anzahlZeichen = bufferedReader.read(buffergetStatus, 0, 40);
			// last handshake, before sending all the data
			String nachricht = new String(buffergetStatus, 0, anzahlZeichen);
			System.out.println(nachricht);
			printWriter.print(data);
			printWriter.flush();
			System.out.println("[Client] Data was send");
			System.out.println("[Client] Wait for Server response");

			anzahlZeichen = bufferedReader.read(getStatus, 0, 5);
			String getBoolean = new String(getStatus, 0, 5);
			boolean status;
			if (getBoolean.contains("true")) {
				status = true;
				return status;

			} else if (getBoolean.contains("false")) {
				status = false;
				return status;
			}

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

	// bekommen der ganzen Daten (alle Termine)


	public static HashMap<Integer, String> getData() {

		int anzahlZeichen;
		try {
			// Hier eine schleife machen, damit der Server einzeln die Hashmap senden kann /
			// die Strings. Dadurch können wir kleine reservierte Speicher verwenden und
			// später in eine Hashmap
			// am lokalen PC zusammenfügen
			anzahlZeichen = bufferedReader.read(bufferInteger, 0, 5);
			String lengthofMap = new String(bufferInteger, 0, anzahlZeichen);

			printWriter.print("ok");
			printWriter.flush();

			int length = Integer.parseInt(lengthofMap);
			System.out.println(length);

			length = Integer.parseInt(lengthofMap);
			System.out.println(length);

			for (int i = 1; i < length; i++) {
				anzahlZeichen = bufferedReader.read(bufferAppointment, 0, 2000);
				String nachricht = new String(bufferAppointment, 0, anzahlZeichen);

				System.out.println(nachricht + " " + i);
				getData.put(i, nachricht);



				// convertedData.put(dateEntry[0], dateEntry[1]);


				printWriter.print("ok");
				printWriter.flush();

			}

			System.out.println(getData);
			System.out.println("geschafft");
			return getData;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}



	public static void activateIOStreams(Socket socket) {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
