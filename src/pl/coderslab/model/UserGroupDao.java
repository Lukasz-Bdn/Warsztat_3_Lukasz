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
	
	public static UserGroup loadById(Connection conn, int groupId) throws SQLException {
		String sql  = "SELECT * FROM user_group WHERE id=?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, groupId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			UserGroup loadedUserGroup = new UserGroup(id, name);
			rs.close();
			ps.close();
			return loadedUserGroup;
		}
		ps.close();
		return null;
	}
	
	public static void saveToDb(Connection conn, UserGroup userGroup) throws SQLException {
		if (userGroup.getId() == 0) {
			String sql = "INSERT INTO user_group(name) VALUES(?);";
			String[] generatedKeys = { "ID" };
			PreparedStatement ps = conn.prepareStatement(sql, generatedKeys);
			ps.setString(1, userGroup.getName());
			ps.executeUpdate();
			ResultSet gk = ps.getGeneratedKeys();
			if (gk.next()) {
				userGroup.setId(gk.getInt(1));
			}
			gk.close();
			ps.close();
		} else {
			String sql = "UPDATE user_group SET name=? WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userGroup.getName());
			ps.setInt(2, userGroup.getId());
			ps.executeUpdate();
			ps.close();
		}
	}
}
