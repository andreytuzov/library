package by.htp.task1.service;

import by.htp.task1.service.exception.ServiceException;

public interface InitializationService {
	void initialization() throws ServiceException;
	void destroy() throws ServiceException;
}
