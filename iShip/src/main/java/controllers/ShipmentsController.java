package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ShipmentService;

@Controller
public class ShipmentsController {
	

	public  final ShipmentService shipmentService = new ShipmentService();

	@RequestMapping(value  = {"/deleteShipment"}, method = RequestMethod.POST)
	public ModelAndView deleteShipment(HttpServletRequest request) {
		return shipmentService.deleteShipment(request);
	}
	
	@RequestMapping(value  = {"/createDelivery"}, method = RequestMethod.POST)
	public ModelAndView createdelivery(HttpServletRequest request, @RequestParam("departureDate") java.sql.Date depDate,
			@RequestParam("arrivalDate") java.sql.Date arrDate,
			@RequestParam("departureLocation") String depLoc,
			@RequestParam("arrivalLocation") String arrLoc,
			@RequestParam("maxWeight") int maxWeight,
			@RequestParam("pricePerKg") double pricePerKg){
		return shipmentService.createDelivery(request, depDate, arrDate, depLoc, arrLoc, maxWeight, pricePerKg);
	}
	
	@RequestMapping(value  = {"/myDeliveries"}, method = RequestMethod.GET)
	public ModelAndView getShipments(HttpServletRequest request){
		return shipmentService.openUserShipmentsPage(request);
	}
	
	@RequestMapping(value  = {"/allShipments"}, method = RequestMethod.GET)
	public ModelAndView getAllShipments(HttpServletRequest request){
		return shipmentService.openUserShipmentsPage(request);
	}
}
