package by.htp.task1.service.impl;

import by.htp.task1.bean.ApplicationContextWrapper;
import by.htp.task1.dao.InitializationDAO;
import by.htp.task1.dao.exception.DAOException;
import by.htp.task1.dao.factory.DAOFactory;
import by.htp.task1.service.InitializationService;
import by.htp.task1.service.exception.ServiceException;

public class InitializationServiceImpl implements InitializationService {

	private InitializationDAO getInitializationDAO() {
		return ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(DAOFactory.class).getInitializationDAO();
	}

	@Override
	public void initialization() throws ServiceException {
		InitializationDAO initializationDAO = getInitializationDAO();
		try {
			initializationDAO.initialization();
		} catch (DAOException e) {
			throw new ServiceException("Error initialization", e);
		}
	}

	@Override
	public void destroy() throws ServiceException {
		InitializationDAO initializationDAO = getInitializationDAO();

		try {
			initializationDAO.destroy();
		} catch (DAOException e) {
			throw new ServiceException("Error destroy", e);
		}
	}

}
