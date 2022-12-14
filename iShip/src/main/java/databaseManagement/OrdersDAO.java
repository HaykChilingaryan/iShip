package databaseManagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.Order;

public class OrdersDAO {

	public int createOrder(Order order) {
		int rowsAffected = 0;

		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String insertQuery = "insert into orders(order_id,order_senderId,order_shipmentId,order_weight,order_status,"
					+ "order_registrationDate,order_price )"
					+ " values(?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setInt(1, order.getOrderId());
			statement.setInt(2, order.getSenderId());
			statement.setInt(3, order.getShipmentId());
			statement.setDouble(4, order.getOrderWeight());
			statement.setString(5, order.getOrderStatus());
			statement.setDate(6, Date.valueOf(LocalDate.now()));
			statement.setDouble(7, order.getOrderPrice());
			
			ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
			shipmentsDAO.bookShipment(order.getShipmentId(),order.getSenderId());
			rowsAffected = statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rowsAffected;
	}
	
	public int deleteOrder(int orderId) {
		int rowsAffected =0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String deleteQueryString = "DELETE FROM orders WHERE order_id = " + orderId;
			PreparedStatement statement = connection.prepareStatement(deleteQueryString);
			rowsAffected = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public int cancelOrder(int orderId) {
		int rowsAffected =0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String deleteQueryString = "UPDATE orders SET order_status = 'Cancelled' WHERE order_id = " + orderId;						
			PreparedStatement statement = connection.prepareStatement(deleteQueryString);
			rowsAffected = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return rowsAffected;
	}
	
	public int getInProgressOrderCountByUserId(int userId) {
		int count =0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String countQueryString = "SELECT COUNT(order_status) FROM orders WHERE order_status = 'In Progress' AND order_senderId = "+userId;
										
			PreparedStatement statement = connection.prepareStatement(countQueryString);
			ResultSet resultSet = statement.executeQuery(countQueryString);
			if(resultSet.next())
				count  = resultSet.getInt(1);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return count;		
	}
	
	
	
	public int cancelOrderSetShipmentNull(int orderId) {
		int rowsAffected =0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String updateQueryString = "UPDATE orders SET order_status = 'Cancelled', order_shipmentId = null WHERE order_id = " + orderId;
			ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
			OrdersDAO ordersDAO = new OrdersDAO();
			shipmentsDAO.setShipmentStatusAvailableById(ordersDAO.getOrderById(orderId).getShipmentId());
			PreparedStatement statement = connection.prepareStatement(updateQueryString);
			rowsAffected = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}
	public Order getOrderByShipmentId(int shipmentId) {
		Order order = new Order();
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String getQueryString = "SELECT * FROM orders WHERE order_shipmentId = " + shipmentId;
			PreparedStatement statement = connection.prepareStatement(getQueryString);
			ResultSet results = statement.executeQuery();			
			while(results.next()) {
				order = Order.builder()
				.orderDate(results.getDate("order_registrationDate"))
				.orderId(results.getInt("order_id"))
				.senderId(results.getInt("order_senderId"))
				.shipmentId(results.getInt("order_shipmentId"))
				.orderWeight(results.getDouble("order_weight"))
				.orderPrice(results.getDouble("order_price"))
				.orderStatus(results.getString("order_status"))
				.build();
			}					
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	
	public Order getOrderById(int orderId) {
		Order order = new Order();		
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String getQueryString = "SELECT * FROM orders WHERE order_id = " + orderId;
			PreparedStatement statement = connection.prepareStatement(getQueryString);
			ResultSet results = statement.executeQuery();			
			while(results.next()) {
				order = Order.builder()
						.orderDate(results.getDate("order_registrationDate"))
						.orderId(results.getInt("order_id"))
						.senderId(results.getInt("order_senderId"))
						.shipmentId(results.getInt("order_shipmentId"))
						.orderWeight(results.getDouble("order_weight"))
						.orderPrice(results.getDouble("order_price"))
						.orderStatus(results.getString("order_status"))
						.build();
			}					
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	
	public List<Order> getOrdersForUser(int userId){
		List<Order> orders = new ArrayList<Order>();	
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String getQueryString = "SELECT * FROM orders WHERE order_senderId = " + userId+ " And order_shipmentId IS NOT NULL";
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
	
	public List<Order> getCanceledOrdersForUser(int userId){
		List<Order> canceledOrders = new ArrayList<Order>();
		
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String getQueryString = "SELECT * FROM orders WHERE order_senderId = " + userId + " And order_status = 'Cancelled'";
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
				canceledOrders.add(order);
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return canceledOrders;	
	}
	
	public boolean validateOrder(int orderId) {
		boolean isValidUser = false;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String sql = "select * from orders where order_id = "+orderId;
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				isValidUser = true;
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return isValidUser;
	}
}
