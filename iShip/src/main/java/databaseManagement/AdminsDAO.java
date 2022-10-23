package databaseManagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Order;
import Model.Shipment;
import Model.User;

public class AdminsDAO {

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "select * from users where user_type != 'Admin'";
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				User user = User.builder()
						.age(set.getInt("user_age"))
						.email(set.getString("user_email"))
						.firstName(set.getString("user_firstName"))
						.lastName(set.getString("user_lastName"))
						.phoneNumber(set.getLong("user_phoneNumber"))
						.password(set.getString("user_password"))
						.registrationDate(set.getDate("user_registrationDate"))
						.type(set.getString("user_type"))
						.userId(set.getInt("user_id"))
						.build();
				users.add(user);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return users;
	}
	
	public List<Shipment> getAllShipments(){
		List<Shipment> shipments = new ArrayList<Shipment>();
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String searchQueryString = "SELECT * FROM shipments";
			PreparedStatement statement = connection.prepareStatement(searchQueryString);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Shipment shipment = Shipment.builder()
						.arrivalDate(results.getDate("shipment_arrivalDate"))
						.arrivalLocation(results.getString("shipment_arrivalLocation"))
						.departureDate(results.getDate("shipment_departureDate"))
						.departureLocation(results.getString("shipment_departureLocation"))
						.shipmentId(results.getInt("shipment_id"))
						.userId(results.getInt("shipment_userId"))
						.shipmentRegistrationDate(results.getDate("shipment_registrationDate"))
						.maxWeight(results.getInt("shipment_maxWeight"))
						.pricePerKg(results.getDouble("shipment_pricePerKg"))
						.senderId(results.getInt("shipment_senderId"))
						.build();
				shipments.add(shipment);
			}
		}catch (SQLException e) {
			e.printStackTrace();;
		}
				
		return shipments;
	}
	
	public List<Order> getAllOrders(){
		List<Order> orders = new ArrayList<Order>();	
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String getQueryString = "SELECT * FROM orders WHERE order_shipmentId NOT NULL AND order_senderId NOT NULL";
			PreparedStatement statement = connection.prepareStatement(getQueryString);
			ResultSet results = statement.executeQuery();	
			while(results.next()) {
				Order order = Order.builder()
						.orderDate(results.getDate("order_registrationDate"))
						.orderId(results.getInt("order_id"))
						.senderId(results.getInt("order_senderId"))
						.shipmentId(results.getInt("order_shipmentId"))
						.orderWeight(results.getDouble("order_weight"))
						.orderPrice(results.getDouble("order_price"))
						.orderStatus(results.getString("order_status"))
						.build();
				orders.add(order);
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	
	
	public int makeShipmentsPointToNull(int userId) {
		Connection connection = DBConnection.getConnectionToDatabase();
		String getQueryString = "UPDATE shipments SET shipment_userId = null WHERE shipment_userId = "+userId;
		System.out.println(getQueryString);
		int rowsAffected = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(getQueryString);
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public void deleteAllShipmentsOfUser(int userId) {
		ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
		List<Shipment> shipmentsOfUserList = shipmentsDAO.getAllShipmentsForUser(userId);
		for (Shipment shipment : shipmentsOfUserList) {
			shipmentsDAO.deleteShipment(shipment.getShipmentId());
		}
	}
	
	public void cancelAllOrdersOfUser(int userId) {
		System.out.println("In cancel all orders");
		OrdersDAO ordersDAO = new OrdersDAO();
		
		List<Order> orderOfUser = ordersDAO.getCanceledOrdersForUser(userId);
		for (Order order: orderOfUser) {
			ordersDAO.cancelOrder(order.getOrderId());
			ordersDAO.deleteOrder(order.getOrderId());
		}
	}
	
	
	
	public int deleteUser(int userId) {
		Connection connection = DBConnection.getConnectionToDatabase();
		String getQueryString = "DELETE FROM users WHERE user_id = "+userId;
		int rowsAffected = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(getQueryString);
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public int updateUserInfo(int userId, String firtsName, 
			String lastName, String password,int age, String email, long phoneNumber) {
		Connection connection = DBConnection.getConnectionToDatabase();
		String getQueryString = "UPDATE users SET user_firstname = '" + firtsName+"', "+
								"user_lastname = '" +lastName + "', "+
								"user_phonenumber = '" + phoneNumber+"', "+
								"user_email = '" + email + "', "+
								"user_password = '" + password + "', "+
								"user_age = " +age+" "+
								"WHERE user_id = " + userId;
		System.out.println(getQueryString);
		int rowsAffected = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(getQueryString);
			rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	
	}
	
	
	
}