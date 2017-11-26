package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
	
	public static List<SolutionWithUser> loadAllWithUser(Connection conn, int count) throws SQLException {
		List<SolutionWithUser> result = new ArrayList<SolutionWithUser>();
		String sql = "SELECT solution.id,solution.updated,solution.description,"
				+ "solution.users_id,users.username "
				+ "FROM solution JOIN users on solution.users_id=users.id "
				+ "ORDER BY solution.updated DESC "
				+ "LIMIT ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, count);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int solutionId = rs.getInt("solution.id");
			String updated = rs.getString("solution.updated");
			String description = rs.getString("solution.description");
			int usersId = rs.getInt("solution.users_id");
			String userName = rs.getString("users.username");
			SolutionWithUser solutionWithUser = new SolutionWithUser(new Solution(solutionId, updated, description, usersId), userName);
			result.add(solutionWithUser);
		}
		ps.close();
		rs.close();
		return result;
	}
	
	public static Solution loadById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM solution WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int solutionId = rs.getInt(1);
			String created = rs.getString(2);
			String updated = rs.getString(3);
			String description = rs.getString(4);
			int exercise_id = rs.getInt(5);
			int users_id = rs.getInt(6);
			Solution solution = new Solution(solutionId, created, updated, description, exercise_id, users_id);
			rs.close();
			ps.close();
			return solution;
		}
		return null;		
	}

}