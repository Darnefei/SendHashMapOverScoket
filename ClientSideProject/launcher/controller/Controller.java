package launcher.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import clientside.Client_HashFunction;
import clientside.Client_Launcher;
import clientside.Client_Logic;
import clientside.WeatherFetcher;

/*
 * In der Controllerklasse werden alle Scenes und Buttons aufgerufen und initalisiert
 * Zudem sind hier die Methoden zu den Knöpfen und zur sonstigen UI-Logic programmiert
 * Wichtige Methoden hierbei sind:
 * 
 * SignIn() und SignUp() um einen Nutzer einzuloggen oder zu registrieren
 * 
 * loadUI ( int , String )  ->  fügt den Kalendertagen die zugehörigen Daten hinzu indem diese die 
 * Methode readValueofHashMap ( int Year, int Month, int Day) um den Terminstring des aktuellen Tages zu bekommen
 * 
 * In der getActualMonth() Methode wird beim ersten mal einloggen das UI neu geladen.
 * 
 * Durch openNewMeeting() kann ein neuer Termin angelegt werden.
 * 
 * Die meisten anderen Methoden sind zuständig, um mit den verschiedenen Monaten und Jahren zu interagieren.
 * 
 * 
 */

public class Controller {
	static int linesofHash = 1;

	String neuerNutzerName;
	static HashMap<Integer, String> allEntrys;
	public static int actYear;
	public static int actMonth;
	static String[] month = new String[12];
	static Controller uicontroller;
	static Controller uicontrollerloadUI;
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
	private SplitMenuButton Nutzername = new SplitMenuButton();
	@FXML
	private TextField textFieldHour;

	@FXML
	private Label errorLabel1;
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

	private Button buttonDayTwo = new Button();

	@FXML
	private Button buttonDayThree = new Button();

	@FXML
	private Button buttonDayEight = new Button();

	@FXML
	private Button buttonDayNine = new Button();

	@FXML
	private Button buttonDayFifteen = new Button();

	@FXML
	private Button buttonDaySixteen = new Button();

	@FXML
	private Button buttonDayTwentyTwo = new Button();

	@FXML
	private Button buttonDayTwentyFive = new Button();

	@FXML
	private Button buttonDayTwentyFour = new Button();

	@FXML
	private Button buttonDayThirty = new Button();

	@FXML
	private Button buttonDayTwentyNine = new Button();

	@FXML
	private Button buttonDayTwentyThree = new Button();

	@FXML
	private Button buttonDayFourteen = new Button();

	@FXML
	private Button buttonDayTwentyOne = new Button();

	@FXML
	private Button buttonDayTwenty = new Button();

	@FXML
	private Button buttonDayTwentyEight = new Button();

	@FXML
	private Button buttonDayTwentySeven = new Button();

	@FXML
	private Button buttonDayTwentySix = new Button();

	@FXML
	private Button buttonDayTwelve = new Button();

	@FXML
	private Button buttonDayFive = new Button();

	@FXML
	private Button buttonDaySeven = new Button();

	@FXML
	private Button buttonDaySix = new Button();

	@FXML
	private Button buttonDayThirteen = new Button();

	@FXML
	private Button buttonDaySeventeen = new Button();

	@FXML
	private Button buttonDayEighteen = new Button();

	@FXML
	private Button buttonDayNineteen = new Button();

	@FXML
	private Button buttonDayTen = new Button();

	@FXML
	private Button buttonDayFour = new Button();

	@FXML
	private Button buttonDayEleven = new Button();

	@FXML
	private Button buttonDayOne = new Button();

	@FXML
	private Button buttonDayThirtyOne = new Button();

	@FXML
	private Label dayInfo;

	@FXML
	private Label uiButton1 = new Label();

