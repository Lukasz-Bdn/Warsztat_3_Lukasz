package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pl.coderslab.warsztat3.db.DbUtil;

public class UserGroup {
	private int id;
	private String name;
	
	public UserGroup() {
		super();
		this.id = 0;
		this.name = "";
	}
	
	public UserGroup(String name) {
		super();
		this.id = 0;
		this.name = name;
	}

	public UserGroup(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	

	public void deleteFromDb(Connection conn) throws SQLException{
		if (this.id != 0) {
			String sql = "DELETE FROM user_group WHERE id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, this.id);
			ps.executeUpdate();
			this.id = 0;
			ps.close();
		}
	}
	
	public static List<UserGroup> loadAll(){
		Connection conn;
		List<UserGroup> result = new ArrayList<>();
		try {
			conn = DbUtil.getConn();
			result = UserGroupDao.loadAll(conn);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static UserGroup loadById(int groupId) {
		Connection conn;
		UserGroup result = new UserGroup();
		try {
			conn = DbUtil.getConn();
			result = UserGroupDao.loadById(conn, groupId);
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
			UserGroup userGroup = this;
			UserGroupDao.saveToDb(conn, userGroup);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}