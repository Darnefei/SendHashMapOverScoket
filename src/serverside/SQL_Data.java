package serverside;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.HashMap;

public class SQL_Data {

	private static String host = "localhost";
	private static String port = "3306";
	private static String database = "calender";
	private static String ssl = "?autoReconnect=true&useSSL=false";
	private static String username = "superuser";
	private static String password = "bU}H05KE2Y=.";
	String noteDate;
	static String noteText;
	static Statement stmt;
	static Connection con = null;
	static ResultSet rs;
	static String getPassword;
	static String connectionString;
	static int getPrimID;

	HashMap<Integer, String> noteElement = new HashMap<Integer, String>();
	int i = 0;
	private static int[] splitints;

	public void checkDrivers() throws InstantiationException, IllegalAccessException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

		} catch (ClassNotFoundException e) {
			System.err.println("Keine Treiber-Klasse!");
			return;
		}

	}

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
				if (validatePass==true) {
					

					getPrimID = rs.getInt("primid");
					getPassword = null;
					password=null;
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

		// Return String with Name, PrimID -> kann danach gleich Methode aufrufen für
		// Abholen aller Daten
		return false;

	}

	// Method to get the Data from One User
	public HashMap<Integer, String> getData() throws IllegalAccessException {

		connectionString = "SELECT * FROM entry where eUser ='" + getPrimID + "'";
		ConnectDB();

		try {

			int i = 0;
			
			rs = stmt.executeQuery(connectionString);

			while (rs.next()) {

				noteDate = rs.getInt("eyear") + "-" + rs.getInt("emonth") + "-" + rs.getInt("eday") + "-" +rs.getString("eHour") + "-" 
						+ rs.getString("eminute");
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

	public static boolean createAccount(String Username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

		connectionString = ("select * from user where username= ?");
		ConnectDB();

		PreparedStatement st;
		try {
			

			st = con.prepareStatement(connectionString);
			st.setString(1, Username);
			rs = st.executeQuery();
			password=Server_HashFunction.generateStorngPasswordHash(password);
			

			// st.setString(, eMail);

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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseDB();

		}

		// TODO Auto-generated method stub
		return false;
	}

}
