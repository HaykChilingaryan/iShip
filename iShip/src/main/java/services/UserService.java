package services;

import java.util.Properties;
import java.util.Random;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Model.User;
import databaseManagement.AdminsDAO;
import databaseManagement.ApplicationDAO;
import databaseManagement.OrdersDAO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	public ModelAndView loginIntoAccount(@RequestParam String email, @RequestParam String password,
			HttpServletRequest request) {
		ModelAndView mView = new ModelAndView();
		ApplicationDAO dao = new ApplicationDAO();
		HttpSession session = request.getSession();
		boolean isValidUser = dao.validateUser(email, password);
		if (isValidUser) {
			User currentUser = dao.getProfileDetails(email);
			session.setAttribute("user", currentUser);
			if (currentUser.getType() == "Admin") {
				mView.setViewName("redirect:/myProfile");
				AdminsDAO adminsDAO = new AdminsDAO();
				mView.addObject("allUsers", adminsDAO.getAllUsers());
				mView.addObject("allOrders", adminsDAO.getAllOrders());
				mView.addObject("allShipments", adminsDAO.getAllShipments());
				return mView;
			}
			OrdersDAO ordersDAO = new OrdersDAO();
			int inProgressOrders = ordersDAO.getInProgressOrderCountByUserId(currentUser.getUserId());
			session.setAttribute("inProgressOrders", inProgressOrders);
			mView.setViewName("redirect:/myProfile");
			return mView;
		} else {
			request.setAttribute("message", "Wrong email or password");
			return openLoginPage(session);
		}
	}

	public ModelAndView openLoginPage(HttpSession session) {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("login");
		return mView;
	}

	public ModelAndView removeSessionAndLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		ModelAndView mView = new ModelAndView();
		mView.setViewName("login");
		return mView;
	}

	public ModelAndView openRegistrationPage() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("register");
		return mView;
	}

	public ModelAndView registerUser(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		ModelAndView mView = new ModelAndView();
		String infoMessage = null;
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		User user = new User(firstName, lastName, age, phoneNumber, email, password);
		ApplicationDAO dao = new ApplicationDAO();
		int rows = 0;
		if (password.equals(repeatPassword)) {
			if (user.checkIfValidPass())
				rows = dao.registerUser(user);
			else {
				fillRequestAttributes(request, "Passwords requirements not met", email, phoneNumber, age, firstName,
						lastName);
				return openRegistrationPage();
			}
		} else {
			fillRequestAttributes(request, "Passwords didn't match", email, phoneNumber, age, firstName, lastName);
			return openRegistrationPage();
		}
		if (rows == 0) {
			fillRequestAttributes(request, "Account with this email exists", email, phoneNumber, age, firstName,
					lastName);
			return openRegistrationPage();
		} else {
			infoMessage = "User Registered, Log In";
			redirectAttributes.addFlashAttribute("message", infoMessage);
			mView.setViewName("redirect:/login");
			return mView;
		}
	}

	private void fillRequestAttributes(HttpServletRequest request, String messageString, String email, long phoneNumber,
			int age, String firstName, String lastName) {
		request.setAttribute("message", messageString);
		request.setAttribute("email", email);
		request.setAttribute("phoneNumber", phoneNumber);
		request.setAttribute("age", age);
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
	}

	public String openForgotPasswordPage() {
		return "forgotPassword";
	}

	public String sendResetPasswordEmail(HttpServletRequest request) {
		String email = request.getParameter("email");
		System.out.println("forgot password?");

		int temporaryPassword = 0;
		if (email != null && !email.equals("")) {
			Random rand = new Random();
			temporaryPassword = rand.nextInt(1255650);
			String to = email;
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.ssl.trust", "*");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("infoishipment@gmail.com", "pwizqylhlnpyhcrb");
				}
			});
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email));// change accordingly
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("iShip Reset Password");
				String textString = "Your request for password reset was received.\n"
						+ "Please follow the instructions below.\n"
						+ "1.Login to your account with the temporary password.\n"
						+ "2.Go to Setting -> Account Settings\n" + "3.Change your password\n\n\n"
						+ "Temporary Password: " + temporaryPassword;
				message.setText(textString);
				Transport.send(message);
			} catch (MessagingException e) {
				log.error(e.getMessage());
			}
			ApplicationDAO applicationDAO = new ApplicationDAO();
			User user = applicationDAO.getProfileDetails(email);
			applicationDAO.setTemporaryPassword(user.getUserId(), String.valueOf(temporaryPassword));
			request.setAttribute("message", "Login with Temporary Password");
		}
		return "login";
	}

	public String updateAccountInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ApplicationDAO applicationDAO = new ApplicationDAO();
		User user = (User) session.getAttribute("user");
		String newPass = request.getParameter("newPassword");
		String confirmNewPass = request.getParameter("confirmNewPassword");
		String email = user.getEmail();
		Long phoneNumber = Long.parseLong(request.getParameter("newPhoneNumber"));
		if (newPass.equals(confirmNewPass) && !newPass.equals("")) {
			applicationDAO.updateUserProfileInfoById(user.getUserId(), newPass, phoneNumber);
			request.setAttribute("message", "Login again");
			if (email != null && !email.equals("")) {
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
					message.setFrom(new InternetAddress(email));// change accordingly
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("iShip Reset Password");
					String textString = "Your password has been changed successfully.\n"
							+ "Use your new password to login to your account.\n";
					message.setText(textString);
					Transport.send(message);
				} catch (MessagingException e) {
					log.error(e.getMessage());
				}
			}
			return "login";

		} else if ((newPass.equals("") && confirmNewPass.equals("")) && phoneNumber != user.getPhoneNumber()) {
			applicationDAO.updateUserProfileInfoById(user.getUserId(), user.getPassword(), phoneNumber);
			request.setAttribute("message", "Login again");
			return "login";
		} else {
			return "accountInfo";
		}
	}

	public String openUserProfilePage(HttpSession session) {
		return "userProfile";
	}

	public String openAccountInfoPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		session.setAttribute("user", user);
		return "accountInfo";
	}

	public ModelAndView openAllUsersPage(HttpServletRequest request) {
		AdminsDAO adminsDAO = new AdminsDAO();
		ModelAndView mView = new ModelAndView();
		mView.setViewName("allUsers");
		mView.addObject("allUsers", adminsDAO.getAllUsers());
		return mView;

	}

}