package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import pl.coderslab.warsztat3.db.DbUtil;

public class User {
	private long id;
	private String username;
	private String email;
	private String password;
	private int user_group_id;
	
	public User() {
		super();
		this.id = 0;
		this.username = "";
		this.email = "";
		this.password = "";
		this.user_group_id = 0;
	}
	
	
	
	public User(long id, String username, String email, String password, int user_group_id) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.user_group_id = user_group_id;
	}



	public User(String username, String email, String password) {
		super();
		this.id = 0;
		this.username = username;
		this.email = email;
		setPassword(password);
		this.user_group_id = 0;
	}
	
	

	public User(String username, String email, String password, int user_group_id) {
		super();
		this.id = 0;
		this.username = username;
		this.email = email;
		setPassword(password);
		this.user_group_id = user_group_id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public boolean checkPassword (String password) {
		return BCrypt.checkpw(password, this.password);
	}

	public int getUser_group_id() {
		return user_group_id;
	}

	public void setUser_group_id(int user_group_id) {
		this.user_group_id = user_group_id;
	}

	public long getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public void deleteFromDb(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, this.id);
			ps.executeUpdate();
			this.id = 0;
			ps.close();
		}
	}
	
	public static List<User> loadAllByGroupId(int user_group_id) {
		Connection conn;
		List<User> result = new ArrayList<>();
		try {
			conn = DbUtil.getConn();
			result = UserDao.loadAllByGroupId(conn, user_group_id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static User loadUserById(int id){
		/*
		 * Reads a record from users table in database and returns a User object that
		 * represents it.
		 */
		Connection conn;
		User result = new User();
		try {
			conn = DbUtil.getConn();
			result = UserDao.loadUserById(conn, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public static List<User> loadAll() {
		/*
		 * Reads all records from users table in database and returns an array of 
		 * user objects. This method is static.
		 */
		Connection conn;
		List<User> result = new ArrayList<>();
		try {
			conn = DbUtil.getConn();
			result = UserDao.loadAll(conn);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void saveToDb() {
		/*
		 * Saves an object to database.
		 */
		Connection conn;
		try {
			conn = DbUtil.getConn();
			UserDao.saveToDb(conn, this);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}