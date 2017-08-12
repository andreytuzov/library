package by.htp.task1.controller.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import by.htp.task1.controller.command.impl.AddEditBook;
import by.htp.task1.controller.command.impl.AddNewBook;
import by.htp.task1.controller.command.impl.DestroySource;
import by.htp.task1.controller.command.impl.GetBookList;
import by.htp.task1.controller.command.impl.RemoveBook;
import by.htp.task1.controller.command.impl.SignIn;
import by.htp.task1.controller.command.impl.SignUp;
import by.htp.task1.controller.command.impl.InitializationSource;
import by.htp.task1.controller.command.impl.WrongRequest;

public final class CommandProvider {
	
	private final static Logger logger = Logger.getLogger(CommandProvider.class);
	
	private static CommandProvider instance = null;
	private static final Map<CommandName, ICommand> repository = new HashMap<CommandName, ICommand>();

	public CommandProvider() {
		repository.put(CommandName.INITIALIZATION_SOURCE,
				new InitializationSource());
		repository.put(CommandName.DESTROY_SOURCE, new DestroySource());
		repository.put(CommandName.ADD_NEW_BOOK, new AddNewBook());
		repository.put(CommandName.SIGN_IN, new SignIn());
		repository.put(CommandName.SIGN_UP, new SignUp());
		repository.put(CommandName.ADD_EDIT_BOOK, new AddEditBook());
		repository.put(CommandName.GET_BOOKLIST, new GetBookList());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		repository.put(CommandName.REMOVE_BOOK, new RemoveBook());
	}

	public static CommandProvider getInstance() {
		if (instance == null) {
			instance = new CommandProvider();
		}
		return instance;
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
