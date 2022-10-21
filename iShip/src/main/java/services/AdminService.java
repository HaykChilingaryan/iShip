package services;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import Model.User;
import databaseManagement.AdminsDAO;
import databaseManagement.ApplicationDAO;
import databaseManagement.ShipmentsDAO;


public class AdminService {
	
	public ModelAndView openAllOrderPage(HttpServletRequest request){
		ModelAndView mView = new ModelAndView();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		mView.setViewName("myOrders");
		return mView;
	}
	
	public ModelAndView deleteUser(HttpServletRequest request, int userId) {
		ModelAndView mView = new ModelAndView();
		AdminsDAO adminsDAO = new AdminsDAO();
		ShipmentsDAO shipmentsDAO = new ShipmentsDAO();
		
		adminsDAO.deleteAllShipmentsOfUser(userId);
		adminsDAO.cancelAllOrdersOfUser(userId);
		int rows = adminsDAO.deleteUser(userId);
		mView.setViewName("allUsers");
		mView.addObject("allUsers", adminsDAO.getAllUsers());
		return mView;
		
	}
	
	public ModelAndView editUser(int userId, String firtsName, 
			String lastName, String password,int age, String email, long phoneNumber) {
		ModelAndView mView = new ModelAndView();
		AdminsDAO adminsDAO = new AdminsDAO();
		adminsDAO.updateUserInfo(userId, firtsName, lastName, password, age, email, phoneNumber);
		mView.setViewName("allUsers");
		mView.addObject("allUsers", adminsDAO.getAllUsers());
		return mView;
	}
}
