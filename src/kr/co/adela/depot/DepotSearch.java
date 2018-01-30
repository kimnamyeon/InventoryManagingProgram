package kr.co.adela.depot;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import kr.co.adela.depot.acc.AccPanel;
import kr.co.adela.depot.bag.ProjectBag;
import kr.co.adela.depot.clothes.ClothesMain;
import kr.co.adela.depot.model.DepotGoodsDAO;
import kr.co.adela.depot.model.DepotGoodsDTO;
import kr.co.adela.depot.shoes.DepotShoes;

public class DepotSearch extends JPanel implements ActionListener {

	DepotMainFrame fMain;
	JPanel pSearch;
	JComboBox<String> cbSearch;
	JTextField tfSearch;
	JButton btnSearch;
	private String search = "res/search.png";
	private String searchClick = "res/searchClick.png";

	public DepotSearch(DepotMainFrame fMain) {
		this.fMain = fMain;
		setBackground(Color.decode("#BDBDBD"));

		String[] strSearch = { "상품명", "상품설명", "상품위치"};
		cbSearch = new JComboBox<>(strSearch);
		tfSearch = new JTextField(20);
		ImageIcon imgSearch = new ImageIcon(search);
		ImageIcon imgSearhClick = new ImageIcon(searchClick);
		
		btnSearch = new JButton(imgSearch);
		btnSearch.setBorderPainted(false);
		btnSearch.setFocusPainted(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setPressedIcon(imgSearhClick);
		btnSearch.addActionListener(this);
		pSearch = new JPanel();
		pSearch.setOpaque(false);
		pSearch.add(cbSearch);
		pSearch.add(tfSearch);
		pSearch.add(btnSearch);
		add(pSearch);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			String searchString = tfSearch.getText();
			String name = "%";
			String detail = "%";
			String location = "%";

			if (searchString.length() == 0) {
				JOptionPane.showMessageDialog(this, "검색어를 입력하세요");
				return;
			} else if (cbSearch.getSelectedIndex() == 0) {
				name = "%" + tfSearch.getText() + "%";
			} else if (cbSearch.getSelectedIndex() == 1) {
				detail = "%" + tfSearch.getText() + "%";
			} else if (cbSearch.getSelectedIndex() == 2) {
				location = "%" + tfSearch.getText() + "%";
			}
			
			DepotGoodsDTO dto = new DepotGoodsDTO();
			dto.setGoodsName(name);
			dto.setGoodsDetail(detail);
			dto.setGoodsLocation(location);

			if(DepotMainFrame.searchCategory == "%") {
				dto.setFkCategoryName(DepotMainFrame.searchCategory);
				DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
				Vector<Object> datas = new Vector<>();
				Vector<String> cols = new Vector<>();
				cols.add("상품번호");
				cols.add("상품명");
				cols.add("상품설명");
				cols.add("가격");
				cols.add("수량");
				cols.add("상품위치");
				cols.add("입고일");
				cols.add("카테고리");
				datas = goodsDAO.searchGoods(dto);
				DefaultTableModel tableModel = new DefaultTableModel(datas, cols);
				InitPanel.tblInit.setModel(tableModel);
			} else if(DepotMainFrame.searchCategory == "의류") {
				dto.setFkCategoryName(DepotMainFrame.searchCategory);
				DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
				Vector<Object> datas = new Vector<>();
				Vector<String> cols = new Vector<>();
				cols.add("상품번호");
				cols.add("상품명");
				cols.add("상품설명");
				cols.add("가격");
				cols.add("수량");
				cols.add("상품위치");
				cols.add("입고일");
				cols.add("카테고리");
				datas = goodsDAO.searchGoods(dto);
				DefaultTableModel tableModel = new DefaultTableModel(datas, cols);
				ClothesMain.tblClothes.setModel(tableModel);
			} else if(DepotMainFrame.searchCategory == "신발") {
				dto.setFkCategoryName(DepotMainFrame.searchCategory);
				DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
				Vector<Object> datas = new Vector<>();
				Vector<String> cols = new Vector<>();
				cols.add("상품번호");
				cols.add("상품명");
				cols.add("상품설명");
				cols.add("가격");
				cols.add("수량");
				cols.add("상품위치");
				cols.add("입고일");
				cols.add("카테고리");
				datas = goodsDAO.searchGoods(dto);
				DefaultTableModel tableModel = new DefaultTableModel(datas, cols);
				DepotShoes.jtable.setModel(tableModel);
			} else if(DepotMainFrame.searchCategory == "가방") {
				dto.setFkCategoryName(DepotMainFrame.searchCategory);
				DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
				Vector<Object> datas = new Vector<>();
				Vector<String> cols = new Vector<>();
				cols.add("상품번호");
				cols.add("상품명");
				cols.add("상품설명");
				cols.add("가격");
				cols.add("수량");
				cols.add("상품위치");
				cols.add("입고일");
				cols.add("카테고리");
				datas = goodsDAO.searchGoods(dto);
				DefaultTableModel tableModel = new DefaultTableModel(datas, cols);
				ProjectBag.jtable.setModel(tableModel);
			} else if(DepotMainFrame.searchCategory == "악세사리") {
				dto.setFkCategoryName(DepotMainFrame.searchCategory);
				DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
				Vector<Object> datas = new Vector<>();
				Vector<String> cols = new Vector<>();
				cols.add("상품번호");
				cols.add("상품명");
				cols.add("상품설명");
				cols.add("가격");
				cols.add("수량");
				cols.add("상품위치");
				cols.add("입고일");
				cols.add("카테고리");
				datas = goodsDAO.searchGoods(dto);
				DefaultTableModel tableModel = new DefaultTableModel(datas, cols);
				AccPanel.accTable.setModel(tableModel);
			}
		}
	}

}
