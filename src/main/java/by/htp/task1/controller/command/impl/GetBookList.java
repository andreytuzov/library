package by.htp.task1.controller.command.impl;

import java.util.List;

import org.apache.log4j.Logger;

import by.htp.task1.bean.ApplicationContextWrapper;
import by.htp.task1.controller.command.ICommand;
import by.htp.task1.entity.Book;
import by.htp.task1.service.BookService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.factory.ServiceFactory;

public class GetBookList implements ICommand {

	private static final Logger logger = Logger.getLogger(GetBookList.class);

	@Override
	public String executeCommand(String request) {
		ServiceFactory serviceFactory = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ServiceFactory.class);
		BookService bookService = serviceFactory.getBookService();

		List<Book> booklist = null;
		String response = null;
		try {
			booklist = bookService.getBooklist();
			response = "List of books received";

			// Circle just for test
			for (Book book : booklist) {
				logger.info(book.toString());
			}
		} catch (ServiceException e) {
			response = "Error getting list of books";
			logger.error(response, e);
		}

		return response;
	}

}
