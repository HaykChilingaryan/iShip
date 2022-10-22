package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdminService;
import services.OrderService;

@Controller
public class OrdersController {
	
	@Autowired
	public static final OrderService ORDER_SERVICE = new OrderService();
	@Autowired
	public static final AdminService ADMIN_SERVICE = new AdminService();

	
	
	@RequestMapping(value  = {"/myOrders"}, method = RequestMethod.GET)
	public ModelAndView openUserOrderPage(HttpServletRequest request){
		return ORDER_SERVICE.openUserOrderPage(request);
		
	}
	
	@RequestMapping(value  = {"/myCancelledOrders"}, method = RequestMethod.GET)
	public ModelAndView openCanceledOrdersPage(HttpServletRequest request){
		return ORDER_SERVICE.openCanceledOrdersPage(request);
		
	}
	
	@RequestMapping(value  = {"/getOrderDetails"}, method = RequestMethod.POST)
	public ModelAndView createOrder(HttpServletRequest request, @RequestParam("departureDate") java.sql.Date depDate,
			@RequestParam("arrivalDate") java.sql.Date arrDate,
			@RequestParam("departureLocation") String depLoc,
			@RequestParam("arrivalLocation") String arrLoc){
		return ORDER_SERVICE.createOrder(request, depDate, arrDate, depLoc, arrLoc);
	}
	
	@RequestMapping(value  = {"placeOrder"}, method = RequestMethod.POST)
	public ModelAndView placeOrder(HttpServletRequest request,@RequestParam("shipmentId") int shipmentId) {
		return ORDER_SERVICE.placeOrder(request, shipmentId);
	}
	
	@RequestMapping(value  = {"placeOrder"}, method = RequestMethod.GET)
	public ModelAndView openPlaceOrderPage(HttpServletRequest request) {
		return ORDER_SERVICE.openPlaceOrderPage(request);
	}
	
	@RequestMapping(value  = {"/cancelOrder"}, method = RequestMethod.POST)	
	public ModelAndView cancelOrder(HttpServletRequest request)throws Exception{
		return ORDER_SERVICE.cancelOrder(request);
	}
	
	@RequestMapping(value  = {"/allOrders"}, method = RequestMethod.GET)	
	public ModelAndView allOrder(HttpServletRequest request)throws Exception{
		return ORDER_SERVICE.openUserOrderPage(request);
	}
}
