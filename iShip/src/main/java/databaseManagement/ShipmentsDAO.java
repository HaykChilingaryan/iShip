package databaseManagement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import Model.Order;
import Model.Shipment;
public class ShipmentsDAO {

	

	public int createShipment(Shipment shipment) {
		int rowsAffected = 0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String insertQuery = "insert into shipments(shipment_userId,shipment_departureLocation,shipment_departureDate,"
					+ "shipment_arrivalLocation,shipment_arrivalDate,shipment_maxWeight, shipment_PricePerKg,shipment_registrationDate,shipment_senderId)"
					+ " values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setInt(1, shipment.getUserId());
			statement.setString(2, shipment.getDepartureLocation());
			statement.setDate(3, (Date) shipment.getDepartureDate());
			statement.setString(4, shipment.getArrivalLocation());
			statement.setDate(5, shipment.getArrivalDate());
			statement.setInt(6, shipment.getMaxWeight());
			statement.setDouble(7, shipment.getPricePerKg());
			statement.setDate(8, Date.valueOf(LocalDate.now()));
			statement.setNull(9, Types.INTEGER);
			rowsAffected = statement.executeUpdate();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return rowsAffected;
	}
	
	public boolean findIfShipmentHasSender(int shipmentId) {
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String insertQuery = "select * from shipments where shipment_senderId IS NOT NULL AND shipment_id = " +shipmentId;
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next())
				return true;

		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return false;
	}
	
	public List<Shipment> findShipmentsForOrders(String departureLocation, String arrivalLocation, Date departureDate, Date arrivalDate, int weight, int userId){
		List<Shipment> shipments = new ArrayList<Shipment>();
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String searchQueryString = "SELECT * FROM shipments"
					+ " WHERE shipment_departureLocation = '" +departureLocation
					+ "' AND shipment_arrivalLocation = '" + arrivalLocation
					+ "' AND shipment_arrivalDate between '" + departureDate + "' AND '" +arrivalDate
					+ "' AND shipment_maxWeight >= " +weight
					+ " AND shipment_senderId IS NULL"
					+ " AND shipment_userId != " +userId;
			PreparedStatement statement = connection.prepareStatement(searchQueryString);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Shipment shipment = new Shipment();
				shipment.setShipmentId(results.getInt("shipment_id"));
				shipment.setUserId(results.getInt("shipment_userId"));
				shipment.setDepartureDate(results.getDate("shipment_departureDate"));
				shipment.setDepartureLocation(results.getString("shipment_departureLocation"));
				shipment.setArrivalDate(results.getDate("shipment_arrivalDate"));
				shipment.setArrivalLocation(results.getString("shipment_arrivalLocation"));
				shipment.setMaxWeight(results.getInt("shipment_maxWeight"));
				shipment.setPricePerKg(results.getDouble("shipment_pricePerKg"));
				shipment.setShipmentRegistrationDate(results.getDate("shipment_registrationDate"));
				shipment.setSenderId(results.getInt("shipment_senderId"));
				shipments.add(shipment);
			}
		}catch (SQLException e) {
			e.printStackTrace();;
		}
				
		return shipments;
		
	}
	
	public List<Shipment> findShipmentsForOrdersWithoutWeight(String departureLocation, String arrivalLocation, Date departureDate, Date arrivalDate, int userId){
		List<Shipment> shipments = new ArrayList<Shipment>();
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String searchQueryString = "SELECT * FROM shipments"
					+ " WHERE shipment_departureLocation = '" +departureLocation
					+ "' AND shipment_arrivalLocation = '" + arrivalLocation
					+ "' AND shipment_arrivalDate between '" + departureDate + "' AND '" +arrivalDate
					+ "' AND shipment_senderId IS NULL"
					+ " AND shipment_userId != " +userId;
			PreparedStatement statement = connection.prepareStatement(searchQueryString);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Shipment shipment = new Shipment();
				shipment.setShipmentId(results.getInt("shipment_id"));
				shipment.setUserId(results.getInt("shipment_userId"));
				shipment.setDepartureDate(results.getDate("shipment_departureDate"));
				shipment.setDepartureLocation(results.getString("shipment_departureLocation"));
				shipment.setArrivalDate(results.getDate("shipment_arrivalDate"));
				shipment.setArrivalLocation(results.getString("shipment_arrivalLocation"));
				shipment.setMaxWeight(results.getInt("shipment_maxWeight"));
				shipment.setPricePerKg(results.getDouble("shipment_pricePerKg"));
				shipment.setShipmentRegistrationDate(results.getDate("shipment_registrationDate"));
				shipment.setSenderId(results.getInt("shipment_senderId"));
				shipments.add(shipment);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return shipments;
		
	}
	public int deleteShipment(int shipmentId) {
		int rowsAffected =0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String deleteQueryString = "DELETE FROM shipments WHERE shipment_id = " + shipmentId;
			OrdersDAO ordersDAO = new OrdersDAO();
			Order order = ordersDAO.getOrderByShipmentId(shipmentId);
			ordersDAO.cancelOrderSetShipmentNull(order.getOrderId());
			PreparedStatement statement = connection.prepareStatement(deleteQueryString);
			rowsAffected = statement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	
	public int bookShipment(int shipmentId, int senderId) {
		int rowsAffected =0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String updateQueryString = "UPDATE  shipments SET shipment_senderId = "+ senderId +" WHERE shipment_id = " + shipmentId;
			PreparedStatement statement = connection.prepareStatement(updateQueryString);
			rowsAffected = statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public int setShipmentStatusAvailableById(int shipmentId) {
		int rowsAffected =0;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String updateQueryString = "UPDATE  shipments SET shipment_senderId = null WHERE shipment_id = " + shipmentId;
			PreparedStatement statement = connection.prepareStatement(updateQueryString);
			rowsAffected = statement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	public Shipment getShipmentById(int shipmentId) {
		Shipment shipment = new Shipment();
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String getQueryString = "SELECT * FROM shipments WHERE shipment_id = " + shipmentId;
			PreparedStatement statement = connection.prepareStatement(getQueryString);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				shipment.setShipmentId(results.getInt("shipment_id"));
				shipment.setUserId(results.getInt("shipment_userId"));
				shipment.setDepartureDate(results.getDate("shipment_departureDate"));
				shipment.setDepartureLocation(results.getString("shipment_departureLocation"));
				shipment.setArrivalDate(results.getDate("shipment_arrivalDate"));
				shipment.setArrivalLocation(results.getString("shipment_arrivalLocation"));
				shipment.setMaxWeight(results.getInt("shipment_maxWeight"));
				shipment.setPricePerKg(results.getDouble("shipment_pricePerKg"));
				shipment.setShipmentRegistrationDate(results.getDate("shipment_registrationDate"));
				shipment.setSenderId(results.getInt("shipment_senderId"));
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return shipment;
	}
	
	public List<Shipment> getAllShipmentsForUser(int userId) {
		List<Shipment> shipments = new ArrayList<Shipment>();
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			String getQueryString = "SELECT * FROM shipments WHERE shipment_userId = " + userId;
			PreparedStatement statement = connection.prepareStatement(getQueryString);
			ResultSet results = statement.executeQuery();		
			while(results.next()) {
				Shipment shipment = new Shipment();
				shipment.setShipmentId(results.getInt("shipment_id"));
				shipment.setUserId(results.getInt("shipment_userId"));
				shipment.setDepartureDate(results.getDate("shipment_departureDate"));
				shipment.setDepartureLocation(results.getString("shipment_departureLocation"));
				shipment.setArrivalDate(results.getDate("shipment_arrivalDate"));
				shipment.setArrivalLocation(results.getString("shipment_arrivalLocation"));
				shipment.setMaxWeight(results.getInt("shipment_maxWeight"));
				shipment.setPricePerKg(results.getDouble("shipment_pricePerKg"));
				shipment.setShipmentRegistrationDate(results.getDate("shipment_registrationDate"));
				shipment.setSenderId(results.getInt("shipment_senderId"));
				shipments.add(shipment);
			}					
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return shipments;		
	}
}
