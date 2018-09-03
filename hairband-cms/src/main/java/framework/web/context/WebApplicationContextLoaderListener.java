package framework.web.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WebApplicationContextLoaderListener implements ServletContextListener{
	
	private static Logger log = LoggerFactory.getLogger(WebApplicationContextLoaderListener.class);
	private static org.springframework.context.ApplicationContext ap;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("ApplicationContext loader ...");
		ap = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		log.info("ApplicationContext loader success");
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("ApplicationContext close ...");
		((AbstractApplicationContext)ap).registerShutdownHook();
		log.info("ApplicationContext close success");
	}
	
	public static org.springframework.context.ApplicationContext getApplicationContext(){
		return WebApplicationContextLoaderListener.ap;
	}

}
