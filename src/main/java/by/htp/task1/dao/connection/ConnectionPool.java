package by.htp.task1.dao.connection;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import by.htp.task1.dao.exception.ConnectionPoolException;
import by.htp.task1.dao.exception.DAOException;

@Component
public final class ConnectionPool implements Closeable {

	private static final Logger logger = Logger.getLogger(ConnectionPool.class);

	private BlockingQueue<Connection> freeConnection;
	private BlockingQueue<Connection> busyConnection;

	@Value("${db.poolsize}")
	private int poolsize;
	
	@Value("${db.driver}")
	private String driver;
	
	@Value("${db.user}")
	private String user;
	
	@Value("${db.password}")
	private String password;
	
	@Value("${db.url}")
	private String url;

	public ConnectionPool() {
	}

	public void init() throws ConnectionPoolException {
		freeConnection = new ArrayBlockingQueue<Connection>(poolsize);
		busyConnection = new ArrayBlockingQueue<Connection>(poolsize);

		try {
			Class.forName(driver);
			for (int i = 0; i < poolsize; i++) {
				freeConnection.add(DriverManager.getConnection(url, user, password));
			}
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("Can't find database driver class", e);
		} catch (SQLException e) {
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);
		}

	}

	public Connection take() throws ConnectionPoolException {
		Connection connection = null;
		try {
			connection = freeConnection.take();
			busyConnection.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Error connecting to the data source", e);
		}
		return connection;
	}

	public void free(Connection connection) throws InterruptedException, DAOException {
		if (connection == null) {
			throw new DAOException("Connection is null");
		}

		Connection tempConnection = connection;
		connection = null;
		busyConnection.remove(tempConnection);
		freeConnection.put(tempConnection);
	}

	@Override
	public void close() throws IOException {
		List<Connection> listConnection = new ArrayList<Connection>();
		listConnection.addAll(this.busyConnection);
		listConnection.addAll(this.freeConnection);

		for (Connection connection : listConnection) {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Error close connection", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt, ResultSet rs) {
		if (con != null) {
			try {
				free(con);
			} catch (InterruptedException | DAOException e) {
				logger.error("Connection isn't return to the pool", e);
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				logger.error("Statement isn't closed", e);
			}
		}

		if (preSt != null) {
			try {
				preSt.close();
			} catch (SQLException e) {
				logger.error("PrepareStatement ins't closed", e);
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.error("ResultSet ins't closed", e);
			}
		}
	}

	public void closeConnection(Connection con, Statement st) {
		closeConnection(con, st, null, null);
	}

	public void closeConnection(Connection con, PreparedStatement preSt) {
		closeConnection(con, null, preSt, null);
	}

	public void closeConnection(Connection con, ResultSet rs) {
		closeConnection(con, null, null, rs);
	}

	public void closeConnection(Connection con, Statement st, PreparedStatement preSt) {
		closeConnection(con, st, preSt, null);
	}

	public void closeConnection(Connection con, PreparedStatement preSt, ResultSet rs) {
		closeConnection(con, null, preSt, rs);
	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) {
		closeConnection(con, st, null, rs);
	}

}
