package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Session {

	private final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.univ-grenoble-alpes.fr:1521:im2ag";
	private final String USER = "rognont";
	private final String PASSWD = "b67429290d";

	private static Connection connect;

	private static int sessionCount = 0;
	private int sessionId = -1;
	
	public Connection open(){
		try {
			sessionId = ++sessionCount;

			// Etablissement de la connection
			System.out.print("Connecting to the database with session S" + sessionCount + "... "); 
			connect = DriverManager.getConnection(CONN_URL,USER,PASSWD);
			System.out.println("connected");

			connect.setAutoCommit(false);
			return connect;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Connection getSession() {
		return connect;
	}

	public void close(){
		System.out.println("Closing session S" + sessionId + "...");
		try {
			sessionCount--;
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void setTransactionIsolation(int level) throws SQLException {
		connect.setTransactionIsolation(level);
	}
}
