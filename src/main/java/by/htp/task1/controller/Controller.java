package by.htp.task1.controller;

import by.htp.task1.controller.command.RequestParameter;
import by.htp.task1.controller.command.CommandProvider;
import by.htp.task1.controller.command.ICommand;

public final class Controller {

	public String executeAction(String request) {
		String commandName = request.substring(0,
				request.indexOf(RequestParameter.PARAM_DELIMITER));
		CommandProvider provider = CommandProvider.getInstance();
		ICommand command = provider.getCommand(commandName);
		
		
		
		return command.executeCommand(request);
	}
}
