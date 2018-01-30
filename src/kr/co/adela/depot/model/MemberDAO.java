package kr.co.adela.depot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import kr.co.adela.depot.DepotMainFrame;

public class MemberDAO {
	public boolean createMember(MemberDTO dto) {	
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;
		
		try {
			String sql = "insert into manager(manager_id,manager_password,manager_name,manager_age,manager_sex,"
					+ "manager_rank,manager_phone,manager_address,manager_joindate,fk_category_name)"
					+ "values(?,?,?,?,?,?,?,?,now(),?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());//manager_id
			ps.setString(2, dto.getPwd());//manager_password
			ps.setString(3, dto.getName());//manager_name
			ps.setInt(4, dto.getAge());//manager_age
			ps.setString(5, dto.getSex());//manager_sex
			ps.setString(6, dto.getPosition());//manager_rank
			ps.setString(7, dto.getPhone());//manager_phone
			ps.setString(8, dto.getAddr());//manager_address
			ps.setString(9, dto.getCategory());//category_name
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if( result == 1 ) {
			System.out.println("회원 가입 성공");
			ok = true;
		} else {
			ok = false;
		}
		return ok;
	}//createMember	
	
	public boolean loginCheck(MemberDTO dto) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select manager_id, manager_name from manager where manager_id=? and manager_password=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPwd());
			rs = ps.executeQuery();
			if( rs.next() ) {
				ok = true;
				DepotMainFrame.managerId = rs.getString(1);
				DepotMainFrame.managerName = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}//finally
		System.out.println("아이디와 비밀번호가 맞는가? = " + ok);
		return ok;
	}//loginCheck
	
	public boolean idCheck(String id) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM manager where manager_id = ?;";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				ok = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		
		return ok;
	}
	
	private void release(Connection conn, PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void release(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		release(conn, ps);
	}
	
	private Connection getConn() {
		Connection conn = null;
		String dbUrl =
				//마리아 DB 사용자 명 : TEST
			"jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, "root", "1234");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}//getConn
}
