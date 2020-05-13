package launcher.controller;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import clientside.FullCalendarView;
import clientside.Client_HashFunction;
import clientside.Client_Launcher;
import clientside.Client_Logic;

public class Controller {

	static int linesofHash = 0;
	
	
	public static int actMonth;
	static String[] month = new String[12];
	static Controller uicontroller;
	static Controller meetingcontroller;
	Date ds = new Date();
	String gz = ds.toString();
	String getState;
	String user;
	String passw;
	String passw2;
	private int waittime = 0;
	private int count = 0;
	private HashMap<String, String> UserData = new HashMap<String, String>();
	private boolean serverStatus;

	@FXML
	private DatePicker datePicker = new DatePicker();

	@FXML
	private TextField textFieldHour;

	@FXML
	private Button addMeeting;

	@FXML
	private TextField textFieldMinute;

	@FXML
	private TextField meetingName;

	@FXML
	private Button signin;

	@FXML
	private Button signup;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Button buttonLogin;

	@FXML
	private Label errorLabel;

	@FXML
	private PasswordField confirmPassword;

	@FXML
	private Label weatherData;

	@FXML
	private Label actualDate = new Label();

	@FXML
	private Button nextMonth;

	@FXML
	private Button prevMonth;

	@FXML
	private Button newMeeting;

	@FXML
	private Button editMeeting;

	@FXML

	private Button buttonDayTwo;

	@FXML
	private Button buttonDayThree;

	@FXML
	private Button buttonDayEight;

	@FXML
	private Button buttonDayNine;

	@FXML
	private Button buttonDayFifteen;

	@FXML
	private Button buttonDaySixteen;

	@FXML
	private Button buttonDayTwentyTwo;

	@FXML
	private Button buttonDayTwentyFive;

	@FXML
	private Button buttonDayTwentyFour;

	@FXML
	private Button buttonDayThirty;

	@FXML
	private Button buttonDayTwentyNine;

	@FXML
	private Button buttonDayTwentyThree;

	@FXML
	private Button buttonDayFourteen;

	@FXML
	private Button buttonDayTwentyOne;

	@FXML
	private Button buttonDayTwenty;

	@FXML
	private Button buttonDayTwentyEight;

	@FXML
	private Button buttonDayTwentySeven;

	@FXML
	private Button buttonDayTwentySix;

	@FXML
	private Button buttonDayTwelve;

	@FXML
	private Button buttonDayFive;

	@FXML
	private Button buttonDaySeven;

	@FXML
	private Button buttonDaySix;

	@FXML
	private Button buttonDayThirteen;

	@FXML
	private Button buttonDaySeventeen;

	@FXML
	private Button buttonDayEighteen;

	@FXML
	private Button buttonDayNineteen;

	@FXML
	private Button buttonDayTen;

	@FXML
	private Button buttonDayFour;

	@FXML
	private Button buttonDayEleven;

	@FXML
	private Button buttonDayOne;

	@FXML
	private Button buttonDayThirtyOne;

	@FXML
	private Label dayInfo;

	@FXML
	private Label uiButtonOne = new Label();

	@FXML
	private Label uiButtonThree = new Label();

	@FXML
	private BorderPane borderpane;

	@FXML
	private Label tagesansicht;

	@FXML
	void login(ActionEvent event) {

		getState = buttonLogin.getText();

		user = username.getText();
		passw = password.getText();
		passw2 = confirmPassword.getText();

		Stage stage = (Stage) signin.getScene().getWindow();
		stage.close();
		openDashboard();

//		if (getState.equals("Sign In")) {
//			SignInMethod();
//
//		} else if (getState.equals("Sign Up")) {
//
//			SignUpMethod();
//		}

//		Stage stage = (Stage) signin.getScene().getWindow();
//		stage.close();
//		openDashboard();

		if (getState.equals("Sign In")) {
			SignInMethod();

		} else if (getState.equals("Sign Up")) {

			SignUpMethod();
		}

	}

	@FXML
	void signin(MouseEvent event) {

		confirmPassword.setVisible(false);
		confirmPassword.setDisable(true);
		buttonLogin.setText("Sign In");

	}

	@FXML
	void signup(MouseEvent event) {

		confirmPassword.setVisible(true);
		confirmPassword.setDisable(false);
		buttonLogin.setText("Sign Up");

	}

	@FXML
	private void buttonDayOne(MouseEvent event) {

		
		loadUI("1", "yiipie ei yah");

	}

	@FXML
	private void buttonDayTwo(MouseEvent event) {
		uiButtonOne.setText("hallo du");
		loadUI("2", "Termin");
	}

	@FXML
	private void buttonDayThree(MouseEvent event) {
		uiButtonOne.setText("hallo du");
		loadUI("3", "noch ein termin");
	}

	@FXML
	private void buttonDayFour(MouseEvent event) {

		// String s = getactualDate() -> aktuelles Datum des Knopfes
		// getDatenaus HashMap -> Hashmap suchen nach Datum -> Eintrag
		// getDatenausHashmap soll erst enden, wenn alle Daten die das Datum haben
		// gelesen wurden.
		// getDatenausHashmap sortieren nach Stunden und Minuten angezeigt werden muss
		// eigentlich dann nur noch Stunden:Mintunen (Jahr:Monat:Tag erschlieﬂt sich aus
		// UI)
		// wir bekommen sendString raus.

		loadUI("4", "sendString");
	}

