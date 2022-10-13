package services;

import org.springframework.web.servlet.ModelAndView;

public class ApplicationService {

	
	public ModelAndView openStartPage() {
		return openHomePage();
	}
	
	public ModelAndView openHomePage() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("home");
		return mView;
	}
	
	public ModelAndView openFaqsPage() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("faqs");
		return mView;
	}
	
	public ModelAndView openContactUsPage() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("contactus");
		return mView;
	}
}
