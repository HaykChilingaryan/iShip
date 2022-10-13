package Model;

import java.time.LocalDate;
import java.util.Date;

import databaseManagement.ApplicationDAO;
import databaseManagement.ShipmentsDAO;

public class Order {

	private int orderId;
	private int senderId;	
	private int shipmentId;
	private double orderWeight;
	private String orderStatus;
	private Date orderDate;
	private double orderPrice;
	
	public Order(int senderId, int shipmentId, double orderWeight, String orderStatus,double orderPrice) {
		super();
		this.senderId = senderId;
		this.shipmentId = shipmentId;
		this.orderWeight = orderWeight;
		this.orderStatus = orderStatus;
		this.orderPrice = orderPrice;
		this.orderDate = java.sql.Date.valueOf(LocalDate.now());
	}
	public Order() {
		
	}
	public Order(int senderId, double orderWeight, String orderStatus,double orderPrice) {
		super();
		this.senderId = senderId;
		this.shipmentId = 0;
		this.orderWeight = orderWeight;
		this.orderStatus = orderStatus;
		this.orderPrice = orderPrice;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}
	public double getOrderWeight() {
		return orderWeight;
	}
	public void setOrderWeight(double d) {
		this.orderWeight = d;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
		
	public Shipment getShipmentById() {
		ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
		return shipmentsDAO.getShipmentById(this.getShipmentId());
	}
	
	public String getShipmentDepartureLocation() {
		return this.getShipmentById().getDepartureLocation();
	}
	public Date getShipmentDepartureDate() {
		return this.getShipmentById().getDepartureDate();
	}
	
	public String getShipmentArrivalLocation() {
		return this.getShipmentById().getArrivalLocation();
	}
	public Date getShipmentArrivalDate() {
		return this.getShipmentById().getArrivalDate();
	}
	
	public User getSenderById(int senderId){
		ApplicationDAO dao = new ApplicationDAO();
		return dao.getUserById(senderId);
	}
	
	public User getShipperById() {
		ApplicationDAO dao = new ApplicationDAO();
		Shipment shipment = getShipmentById();
		return dao.getUserById(shipment.getUserId());
	}
	public String getShipperName() {
		User shipper = getShipperById();
		return shipper.getFirstName()+" " +shipper.getLastName();
	}
	public long getShipperPhoneNumber() {
		User shipper = getShipperById();
		return shipper.getPhoneNumber();
	}
	public String getShipperEmail() {
		User shipper = getShipperById();
		return shipper.getEmail();
	}
	public int getShipperAge() {
		User shipper = getShipperById();
		return shipper.getAge();
	}
	public int getShipperId() {
		User shipper = getShipperById();
		return shipper.getId();
	}
}
