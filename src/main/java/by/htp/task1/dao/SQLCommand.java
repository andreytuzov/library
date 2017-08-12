package by.htp.task1.dao;

public final class SQLCommand {
	private SQLCommand() {}
	public static final String INSERT_USER = "INSERT INTO user (u_login, u_password) VALUES (?,?)";
	public static final String SELECT_USER_BY_LOGIN_PASSWORD = "SELECT u_id, u_login, u_password FROM user WHERE u_login = ? AND u_password = ?";
	public static final String INSERT_BOOK = "INSERT INTO book (b_title, b_author, b_genre, b_year, b_quantity) VALUES (?,?,?,?,?)";
	public static final String UPDATE_BOOK = "UPDATE book SET b_title = ?, b_author = ?, b_genre = ?, b_year = ?, b_quantity = ? WHERE b_id = ?";
	public static final String SELECT_BOOK = "SELECT * FROM book";
	public static final String REMOVE_BOOK = "DELETE FROM book WHERE b_id = ?";
	
	public static class IndexLabel {
		
		public static final int TITLE_BOOK = 1;
		public static final int AUTHOR_BOOK = 2;
		public static final int GENRE_BOOK = 3;
		public static final int YEAR_BOOK = 4;
		public static final int QUANTITY_BOOK = 5;
		public static final int ID_BOOK = 6;
		
		public static final int ID_BOOK_REMOVE = 1;
		
		
		public static final int LOGIN_USER = 1;
		public static final int PASSWORD_USER = 2;
	}
}
