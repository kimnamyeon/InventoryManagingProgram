package kr.co.adela.depot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import kr.co.adela.depot.model.ProjectDTO;

public class ProjectDAO {
	
	
	public Vector Projectlist() {
		// 상품들은 하나하나 테이블값에 넣어주는 작업
		Vector vt = new Vector();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from goods WHERE fk_category_name = '가방';";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				Vector v = new Vector();
				v.add(rs.getString("goods_number"));
				v.add(rs.getString("goods_name"));
				v.add(rs.getString("goods_detail"));
				v.add(rs.getString("goods_price"));
				v.add(rs.getString("goods_amount"));
				v.add(rs.getString("goods_location"));
				v.add(rs.getString("goods_date"));
				v.add(rs.getString("fk_category_name"));
				vt.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vt;
		
	}
	public Vector Projectmanager() {
		Vector vt = new Vector();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from manager";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				Vector v = new Vector();
				v.add(rs.getString("manager_id"));
				v.add(rs.getString("manager_password"));
				v.add(rs.getString("manager_name"));
				v.add(rs.getString("manager_age"));
				v.add(rs.getString("manager_sex"));
				v.add(rs.getString("manager_rank"));
				v.add(rs.getString("manager_phone"));
				v.add(rs.getString("manager_address"));
				v.add(rs.getString("manager_joindate"));
				vt.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			release(conn, ps);
		}
		
		return vt;
	}
	public boolean updateGoods(ProjectDTO dto) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "update goods set goods_name=?, goods_detail=?, goods_price=?,"
				+ "goods_amount=?, goods_location=?, Fk_category_name=? WHERE goods_number=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getGoods_name());
			ps.setString(2, dto.getGoods_detail());
			ps.setString(3, dto.getGoods_price());
			ps.setString(4, dto.getGoods_amount());
			ps.setString(5, dto.getGoods_location());
			ps.setString(6, dto.getFk_category_name());
			ps.setString(7, dto.getGoods_number());
			
			int count = ps.executeUpdate();
			if(count == 1) {
				ok = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		} // finally
		
		return ok;
		
	}
	
	
	
	public ProjectDTO updateGoods() {   // 수정 버튼 눌렀을때 자동생성을 위한 작업
		ProjectDTO dto = new ProjectDTO();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from goods";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) {
				dto.setGoods_name(rs.getString("goods_name"));
				dto.setGoods_detail(rs.getString("goods_detail"));
				dto.setGoods_price(rs.getString("goods_price"));
				dto.setGoods_amount(rs.getString("goods_amount"));
				dto.setGoods_location(rs.getString("goods_location"));
				dto.setGoods_location(rs.getString("goods_date"));
				dto.setGoods_location(rs.getString("fk_category_name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		
		return dto;
	}
	
	public boolean deleteGoods(int number) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;
	    try {
	    	String sql = "delete from goods where goods_number = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "" + number);
			result = ps.executeUpdate();
			if(result == 1) {
				ok = true;
				System.out.println("삭제 완료!!");
			} else {
				System.out.println("삭제 실패!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		return ok;
		
	}
	
	public boolean ProjectInsert(ProjectDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		
		int result = 0;	
		boolean ok = false;
		try {
			String sql = "insert into goods" + "( goods_name, goods_detail, goods_price, "
					+ "goods_amount, goods_location, goods_date, fk_category_name)"
		                  + "values(?, ?, ?, ?, ?, now(), ? )";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getGoods_name());
			ps.setString(2, dto.getGoods_detail());
			ps.setString(3, dto.getGoods_price());
			ps.setString(4, dto.getGoods_amount());
			ps.setString(5, dto.getGoods_location());
			ps.setString(6, dto.getFk_category_name());
			
			result = ps.executeUpdate();
			if(result == 1) {
				System.out.println("성공");
				ok = true;
			} else {
				System.out.println("실패");
				ok = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		return ok;
}
	private void release(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	private void release(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
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
		String dbUrl = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, "root", "1234");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}

