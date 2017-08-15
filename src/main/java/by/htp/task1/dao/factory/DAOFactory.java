package by.htp.task1.dao.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.task1.dao.BookDAO;
import by.htp.task1.dao.InitializationDAO;
import by.htp.task1.dao.UserDAO;

@Component
public final class DAOFactory {
	
	private final UserDAO userDAO;
	private final BookDAO bookDAO;
	private final InitializationDAO initializationDAO;
	
	
	@Autowired
	public DAOFactory(UserDAO userDAO, BookDAO bookDAO, InitializationDAO initializationDAO) {
		this.userDAO = userDAO;
		this.bookDAO = bookDAO;
		this.initializationDAO = initializationDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	public InitializationDAO getInitializationDAO() {
		return initializationDAO;
	}
	
}
