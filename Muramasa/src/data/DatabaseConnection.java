package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String URL = "jdbc:postgresql://dpg-csr265pu0jms738lo7u0-a.singapore-postgres.render.com:5432/muramasa_database_yuzu";
	private static final String USER = "muramasa_database_yuzu_user";
	private static final String PASSWORD = "nektsAaw28GdNzxba8iri0usHkLaupii";

	public Connection getConnection() {
	    Connection connection = null;
	    try {
	        connection = DriverManager.getConnection(URL, USER, PASSWORD);
	    } catch (SQLException e) {
	        System.err.println("Failed!");
	        e.printStackTrace();
	    }
	    return connection;
	}
}
