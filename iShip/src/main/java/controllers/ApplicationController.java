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
	
	
	public final ApplicationService applicationService = new ApplicationService();
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView openStartPage() {
		return applicationService.openHomePage();
	}
	
	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public ModelAndView openHomePage() {
		return applicationService.openHomePage();
	}
	
	@RequestMapping(value = {"/faqs"}, method = RequestMethod.GET)
	public ModelAndView openFaqsPage() {
		return applicationService.openFaqsPage();
	}
	
	@RequestMapping(value = {"/contactUs"}, method = RequestMethod.GET)
	public ModelAndView openContactUsPage() {
		return applicationService.openContactUsPage();
	}
}
