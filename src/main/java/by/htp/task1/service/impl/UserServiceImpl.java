package by.htp.task1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.task1.dao.UserDAO;
import by.htp.task1.dao.exception.DAOException;
import by.htp.task1.dao.factory.DAOFactory;
import by.htp.task1.entity.User;
import by.htp.task1.service.UserService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.validation.ValidationData;


@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private DAOFactory daoFactory;
	
	@Override
	public void signIn(String login, String password) throws ServiceException {
		if (!ValidationData.validUser(login, password)) {
			throw new ServiceException("Iccorrent user's login or password");
		}

		UserDAO userDAO = daoFactory.getUserDAO();

		// Attention String_paswword convert to int_password(HashCode)
		try {
			User user = userDAO.signIn(login, password.hashCode());
			if (user == null) {
				throw new ServiceException("User is not found");
			}
		} catch (DAOException e) {
			throw new ServiceException("Error sign in", e);
		}
	}

	@Override
	public void signUp(String login, String password) throws ServiceException {
		if (!ValidationData.validUser(login, password)) {
			throw new ServiceException("Icorrent user's login or password");
		}
		
		UserDAO userDAO = daoFactory.getUserDAO();

		// Attention String_paswword convert to int_password(HashCode)
		try {
			userDAO.signUp(login, password.hashCode());
		} catch (DAOException e) {
			throw new ServiceException("Error sign up", e);
		}
	}

}
