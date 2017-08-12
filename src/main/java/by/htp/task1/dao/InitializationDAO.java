package by.htp.task1.dao;

import by.htp.task1.dao.exception.DAOException;

public interface InitializationDAO {
	void initialization() throws DAOException;
	void destroy() throws DAOException;
}
