package databaseManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.User;


public class ApplicationDAO {
	public int setTemporaryPassword(int userId, String temporaryPassword) {
		int rowsAffected =0 ;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "UPDATE users SET user_password = '" + temporaryPassword +"' WHERE user_id = " +userId;
			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			rowsAffected = statement.executeUpdate();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rowsAffected;
	}
	
	public int registerUser(User user) {
		int rowsAffected = 0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String insertQuery = "insert into users(user_firstname,user_lastname,user_age,user_phonenumber,user_email,user_password,user_registrationDate,user_type)"
					+ " values(?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setInt(3, user.getAge());
			statement.setLong(4, user.getPhoneNumber());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getPassword());
			statement.setDate(7, user.getRegistrationDate());
			statement.setString(8, user.getType());
			
			rowsAffected = statement.executeUpdate();

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rowsAffected;
	}

	public boolean validateUser(String username, String password) {
		boolean isValidUser = false;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();

			String sql = "select * from users where user_email=? and user_password=?";
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				isValidUser = true;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return isValidUser;
	}

	public User getProfileDetails(String email) {
		User user = null;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "select * from users where user_email=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				user = new User();
				user.setUserId(set.getInt("user_id"));
				user.setEmail(set.getString("user_email"));
				user.setFirstName(set.getString("user_firstName"));
				user.setLastName(set.getString("user_lastName"));
				user.setPhoneNumber(set.getLong("user_phoneNumber"));
				user.setAge(set.getInt("user_age"));
				user.setPassword(set.getString("user_password"));
				user.setRegistrationDate(set.getDate("user_registrationDate"));
				user.setType(set.getString("user_type"));
			}
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		return user;
	}
	
	public User getUserById(int id) {
		User user = null;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();

			String sql = "select * from users where user_id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				user = new User();
				user.setUserId(set.getInt("user_id"));
				user.setEmail(set.getString("user_email"));
				user.setFirstName(set.getString("user_firstName"));
				user.setLastName(set.getString("user_lastName"));
				user.setPhoneNumber(set.getLong("user_phoneNumber"));
				user.setAge(set.getInt("user_age"));
				user.setPassword(set.getString("user_password"));
				user.setRegistrationDate(set.getDate("user_registrationDate"));
				user.setType(set.getString("user_type"));
			}
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		return user;
	}
	
	public int updateUserProfileInfoById(int userId, String newPass, long phoneNumber) {
		int rowsAffected =0 ;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "UPDATE users SET user_password = '" + newPass + "',user_phonenumber = " + phoneNumber + " WHERE user_id = " +userId;
			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			rowsAffected = statement.executeUpdate();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rowsAffected;
		
	}
}

