package by.htp.task1.service.impl;

import java.util.IllegalFormatException;
import java.util.List;

import by.htp.task1.bean.ApplicationContextWrapper;
import by.htp.task1.dao.BookDAO;
import by.htp.task1.dao.exception.DAOException;
import by.htp.task1.dao.factory.DAOFactory;
import by.htp.task1.entity.Book;
import by.htp.task1.service.BookService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.validation.ValidationData;

public class BookServiceImpl implements BookService {

	private BookDAO getBookDAO() {
		return ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(DAOFactory.class).getBookDAO();
	}

	@Override
	public void addNewBook(String title, String genre, String author,
			String year, String quantityStr) throws ServiceException {
		if (!ValidationData.validBook(title, genre, author, year, quantityStr)) {
			throw new ServiceException("Incorrect data about book");
		}

		int quantity = 0;
		try {
			quantity = Integer.parseInt(quantityStr);
		} catch (IllegalFormatException e) {
			throw new ServiceException("Year format exception");
		}

		BookDAO bookDAO = getBookDAO();
		try {
			bookDAO.addNewBook(title, author, genre, year, quantity);
		} catch (DAOException e) {
			throw new ServiceException("Error adding a book to the library", e);
		}

	}

	@Override
	public void addEditBook(String title, String genre, String author,
			String year, String quantityStr, String idBookStr)
			throws ServiceException {
		if (!ValidationData.validBook(title, genre, author, year, quantityStr,
				idBookStr)) {
			throw new ServiceException("Incorrect data about book");
		}

		int idBook = Integer.parseInt(idBookStr);
		int quantity = Integer.parseInt(quantityStr);

		BookDAO bookDAO = getBookDAO();
		try {
			bookDAO.addEditBook(title, genre, author, year, quantity, idBook);
		} catch (DAOException e) {
			throw new ServiceException("Error editing book", e);
		}
	}

	@Override
	public void removeBook(String idBookStr) throws ServiceException {
		if (!ValidationData.validString(idBookStr)) {
			throw new ServiceException("Incorrect idBook");
		}

		int idBook = Integer.parseInt(idBookStr);

		BookDAO bookDAO = getBookDAO();
		try {
			bookDAO.removeBook(idBook);
		} catch (DAOException ex) {
			throw new ServiceException("Error removing book", ex);
		}

	}

	@Override
	public List<Book> getBooklist() throws ServiceException {
		BookDAO bookDAO = getBookDAO();
		List<Book> booklist = null;

		try {
			booklist = bookDAO.getBooklist();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		if (booklist == null) {
			throw new ServiceException("Booklist not found");
		}

		return booklist;
	}

}
