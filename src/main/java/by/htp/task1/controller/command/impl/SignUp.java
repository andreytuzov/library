package by.htp.task1.controller.command.impl;

import org.apache.log4j.Logger;

import by.htp.task1.bean.ApplicationContextWrapper;
import by.htp.task1.controller.command.ICommand;
import by.htp.task1.controller.command.RequestParameter;
import by.htp.task1.service.UserService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.factory.ServiceFactory;

public class SignUp implements ICommand {

	private static final Logger logger = Logger.getLogger(SignUp.class);

	@Override
	public String executeCommand(String request) {
		String[] parameter = request.split(RequestParameter.PARAM_DELIMITER);
		String login = parameter[RequestParameter.INDEX_LOGIN_USER];
		String password = parameter[RequestParameter.INDEX_PASSWORD_USER];

		ServiceFactory serviceFactory = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ServiceFactory.class);
		UserService userService = serviceFactory.getUserService();
		String response = null;

		try {
			userService.signUp(login, password);
			response = "User was registrated " + login;
		} catch (ServiceException e) {
			response = "Sign up error";
			logger.error(response, e);
		}

		return response;
	}

}
