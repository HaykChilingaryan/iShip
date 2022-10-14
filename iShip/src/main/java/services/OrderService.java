package services;

import java.util.ArrayList;
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

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import Model.Order;
import Model.Shipment;
import Model.User;
import databaseManagement.ApplicationDAO;
import databaseManagement.OrdersDAO;
import databaseManagement.ShipmentsDAO;

public class OrderService {

	public ModelAndView openUserOrderPage(HttpServletRequest request){
		OrdersDAO ordersDAO = new OrdersDAO();
		ModelAndView mView = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");	
		List<Order> currentUserOrders = ordersDAO.getOrdersForUser(user.getId());
		session.setAttribute("user", user);
		request.setAttribute("orders", currentUserOrders);
		mView.setViewName("myOrders");
		mView.addObject("currentUserOrders", currentUserOrders);
		return mView;
	}
	
	public ModelAndView openCanceledOrdersPage(HttpServletRequest request){
		ModelAndView mView = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		OrdersDAO ordersDAO = new OrdersDAO();
		List<Order> currentUserCanceledOrders = ordersDAO.getCanceledOrdersForUser(user.getId());
		session.setAttribute("user", user);
		request.setAttribute("orders", currentUserCanceledOrders);
		mView.setViewName("myCanceledOrders");
		mView.addObject("currentUserCanceledOrders", currentUserCanceledOrders);
		return mView;
		
	}
	public static boolean checkIfValidDate( String date) {
		String[] dateStrings = date.split("-");
		if(dateStrings.length == 3) {
			if(Integer.parseInt(dateStrings[1]) > 0 && Integer.parseInt(dateStrings[1]) <= 12) {
				if(Integer.parseInt(dateStrings[2])> 0 && Integer.parseInt(dateStrings[1])<31) {
					return true;
				}
			}
		}
		return false;
		
	}
	public ModelAndView createOrder(HttpServletRequest request, @RequestParam("departureDate") java.sql.Date depDate,
			@RequestParam("arrivalDate") java.sql.Date arrDate,
			@RequestParam("departureLocation") String depLoc,
			@RequestParam("arrivalLocation") String arrLoc){
		ModelAndView mView = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
		List<Shipment> availableShipments = new ArrayList<Shipment>();
		System.out.println(checkIfValidDate("2022-35-10"));
		System.out.println(checkIfValidDate(arrDate.toString()));
		int weight = 0;
		if(depDate.compareTo(arrDate) < 0) {	
				mView.addObject("message","Available Options");
				if(!request.getParameter("weight").equals("")){
					weight = Integer.parseInt(request.getParameter("weight"));
				}
				if(weight == 0) {
					 availableShipments =  shipmentsDAO.findShipmentsForOrdersWithoutWeight(depLoc, arrLoc, depDate, arrDate, user.getId());
				}else {
					
					availableShipments =  shipmentsDAO.findShipmentsForOrders(depLoc, arrLoc, depDate, arrDate, weight,user.getId());
				}
				List<Shipment> currentUserShipments = shipmentsDAO.getAllShipmentsForUser(user.getId());
				request.setAttribute("currentUserShipments", currentUserShipments);
				request.setAttribute("availableShipments", availableShipments);
				session.setAttribute("weight",weight);
				mView.setViewName("placeOrder");
				return mView;
			
		}
		mView.addObject("message","No Options, Wrong Dates");
		mView.setViewName("placeOrder");
		return mView;
		
	}
	
	public ModelAndView openPlaceOrderPage(HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("placeOrder");
		return mView;
	}
	