	@FXML
	private Label uiButton2 = new Label();
	@FXML
	private Label uiButton3 = new Label();
	@FXML
	private Label uiButton4 = new Label();
	@FXML
	private Label uiButton5 = new Label();
	@FXML
	private Label uiButton6 = new Label();
	@FXML
	private Label uiButton7 = new Label();
	@FXML
	private Label uiButton8 = new Label();
	@FXML
	private Label uiButton9 = new Label();
	@FXML
	private Label uiButton10 = new Label();
	@FXML
	private Label uiButton11 = new Label();
	@FXML
	private Label uiButton12 = new Label();
	@FXML
	private Label uiButton13 = new Label();
	@FXML
	private Label uiButton14 = new Label();
	@FXML
	private Label uiButton15 = new Label();
	@FXML
	private Label uiButton16 = new Label();
	@FXML
	private Label uiButton17 = new Label();
	@FXML
	private Label uiButton18 = new Label();
	@FXML
	private Label uiButton19 = new Label();
	@FXML
	private Label uiButton20 = new Label();
	@FXML
	private Label uiButton21 = new Label();
	@FXML
	private Label uiButton22 = new Label();
	@FXML
	private Label uiButton23 = new Label();
	@FXML
	private Label uiButton24 = new Label();
	@FXML
	private Label uiButton25 = new Label();
	@FXML
	private Label uiButton26 = new Label();
	@FXML
	private Label uiButton27 = new Label();
	@FXML
	private Label uiButton28 = new Label();
	@FXML
	private Label uiButton29 = new Label();
	@FXML
	private Label uiButton30 = new Label();
	@FXML
	private Label uiButton31 = new Label();

	@FXML
	private BorderPane borderpane = new BorderPane();

	@FXML
	private Label tagesansicht;

	@FXML
	private AnchorPane closePane;

