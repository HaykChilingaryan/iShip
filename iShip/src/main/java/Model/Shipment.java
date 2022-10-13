package Model;

import java.sql.Date;
import java.time.LocalDate;

import databaseManagement.ApplicationDAO;

public class Shipment {

	private int shipmentId;
	private int userId;
	private int senderId;
	
	private String departureLocation;
	private Date departureDate;
	private String arrivalLocation;
	private Date arrivalDate;
	
	private int maxWeight;
	private double pricePerKg;
	private Date shipmentRegistrationDate;
	
	public Shipment(int userId, String departureLocation, Date depDate, String arrivalLocation,
			Date arrDate, int maxWeight, double pricePerKg, int senderId) {
		this.userId = userId;
		this.departureLocation = departureLocation;
		this.departureDate = depDate;
		this.arrivalLocation = arrivalLocation;
		this.arrivalDate = arrDate;
		this.maxWeight = maxWeight;
		this.pricePerKg = pricePerKg;
		this.senderId = senderId;
		this.shipmentRegistrationDate = Date.valueOf(LocalDate.now());
	}
	public Shipment() {
		
	}
	public int getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDepartureLocation() {
		return departureLocation;
	}
	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departuredate) {
		this.departureDate = departuredate;
	}
	public String getArrivalLocation() {
		return arrivalLocation;
	}
	public void setArrivalLocation(String arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public int getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}
	public double getPricePerKg() {
		return pricePerKg;
	}
	public void setPricePerKg(double pricePerKg) {
		this.pricePerKg = pricePerKg;
	}
	public Date getShipmentRegistrationDate() {
		return shipmentRegistrationDate;
	}
	public void setShipmentRegistrationDate(Date shipmentRegistrationDate) {
		this.shipmentRegistrationDate = shipmentRegistrationDate;
	}
	@Override
	public String toString() {
		return this.departureLocation;
	}
	
	public User getSenderById(int senderId) {
		ApplicationDAO applicationDAO = new ApplicationDAO();
		User senderUser = applicationDAO.getUserById(senderId);
		return senderUser;
	}
	
	public String getSenderName() {
		User user = getSenderById(this.getSenderId());
		if(user != null)
			return user.getFirstName()+" " + user.getLastName();
		else {
			return null;
		}
	}
	public int getSenderAge() {
		User user = getSenderById(this.getSenderId());
		if(user != null)
			return user.getAge();
		else {
			return 0;
		}
	}
	public String getSenderEmail() {
		User user = getSenderById(this.getSenderId());
		if(user != null)
			return user.getEmail();
		else {
			return null;
		}
		
	}
	public long getSenderPhoneNumber() {
		User user = getSenderById(this.getSenderId());
		if(user != null)
			return user.getPhoneNumber();
		else {
			return 0;
		}	
	}
}
