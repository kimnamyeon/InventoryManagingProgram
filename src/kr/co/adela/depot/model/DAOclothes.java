package kr.co.adela.depot.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DAOclothes {
	public Vector showClothesData() {
		DTOclothes dtoc = new DTOclothes();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from goods where fk_category_name = '의류'";
		Vector data = new Vector();
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
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
			e.printStackTrace();
		}finally {
			release(conn, ps, rs);
		}
		return data;
	}
	
	public Vector search(String searchData) {
		DTOclothes dtoc = new DTOclothes();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from goods where goods_name like '%" + 
					  searchData + "%'";
		Vector data = new Vector();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
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
			release(conn, ps, rs);
		}
		return data;
	}
	
	public Vector showHistory() {
		DTOclothes dtoc = new DTOclothes();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector data = new Vector();
		
		try {
			String sql = "select * from history";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("history_number"));
				v.add(rs.getString("goods_name"));
				v.add(rs.getString("history_detail"));
				v.add(rs.getString("goods_amount"));
				v.add(rs.getString("update_date"));
				v.add(rs.getString("manager_name"));
				data.add(v);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public boolean insertGoods(DTOclothes dtoc) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;
		
		try {
			String sql = "insert into goods(goods_name,goods_detail,goods_price,goods_amount,"
					+ "goods_location,goods_date,fk_category_name)"
					+ "values(?,?,?,?,?,now(),?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, dtoc.getName());
			ps.setString(2,dtoc.getDetail());
			ps.setInt(3, dtoc.getPrice());
			ps.setInt(4, dtoc.getAmount());
			ps.setString(5, dtoc.getLocation());
			ps.setString(6, dtoc.getCategory());
			
			result = ps.executeUpdate();
			if(result ==1) {
				System.out.println("저장 성공!");
				ok = true;
			}else {
				System.out.println("저장 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			release(conn,ps);
		}
		return ok;
	}
	//history 테이블에 insert
	public void insertHistory(DTOclothes dtoc) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		
		try {
			String sql = "insert into history (history_detail,manager_id,manager_name,update_date,goods_name,goods_amount)"
					+ "values(?,?,?,now(),?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dtoc.getHistoryDetail());
			ps.setString(2, dtoc.getManagerId());
			ps.setString(3, dtoc.getManagerName());
			ps.setString(4, dtoc.getName());
			ps.setInt(5, dtoc.getAmount());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			release(conn,ps);
		}
				
	}
	//delete history
	public void deleteHistory(DTOclothes dtoc) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		try {
			String sql = "insert into history (history_detail,manager_id,manager_name,update_date,goods_name,goods_amount)"
					+ "values(?,?,?,now(),?,0)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dtoc.getHistoryDetail());
			ps.setString(2, dtoc.getManagerId());
			ps.setString(3, dtoc.getManagerName());
			ps.setString(4, dtoc.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			release(conn,ps);
		}
		
	}
	
	public boolean updateGoods(DTOclothes dtoc) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;
		
		try {
			String sql = "update goods set goods_name = ?, goods_detail = ?, goods_price = ?, "
					+ "goods_amount = ?, goods_location = ?, goods_date = now(), fk_category_name = ?"
					+ "where goods_number = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dtoc.getName());
			ps.setString(2, dtoc.getDetail());
			ps.setInt(3, dtoc.getPrice());
			ps.setInt(4, dtoc.getAmount());
			ps.setString(5, dtoc.getLocation());
			ps.setString(6, dtoc.getCategory());
			ps.setInt(7, dtoc.getNumber());
			result = ps.executeUpdate();
			if(result == 1) {
				System.out.println("수정 성공");
				ok = true;
			}else {
				System.out.println("수정 실패 ㅠㅠ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			release(conn,ps);
		}
		return ok;
	}
	public boolean deleteGoods(int deleteNum, DTOclothes dtoc) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;
		
		try {
			String sql = "delete from goods where goods_number = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dtoc.getNumber());
			result = ps.executeUpdate();
			if(result == 1) {
				ok = true;
				System.out.println("삭제쿼리 발동!");
			}else {
				System.out.println("삭제쿼리 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			release(conn,ps);
		}
		
		return ok;
	}
	
	public Vector showAllData() {
	      DTOclothes dtoc = new DTOclothes();
	      Connection conn = getConn();
	      PreparedStatement ps = null;
	      ResultSet rs = null;
	      String sql = "select * from goods";
	      Vector data = new Vector();

	      try {
	         ps = conn.prepareStatement(sql);
	         rs = ps.executeQuery();
	         while(rs.next()) {
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
	      }catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         release(conn, ps, rs);
	      }
	      return data;
	   }
	
	public void realignment() {
		Connection conn = getConn();
		Connection conn2 = getConn();
		PreparedStatement ps = null;
//		PreparedStatement ps2 = null;
		try {
			String sql1 = "set @cnt=0";
			ps = conn.prepareStatement(sql1);
			ps.executeUpdate();
			String sql2 = "update goods set goods.goods_number = @cnt := @cnt+1";
			ps = conn.prepareStatement(sql2);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			release(conn,ps);
		}
	}

	
	private Connection getConn() {
		Connection conn = null;
		PreparedStatement ps = null;
		String dbUrl = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl,"root","1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	


	
	private void release(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) { //select 의 경우에만 rs가 필요함.
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		release(conn, ps);
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



}
