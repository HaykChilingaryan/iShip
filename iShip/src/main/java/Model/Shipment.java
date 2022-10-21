package Model;

import java.sql.Date;
import java.time.LocalDate;

import databaseManagement.ApplicationDAO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
	@Override
	public String toString() {
		return this.departureLocation;
	}
	
	public User getSenderById(int senderId) {
		ApplicationDAO applicationDAO = new ApplicationDAO();
		User senderUser = applicationDAO.getUserById(senderId);
		return senderUser;
	}
	public User getShipper() {
		ApplicationDAO applicationDAO = new ApplicationDAO();
		User shipperUser = applicationDAO.getUserById(this.userId);
		return shipperUser;
	}
	
	public String getShipperName() {
		User user = getShipper();
		return user.getFirstName()+" "+user.getLastName();
	}
	public String getShipperEmail() {
		User user = getShipper();
		return user.getEmail();
	}
	public long getShipperPhoneNumber() {
		User user = getShipper();
		return user.getPhoneNumber();
	}
	public int getShipperAge() {
		User user = getShipper();
		return user.getAge();
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
