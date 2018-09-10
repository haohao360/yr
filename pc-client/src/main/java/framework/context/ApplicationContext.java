package framework.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContext implements ApplicationContextAware{

	private static org.springframework.context.ApplicationContext applicationContext; 
	public static org.springframework.context.ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	@Override
	public void setApplicationContext(
			org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
		ApplicationContext.applicationContext =applicationContext;
	}
	
	public static <T> T getBean(Class<T> clazz){
		return applicationContext.getBean(clazz);
	}

}
