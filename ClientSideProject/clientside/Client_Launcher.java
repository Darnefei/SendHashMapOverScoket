package clientside;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author MaierL
 *
 */
public class Client_Launcher extends Application {

	/**
	 * globalbe Stage dashboardStage
	 */
	private static Stage dashboardStage = null;

	/**
	 * not used
	 */
	@Override
	public void init() {
		
	}

	/**
	 *Stage launcher wird aufgebaut
	 */
	@Override
	public void start(Stage loginStage) throws IOException {

		Parent loader = FXMLLoader.load(getClass().getResource("/launcher/fxml/login.fxml"));
		Scene login = new Scene(loader);

		loginStage.setTitle("Kalk20");
		loginStage.setScene(login);
		loginStage.setResizable(false);
		loginStage.show();
		

		createDashboard();

	}

	/**
	 * baut das aeu�ere Dashbaord auf
	 */
	public static void createDashboard() {

		dashboardStage = new Stage();
		dashboardStage.setTitle("Kalk20");

	}

	/**
	 * Gibt die Stage zurueck
	 * @return gibt die Stage zurueck
	 */
	public static Stage getDashboardStage() {
		return dashboardStage;
	}

	/**
	 * Stop die Applikation
	 */
	@Override
	public void stop() {
		System.out.println("Stop");
		Client_Logic.sendCommand("Servergoodbye");
		
	}

	/**
	 * main die die Method StartConnection und die UI aufruft
	 * @param args f�r JavaFx
	 */
	public static void main(String[] args) {

//

//		
//		boolean serverSuccess;
//		HashMap<String,String> app= new HashMap <String, String>();
//		app.put("2020:05:10:15:30","Eine neuer Termin zum 11.05");

		// Connects to Client to the Server
		boolean StartConnection = Client_ConnectToServer.startConnection();


		if(StartConnection == false) {
			System.exit(0);
		}		


		launch(args);

//		// Gehe in die Methode sendCommand von der Klasse Client_Logic und sende das Kommando login an den Server
//		Client_Logic.sendCommand("login");
//		//Server ist nun bereit die Daten die zu Login passen zu empfangen. Wir senden nun die Daten data (hier ist es Leonhard und 123)
//		serverSuccess = Client_Logic.sendData(data);
//		//wir bekommen einen Boolean Wert zur�ck => True f�r erfolg des Logins, False f�r Fehler
//		System.out.println(serverSuccess);
//		
//		//das selbe f�r registration
//		Client_Logic.sendCommand("registration");
//		serverSuccess = Client_Logic.sendData(data);
//		System.out.println(serverSuccess);
//		
//		
//		
//		//Senden newappointment zu dem Server
//		Client_Logic.sendCommand("newappointment");
//		//Server ist bereit ein appoitnment zu bekommen -> Wir senden app (2020:05:10:15:30","Eine neuer Termin)
//		serverSuccess = Client_Logic.sendData(app);
//		System.out.println(serverSuccess);
//		
//		HashMap<String,String> data = new HashMap<String, String>();
//	HashMap<Integer,String> getDataMap = new HashMap<Integer,String>();
//		data.put("Leonhard", "123");
//	
//	Client_Logic.sendCommand("login");
//	boolean serverSuccess = Client_Logic.sendData(data);
//	
//	Client_Logic.sendCommand("data");
//	
//	getDataMap = Client_Logic.getData();
//	System.out.println(getDataMap);
//	Client_Logic.sendCommand("data");
//	getDataMap = Client_Logic.getData();
////
//		System.out.println(getDataMap);
	}

}
