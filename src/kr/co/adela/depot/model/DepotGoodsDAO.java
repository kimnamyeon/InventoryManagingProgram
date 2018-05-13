package kr.co.adela.depot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DepotGoodsDAO {
	
	public Vector<Object> printGoods() {
		Vector<Object> datas = new Vector<>();
		
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM goods where fk_category_name = '신발'";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector<Object> v = new Vector<>();
				
				v.add(rs.getString("goods_number"));
				v.add(rs.getString("goods_name"));
				v.add(rs.getString("goods_detail"));
				v.add(rs.getInt("goods_price"));
				v.add(rs.getInt("goods_amount"));
				v.add(rs.getString("goods_location"));
				v.add(rs.getString("goods_date"));
				v.add(rs.getString("fk_category_name"));
				datas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			release(conn, ps, rs);
		}
		
		return datas;
	}
	
	public Vector<Object> searchGoods(DepotGoodsDTO dto) {
		Vector<Object> datas = new Vector<>();
		
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM goods WHERE goods_name LIKE ? AND goods_detail LIKE ? "
				+ "AND goods_location LIKE ? AND fk_category_name LIKE ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getGoodsName());
			ps.setString(2, dto.getGoodsDetail());
			ps.setString(3, dto.getGoodsLocation());
			ps.setString(4, dto.getFkCategoryName());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector<Object> v = new Vector<>();
				
				v.add(rs.getString("goods_number"));
				v.add(rs.getString("goods_name"));
				v.add(rs.getString("goods_detail"));
				v.add(rs.getInt("goods_price"));
				v.add(rs.getInt("goods_amount"));
				v.add(rs.getString("goods_location"));
				v.add(rs.getString("goods_date"));
				v.add(rs.getString("fk_category_name"));
				datas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 해제
			release(conn, ps, rs);
		}
		
		return datas;
	}
	
	public boolean insertGoods(DepotGoodsDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;
		
		try {
			String sql = "INSERT INTO goods(goods_name, goods_detail, goods_price, goods_amount, goods_location, goods_date, fk_category_name)"
					+ "VALUES(?, ?, ?, ?, ?, now(), ?)";
			
			ps = conn.prepareStatement(sql);
			
			// 위에 물음표에 값을 넣음
			ps.setString(1, dto.getGoodsName());
			ps.setString(2, dto.getGoodsDetail());
			ps.setInt(3, dto.getGoodsPrice());
			ps.setInt(4, dto.getGoodsAmount());
			ps.setString(5, dto.getGoodsLocation());
			ps.setString(6, dto.getFkCategoryName());
			
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
	
	public boolean updateGoods(DepotGoodsDTO dto) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "UPDATE goods SET goods_name=?, goods_detail=?, goods_price=?, goods_amount=?, goods_location=?, fk_category_name=? "
				+ "WHERE goods_number = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getGoodsName());
			ps.setString(2, dto.getGoodsDetail());
			ps.setInt(3, dto.getGoodsPrice());
			ps.setInt(4, dto.getGoodsAmount());
			ps.setString(5, dto.getGoodsLocation());
			ps.setString(6, dto.getFkCategoryName());
			ps.setInt(7, dto.getGoodsNumber());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				ok = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		
		return ok;
	}
	
	public boolean deleteGoods(DepotGoodsDTO dto) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "DELETE FROM goods WHERE goods_number = ?;";
				
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getGoodsNumber());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				ok = true;
			}
		} catch (SQLException e) {
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
