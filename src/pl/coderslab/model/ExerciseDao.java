package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {
	
		public static List<Exercise> loadAll(Connection conn) throws SQLException {
		List<Exercise> result = new ArrayList<Exercise>();
		String sql = "SELECT * FROM exercise";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			String description = rs.getString("description");
			Exercise loadedExercise = new Exercise(id, title, description);
			result.add(loadedExercise);
		}
		rs.close();
		ps.close();
		return result;
		}
		
		public static List<Exercise> loadAllByUserId(Connection conn, int userId) throws SQLException {
			List<Exercise> result = new ArrayList<Exercise>();
			String sql = "SELECT exercise.id,exercise.title,exercise.description "
					+ "FROM users "
					+ "JOIN solution ON users.id=solution.users_id "
					+ "JOIN exercise ON solution.exercise_id=exercise.id "
					+ "WHERE users.id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int exerciseId = rs.getInt("exercise.id");
				String title = rs.getString("exercise.title");
				String description = rs.getString("exercise.description");
				Exercise loadedExercise = new Exercise(exerciseId, title, description);
				result.add(loadedExercise);			
			}
			rs.close();
			ps.close();
			return result;
		}
		
		public static Exercise loadById(Connection conn, int id) throws SQLException {
			String sql = "SELECT * FROM exercise WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int exerciseId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				rs.close();
				ps.close();
				Exercise loadedExercise = new Exercise(exerciseId, title, description);
				return loadedExercise;
			}
			return null;
		}
		public static void saveToDb(Connection conn, Exercise exercise) throws SQLException {
			if (exercise.getId() == 0) {
				String sql = "INSERT INTO exercise(title, description) " + "VALUES(?, ?);";
				String[] generatedKeys = { "ID" };
				PreparedStatement ps = conn.prepareStatement(sql, generatedKeys);
				ps.setString(1, exercise.getTitle());
				ps.setString(2, exercise.getDescription());
				ps.executeUpdate();
				ResultSet gk = ps.getGeneratedKeys();
				if (gk.next()) {
					exercise.setId(gk.getInt(1));
				}
				gk.close();
				ps.close();
			} else {
				String sql = "UPDATE exercise SET title=?, description=? WHERE id=?;";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, exercise.getTitle());
				ps.setString(2, exercise.getDescription());
				ps.setInt(3, exercise.getId());
				ps.executeUpdate();
				ps.close();
			}
		}

}