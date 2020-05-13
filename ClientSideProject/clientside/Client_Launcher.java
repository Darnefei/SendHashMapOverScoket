package clientside;

import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client_Launcher extends Application {

	private static Stage dashboardStage = null;

	@Override
	public void init() {
		System.out.println("Start");

	}

	@Override
	public void start(Stage loginStage) throws IOException {

		System.out.println(loginStage);
		Parent loader = FXMLLoader.load(getClass().getResource("/launcher/fxml/login.fxml"));
		Scene login = new Scene(loader);

		loginStage.setTitle("Kalk20");
		loginStage.setScene(login);
		loginStage.show();

		createDashboard();

	}

	public static void createDashboard() {

		dashboardStage = new Stage();
		dashboardStage.setTitle("Kalk20");

	}

	public static Stage getDashboardStage() {
		return dashboardStage;
	}

	@Override
	public void stop() {
		System.out.println("Stop");
	}

	public static void main(String[] args) {

//

//		
//		boolean serverSuccess;
//		HashMap<String,String> app= new HashMap <String, String>();
//		app.put("2020:05:10:15:30","Eine neuer Termin zum 11.05");

		// Connects to Client to the Server
		boolean StartConnection = Client_ConnectToServer.startConnection();


//		if(StartConnection == false) {
//			System.exit(0);
//		}		


		launch(args);

//		// Gehe in die Methode sendCommand von der Klasse Client_Logic und sende das Kommando login an den Server
//		Client_Logic.sendCommand("login");
//		//Server ist nun bereit die Daten die zu Login passen zu empfangen. Wir senden nun die Daten data (hier ist es Leonhard und 123)
//		serverSuccess = Client_Logic.sendData(data);
//		//wir bekommen einen Boolean Wert zurück => True für erfolg des Logins, False für Fehler
//		System.out.println(serverSuccess);
//		
//		//das selbe für registration
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
