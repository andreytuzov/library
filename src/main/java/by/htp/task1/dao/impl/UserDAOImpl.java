package by.htp.task1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.htp.task1.dao.ColumnLabel;
import by.htp.task1.dao.SQLCommand;
import by.htp.task1.dao.UserDAO;
import by.htp.task1.dao.connection.ConnectionPool;
import by.htp.task1.dao.exception.ConnectionPoolException;
import by.htp.task1.dao.exception.DAOException;
import by.htp.task1.entity.User;

@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	private ConnectionPool pool;
	
	@Override
	public User signIn(String login, int password) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLCommand.SELECT_USER_BY_LOGIN_PASSWORD);
			preparedStatement.setString(SQLCommand.IndexLabel.LOGIN_USER, login);
			preparedStatement.setInt(SQLCommand.IndexLabel.PASSWORD_USER, password);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(ColumnLabel.USER_ID));
				user.setLogin(resultSet.getString(ColumnLabel.USER_LOGIN));
				user.setPassword(resultSet.getInt(ColumnLabel.USER_PASSWORD));
			}
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'select_user_id_by_login_password'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement, resultSet);
		}

		return user;

	}

	@Override
	public void signUp(String login, int password) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = pool.take();
			preparedStatement = connection.prepareStatement(SQLCommand.INSERT_USER);
			preparedStatement.setString(SQLCommand.IndexLabel.LOGIN_USER, login);
			preparedStatement.setInt(SQLCommand.IndexLabel.PASSWORD_USER, password);
			preparedStatement.executeUpdate();
		} catch (ConnectionPoolException e) {
			throw new DAOException("There was a problem connecting to the database", e);
		} catch (SQLException e) {
			throw new DAOException("Error executing the query 'insert_user'", e);
		} finally {
			pool.closeConnection(connection, preparedStatement);
		}
	}

}
