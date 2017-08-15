package by.htp.task1.dao.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.task1.dao.InitializationDAO;
import by.htp.task1.dao.connection.ConnectionPool;
import by.htp.task1.dao.exception.ConnectionPoolException;
import by.htp.task1.dao.exception.DAOException;

@Component
public class InitializationDAOImpl implements InitializationDAO {

	@Autowired
	private ConnectionPool pool;
	
	@Override
	public void initialization() throws DAOException {
		try {
			pool.init();
		} catch (ConnectionPoolException e) {
			throw new DAOException(
					"There was a problem initialization database", e);
		}
	}

	@Override
	public void destroy() throws DAOException {
		try {
			pool.close();
		} catch (IOException e) {
			throw new DAOException("Failure to close all connections", e);
		}
	}

}
