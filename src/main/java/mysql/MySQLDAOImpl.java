package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import vo.GuestBookVo;

/**
 * MySQL과 연결해주는 DAO 클래스
 * - 상속할 일이 없어서 final로 지정
 * */
public final class MySQLDAOImpl implements MySQLDAO{
	private final String url = "jdbc:mysql://localhost:1521/questbook.mysql?serverTimezone=UTC";
	private String dbID; 
	private String dbPass;
	
	/**
	 * DAO 생성자(String, String)
	 * Param1 : String dbID
	 * - db계정 아이디
	 * 
	 * Param2 : String dbPass
	 *  -db계정 비밀번호
	 *  
	 * 생성자, url은 변동될 가능성이 적으니 매개변수에서 제외함
	 * */
	public MySQLDAOImpl(String dbID, String dbPass) {
		this.dbID = dbID;
		this.dbPass = dbPass;
	}

	/**
	 * MySQL DB에서 연결을 얻어와 Connection을 리턴해주는 메서드
	 * - MySQLDAOImpl에서만 사용 바람 -> private 
	 * */
	private Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, dbID, dbPass);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("연결 성공");
		
		return conn;
	}
	
	/**
	 *  MySQL DB에서 데이터를 읽어와 vo객체 리스트로 리턴해주는 메서드
	 * */
	@Override
	public List<GuestBookVo> getList() {
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT * FROM guestbook";
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String pass = rs.getString("password");
				String cont = rs.getString("content");
				Date date = rs.getDate("reg_date");
				
				GuestBookVo vo = new GuestBookVo(no, name, pass, cont, date);
				list.add(vo);
			}
		} catch (SQLException e) {
			System.err.println("Error in OracleDAOImpl::getList()");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error in OracleDAOImpl::getList()");
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
				e.printStackTrace();
				System.err.println("Error in OracleDAOImpl::getList()");
			}
		}
		return list;
	}

	/**
	 * 매개변수에 기반하여 DB에 등록해주는 메서드
	 * - Primary Key인 no은 시퀀스로, Date는 현재를 기준으로 자동으로 등록된다
	 * */
	@Override
	public boolean insert(String name, String pass, String content) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		String query = "INSERT INTO guestbook\r\n"
				+ "VALUES(seq_guestbook_no.nextval, ?, ?, ?, SYSDATE)";
		int queryResult = 0;
		
		try {
			conn = getConnection();
			
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, name);
			pStmt.setString(2, pass);
			pStmt.setString(3, content);
			
			queryResult = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Error in OracleDAOImpl::insert()");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error in OracleDAOImpl::insert()");
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null)
					pStmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Error in OracleDAOImpl::insert()");
			}
		}
		return queryResult == 1;
	}

	/**
	 * */
	@Override
	public boolean delete(Long delNo) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		String query = "DELETE FROM guestbook WHERE no = ?";
		int queryResult = 0;
		
		try {
			conn = getConnection();

			pStmt = conn.prepareStatement(query);
			pStmt.setLong(1, delNo);
			
			queryResult = pStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error in OracleDAOImpl::delete()");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error in OracleDAOImpl::delete()");
			e.printStackTrace();
		} finally {
			try {
				if(pStmt != null)
					pStmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Error in OracleDAOImpl::delete()");
			}
		}
		return queryResult == 1;
	}


}
