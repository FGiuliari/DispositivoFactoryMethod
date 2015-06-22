package DataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/*
 * Classe che si occupa di interfacciare le query disponibili in modo semplice e leggibile
 */
import Models.Dispositivo;

public class DatabaseDriver {
	private static DatabaseDriver instance;
	private Connection connection;

	private static ReservedReader DBCredential; // Evito le credenziali
												// hard-coded. Esse sono in un
												// file inaccessibile
	private static String dbFile = "credential_DBMS.txt";
	private static String separator = "=>";

	private DatabaseDriver() {
		super();
	}

	public static DatabaseDriver getInstance() {
		if (instance == null) {
			instance = new DatabaseDriver();
			DBCredential = new ReservedReader(instance, dbFile, separator);

			System.out.println(DBCredential.getValue("driver"));
			System.out.println(DBCredential.getValue("url"));
			System.out.println(DBCredential.getValue("username"));
			System.out.println(DBCredential.getValue("password"));
		}
		return instance;
	}

	private void checkInstantiation() {
		if (instance == null) {
			System.out
					.println("Non aprire connessioni senza prima avere istanziato l'oggetto!");
			instance = new DatabaseDriver();
		}
	}

	/*
	 * L'utene ha la possibilità di aprirsi e chiudersi la connessione a
	 * piacere. In questo modo se deve eseguire più query non deve continuamente
	 * aprire e chiudere la connessione
	 */
	public void openConnection() {
		checkInstantiation();
		try {
			Driver myDriver = (Driver) Class.forName(
					DBCredential.getValue("driver")).newInstance();
			DriverManager.registerDriver(myDriver);
			// creazione della connessione
			connection = DriverManager.getConnection(
					DBCredential.getValue("url"),
					DBCredential.getValue("username"),
					DBCredential.getValue("password"));
		} catch (ClassNotFoundException e) {
			System.out.println("Driver non trovato");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Conessione al database non riuscita");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			while (e.getNextException() instanceof SQLException)
				e.printStackTrace();
		}
	}
	


	public boolean setPort(int id_dis,String ip, int socketport) {
		PreparedStatement stmt = null;
		String sql = Query.getInstance().getQuery("update_port");

		checkInstantiation();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, ip);
			stmt.setInt(2, socketport);
			stmt.setInt(3, id_dis);

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}

}
