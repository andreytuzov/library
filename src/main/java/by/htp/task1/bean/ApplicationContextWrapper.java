package by.htp.task1.bean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.task1.resources.ResourceParameter;

public class ApplicationContextWrapper {
	
	private static ApplicationContextWrapper instance;
	
	private ClassPathXmlApplicationContext applicationContext;
	
	private ApplicationContextWrapper() {}
	
	public static ApplicationContextWrapper getInstance() {
		if (instance == null) {
			synchronized (ApplicationContextWrapper.class) {
				if (instance == null) {
					instance = new ApplicationContextWrapper();
					instance.applicationContext = new ClassPathXmlApplicationContext(ResourceParameter.APPLICATION_CONTEXT_PATH);
					instance.applicationContext.registerShutdownHook();
				}
			}
		}
		return instance;
	}
	
	public ClassPathXmlApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
}
