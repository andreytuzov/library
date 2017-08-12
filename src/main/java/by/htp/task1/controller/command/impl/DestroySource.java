package by.htp.task1.controller.command.impl;

import org.apache.log4j.Logger;

import by.htp.task1.bean.ApplicationContextWrapper;
import by.htp.task1.controller.command.ICommand;
import by.htp.task1.service.InitializationService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.factory.ServiceFactory;

public class DestroySource implements ICommand {

	private static final Logger logger = Logger.getLogger(DestroySource.class);

	@Override
	public String executeCommand(String request) {
		ServiceFactory serviceFactory = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ServiceFactory.class);
		InitializationService initializationService = serviceFactory.getInitializationService();
		String response = null;

		try {
			initializationService.destroy();
			response = "Database has been destroyed";
		} catch (ServiceException e) {
			response = "Database has not been destroyed";
			logger.error(response, e);
		}

		return response;
	}

}