	private void loadUI(String ui, String datum) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/launcher/fxml/uiButton" + ui + ".fxml"));
			Parent root = loader.load();

			uicontroller = loader.getController();
			uicontroller.uiButtonOne.setText(datum);

			borderpane.setCenter(root);
		} catch (IOException e) {

			System.out.println(e);
		}

	}

	public void getMonths() {

		String[] monthtinayear = { "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };

		for (int i = 0; i < 12; i++) {
			month[i] = monthtinayear[i];
		}

	}
	// String s = getactualDate() -> aktuelles Datum des Knopfes
			// getDatenaus HashMap -> Hashmap suchen nach Datum -> Eintrag
			// getDatenausHashmap soll erst enden, wenn alle Daten die das Datum haben
			// gelesen wurden.
			// getDatenausHashmap sortieren nach Stunden und Minuten angezeigt werden muss
			// eigentlich dann nur noch Stunden:Mintunen (Jahr:Monat:Tag erschlieﬂt sich aus
			// UI)
			// wir bekommen sendString raus.
	
	//----------------------------------------------------Mehotde---------------------
	//hier werden die Daten geholt!!!!
	//‹bergeben werden muss Jahr/Monat
	void readValueofHashMap(int Year, int Month){
		//2020-12
		HashMap<Integer, String> allEntrys = new HashMap<Integer, String>();
		allEntrys = Client_Logic.getData();
		String dateandEntry;
		String date;
		String note;
		String[] splitter;
		String allNote = null;
		int[] splitints = new int[5];
		
		//iterriert die ganze HashMap durch
		while(allEntrys.containsKey(linesofHash)) {
			dateandEntry = allEntrys.get(linesofHash);
			splitter = dateandEntry.split("," , 2);
			date=splitter[0]; note = splitter[1];
			splitter = date.split("-");
			
			for (int i = 0; i < splitter.length; i++) {

				splitints[i] = Integer.parseInt(splitter[i]);
			}		
			
			if(splitints[0]==Year) {
				if(splitints[1]==Month) {
					allNote += splitints[3] + ":" + splitints[4] + "\n" + note + "\n\n";
					
					
				}
				
				
			}
			
			
			
			linesofHash++;
			
		}
		loadUI(Integer.toString(splitints[2]), allNote );
		
		
	}
	
	
	public void openDashboard() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/launcher/fxml/dashboard.fxml"));
			Parent root = loader.load();
			uicontroller = loader.getController();

			/*
			 * FXMLLoader lloader = new
			 * FXMLLoader(getClass().getResource("/launcher/fxml/uiButton3.fxml"));
			 * Controller uuicontroller = lloader.getController();
			 * uuicontroller.uiButtonThree.setText("");
			 * 
			 * if(uiButtonThree.getText().equals("")) {
			 * buttonDayThree.setStyle("-fx-border-color: red"); }
			 */
			getMonths();
			getActualMonth();
			getDaysperMonth();

			// new Client_Launcher().start()(root));
			Client_Launcher.getDashboardStage().setScene(new Scene(root));
			Client_Launcher.getDashboardStage().show();

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void getActualMonth() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		String actualMonth = formatter.format(date);
		actMonth = Integer.parseInt(actualMonth) - 1;

		uicontroller.actualDate.setText(month[actMonth]);

	}

	private void getDaysperMonth() {

		// braucht evtl noch eine Methode f¸r aktuelles Datum?

		switch (month[actMonth]) {
		case "January":
		case "March":
		case "May":
		case "July":
		case "August":
		case "October":
		case "December":

			uicontroller.buttonDayThirtyOne.setDisable(false);
			uicontroller.buttonDayThirtyOne.setVisible(true);
			uicontroller.buttonDayThirty.setDisable(false);
			uicontroller.buttonDayThirty.setVisible(true);
			uicontroller.buttonDayTwentyNine.setDisable(false);
			uicontroller.buttonDayTwentyNine.setVisible(true);
			break;

		case "April":
		case "June":
		case "September":
		case "November":

			System.out.println("Es ist " + month);
			uicontroller.buttonDayThirty.setDisable(false);
			uicontroller.buttonDayThirty.setVisible(true);
			uicontroller.buttonDayTwentyNine.setDisable(false);
			uicontroller.buttonDayTwentyNine.setVisible(true);
			uicontroller.buttonDayThirtyOne.setDisable(true);
			uicontroller.buttonDayThirtyOne.setVisible(false);
			break;

		case "February":
			System.out.println("Es ist " + month);
			uicontroller.buttonDayThirtyOne.setDisable(true);
			uicontroller.buttonDayThirtyOne.setVisible(false);
			uicontroller.buttonDayThirty.setDisable(true);
			uicontroller.buttonDayThirty.setVisible(false);
			uicontroller.buttonDayTwentyNine.setDisable(true);
			uicontroller.buttonDayTwentyNine.setVisible(false);
			break;
		}

	}

	@FXML
	void newMeeting(MouseEvent event) {
		openNewMeeting();
	}

	@FXML
	void editMeeting(MouseEvent event) {

	}

	public void openNewMeeting() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/launcher/fxml/meeting.fxml"));
			Parent root = loader.load();

			meetingcontroller = loader.getController();
			// actuelles Datum auslesen + String -> in die HashMap rein. Aktualisiere Daten
			// (Knopf)
			// Senden an Datenbank

			setActualDate();

			// actuelles Datum auslesen + String -> in die HashMap rein. Aktualisiere Daten
			// (Knopf)
			// Senden an Datenbank


			Stage Meeting = new Stage();
			Meeting.setScene(new Scene(root));
			Meeting.setAlwaysOnTop(true);
			Meeting.show();

		} catch (IOException e) {
			System.err.println(e);
		}
	}


	public void setActualDate() {
		meetingcontroller.datePicker.setValue(LocalDate.now());
	}

	@FXML
	private void addMeeting() {
		LocalDate datum = datePicker.getValue();
		String hour = textFieldHour.getText();
		String min = textFieldMinute.getText();
		String meetingNamen = meetingName.getText();
		String hourmin = hour + "-" + min + "," + meetingNamen;

		System.out.println(datum + "-" + hourmin);

		Stage stage = (Stage) addMeeting.getScene().getWindow();
		stage.close();

	}



	@FXML
	void nextMonth(MouseEvent event) throws IOException {


		/*
		 * FXMLLoader loader = new
		 * FXMLLoader(getClass().getResource("/launcher/fxml/dashboard.fxml"));
		 * Controller uicontroller = loader.getController();
		 * uicontroller.actualDate.setText("February");
		 */

		if (actMonth == 11) {
			actMonth = 0;
		} else {
			actMonth += 1;
		}
		actualDate.setText(month[actMonth]);
		getDaysperMonth();

	}

	@FXML
	void prevMonth(MouseEvent event) throws IOException {

		if (actMonth == 0) {
			actMonth = 11;
		} else {
			actMonth -= 1;
		}

		actualDate.setText(month[actMonth]);

		getDaysperMonth();
	}

	private void SignUpMethod() {

		username.clear();
		username.setPromptText("Nutzername");
		password.clear();
		password.setPromptText("Passwort");
		confirmPassword.clear();
		confirmPassword.setPromptText("Wiederholen");
		if (!user.isEmpty() && !passw.isEmpty() && !passw2.isEmpty()) {
			errorLabel.setStyle("-fx-text-fill: red;");
			if (user.length() < 7) {
				errorLabel.setText("Nutzer muss mind. 7 Zeichen haben");
				return;
			}
			if (!passw.equals(passw2)) {
				errorLabel.setText("Passwˆrter m¸ssen gleich sein");
				return;
			}
			if (passw.length() < 8) {
				errorLabel.setText("Passwort muss mind. 8 Zeichen lang sein");
				return;

			}
			if (passw.contains("=") || passw.contains(":") || passw.contains("-")) {
				errorLabel.setText("Zeichen sind nicht erlaubt");
				return;
			}
			Client_Logic.sendCommand("registration");
			try {
				passw = Client_HashFunction.generateStorngPasswordHash(passw);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
			UserData.put(user, passw);
			serverStatus = Client_Logic.sendData(UserData);
			if (serverStatus == true) {
				username.clear();
				password.clear();
				errorLabel.setStyle("-fx-text-fill: green;");
				errorLabel.setText("Erfolgreich registriert");
				UserData.remove(user);
				confirmPassword.setVisible(false);
				confirmPassword.setDisable(true);
				buttonLogin.setText("Sign In");
			} else {
				UserData.remove(user);
				count++;
				errorLabel.setText("Name vergeben");
				if (count % 3 == 0) {
					try {
						waittime++;
						System.out.println("Bitte warten Sie " + waittime * 3 + " Sekunden!");
						TimeUnit.SECONDS.sleep(3 * waittime);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else {
			errorLabel.setText("Bitte Daten eingeben");
			errorLabel.setStyle("-fx-text-fill: red;");
			errorLabel.setDisable(false);
		}
	}

	private void SignInMethod() {

		if (!user.isEmpty() && !passw.isEmpty()) {
			Client_Logic.sendCommand("login");
			try {
				passw = Client_HashFunction.generateStorngPasswordHash(passw);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
			UserData.put(user, passw);
			serverStatus = Client_Logic.sendData(UserData);
			if (serverStatus == true) {
				Stage stage = (Stage) signin.getScene().getWindow();
				stage.close();
				openDashboard();
			} else {
				UserData.remove(user);
				count++;
				System.out.println("Falsche Zugangsdaten");
				if (count % 3 == 0) {
					try {
						waittime++;
						System.out.println("Bitte warten Sie " + waittime * 3 + " Sekunden!");
						TimeUnit.SECONDS.sleep(3 * waittime);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else {
			errorLabel.setText("Bitte Daten eingeben");
			errorLabel.setStyle("-fx-text-fill: red;");
			errorLabel.setDisable(false);
		}

	}

}
