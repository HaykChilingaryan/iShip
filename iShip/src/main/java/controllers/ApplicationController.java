package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;

@Controller
@SessionAttributes("message")
public class ApplicationController {
	
	public static final ApplicationService APPLICATION_SERVICE = new ApplicationService();
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView openStartPage() {
		return APPLICATION_SERVICE.openHomePage();
	}
	
	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public ModelAndView openHomePage() {
		return APPLICATION_SERVICE.openHomePage();
	}
	
	@RequestMapping(value = {"/faqs"}, method = RequestMethod.GET)
	public ModelAndView openFaqsPage() {
		return APPLICATION_SERVICE.openFaqsPage();
	}
	
	@RequestMapping(value = {"/contactUs"}, method = RequestMethod.GET)
	public ModelAndView openContactUsPage() {
		return APPLICATION_SERVICE.openContactUsPage();
	}
}