	@FXML
	void login(ActionEvent event) {

		getState = buttonLogin.getText();

		user = username.getText();
		passw = password.getText();
		passw2 = confirmPassword.getText();

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
	private void buttonDay1(MouseEvent event) {

		loadUI(1, "");

	}

	@FXML
	private void buttonDay2(MouseEvent event) {

		loadUI(2, "");
	}

	@FXML
	private void buttonDay3(MouseEvent event) {

		loadUI(3, "");
	}

	@FXML
	private void buttonDay4(MouseEvent event) {

		loadUI(4, "");
	}

	@FXML
	void buttonDay10(MouseEvent event) {
		loadUI(10, "");
	}

	@FXML
	void buttonDay11(MouseEvent event) {
		loadUI(11, "");
	}

	@FXML
	void buttonDay12(MouseEvent event) {
		loadUI(12, "");
	}

	@FXML
	void buttonDay13(MouseEvent event) {
		loadUI(13, "");
	}

	@FXML
	void buttonDay14(MouseEvent event) {
		loadUI(14, "");
	}

	@FXML
	void buttonDay15(MouseEvent event) {
		loadUI(15, "");
	}

	@FXML
	void buttonDay16(MouseEvent event) {
		loadUI(16, "");
	}

	@FXML
	void buttonDay17(MouseEvent event) {
		loadUI(17, "");
	}

	@FXML
	void buttonDay18(MouseEvent event) {
		loadUI(18, "");
	}

	@FXML
	void buttonDay19(MouseEvent event) {
		loadUI(19, "");
	}

	@FXML
	void buttonDay20(MouseEvent event) {
		loadUI(20, "");
	}

	@FXML
	void buttonDay21(MouseEvent event) {
		loadUI(21, "");
	}

	@FXML
	void buttonDay22(MouseEvent event) {
		loadUI(22, "");
	}

	@FXML
	void buttonDay23(MouseEvent event) {
		loadUI(23, "");
	}

	@FXML
	void buttonDay24(MouseEvent event) {
		loadUI(24, "");
	}

	@FXML
	void buttonDay25(MouseEvent event) {
		loadUI(25, "");
	}

	@FXML
	void buttonDay26(MouseEvent event) {
		loadUI(26, "");
	}

	@FXML
	void buttonDay27(MouseEvent event) {
		loadUI(27, "");
	}

	@FXML
	void buttonDay28(MouseEvent event) {
		loadUI(28, "");
	}

	@FXML
	void buttonDay29(MouseEvent event) {
		loadUI(29, "");
	}

	@FXML
	void buttonDay30(MouseEvent event) {
		loadUI(30, "");

	}

	@FXML
	void buttonDay31(MouseEvent event) {
		loadUI(31, "");
	}

	@FXML
	void buttonDay5(MouseEvent event) {
		loadUI(5, "");
	}

	@FXML
	void buttonDay6(MouseEvent event) {
		loadUI(6, "");
	}

	@FXML
	void buttonDay7(MouseEvent event) {
		loadUI(7, "");
	}

	@FXML
	void buttonDay8(MouseEvent event) {
		loadUI(8, "");
	}

	@FXML
	void buttonDay9(MouseEvent event) {
		loadUI(9, "jo");
	}

	@FXML
	private void loadUI(int ui, String datum) {

		linesofHash = 1;

		try {

			datum = readValueofHashMap(actYear, actMonth + 1, ui);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/launcher/fxml/uiButton" + ui + ".fxml"));
			Parent root = loader.load();
			if (datum != null && !datum.isEmpty()) {

				// System.out.println(counto + " " + datum);

				uicontrollerloadUI = loader.getController();

				switch (ui) {
				case 1:

					uicontrollerloadUI.uiButton1.setText(datum);
					uicontroller.buttonDayOne.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 2:
					uicontrollerloadUI.uiButton2.setText(datum);
					uicontroller.buttonDayTwo.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 3:
					uicontrollerloadUI.uiButton3.setText(datum);
					uicontroller.buttonDayThree.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 4:
					uicontrollerloadUI.uiButton4.setText(datum);
					uicontroller.buttonDayFour.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 5:
					uicontrollerloadUI.uiButton5.setText(datum);
					uicontroller.buttonDayFive.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 6:
					uicontrollerloadUI.uiButton6.setText(datum);
					uicontroller.buttonDaySix.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 31:
					uicontrollerloadUI.uiButton31.setText(datum);
					uicontroller.buttonDayThirtyOne.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 7:
					uicontrollerloadUI.uiButton7.setText(datum);
					uicontroller.buttonDaySeven.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 9:
					uicontrollerloadUI.uiButton9.setText(datum);
					uicontroller.buttonDayNine.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 8:
					uicontrollerloadUI.uiButton8.setText(datum);
					uicontroller.buttonDayEight.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 10:
					uicontrollerloadUI.uiButton10.setText(datum);
					uicontroller.buttonDayTen.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 11:
					uicontrollerloadUI.uiButton11.setText(datum);
					uicontroller.buttonDayEleven.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 12:
					uicontrollerloadUI.uiButton12.setText(datum);
					uicontroller.buttonDayTwelve.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 13:
					uicontrollerloadUI.uiButton13.setText(datum);
					uicontroller.buttonDayThirteen.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 14:

					uicontrollerloadUI.uiButton14.setText(datum);
					uicontroller.buttonDayFourteen.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 15:
					uicontrollerloadUI.uiButton15.setText(datum);
					uicontroller.buttonDayFifteen.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 16:
					uicontrollerloadUI.uiButton16.setText(datum);
					uicontroller.buttonDaySixteen.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 17:
					uicontrollerloadUI.uiButton17.setText(datum);
					uicontroller.buttonDaySeventeen.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 18:
					uicontrollerloadUI.uiButton18.setText(datum);
					uicontroller.buttonDayEighteen.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 19:
					uicontrollerloadUI.uiButton19.setText(datum);
					uicontroller.buttonDayNineteen.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 20:
					uicontrollerloadUI.uiButton20.setText(datum);
					uicontroller.buttonDayTwenty.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 21:
					uicontrollerloadUI.uiButton21.setText(datum);
					uicontroller.buttonDayTwentyOne.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 22:
					uicontrollerloadUI.uiButton22.setText(datum);
					uicontroller.buttonDayTwentyTwo.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 23:
					uicontrollerloadUI.uiButton23.setText(datum);
					uicontroller.buttonDayTwentyThree.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 24:
					uicontrollerloadUI.uiButton24.setText(datum);
					uicontroller.buttonDayTwentyFour.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 25:
					uicontrollerloadUI.uiButton25.setText(datum);
					uicontroller.buttonDayTwentyFive.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 26:
					uicontrollerloadUI.uiButton26.setText(datum);
					uicontroller.buttonDayTwentySix.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 27:
					uicontrollerloadUI.uiButton27.setText(datum);
					uicontroller.buttonDayTwentySeven.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 28:
					uicontrollerloadUI.uiButton28.setText(datum);
					uicontroller.buttonDayTwentyEight.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 29:
					uicontrollerloadUI.uiButton29.setText(datum);
					uicontroller.buttonDayTwentyNine.setStyle("-fx-background-color: #6b97b5;");
					break;
				case 30:
					uicontrollerloadUI.uiButton30.setText(datum);
					uicontroller.buttonDayThirty.setStyle("-fx-background-color: #6b97b5;");
					break;

				}

			}

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

	String readValueofHashMap(int Year, int Month, int Day) {
		

		String dateandEntry;
		String date;
		String note = null;
		String[] splitter;
		String allNote = "";
		int[] splitints = new int[5];

		// iterriert die ganze HashMap durch
		while (allEntrys.containsKey(linesofHash)) {
			// System.out.println(allEntrys.get(linesofHash));

			dateandEntry = allEntrys.get(linesofHash);
			splitter = dateandEntry.split(",", 2);
			date = splitter[0];
			note = splitter[1];
			splitter = date.split("-");

			for (int i = 0; i < splitter.length; i++) {

				splitints[i] = Integer.parseInt(splitter[i]);
			}

			if (splitints[0] == Year) {
				if (splitints[1] == Month) {
					if (splitints[2] == Day) {

						allNote += "\t" + splitints[3] + ":" + splitints[4] + "\n\t" + note + "\n\n";
					}

				}

			}

			linesofHash++;
		}

		System.out.println(allNote);

		return allNote;

	}

	private void getActualMonth() {
		Client_Logic.sendCommand("data");
		allEntrys = new HashMap<Integer, String>();
		allEntrys = Client_Logic.getData();

		Date date = new Date();
		SimpleDateFormat formatterMonth = new SimpleDateFormat("MM");
		SimpleDateFormat formatterYear = new SimpleDateFormat("YYYY");
		String actualMonth = formatterMonth.format(date);
		String actualYear = formatterYear.format(date);
		actMonth = Integer.parseInt(actualMonth) - 1;
		actYear = Integer.parseInt(actualYear);

		for (int i = 1; i <= 31; i++) {
			loadUI(i, "");
		}

		uicontroller.Nutzername.setText("Hallo " + user);
		uicontroller.actualDate.setText(actYear + " " + month[actMonth]);

	}

	private void getDaysperMonth() {

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

			uicontroller.buttonDayThirty.setDisable(false);
			uicontroller.buttonDayThirty.setVisible(true);
			uicontroller.buttonDayTwentyNine.setDisable(false);
			uicontroller.buttonDayTwentyNine.setVisible(true);
			uicontroller.buttonDayThirtyOne.setDisable(true);
			uicontroller.buttonDayThirtyOne.setVisible(false);
			break;

		case "February":
			if(actYear%4==0) {
				uicontroller.buttonDayThirtyOne.setDisable(true);
				uicontroller.buttonDayThirtyOne.setVisible(false);
				uicontroller.buttonDayThirty.setDisable(true);
				uicontroller.buttonDayThirty.setVisible(false);
				uicontroller.buttonDayTwentyNine.setDisable(false);
				uicontroller.buttonDayTwentyNine.setVisible(true);
				
			} else {
			uicontroller.buttonDayThirtyOne.setDisable(true);
			uicontroller.buttonDayThirtyOne.setVisible(false);
			uicontroller.buttonDayThirty.setDisable(true);
			uicontroller.buttonDayThirty.setVisible(false);
			uicontroller.buttonDayTwentyNine.setDisable(true);
			uicontroller.buttonDayTwentyNine.setVisible(false);
			break;
			}
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
			setActualDate();

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

		String cutDate = datum.toString();
		String[] splitter = cutDate.split("-");
		int splitterint = Integer.parseInt(splitter[2]);

		String wholeDate = datum.toString() + "-" + hour + "-" + min;

		if (hour.equals("") || min.equals("") || meetingNamen.equals("")) {
			errorLabel1.setText("Bitte alle Felder befüllen");

			return;

		} else if (!min.matches("[0-9]+")) {
			errorLabel1.setText("Es sind nur Zahlen von 0 bis 60 erlaubt");
			return;

		} else if (!hour.matches("[0-9]+")) {

			errorLabel1.setText("Es sind nur Zahlen von 0 bis 23 erlaubt");
			return;
		}
		int hourint = Integer.parseInt(hour);
		int minint = Integer.parseInt(min);

		if (!(hourint >= 0 && hourint < 24)) {
			meetingcontroller.errorLabel1.setText("Es sind nur Zahlen von 0 bis 23 erlaubt");
			return;
		} else if (!(minint >= 0 && minint < 60)) {

			meetingcontroller.errorLabel1.setText("Es sind nur Zahlen von 0 bis 59 erlaubt");
			return;
		} else {

			Client_Logic.sendCommand("setupnewappointment");
			UserData.put(wholeDate, meetingNamen);
			allEntrys.put(linesofHash, wholeDate + "," + meetingNamen);
			linesofHash++;
			Client_Logic.sendData(UserData);
			UserData.remove(wholeDate);

			loadUI(splitterint, "");
			
			Stage stage = (Stage) addMeeting.getScene().getWindow();
			stage.close();
		}

	}

	@FXML
	void nextMonth(MouseEvent event) throws IOException {

		if (actMonth == 11) {
			actMonth = 0;
			actYear += 1;
		} else {
			actMonth += 1;
		}
		settogray();
		actualDate.setText(actYear + " " + month[actMonth]);
		getDaysperMonth();
		for (int i = 1; i <= 31; i++) {
			loadUI(i, "");
		}
		

	}

	@FXML
	void prevMonth(MouseEvent event) throws IOException {

		if (actMonth == 0) {
			actMonth = 11;
			actYear -= 1;
		} else {
			actMonth -= 1;
		}

		settogray();
		actualDate.setText(actYear + " " + month[actMonth]);
		getDaysperMonth();
		for (int i = 1; i <= 31; i++) {
			loadUI(i, "");
		}
		// readValueofHashMap(actYear, actMonth);
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
				errorLabel.setText("Passwoerter muessen gleich sein");
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

	public void openDashboard() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/launcher/fxml/dashboard.fxml"));
			Parent root = loader.load();
			uicontroller = loader.getController();

			WeatherFetcher.getWeather();
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

			Client_Launcher.getDashboardStage().setScene(new Scene(root));
			Client_Launcher.getDashboardStage().show();

			// new Client_Launcher().start()(root));

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	void settogray() {

		uicontroller.buttonDayOne.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwo.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayThree.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayFour.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayFive.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDaySix.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayThirtyOne.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDaySeven.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayNine.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayEight.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTen.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayEleven.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwelve.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayThirteen.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayFourteen.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayFifteen.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDaySixteen.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDaySeventeen.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayEighteen.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayNineteen.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwenty.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwentyOne.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwentyTwo.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwentyThree.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwentyFour.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwentyFive.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwentySix.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwentySeven.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwentyEight.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayTwentyNine.setStyle("-fx-background-color  #6b97b5;");

		uicontroller.buttonDayThirty.setStyle("-fx-background-color  #6b97b5;");

	}
}
