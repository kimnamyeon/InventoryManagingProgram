package kr.co.adela.depot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import kr.co.adela.depot.model.DAOclothes;

public class InitPanel extends JPanel {
	private DepotMainFrame mF;
	public static JTable tblInit;
	private Vector data, titles;
	private JScrollPane scrollPane;
	private DefaultTableModel model;
	private JPanel pCenter;

	public InitPanel(DepotMainFrame mF) {
		this.mF = mF;
		setLayout(new BorderLayout());

		DAOclothes daoc = new DAOclothes();

		// 물품 넘버링
		daoc.realignment();

		// vector data,title에 db 컬럼 넣기
		data = new Vector();
		data = daoc.showAllData();
		titles = new Vector();
		titles.add("No");
		titles.add("상품명");
		titles.add("상품설명");
		titles.add("가격");
		titles.add("수량");
		titles.add("위치");
		titles.add("날짜");
		titles.add("카테고리");
		model = new DefaultTableModel(data, titles) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblInit = new JTable(model);
		tblInit.setOpaque(false);
		tblInit.setShowGrid(false);
		scrollPane = new JScrollPane(tblInit) {
			@Override
			public void setBorder(Border border) {}
		};
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setPreferredSize(new Dimension(1000, 590));
		pCenter = new JPanel();
		pCenter.setBackground(Color.decode("#BDBDBD"));
		pCenter.add(scrollPane);
		add(pCenter, BorderLayout.CENTER);
	}// constructor

	public void showAll() {
		DAOclothes daoc = new DAOclothes();

		// 물품 넘버링
		daoc.realignment();

		// vector data,title에 db 컬럼 넣기
		data = new Vector();
		data = daoc.showAllData();
		titles = new Vector();
		titles.add("No");
		titles.add("상품명");
		titles.add("상품설명");
		titles.add("가격");
		titles.add("수량");
		titles.add("위치");
		titles.add("날짜");
		titles.add("카테고리");
		model = new DefaultTableModel(data, titles);
		tblInit.setModel(model);
	}

}