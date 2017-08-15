package by.htp.task1.dao.connection.manager;

import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import by.htp.task1.resources.ResourceParameter;

@Component
public final class DBResourceManager {
	private final ResourceBundle bundle = ResourceBundle.getBundle(ResourceParameter.DATABASE_PROPERTIES_PATH);
	
	public DBResourceManager() {}


	public String getValue(String key){
		return bundle.getString(key);
	}
	
}
