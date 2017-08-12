package by.htp.task1.service.impl;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.htp.task1.dao.connection.ConnectionPool;
import by.htp.task1.dao.exception.ConnectionPoolException;
import by.htp.task1.resources.ResourceParameter;
import by.htp.task1.service.BookService;
import by.htp.task1.service.exception.ServiceException;
import by.htp.task1.service.factory.ServiceFactory;

public class TestBookServiceImpl {

	private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			ResourceParameter.APPLICATION_CONTEXT_PATH);
	private static final ConnectionPool connectionPool = context.getBean(ConnectionPool.class);
	
	private final BookService bookService = context.getBean(ServiceFactory.class).getBookService();

	@BeforeClass
	public static void initSource() throws ConnectionPoolException {
		connectionPool.init();
	}

	@AfterClass
	public static void destroySource() throws ConnectionPoolException,
			IOException {
		connectionPool.close();
	}

	@Test(expected = ServiceException.class)
	public void testAddNewBook() throws ServiceException {
		bookService.addNewBook(null, null, null, null, null);
	}

	@Test
	public void testAddEditBook() {
		try {
			bookService.addEditBook(null, "MyAuthor", "MyGenre", "2017", "10",
					"1");
		} catch (ServiceException e) {
			Assert.assertEquals("Incorrect data about book", e.getMessage());
		}
	}

}
