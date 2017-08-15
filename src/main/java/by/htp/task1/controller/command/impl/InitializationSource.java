package by.htp.task1.controller.command.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.task1.controller.command.ICommand;
import by.htp.task1.service.InitializationService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.factory.ServiceFactory;

@Component
public class InitializationSource implements ICommand {

	Logger logger = Logger.getLogger(InitializationService.class);

	@Autowired
	private ServiceFactory serviceFactory;
	
	@Override
	public String executeCommand(String request) {
		InitializationService initializationService = serviceFactory.getInitializationService();
		String response = null;

		try {
			initializationService.initialization();
			response = "Database has been initialized";
		} catch (ServiceException e) {
			response = "Database has not been initialized";
			e.printStackTrace();
			logger.error(response, e);
		}

		return response;
	}

}
