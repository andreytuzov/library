package by.htp.task1.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.task1.service.BookService;
import by.htp.task1.service.InitializationService;
import by.htp.task1.service.UserService;

@Component
public final class ServiceFactory {

	private final UserService userService;
	private final BookService bookService;
	private final InitializationService initializationService;

	@Autowired
	public ServiceFactory(UserService userService, BookService bookService,
			InitializationService initializationService) {
		this.userService = userService;
		this.bookService = bookService;
		this.initializationService = initializationService;
	}

	public UserService getUserService() {
		return userService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public InitializationService getInitializationService() {
		return initializationService;
	}

}
