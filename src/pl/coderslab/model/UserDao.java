package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	
	public static List<User> loadAllByGroupId(Connection conn, int user_group_id) throws SQLException {
		ArrayList<User> result = new ArrayList<User>();
		String sql = "SELECT * FROM users WHERE user_group_id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, user_group_id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			long id = rs.getLong(1);
			String username = rs.getString(2);
			String email = rs.getString(3);
			String password = rs.getString(4);
			User loadedUser = new User(id, username, email, password, user_group_id);
			result.add(loadedUser);
		}
		ps.close();
		rs.close();
		return result;
	}
	
	public static User loadUserById(Connection conn, int id) throws SQLException {
		/*
		 * Reads a record from users table in database and returns a User object that
		 * represents it.
		 */
		String sql = "SELECT * FROM users WHERE id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int userId = rs.getInt("id");
			String username = rs.getString("username");
			String email = rs.getString("email");
			String password = rs.getString("password");
			int user_group_id = rs.getInt("user_group_id");
			rs.close();
			ps.close();
			User loadedUser = new User(userId, username, email, password, user_group_id);
			return loadedUser;
		}
		ps.close();
		return null;
	}
	
	public static List<User> loadAll(Connection conn) throws SQLException {
		/*
		 * Reads all records from users table in database and returns an array of 
		 * user objects. This method is static.
		 */
		ArrayList<User> result = new ArrayList<User>();
		String sql = "SELECT * FROM users;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt(1);
			String username = rs.getString(2);
			String email = rs.getString(3);
			String password = rs.getString(4);
			int userGroupId = rs.getInt(5);
			User loadedUser = new User(id, username, email, password, userGroupId);
			result.add(loadedUser);
		}
		rs.close();
		return result;
	}
	
	public static void saveToDb(Connection conn, User user)  throws SQLException {
		/*
		 * Saves an object to database.
		 */
		if (user.getId() == 0) {
			String sql = "INSERT INTO users(username, email, password, user_group_id) "
					+ "VALUES(?, ?, ?, ?);";
			String[] generatedColumns = {"ID"};
			PreparedStatement ps = conn.prepareStatement(sql, generatedColumns);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getUser_group_id());
			ps.executeUpdate();
			ResultSet gk = ps.getGeneratedKeys();
			if (gk.next()) {
				user.setId(gk.getInt(1)); // reads automatically generated id (AUTO_INCREMENT)
			}
			gk.close();
			ps.close();
			
		} else {
			String sql = "UPDATE users SET username =?, email=?, password=?, user_group_id =? "
					+ "WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getUser_group_id());
			ps.setLong(5, user.getId());
			ps.executeUpdate();
			ps.close();
		}
	}
}