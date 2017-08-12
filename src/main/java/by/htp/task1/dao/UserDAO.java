package by.htp.task1.dao;

import by.htp.task1.dao.exception.DAOException;
import by.htp.task1.entity.User;

public interface UserDAO {
	User signIn(String login, int password) throws DAOException;
	void signUp(String login, int password) throws DAOException;
}
