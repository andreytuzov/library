package by.htp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.task1.controller.command.RequestParameter;
import by.htp.task1.controller.command.CommandProvider;
import by.htp.task1.controller.command.ICommand;

@Component
public final class Controller {

	@Autowired
	private CommandProvider provider;


	
	public String executeAction(String request) {
		String commandName = request.substring(0, request.indexOf(RequestParameter.PARAM_DELIMITER));
		ICommand command = provider.getCommand(commandName);
		return command.executeCommand(request);
	}
}
