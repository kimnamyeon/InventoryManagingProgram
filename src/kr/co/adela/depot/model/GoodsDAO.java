package kr.co.adela.depot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class GoodsDAO {

	public Vector showGoods() {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from goods where fk_category_name = '악세사리'";
		Vector data = new Vector();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("goods_number"));
				v.add(rs.getString("goods_name"));
				v.add(rs.getString("goods_detail"));
				v.add(rs.getInt("goods_price"));
				v.add(rs.getInt("goods_amount"));
				v.add(rs.getString("goods_location"));
				v.add(rs.getString("goods_date"));
				v.add(rs.getString("fk_category_name"));
				data.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			release(conn,ps,rs);
		}
		return data;
	}

	public boolean insertGoods(GoodsDTO dtoAcc) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;

		String sql = "insert into goods(goods_name,goods_detail,goods_price,goods_amount,"
				+ "goods_location,goods_date,fk_category_name)" + "values(?,?,?,?,?,now(),?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, dtoAcc.getGoodsName());
			ps.setString(2, dtoAcc.getGoodsDetail());
			ps.setInt(3, dtoAcc.getGoodsPrice());
			ps.setInt(4, dtoAcc.getGoodsAmount());
			ps.setString(5, dtoAcc.getGoodsLocation());
			ps.setString(6, dtoAcc.getFkCategoryName());

			result = ps.executeUpdate();
			if (result == 1) {
				System.out.println("저장을 성공하였다.");
				ok = true;
			} else {
				System.out.println("저장을 실패하였다.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}

		return ok;
	}

	// Update
	public boolean updateGoods(GoodsDTO dtoACC) {	
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;
		
		try {
			String sql = "update goods set goods_name = ?, goods_detail = ?, goods_price = ?, "
					+ "goods_amount = ?, goods_location = ?, fk_category_name = ?"
					+ "where goods_number = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dtoACC.getGoodsName());
			ps.setString(2, dtoACC.getGoodsDetail());
			ps.setInt(3, dtoACC.getGoodsPrice());
			ps.setInt(4, dtoACC.getGoodsAmount());
			ps.setString(5, dtoACC.getGoodsLocation());
			ps.setString(6, dtoACC.getFkCategoryName());
			ps.setInt(7, dtoACC.getGoodsNumber());
			result = ps.executeUpdate();
			
			if(result == 1) {
				System.out.println("수정 성공하였습니다.");
				ok = true;
			}else {
				System.out.println("수정 실패하였습니다.");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		
		return ok;
		

	}
	
	public boolean delteGoods(int deleteNum, GoodsDTO dtoAcc) {
		Connection conn = getConn();
		PreparedStatement ps =null;
		int result = 0;
		boolean ok = false;
		
		String sql;
		try {
			sql = "delete from goods where goods_number =? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,  dtoAcc.getGoodsNumber());
			result = ps.executeUpdate();
			if(result == 1) { //삭제 커리 발동!
				ok = true;
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		return ok;
	}

	
	
	// 복붙 db 연결관련 소스
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
		String dbUrl =
				// 마리아 DB 사용자 명 : TEST
				"jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, "root", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}// getConn

}
