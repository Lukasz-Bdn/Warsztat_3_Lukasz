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
}