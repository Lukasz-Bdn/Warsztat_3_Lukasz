package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.warsztat3.db.DbUtil;

public class Exercise {
	private int id;
	private String title;
	private String description;
	
	public Exercise() {
		super();
		this.id=0;
		this.title = "";
		this.description ="";
	}
	
	public Exercise(String title, String description) {
		super();
		this.id = 0;
		this.title = title;
		this.description = description;
	}

	
	public Exercise(int id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void deleteFromDb(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM exercise WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, this.id);
			ps.executeUpdate();
			this.id = 0;
			ps.close();
		}
	}
	
	
	public static List<Exercise> loadAll(){
		Connection conn;
		List<Exercise> result = new ArrayList<>();
		try {
			conn = DbUtil.getConn();
			result = ExerciseDao.loadAll(conn);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<Exercise> loadAllByUserId(int id) {
		Connection conn;
		List<Exercise> result = new ArrayList<>();
		try {
			conn = DbUtil.getConn();
			result = ExerciseDao.loadAllByUserId(conn, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static Exercise loadById(int id) {
		Connection conn;
		Exercise result = new Exercise();
		try {
			conn = DbUtil.getConn();
			result = ExerciseDao.loadById(conn, id);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public void saveToDb() {
		Connection conn;
		try {
			conn = DbUtil.getConn();
			ExerciseDao.saveToDb(conn, this);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}