package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.OrderService;

@Controller
public class OrdersController {
	
	public final OrderService orderService = new OrderService();

	
	
	@RequestMapping(value  = {"/myOrders"}, method = RequestMethod.GET)
	public ModelAndView openUserOrderPage(HttpServletRequest request){
		return orderService.openUserOrderPage(request);
		
	}
	
	@RequestMapping(value  = {"/myCancelledOrders"}, method = RequestMethod.GET)
	public ModelAndView openCanceledOrdersPage(HttpServletRequest request){
		return orderService.openCanceledOrdersPage(request);
		
	}
	/*
	 * javax validator(javax validation package)
	 */
	@RequestMapping(value  = {"/getOrderDetails"}, method = RequestMethod.POST)
	public ModelAndView createOrder(HttpServletRequest request, @RequestParam("departureDate") java.sql.Date depDate,
			@RequestParam("arrivalDate") java.sql.Date arrDate,
			@RequestParam("departureLocation") String depLoc,
			@RequestParam("arrivalLocation") String arrLoc){
		return orderService.createOrder(request, depDate, arrDate, depLoc, arrLoc);
	}
	
	@RequestMapping(value  = {"placeOrder"}, method = RequestMethod.POST)
	public ModelAndView placeOrder(HttpServletRequest request,@RequestParam("shipmentId") int shipmentId) {
		return orderService.placeOrder(request, shipmentId);
	}
	
	@RequestMapping(value  = {"placeOrder"}, method = RequestMethod.GET)
	public ModelAndView openPlaceOrderPage(HttpServletRequest request) {
		return orderService.openPlaceOrderPage(request);
	}
	
	@RequestMapping(value  = {"/cancelOrder"}, method = RequestMethod.POST)	
	public ModelAndView cancelOrder(HttpServletRequest request)throws Exception{
		return orderService.cancelOrder(request);
	}
	
	@RequestMapping(value  = {"/allOrders"}, method = RequestMethod.GET)	
	public ModelAndView allOrder(HttpServletRequest request)throws Exception{
		return orderService.openUserOrderPage(request);
	}
}
