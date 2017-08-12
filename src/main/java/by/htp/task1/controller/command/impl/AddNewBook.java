package by.htp.task1.controller.command.impl;

import org.apache.log4j.Logger;

import by.htp.task1.bean.ApplicationContextWrapper;
import by.htp.task1.controller.command.ICommand;
import by.htp.task1.controller.command.RequestParameter;
import by.htp.task1.service.BookService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.factory.ServiceFactory;

public class AddNewBook implements ICommand {

	private static final Logger logger = Logger.getLogger(AddNewBook.class);

	@Override
	public String executeCommand(String request) {
		String[] parameter = request.split(RequestParameter.PARAM_DELIMITER);
		String title = parameter[RequestParameter.INDEX_TITLE_BOOK];
		String author = parameter[RequestParameter.INDEX_AUTHOR_BOOK];
		String genre = parameter[RequestParameter.INDEX_GENRE_BOOK];
		String year = parameter[RequestParameter.INDEX_YEAR_BOOK];
		String quantity = parameter[RequestParameter.INDEX_QUANTITY_BOOK];

		ServiceFactory serviceFactory = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ServiceFactory.class);
		BookService bookService = serviceFactory.getBookService();
		String response = null;

		try {
			bookService.addNewBook(title, genre, author, year, quantity);
			response = "Book successfully added";
		} catch (ServiceException e) {
			response = "Error adding book";
			logger.error(response, e);
		}

		return response;
	}

}