	public ModelAndView placeOrder(HttpServletRequest request,@RequestParam("shipmentId") int shipmentId) {
		ModelAndView mView = new ModelAndView();
		OrdersDAO ordersDAO = new OrdersDAO();
		ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int weight = (int) session.getAttribute("weight");
		Shipment shipment = shipmentsDAO.getShipmentById(shipmentId);
		if(weight == 0) {
			weight = shipment.getMaxWeight();
		}
		double price  = weight *1.0*shipment.getPricePerKg();
		Order order = new Order(user.getId(), shipmentId, weight, "In Progress",price);
		ordersDAO.createOrder(order);
		List<Order> currentUserOrders = ordersDAO.getOrdersForUser(user.getId());
		request.setAttribute("currentUserOrders", currentUserOrders);
		int inProgressOrders = ordersDAO.getInProgressOrderCountByUserId(user.getId());
		session.setAttribute("inProgressOrders", inProgressOrders);
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
				message.setSubject("Order Placement Confirmation");
				String textString = "Your Order has been successfully Placed\n\n"
						+ "\nDeparting From: " + shipment.getDepartureLocation() + " on " + shipment.getDepartureDate()
						+ "\nArriving In: " + shipment.getArrivalLocation() + " on " + shipment.getDepartureDate()
						+ "\nWeight " + order.getOrderWeight() + "kg"
						+ "\nPrice: $"+ order.getOrderPrice()
						+ "\nOrder placed on: " + order.getOrderDate()
						+ "\n\n\nThank you for your continous trust.";
				message.setText(textString);
				// send message
				System.out.println("sending to sender");
				Transport.send(message);
				System.out.println("message sent successfully to sender");
				
				ApplicationDAO applicationDAO = new ApplicationDAO();
				User shipperUser = applicationDAO.getUserById(shipment.getUserId());
				to= shipperUser.getEmail();
				message.setFrom(new InternetAddress(email));// change accordingly
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Shipment Booking Confirmation");
				String textStringBooking = "Your Shipment has been successfully Booked\n\n"
						+ "\nShipment Uniwue Number: " + shipmentId
						+ "\nDeparting From: " + shipment.getDepartureLocation() + " on " + shipment.getDepartureDate()
						+ "\nArriving In: " + shipment.getArrivalLocation() + " on " + shipment.getDepartureDate()
						+ "\nWeight " + order.getOrderWeight() + "kg"
						+ "\nPrice: $"+ order.getOrderPrice()
						+ "\nOrder placed on: " + order.getOrderDate()
						+ "\n\n\nThank you for your continous trust.";
				message.setText(textStringBooking);
				Transport.send(message);	
			}
			catch (MessagingException e) {
				throw new RuntimeException(e);
			}	
		}
		mView.setViewName("redirect:/myOrders");
		return mView;
	}
	
	public ModelAndView cancelOrder(HttpServletRequest request)throws Exception{
		ModelAndView mView = new ModelAndView();
		OrdersDAO ordersDAO = new OrdersDAO();
		HttpSession session = request.getSession();
		ApplicationDAO applicationDAO = new ApplicationDAO();
		ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
		User user = (User) session.getAttribute("user");
		int cancelId = Integer.parseInt(request.getParameter("orderId"));
		System.out.println(cancelId);
		Order order = ordersDAO.getOrderById(cancelId);
		Shipment shipment  = shipmentsDAO.getShipmentById(order.getShipmentId());
		shipmentsDAO.setShipmentStatusAvailableById(order.getShipmentId());
		ordersDAO.cancelOrderSetShipmentNull(cancelId);
		List<Order> currentUserOrders = ordersDAO.getOrdersForUser(user.getId());
		request.setAttribute("currentUserOrders", currentUserOrders);
		int inProgressOrders = ordersDAO.getInProgressOrderCountByUserId(user.getId());
		session.setAttribute("inProgressOrders", inProgressOrders);
		String email = user.getEmail();
		if(email != null && !email.equals("")) {
			String to = email;// change accordingly
			// Get the session object
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
				message.setSubject("Order Cancellation Confirmation");
				String textString = "Your Shipment is now available again\n\n"
						+ "\nDeparting From: " + shipment.getDepartureLocation() + " on " + shipment.getDepartureDate()
						+ "\nArriving In: " + shipment.getArrivalLocation() + " on " + shipment.getDepartureDate()
						+ "\nAvailable Space:  " + shipment.getMaxWeight() + "kg"
						+ "\nPrice Per Kg: $"+ shipment.getPricePerKg()
						+ "\nShipment placed on: " + shipment.getShipmentRegistrationDate()
						+ "\n\n\nThank you for your continous trust.";
				String textStringBooking = "Your Order has been successfully cancelled\n\n"
						+ "\nDeparting From: " + shipment.getDepartureLocation() + " on " + shipment.getDepartureDate()
						+ "\nArriving In: " + shipment.getArrivalLocation() + " on " + shipment.getDepartureDate()
						+ "\nWeight " + order.getOrderWeight() + "kg"
						+ "\nPrice: $"+ order.getOrderPrice()
						+ "\nOrder placed on: " + order.getOrderDate()
						+ "\n\n\nThank you for your continous trust.";
				message.setText(textStringBooking);
				Transport.send(message);
				User shipperUser = applicationDAO.getUserById(shipment.getUserId());
				to= shipperUser.getEmail();
				message.setFrom(new InternetAddress(email));// change accordingly
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Order Cancellation Notice");
				message.setText(textString);
				Transport.send(message);
			}
			catch (MessagingException e) {
				throw new RuntimeException(e);
			}	
		}
		mView.setViewName("redirect:/myCancelledOrders");
		return mView;
	}
}
