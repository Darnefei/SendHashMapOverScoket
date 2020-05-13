package launcher.controller;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.YearMonth;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import clientside.FullCalendarView;
import clientside.Client_HashFunction;
import clientside.Client_Launcher;
import clientside.Client_Logic;

public class Controller implements Initializable {

	Date ds = new Date();
	String gz = ds.toString();
	String getState;
	String user;
	String passw;
	String passw2;

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
	private Label actualDate;

	@FXML
	private Button nextMonth;

	@FXML
	private Button prevMonth;

	@FXML
	private Button newMeeting;

	@FXML
	private Button editMeeting;

	private int waittime = 0;
	private int count = 0;
	private HashMap<String, String> UserData = new HashMap<String, String>();
	private boolean serverStatus;

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

		errorLabel.setText("Willkommen");
		confirmPassword.setVisible(false);
		confirmPassword.setDisable(true);
		buttonLogin.setText("Sign In");

	}

	@FXML
	void signup(MouseEvent event) {
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		errorLabel.setStyle("-fx-text-fill: grey;");
		errorLabel.setText("Registrieren");
		confirmPassword.setVisible(true);
		confirmPassword.setDisable(false);
		buttonLogin.setText("Sign Up");

	}

	@FXML
	void newMeeting(MouseEvent event) {
		actualDate.setText("hi");
		openNewMeeting();
	}

	@FXML
	void editMeeting(MouseEvent event) {
		getActualDate();

	}

	public void openNewMeeting() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/launcher/fxml/meeting.fxml"));
			Parent root = loader.load();

			Stage Meeting = new Stage();
			Meeting.setScene(new Scene(root));
			Meeting.setAlwaysOnTop(true);
			Meeting.initModality(Modality.APPLICATION_MODAL);
			Meeting.show();

		} catch (IOException e) {
			System.err.println(e);
		}
	}
	// public void openDashboard() {
	// Stage primaryStage = new Stage();
	// primaryStage.setTitle("Full Calendar Example");
	// primaryStage.setScene(new Scene(new
	// FullCalendarView(YearMonth.now()).getView()));
	// primaryStage.show();
	// }

	public void openDashboard() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/launcher/fxml/dashboard.fxml"));
			Parent root = loader.load();

			Client_Launcher.getDashboardStage().setScene(new Scene(root));
			Client_Launcher.getDashboardStage().show();

		} catch (IOException e) {
			System.err.println(e);
		}

	}

	private void getActualDate() {
		Date dt = new Date();
		String ab = dt.toString();
		actualDate.setText(ab);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String g = arg0.toString();
		if (g.contains("dashboard")) {
			getActualDate();
		}
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
				errorLabel.setText("Passwörter müssen gleich sein");
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