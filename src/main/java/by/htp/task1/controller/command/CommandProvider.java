package by.htp.task1.controller.command;

import java.util.Map;

import org.apache.log4j.Logger;

public final class CommandProvider {
	
	private final static Logger logger = Logger.getLogger(CommandProvider.class);
	
	private Map<CommandName, ICommand> repository;

	public CommandProvider(Map<CommandName, ICommand> repository) {
		this.repository = repository;
	}
	
	public ICommand getCommand(String key) {
		ICommand command;

		try {
			CommandName commandName = CommandName.valueOf(key.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			logger.error(e);
			command = repository.get(CommandName.WRONG_REQUEST);
		}

		return command;
	}

}
