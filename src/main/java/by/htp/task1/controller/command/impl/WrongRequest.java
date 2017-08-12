package by.htp.task1.controller.command.impl;

import by.htp.task1.controller.command.ICommand;

public class WrongRequest implements ICommand {

	@Override
	public String executeCommand(String request) {
		return "Wrong request!";
	}

}
