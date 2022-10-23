package Model;

import java.time.LocalDate;
import java.util.Date;

import databaseManagement.ApplicationDAO;
import databaseManagement.ShipmentsDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	
	public String getSenderName() {
		User sender = getSenderById(this.getSenderId());
		return sender.getFirstName()+" " +sender.getLastName();
	}
	public long getShipperPhoneNumber() {
		User shipper = getShipperById();
		return shipper.getPhoneNumber();
	}
	public long getSenderPhoneNumber() {
		User sender = getSenderById(this.getSenderId());
		return sender.getPhoneNumber();
	}
	public String getShipperEmail() {
		User shipper = getShipperById();
		return shipper.getEmail();
	}
	public String getSenderEmail() {
		User sender = getSenderById(this.getSenderId());
		return sender.getEmail();
	}
	public int getShipperAge() {
		User shipper = getShipperById();
		return shipper.getAge();
	}
	public int getSenderAge() {
		User sender = getSenderById(this.getSenderId());
		return sender.getAge();
	}
	public int getShipperId() {
		User shipper = getShipperById();
		return shipper.getUserId();
	}
}