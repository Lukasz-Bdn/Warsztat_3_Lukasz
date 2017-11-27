package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.warsztat3.db.DbUtil;

public class Solution {
	private int id;
	private String created;
	private String updated;
	private String description;
	private int exercise_id;
	private int users_id;
	
	public Solution() {
		super();
		this.id = 0;
		this.created = "";
		this.updated = "";
		this.description = "";
		this.exercise_id = 0;
		this.users_id = 0;
	}
	
	public Solution(String description, int exercise_id, int users_id) {
		super();
		this.id = 0;
		this.created = "";
		this.updated = "";
		this.description = description;
		this.exercise_id = exercise_id;
		this.users_id = users_id;
	}
	
	public Solution(int id, String updated, String description, int users_id) {
		super();
		this.id = id;
		this.created = "";
		this.updated = updated;
		this.description = description;
		this.users_id = users_id;
		this.exercise_id = 0;
	}
	
	
	public Solution(int id, String created, String updated, String description, int exercise_id, int users_id) {
		super();
		this.id = id;
		this.created = created;
		this.updated = updated;
		this.description = description;
		this.exercise_id = exercise_id;
		this.users_id = users_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExercise_id() {
		return exercise_id;
	}

	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public int getId() {
		return id;
	}

	public String getCreated() {
		return created;
	}

	public String getUpdated() {
		return updated;
	}
	
	public void saveToDb(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO " + "solution(created, updated, description, "
					+ "exercise_id, users_id) "
					+ "VALUES(NOW(), NOW(), ?, ?, ?);";
			String[] generatedKeys = { "id"};
			PreparedStatement ps = conn.prepareStatement(sql, generatedKeys);
			ps.setString(1, this.description);
			ps.setInt(2, this.exercise_id);
			ps.setInt(3, this.users_id);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
			Solution tempSolution = Solution.loadById(this.id);
			this.created = tempSolution.created;
			this.updated = tempSolution.updated;

			rs.close();
			ps.close();
		} else {
			String sql = "UPDATE solution SET updated= NOW(), description=?, exercise_id=?, users_id=?"
					+ " WHERE id=?;";
			PreparedStatement ps =conn.prepareStatement(sql);
			ps.setString(1, this.description);
			ps.setInt(2, this.exercise_id);
			ps.setInt(3, this.users_id);
			ps.setInt(4, this.id);
			ps.executeUpdate();
			Solution tempSolution = Solution.loadById(this.id);
			this.updated = tempSolution.updated;
			
			ps.close();
		}
	}
	
	
	public static Solution[] loadAll(Connection conn) throws SQLException {
		ArrayList<Solution> solutionArrayList = new ArrayList<Solution>();
		String sql = "SELECT * FROM solution;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = rs.getInt("id");
			loadedSolution.created = rs.getString("created");
			loadedSolution.updated = rs.getString("updated");
			loadedSolution.description = rs.getString("description");
			loadedSolution.exercise_id = rs.getInt("exercise_id");
			loadedSolution.users_id = rs.getInt("users_id");
			solutionArrayList.add(loadedSolution);
		}
		
		rs.close();
		ps.close();
		Solution[] solutionArray = new Solution[solutionArrayList.size()];
		solutionArray = solutionArrayList.toArray(solutionArray);
		return solutionArray;
	}
	
	public void deleteFromDb(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql ="DELETE FROM solution WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, this.id);
			ps.executeUpdate();
			this.id = 0;
			ps.close();
		}
	}
	public static Solution[] loadAllByExerciseId(Connection conn, int exercise_id) throws SQLException {
		ArrayList<Solution> solutionArrayList = new ArrayList<>();
		String sql = "SELECT * FROM solution "
				+ "WHERE exercise_id=? "
				+ "ORDER BY updated ASC;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, exercise_id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = rs.getInt("id");
			loadedSolution.created = rs.getString("created");
			loadedSolution.updated = rs.getString("updated");
			loadedSolution.description = rs.getString("description");
			loadedSolution.exercise_id = rs.getInt("exercise_id");
			loadedSolution.users_id = rs.getInt("users_id");
			solutionArrayList.add(loadedSolution);
		}
		rs.close();
		ps.close();
		Solution[] solutionArray = new Solution[solutionArrayList.size()];
		solutionArray = solutionArrayList.toArray(solutionArray);
		return solutionArray;
	}
	
	public static List<SolutionWithUser> loadAllWithUser(int count) {
		Connection conn;
		List<SolutionWithUser> result = new ArrayList<>();
		try {
			conn = DbUtil.getConn();
			result = SolutionDao.loadAllWithUser(conn, count);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static Solution loadById(int id) {
		Connection conn;
		Solution result = new Solution();
		try {
			conn = DbUtil.getConn();
			result = SolutionDao.loadById(conn, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}

	public static List<Solution> loadByUserId(int id) {
		Connection conn;
		List<Solution> result = new ArrayList<>();
		try {
			conn = DbUtil.getConn();
			result = SolutionDao.loadByUserId(conn, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
