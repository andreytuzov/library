package by.htp.task1.controller.command.impl;

import org.apache.log4j.Logger;

import by.htp.task1.bean.ApplicationContextWrapper;
import by.htp.task1.controller.command.ICommand;
import by.htp.task1.controller.command.RequestParameter;
import by.htp.task1.service.BookService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.factory.ServiceFactory;

public class RemoveBook implements ICommand {

	private static final Logger logger = Logger.getLogger(RemoveBook.class);
	
	@Override
	public String executeCommand(String request) {
		String[] parameters = request.split(RequestParameter.PARAM_DELIMITER);
		String idBook = parameters[RequestParameter.INDEX_ID_BOOK_REMOVE];
		
		ServiceFactory serviceFactory = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ServiceFactory.class);
		BookService bookService = serviceFactory.getBookService();
		
		String response = null;
		
		try {
			bookService.removeBook(idBook);
			response = "book successfully removed";
		} catch (ServiceException ex) {
			response = "Error removing book";
			logger.error(response, ex);
		}
		
		return response;
	}

}
