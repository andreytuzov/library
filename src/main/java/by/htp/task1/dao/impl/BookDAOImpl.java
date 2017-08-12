package by.htp.task1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.task1.bean.ApplicationContextWrapper;
import by.htp.task1.dao.BookDAO;
import by.htp.task1.dao.ColumnLabel;
import by.htp.task1.dao.SQLCommand;
import by.htp.task1.dao.connection.ConnectionPool;
import by.htp.task1.dao.exception.ConnectionPoolException;
import by.htp.task1.dao.exception.DAOException;
import by.htp.task1.entity.Book;

public class BookDAOImpl implements BookDAO {

	@Override
	public void addNewBook(String title, String author, String genre, String year, int quantity) throws DAOException {

		ConnectionPool pool = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ConnectionPool.class);

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLCommand.INSERT_BOOK);
			preparedStatement.setString(SQLCommand.IndexLabel.TITLE_BOOK, title);
			preparedStatement.setString(SQLCommand.IndexLabel.AUTHOR_BOOK, author);
			preparedStatement.setString(SQLCommand.IndexLabel.GENRE_BOOK, genre);
			preparedStatement.setString(SQLCommand.IndexLabel.YEAR_BOOK, year);
			preparedStatement.setInt(SQLCommand.IndexLabel.QUANTITY_BOOK, quantity);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'inser_book'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public void addEditBook(String title, String genre, String author, String year, int quantity, int idBook)
			throws DAOException {
		ConnectionPool pool = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ConnectionPool.class);
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLCommand.UPDATE_BOOK);
			preparedStatement.setString(SQLCommand.IndexLabel.TITLE_BOOK, title);
			preparedStatement.setString(SQLCommand.IndexLabel.AUTHOR_BOOK, author);
			preparedStatement.setString(SQLCommand.IndexLabel.GENRE_BOOK, genre);
			preparedStatement.setString(SQLCommand.IndexLabel.YEAR_BOOK, year);
			preparedStatement.setInt(SQLCommand.IndexLabel.QUANTITY_BOOK, quantity);
			preparedStatement.setInt(SQLCommand.IndexLabel.ID_BOOK, idBook);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'update_book'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

	@Override
	public void removeBook(int id) throws DAOException {
		ConnectionPool pool = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ConnectionPool.class);
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLCommand.REMOVE_BOOK);
			preparedStatement.setInt(SQLCommand.IndexLabel.ID_BOOK_REMOVE, id);
			int count = preparedStatement.executeUpdate();
			if (count == 0) {
				throw new DAOException("Book with " + id + " is not found");
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'remove_book'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}

	}

	@Override
	public List<Book> getBooklist() throws DAOException {
		ConnectionPool pool = ApplicationContextWrapper.getInstance().getApplicationContext()
				.getBean(ConnectionPool.class);
		
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Book> booklist = null;

		try {
			connection = pool.take();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQLCommand.SELECT_BOOK);

			booklist = new ArrayList<Book>();
			Book book = null;

			while (resultSet.next()) {
				book = new Book();
				book.setId(resultSet.getInt(ColumnLabel.BOOK_ID));
				book.setTitle(resultSet.getString(ColumnLabel.BOOK_TITLE));
				book.setAuthor(resultSet.getString(ColumnLabel.BOOK_AUTHOR));
				book.setGenre(resultSet.getString(ColumnLabel.BOOK_GENRE));
				book.setYear(resultSet.getString(ColumnLabel.BOOK_YEAR));
				book.setQuantity(resultSet.getInt(ColumnLabel.BOOK_QUANTITY));
				book.setStatus(resultSet.getBoolean(ColumnLabel.BOOK_STATUS));
				booklist.add(book);
			}

		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'select_book'", e);
		} finally {
			pool.closeConnection(connection, statement, resultSet);
		}

		return booklist;
	}

}
