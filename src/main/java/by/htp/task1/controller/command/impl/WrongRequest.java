package by.htp.task1.controller.command.impl;

import org.springframework.stereotype.Component;

import by.htp.task1.controller.command.ICommand;

@Component
public class WrongRequest implements ICommand {

	@Override
	public String executeCommand(String request) {
		return "Wrong request!";
	}

}
