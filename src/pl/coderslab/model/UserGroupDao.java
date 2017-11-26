package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDao {

	public static List<UserGroup> loadAll(Connection conn) throws SQLException {
		List<UserGroup> result = new ArrayList<UserGroup>();
		String sql = "SELECT * FROM user_group;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			UserGroup userGroup = new UserGroup(id, name);
			result.add(userGroup);
		}
		ps.close();
		rs.close();
		return result;
	}
	
}
