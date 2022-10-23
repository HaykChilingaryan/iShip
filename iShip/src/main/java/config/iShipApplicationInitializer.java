package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class iShipApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		@SuppressWarnings("rawtypes")
		Class[] configClasses = { iShipApplicationConfig.class };
		return configClasses;
	}

	@Override
	protected String[] getServletMappings() {
		String servletMappingStrings[] = { "/" };
		return servletMappingStrings;
	}

}
