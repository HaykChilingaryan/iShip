package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import databaseManagement.AdminsDAO;
import services.AdminService;
import services.UserService;

@Controller
public class AuthenticationController {
	
	public static final UserService USER_SERVICE = new UserService();
	public static final AdminService ADMIN_SERVICE = new AdminService();
	
	/*
	 * LOGIN
	 * */
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView openLoginPage(HttpSession session) {
		return USER_SERVICE.openLoginPage(session);
	}
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String email, @RequestParam String password,HttpServletRequest request) {
		return USER_SERVICE.loginIntoAccount(email, password, request);
	}
	
	
	/*
	 * LOGOUT
	 * */
	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public ModelAndView logoutAndRedirectToLoginPage(HttpSession session) {
		return USER_SERVICE.openLoginPage(session);
	}
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
	public ModelAndView removeSessionAndLogout(HttpServletRequest request) {
		return USER_SERVICE.removeSessionAndLogout(request);
	}
	
	/*
	  	REGISTER
	*/
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public ModelAndView openRegistrationPage() {
		return USER_SERVICE.openRegistrationPage();
	}
	
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		return USER_SERVICE.registerUser(request, redirectAttributes);
	}
	
	/*
	 	FORGOTPASSWORD
	*/
	@RequestMapping(value = {"/forgotPassword"}, method = RequestMethod.GET)
	public String openForgotPasswordPage() {
		return USER_SERVICE.openForgotPasswordPage();
	}
	
	@RequestMapping(value = {"/forgotPassword"}, method = RequestMethod.POST)
	public String sendResetPasswordEmail(HttpServletRequest request) {
		return USER_SERVICE.sendResetPasswordEmail(request);
	}
	
	@RequestMapping(value = {"/updateAccountInfo"}, method = RequestMethod.POST)
	public String updateAccountInfo(HttpServletRequest request) {
		return USER_SERVICE.updateAccountInfo(request);
	}
	
	@RequestMapping(value = {"/myProfile"}, method = RequestMethod.GET)
	public String openUserProfilePage(HttpSession session) {
		return USER_SERVICE.openUserProfilePage(session);
	}
	
	@RequestMapping(value = {"/accountInfo"}, method = RequestMethod.GET)
	public String openAccountInfoPage(HttpServletRequest request) {
		return USER_SERVICE.openAccountInfoPage(request);
	}
	
	@RequestMapping(value = {"/allUsers"}, method = RequestMethod.GET)
	public ModelAndView openAllUsersPage(HttpServletRequest request) {
		return USER_SERVICE.openAllUsersPage(request);
	}
	@RequestMapping(value = {"/deleteUser"}, method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request, @RequestParam int userId) {
		return ADMIN_SERVICE.deleteUser(request,userId);
	}
	@RequestMapping(value = {"/editUser"}, method = RequestMethod.POST)
	public ModelAndView editUser(HttpServletRequest request, @RequestParam int userId) {
		int age = Integer.parseInt(request.getParameter("age"));
		long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		return ADMIN_SERVICE.editUser(userId, firstName, lastName, password, age, email, phoneNumber);
	}
}
