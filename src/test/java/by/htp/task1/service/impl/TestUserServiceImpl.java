package by.htp.task1.service.impl;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.task1.resources.ResourceParameter;
import by.htp.task1.service.UserService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.factory.ServiceFactory;

public class TestUserServiceImpl {
	/*
	 * Зарегистрировать пользователя не получиться, т.к. мы не инициализировали
	 * ConnectionPool Соответственно когда берем Connection получаем
	 * NullPointerException.
	 */
	@Test(expected = NullPointerException.class)
	public void signUp() throws ServiceException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				ResourceParameter.APPLICATION_CONTEXT_PATH);
		ServiceFactory serviceFactory = context.getBean(ServiceFactory.class);

		UserService userService = serviceFactory.getUserService();
		userService.signUp("Dylan O'Brien", "12345678");
		context.close();
	}
}
