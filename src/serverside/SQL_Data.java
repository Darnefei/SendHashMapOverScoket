package serverside;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.HashMap;

/**
 * @author MaierL Verbindet und trennt sich von der mySQL_Datenbank. Die
 *         AnmeldeDaten sind zurzeit noch in der Klasse enthalten, können aber
 *         später ausgelagert werden. Holt alle Daten, je nach Kommando, welches
 *         vom Clienten empfangen wird
 */
public class SQL_Data {

	/**
	 * Hoststring
	 */
	private static String host = "localhost";
	/**
	 * Portstring
	 */
	private static String port = "3306";
	/**
	 * DatenbankString
	 */
	private static String database = "calender";

	/**
	 * Nutzername der Datenbank
	 */
	private static String username = "superuser";
	/**
	 * Password des Nutzers
	 */
	private static String password = "bU}H05KE2Y=.";
	/**
	 * Datum des Datenbankelements
	 */
	String noteDate;
	/**
	 * Termineintrag des Datenbankelements
	 */
	static String noteText;
	/**
	 * Statement zum Verbindungsaufbau
	 */
	static Statement stmt;
	/**
	 * Connection con zum Verbinden
	 */
	static Connection con = null;
	/**
	 * ResultSet
	 */
	static ResultSet rs;
	/**
	 * Password des Nutzers (gehast)
	 */
	static String getPassword;
	/**
	 * String für Datensuche / Daten einfügen
	 */
	static String connectionString;
	/**
	 * ID des Nutzers
	 */
	static int getPrimID;

	/**
	 * HashMap in der die Daten gespeichert werden
	 */
	HashMap<Integer, String> noteElement = new HashMap<Integer, String>();
	/**
	 * Zählvariable die Global sein muss
	 */
	int i = 0;
	/**
	 * Aufsplittung des Datums (z.b 2020-20-20 in 2020 20 20
	 */
	private static int[] splitints;

	/**
	 * Überprüft, ob die Treiber (JDBC) richtig sind
	 * 
	 * @throws InstantiationException Errormeldung
	 * @throws IllegalAccessException Errormeldung
	 */
	public void checkDrivers() throws InstantiationException, IllegalAccessException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		} catch (ClassNotFoundException e) {
			System.err.println("Keine Treiber-Klasse!");
			return;
		}

	}

	/**
	 * checkLogin überprüft, ob der Nutzer erlaubt ist sich einzuloggen
	 * 
	 * @param Username Name des Nutzers der sich einloggen will
	 * @param password Passwort des Nutzers (gehast)
	 * @return gibt einen Boolean zurück (true hat funktioniert, sonst false)
	 */
	public static boolean checkLogin(String Username, String password) {
		connectionString = ("SELECT primid,password FROM user Where username = ?");
		ConnectDB();
		PreparedStatement st;
		try {
			boolean validatePass = false;

			st = con.prepareStatement(connectionString);
			st.setString(1, Username);
			rs = st.executeQuery();
			while (rs.next()) {

				getPassword = rs.getString("password");
				try {
					validatePass = Server_HashFunction.validatePassword(password, getPassword);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					e.printStackTrace();
				}
				if (validatePass == true) {

					getPrimID = rs.getInt("primid");
					getPassword = null;
					password = null;
					CloseDB();
					return true;
				} else {
					System.out.println("[SQL] Wrong Password");
					return false;
				}

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			CloseDB();

		}

		System.out.println("[SQL] User does not exist");

		return false;

	}

	/**
	 * Schreibt Kalenderdaten des Nutzers in den Kalender
	 * 
	 * @return gibt die HashMap mit Kalenderdaten zurück
	 * @throws IllegalAccessException Errormeldung
	 */
	public HashMap<Integer, String> getData() throws IllegalAccessException {

		connectionString = "SELECT * FROM entry where eUser = ?";
		ConnectDB();

		PreparedStatement st;
		try {

			int i = 0;
			st = con.prepareStatement(connectionString);
			st.setInt(1, getPrimID);
			rs = st.executeQuery();

			while (rs.next()) {

				noteDate = rs.getInt("eyear") + "-" + rs.getInt("emonth") + "-" + rs.getInt("eday") + "-"
						+ rs.getString("eHour") + "-" + rs.getString("eminute");
				noteText = rs.getString("enote");
				noteElement.put(i, noteDate + "," + noteText);
				i++;

			}

			con.close();
			rs.close();
			stmt.close();

			return noteElement;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			CloseDB();
		}

		return noteElement;

	}

	/**
	 * Erstellt einen Accout
	 * 
	 * @param Username Nutzername
	 * @param password Password des Nutzers
	 * @return true falls vorgaben erfüllt sind, sonst false
	 * @throws NoSuchAlgorithmException Errormeldung
	 * @throws InvalidKeySpecException Errormeldung
	 */
	public static boolean createAccount(String Username, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		connectionString = ("select * from user where username= ?");
		ConnectDB();

		PreparedStatement st;
		try {

			st = con.prepareStatement(connectionString);
			st.setString(1, Username);
			rs = st.executeQuery();
			password = Server_HashFunction.generateStorngPasswordHash(password);

			if (rs.next()) {
				System.out.println("User existiert");
				CloseDB();
				return false;
			} else {
				System.out.println("User wird angelegt");
				String sql = "Insert into user (username, password) values (?,?)";
				st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				st.setString(1, Username);
				st.setString(2, password);
				st.executeUpdate();
				CloseDB();
				return true;

			}
		} catch (SQLException e) {
			System.out.println("existiert");
			e.printStackTrace();
		} finally {
			CloseDB();

		}
		return false;
	}

	/**
	 * Schließt die Datenbankconnection
	 */
	public static void CloseDB() {
		if (con != null)
			try {

				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Baut die Datenbankverbindung auf
	 * @return gibt das Statement zurück
	 */
	public static Statement ConnectDB() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database
					+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
					username, password);
			stmt = con.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;

	}

	/**
	 * 
	 * Erstellt einen neuen Eintrag
	 * @param Date Datum des eintrages
	 * @param Note Eintrag
	 * 
	 * @return true falls es funktioniert, sonst false
	 */
	public static boolean createEntry(String Date, String Note) {

		ConnectDB();
		splitints = new int[5];
		String[] split = Date.split("-");

		for (int i = 0; i < split.length; i++) {

			splitints[i] = Integer.parseInt(split[i]);
		}

		String sql = "Insert into entry (eyear, emonth,eday,ehour,eminute, enote,eUser) values (?,?,?,?,?,?,?)";
		PreparedStatement st;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, splitints[0]);
			st.setInt(2, splitints[1]);
			st.setInt(3, splitints[2]);
			st.setInt(4, splitints[3]);
			st.setInt(5, splitints[4]);
			st.setString(6, Note);
			st.setInt(7, getPrimID);
			st.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseDB();

		}

		// TODO Auto-generated method stub
		return false;
	}

}
