package by.htp.task1.service;

import by.htp.task1.service.exception.ServiceException;

public interface UserService {
	void signIn(String login, String password) throws ServiceException;
	void signUp(String login, String password) throws ServiceException;
}
