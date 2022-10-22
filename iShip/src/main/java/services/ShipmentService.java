package services;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Model.City;
import Model.Order;
import Model.Shipment;
import Model.User;
import databaseManagement.AdminsDAO;
import databaseManagement.ApplicationDAO;
import databaseManagement.CitiesDAO;
import databaseManagement.OrdersDAO;
import databaseManagement.ShipmentsDAO;

@Service
public class ShipmentService {

	public ModelAndView deleteShipment(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
		HttpSession session = request.getSession();
		OrdersDAO orderDAO = new OrdersDAO();
		ApplicationDAO applicationDAO = new ApplicationDAO();
		User user = (User) session.getAttribute("user");
		int deleteId = Integer.parseInt(request.getParameter("shipmentId"));
		boolean hasSender = shipmentsDAO.findIfShipmentHasSender(deleteId);
		Shipment shipment = shipmentsDAO.getShipmentById(deleteId);
		Order order = new Order();
		if(hasSender)
			 order = orderDAO.getOrderByShipmentId(shipment.getShipmentId());
		shipmentsDAO.deleteShipment(deleteId);
		String email = user.getEmail();
		if(email != null && !email.equals("")) {
			String to = email;
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.ssl.trust", "*");
			Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("infoishipment@gmail.com", "pwizqylhlnpyhcrb");
				}
			});
			try {
				
				MimeMessage message = new MimeMessage(mailSession);
				message.setFrom(new InternetAddress(email));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Shipment Deletion Confirmation");
				String textString = "Your Shipment has been successfully deleted\n\n\n\n"
						+ "\nDeparting From: " + shipment.getDepartureLocation() + " on " + shipment.getDepartureDate()
						+ "\nArriving In: " + shipment.getArrivalLocation() + " on " + shipment.getDepartureDate()
						+ "\nAvailable Space:  " + shipment.getMaxWeight() + "kg"
						+ "\nPrice Per Kg: $"+ shipment.getPricePerKg()
						+ "\nShipment placed on: " + shipment.getShipmentRegistrationDate()
						+ "\n\n\nThank you for your continous trust.";
				String textStringBooking = "Your Order has been cancelled by the Shipper\n\n\n\n"
						+ "\nDeparting From: " + shipment.getDepartureLocation() + " on " + shipment.getDepartureDate()
						+ "\nArriving In: " + shipment.getArrivalLocation() + " on " + shipment.getDepartureDate()
						+ "\nWeight " + order.getOrderWeight() + "kg"
						+ "\nPrice: $"+ order.getOrderPrice()
						+ "\nOrder placed on: " + order.getOrderDate()
						+ "\n\n\nThank you for your continous trust.";
				message.setText(textString);
				Transport.send(message);
				if(hasSender){
					User senderUser = applicationDAO.getUserById(shipment.getSenderId());
					to= senderUser.getEmail();
					message.setFrom(new InternetAddress(email));// change accordingly
					message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("Order Cancellation Notice");
					message.setText(textStringBooking);
					Transport.send(message);

				}
			}
			catch (MessagingException e) {
				throw new RuntimeException(e);
			}	
		}
		List<Shipment> currentUserShipments = shipmentsDAO.getAllShipmentsForUser(user.getUserId());
		request.setAttribute("shipments", currentUserShipments);
		mView.setViewName("redirect:/myDeliveries");
		return mView;
	}
	
	public ModelAndView openUserShipmentsPage(HttpServletRequest request){
		ModelAndView mView = new ModelAndView();
		AdminsDAO adminsDAO = new AdminsDAO();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
		CitiesDAO citiesDAO = new CitiesDAO();
		if(user.getType().equals("Admin")) {
			mView.setViewName("myDeliveries");
			session.setAttribute("user", user);
			List<City> allCities = citiesDAO.getAllCities();
			mView.addObject("allCities", allCities);
			mView.addObject("currentUserShipments", adminsDAO.getAllShipments());
			return mView;
		}
		List<Shipment> currentUserShipments = shipmentsDAO.getAllShipmentsForUser(user.getUserId());
		request.setAttribute("shipments", currentUserShipments);
		mView.setViewName("myDeliveries");
		List<City> allCities = citiesDAO.getAllCities();
		mView.addObject("allCities", allCities);
		mView.addObject("currentUserShipments", currentUserShipments);
		return mView;
	}
	
	public ModelAndView createDelivery(HttpServletRequest request, @RequestParam("departureDate") java.sql.Date depDate,
			@RequestParam("arrivalDate") java.sql.Date arrDate,
			@RequestParam("departureLocation") String depLoc,
			@RequestParam("arrivalLocation") String arrLoc,
			@RequestParam("maxWeight") int maxWeight,
			@RequestParam("pricePerKg") double pricePerKg){
		ModelAndView mView = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
		if(depDate.compareTo(arrDate) < 0) {
			Shipment shipment = new Shipment(user.getUserId(), depLoc, depDate, arrLoc, arrDate, maxWeight, pricePerKg,0);
			shipmentsDAO.createShipment(shipment);
			String email = user.getEmail();
			if(email != null && !email.equals("")) {
				String to = email;
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");
				props.put("mail.smtp.ssl.trust", "*");
				Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("infoishipment@gmail.com", "pwizqylhlnpyhcrb");
					}
				});
				try {
					MimeMessage message = new MimeMessage(mailSession);
					message.setFrom(new InternetAddress(email));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("Shipment Creation Confirmation");
					String textString = "Your Shipment Option has been successfully created\n\n"
							+ "\n\nShipment Information" 
							+ "\nDeparting From: " + shipment.getDepartureLocation() + " on " + shipment.getDepartureDate()
							+ "\nArriving In: " + shipment.getArrivalLocation() + " on " + shipment.getDepartureDate()
							+ "\nAvailable space: " + shipment.getMaxWeight()+"kg"
							+ "\nPrice Per Kilogram: $"+ shipment.getPricePerKg()
							+ "\nShipment created on: " + shipment.getShipmentRegistrationDate()
							+ "\n\n\nThank you for your continous trust."
							+ "\nWe will let you know once your shipment is booked.";
					message.setText(textString);
					Transport.send(message);
				}

				catch (MessagingException e) {
					throw new RuntimeException(e);
				}	
			}
		}
		List<Shipment> currentUserShipments = shipmentsDAO.getAllShipmentsForUser(user.getUserId());
		
		request.setAttribute("currentUserShipments", currentUserShipments);
		
		mView.setViewName("redirect:/myDeliveries");
		return mView;
	}
}
