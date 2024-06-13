package himedia.myhome.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import himedia.myhome.vo.UserVo;

public class UsersDaoOracleImpl implements UsersDao{
	private String dbUser;
	private String dbPass;
	
	public UsersDaoOracleImpl(String dbUser, String dbPass) {
		super();
		this.dbUser = dbUser;
		this.dbPass = dbPass;
	}

	// connection 생성해서 반환해주는 메서드
	private Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", dbUser, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return conn;
	}
	
	@Override
	public List<UserVo> getList() {
		List<UserVo> list = new ArrayList<UserVo>();
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM users";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				String email = rs.getString("email");
				Character gender = (rs.getString("gender")).charAt(0);
				Date date = rs.getDate("created_at");
				
				UserVo vo = new UserVo(no, name, pass, email, gender, date);
				
				list.add(vo);
			}
		} catch (Exception e) {
			System.err.println("UsersDaoOracleImpl:getList():1");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("UsersDaoOracleImpl:getList():2");
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public boolean insert(UserVo userVo) {
		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		int result = 0;

		String sql = "INSERT INTO users(no, name, password, email, gender) "
				+ "VALUES(seq_users_pk.nextval, ?, ?, ?, ?)";
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userVo.getName());
			pStmt.setString(2, userVo.getPass());
			pStmt.setString(3, userVo.getEmail());
			pStmt.setString(4, userVo.getGender().toString());
			result = pStmt.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("UsersDaoOracleImpl:insert():1");
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null)
					pStmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("UsersDaoOracleImpl:insert():2");
				e.printStackTrace();
			}
		}
		
		return result == 1;
	}

	@Override
	public boolean update(UserVo userVo) {
		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		int result = 0;

		String sql = "UPDATE users "
				+ "SET name = ?, password = ?, email = ?, gender = ?"
				+ "WHERE no = ?";
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userVo.getName());
			pStmt.setString(2, userVo.getPass());
			pStmt.setString(3, userVo.getEmail());
			pStmt.setString(4, userVo.getGender().toString());
			pStmt.setLong(5, userVo.getNo());
			result = pStmt.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("UsersDaoOracleImpl:update():1");
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null)
					pStmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("UsersDaoOracleImpl:update():2");
				e.printStackTrace();
			}
		}
		
		return result == 1;
	}

	@Override
	public boolean delete(Long no) {
		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		int result = 0;

		String sql = "DELETE FROM users WHERE no = ?";
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setLong(1, no);

			result = pStmt.executeUpdate();
			
		} catch (Exception e) {
			System.err.println("UsersDaoOracleImpl:delete():1");
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null)
					pStmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("UsersDaoOracleImpl:delete():2");
				e.printStackTrace();
			}
		}
		
		return result == 1;
	}

	@Override
	public UserVo getUserByIdAndPassword(String email, String password) {
		UserVo vo = null;
		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM users WHERE email=? AND password=?";
		
		try {
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
			pStmt.setString(2, password);
			rs = pStmt.executeQuery();
			
			if(rs.next()){
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				String mail = rs.getString("email");
				Character gender = (rs.getString("gender")).charAt(0);
				Date date = rs.getDate("created_at");
					
				vo = new UserVo(no, name, pass, email, gender, date);
			}
		} catch (Exception e) {
			System.err.println("UsersDaoOracleImpl:getUserByIdAndPassword():1");
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(pStmt != null)
					pStmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				System.err.println("UsersDaoOracleImpl:getUserByIdAndPassword():2");
				e.printStackTrace();
			}
		}
		
		return vo;
	}

}
