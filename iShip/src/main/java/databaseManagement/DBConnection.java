package databaseManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import config.ApplicationProperties;

public class DBConnection {
	public static Connection getConnectionToDatabase() {
		Connection connection = null;
		ApplicationProperties applicationProperties = new ApplicationProperties();
		String user = applicationProperties.readProperty("dbuser");
		String password = applicationProperties.readProperty("dbpassword");
		String database = applicationProperties.readProperty("database");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("Connection made to DB!");
		}
		return connection;
	}

	public static Connection getConnectionToDatabaseWorld() {
		Connection connection = null;
		ApplicationProperties applicationProperties = new ApplicationProperties();
		String user = applicationProperties.readProperty("dbuser");
		String password = applicationProperties.readProperty("dbpassword");
		String database = applicationProperties.readProperty("databaseWorld");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("Connection made to Cities DB!");
		}
		return connection;
	}

}
