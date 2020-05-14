package serverside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

public class Server_Logic {
	private static final char[] Clientready = new char[2];
	static char[] bufferCommand = new char[20];
	static char[] bufferData = new char[2047];
	static BufferedReader bufferedReader;
	static PrintWriter printWriter;

	// reads the send command from the Client
	public static String readCommand() {

		try {

			int anzahlZeichen = bufferedReader.read(bufferCommand, 0, 20);
			String nachricht = new String(bufferCommand, 0, anzahlZeichen);
			return nachricht;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	// executes the coommand
	public static void executeCommand(String command) {
		if (command.equals("data")) {
			System.out.println(command);
			sendData();

		} else {
			System.out.println(command);
			printWriter.print("[Server]: Ready to get Information");
			printWriter.flush();
			String[] convertedData = readData();
			boolean checkDataBase = false;

			 

			// Login, Registration, newappointment = Boolean zum senden
			// data = HashMap
			if (command.equals("login")) {
				checkDataBase = SQL_Data.checkLogin(convertedData[0], convertedData[1]);
			} else if (command.equals("registration")) {
				try {
					checkDataBase = SQL_Data.createAccount(convertedData[0], convertedData[1]);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(command.contains("newappointment")) {
				checkDataBase = SQL_Data.createEntry(convertedData[0], convertedData[1]);
			} else if(command.contains("goodbye")) {
		
				System.out.println(command + " Client");
				return;
			}

			
				

			

			
			System.out.println(checkDataBase);
			printWriter.print(checkDataBase);
			printWriter.flush();

		}

		// TODO Auto-generated method stub

	}

	// Sendet alles einträge für den Nutzer

	private static void sendData() {
		SQL_Data sql = new SQL_Data();
		try {
			HashMap<Integer, String> dataMap = sql.getData();

			printWriter.print(dataMap.size());
			printWriter.flush();

			int ready;
			try {
				ready = bufferedReader.read(Clientready, 0, 2);
				String isready = new String(bufferData, 0, ready);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			for (int i = 1; i <= dataMap.size(); i++) {

				System.out.println("sende Daten");
				// sendet alles Values einzeln bis i = dataMap.size ist;
				printWriter.print(dataMap.get(i));
				printWriter.flush();

				int anzahlZeichen;
				System.out.println("bekomme Daten");
				try {
					anzahlZeichen = bufferedReader.read(Clientready, 0, 2);
					String nachricht = new String(bufferData, 0, anzahlZeichen);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			int anzahlZeichen;
			try {
				anzahlZeichen = bufferedReader.read(Clientready, 0, 2);
				String nachricht = new String(bufferData, 0, anzahlZeichen);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String[] readData() {
		String nachricht;
		int anzahlZeichen;
		try {
			anzahlZeichen = bufferedReader.read(bufferData, 0, 2000);
			nachricht = new String(bufferData, 0, anzahlZeichen);

			StringBuilder sb = new StringBuilder(nachricht);
			sb.deleteCharAt(nachricht.length() - 1);
			sb.deleteCharAt(0);

			nachricht = sb.toString();

			String[] splitData = nachricht.split("=");

			//System.out.println(splitData[0] + " : " + splitData[1]);

			return splitData;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static void activateIOStreams(Socket socket) {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
