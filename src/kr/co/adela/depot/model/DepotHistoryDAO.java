package kr.co.adela.depot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepotHistoryDAO {
	
	public boolean insertHistory(DepotHistoryDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;
		
		try {
			String sql = "INSERT INTO history(goods_name, history_detail, goods_amount, manager_id, manager_name, update_date)"
					+ "VALUES(?, ?, ?, ?, ?, now());";
			
			ps = conn.prepareStatement(sql);
			
			// 위에 물음표에 값을 넣음
			ps.setString(1, dto.getGoodsName());
			ps.setString(2, dto.getHistoryDetail());
			ps.setInt(3, dto.getGoodsAmount());
			ps.setString(4, dto.getManagerId());
			ps.setString(5, dto.getManagerName());
			
			// 실행
			result = ps.executeUpdate();
			
			if(result == 1) {
				ok = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		
		return ok;
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
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		release(conn, ps);
	}
	
}
