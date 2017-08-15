package by.htp.task1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.task1.dao.InitializationDAO;
import by.htp.task1.dao.exception.DAOException;
import by.htp.task1.dao.factory.DAOFactory;
import by.htp.task1.service.InitializationService;
import by.htp.task1.service.exception.ServiceException;

@Component
public class InitializationServiceImpl implements InitializationService {

	@Autowired
	private DAOFactory daoFactory;

	@Override
	public void initialization() throws ServiceException {
		InitializationDAO initializationDAO = daoFactory.getInitializationDAO();
		try {
			initializationDAO.initialization();
		} catch (DAOException e) {
			throw new ServiceException("Error initialization", e);
		}
	}

	@Override
	public void destroy() throws ServiceException {
		InitializationDAO initializationDAO = daoFactory.getInitializationDAO();

		try {
			initializationDAO.destroy();
		} catch (DAOException e) {
			throw new ServiceException("Error destroy", e);
		}
	}

}
