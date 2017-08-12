package by.htp.task1.dao.impl;

import java.io.IOException;

import by.htp.task1.bean.ApplicationContextWrapper;
import by.htp.task1.dao.InitializationDAO;
import by.htp.task1.dao.connection.ConnectionPool;
import by.htp.task1.dao.exception.ConnectionPoolException;
import by.htp.task1.dao.exception.DAOException;

public class InitializationDAOImpl implements InitializationDAO {

	@Override
	public void initialization() throws DAOException {
		ConnectionPool pool = ApplicationContextWrapper.getInstance()
				.getApplicationContext().getBean(ConnectionPool.class);

		try {
			pool.init();
		} catch (ConnectionPoolException e) {
			throw new DAOException(
					"There was a problem initialization database", e);
		}
	}

	@Override
	public void destroy() throws DAOException {
		ConnectionPool pool = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ConnectionPool.class);

		try {
			pool.close();
		} catch (IOException e) {
			throw new DAOException("Failure to close all connections", e);
		}
	}

}
