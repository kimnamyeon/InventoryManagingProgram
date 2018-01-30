package kr.co.adela.depot.shoes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.adela.depot.DepotMainFrame;
import kr.co.adela.depot.model.DepotGoodsDAO;
import kr.co.adela.depot.model.DepotGoodsDTO;
import kr.co.adela.depot.model.DepotHistoryDAO;
import kr.co.adela.depot.model.DepotHistoryDTO;
import java.awt.Font;

public class DepotShoes extends JPanel implements ActionListener {

	DepotMainFrame fMain;
	JPanel pSearch, pNorth, pCenter, pSouth;
	JLabel lblTitle;
	DefaultTableModel tableModel;
	public static JTable jtable;
	JButton btnInsert, btnUpdate, btnDelete;
	JScrollPane pane;
	Vector<Object> datas;
	boolean isInsert;
	String managerId, managerName;
	private String imgInsert = "res/btnInsert.png";
	private String imgInsertClick = "res/btnInsertClick.png";
	private String imgUpdate = "res/btnUpdate.png";
	private String imgUpdateClick = "res/btnUpdateClick.png";
	private String imgDelete = "res/btnDelete.png";
	private String imgDeleteClick = "res/btnDeleteClick.png";
	DefaultTableCellRenderer tableRenderRight, tableRenderCenter;

	public DepotShoes(DepotMainFrame fMain) {
		this.fMain = fMain;
		setLayout(new BorderLayout());
		
		lblTitle = new JLabel("신발");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 25));
		pNorth = new JPanel();
		pNorth.setBackground(Color.decode("#BDBDBD"));
		pNorth.add(lblTitle);
		add(pNorth, BorderLayout.NORTH);

		// 상품 DB에서 값 가져오기
		DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
		Vector<String> cols = new Vector<>();
		cols.add("상품번호");
		cols.add("상품명");
		cols.add("상품설명");
		cols.add("가격");
		cols.add("수량");
		cols.add("상품위치");
		cols.add("입고일");
		cols.add("카테고리");
		datas = goodsDAO.printGoods();

		// 테이블 추가 (pCenter)
		tableRenderCenter = new DefaultTableCellRenderer();
		tableRenderRight = new DefaultTableCellRenderer();
		tableRenderCenter.setHorizontalAlignment(SwingConstants.CENTER);
		tableRenderRight.setHorizontalAlignment(SwingConstants.RIGHT);
		
		jtable = new JTable(datas, cols);
		jtable.setSelectionMode(0);
		jtable.setOpaque(false);
		jtable.setShowGrid(false);
		pane = new JScrollPane(jtable) {
			@Override
			public void setBorder(Border border) {}
		};
		pane.setOpaque(false);
		pane.getViewport().setOpaque(false);
		pane.setPreferredSize(new Dimension(1000, 590));
		pCenter = new JPanel();
		pCenter.add(pane);
		pCenter.setBackground(Color.decode("#BDBDBD"));
		add(pCenter, BorderLayout.CENTER);

		ImageIcon insert = new ImageIcon(imgInsert);
		ImageIcon insertClick = new ImageIcon(imgInsertClick);
		ImageIcon update = new ImageIcon(imgUpdate);
		ImageIcon updateClick = new ImageIcon(imgUpdateClick);
		ImageIcon delete = new ImageIcon(imgDelete);
		ImageIcon deleteClick = new ImageIcon(imgDeleteClick);
		// 버튼 추가 (pSouth)
		btnInsert = new JButton(insert);
		btnInsert.setBorderPainted(false);
		btnInsert.setFocusPainted(false);
		btnInsert.setContentAreaFilled(false);
		btnInsert.setPressedIcon(insertClick);
		btnInsert.addActionListener(this);
		
		btnUpdate = new JButton(update);
		btnUpdate.setBorderPainted(false);
		btnUpdate.setFocusPainted(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setPressedIcon(updateClick);
		btnUpdate.addActionListener(this);
		
		btnDelete = new JButton(delete);
		btnDelete.setBorderPainted(false);
		btnDelete.setFocusPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setPressedIcon(deleteClick);
		btnDelete.addActionListener(this);
		pSouth = new JPanel();
		pSouth.setBackground(Color.decode("#BDBDBD"));
		pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pSouth.add(btnInsert);
		pSouth.add(btnUpdate);
		pSouth.add(btnDelete);
		add(pSouth, BorderLayout.SOUTH);
	}

	public void refreshTable() {
		DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
		Vector<String> cols = new Vector<>();
		cols.add("상품번호");
		cols.add("상품명");
		cols.add("상품설명");
		cols.add("가격");
		cols.add("수량");
		cols.add("상품위치");
		cols.add("입고일");
		cols.add("카테고리");
		datas = goodsDAO.printGoods();

		tableModel = new DefaultTableModel(datas, cols);
		jtable.setModel(tableModel);
		
		jtable.getColumnModel().getColumn(0).setMaxWidth(55);
		jtable.getColumnModel().getColumn(0).setCellRenderer(tableRenderCenter);
		jtable.getColumnModel().getColumn(1).setMaxWidth(130);
		jtable.getColumnModel().getColumn(1).setCellRenderer(tableRenderCenter);
		jtable.getColumnModel().getColumn(2).setMaxWidth(450);
		jtable.getColumnModel().getColumn(2).setCellRenderer(tableRenderCenter);
		jtable.getColumnModel().getColumn(3).setMaxWidth(70);
		jtable.getColumnModel().getColumn(3).setCellRenderer(tableRenderRight);
		jtable.getColumnModel().getColumn(4).setMaxWidth(55);
		jtable.getColumnModel().getColumn(4).setCellRenderer(tableRenderRight);
		jtable.getColumnModel().getColumn(5).setMaxWidth(55);
		jtable.getColumnModel().getColumn(5).setCellRenderer(tableRenderCenter);
		jtable.getColumnModel().getColumn(6).setMaxWidth(130);
		jtable.getColumnModel().getColumn(6).setCellRenderer(tableRenderCenter);
		jtable.getColumnModel().getColumn(7).setMaxWidth(55);
		jtable.getColumnModel().getColumn(7).setCellRenderer(tableRenderCenter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnInsert) {
			isInsert = true;
			fMain.remove(this);
			DepotMainFrame.pShoesInsert = new DepotShoesInsertUpdate(fMain, isInsert, null);
			DepotMainFrame.pShoesInsert.setBounds(200, 50, 1064, 632);
			fMain.getContentPane().add(DepotMainFrame.pShoesInsert);
			fMain.invalidate();
			fMain.validate();
			fMain.repaint();
		} else if (e.getSource() == btnUpdate) {
			isInsert = false;

			DepotGoodsDTO dto = new DepotGoodsDTO();
			int row = jtable.getSelectedRow();

			if (row < 0) {
				JOptionPane.showMessageDialog(this, "선택된 상품이 없습니다.");
				return;
			}

			String strNumber = (String) jtable.getValueAt(row, 0);
			dto.setGoodsNumber(Integer.parseInt(strNumber));
			dto.setGoodsName((String) jtable.getValueAt(row, 1));
			dto.setGoodsDetail((String) jtable.getValueAt(row, 2));
			dto.setGoodsPrice((Integer) jtable.getValueAt(row, 3));
			dto.setGoodsAmount((Integer) jtable.getValueAt(row, 4));
			dto.setGoodsLocation((String) jtable.getValueAt(row, 5));
			dto.setGoodsDate((String) jtable.getValueAt(row, 6));
			dto.setFkCategoryName((String) jtable.getValueAt(row, 7));

			fMain.remove(this);
			DepotMainFrame.pShoesInsert = new DepotShoesInsertUpdate(fMain, isInsert, dto);
			DepotMainFrame.pShoesInsert.setBounds(200, 50, 1064, 632);
			fMain.getContentPane().add(DepotMainFrame.pShoesInsert);
			fMain.invalidate();
			fMain.validate();
			fMain.repaint();
		} else if (e.getSource() == btnDelete) {
			int row = jtable.getSelectedRow();

			if (row < 0) {
				JOptionPane.showMessageDialog(this, "선택된 상품이 없습니다.");
				return;
			}

			int isDelete = JOptionPane.showConfirmDialog(this, "해당 상품을 삭제하시겠습니까?", "상품 삭제", JOptionPane.YES_NO_OPTION);

			if (isDelete == 0) {
				DepotGoodsDTO dto = new DepotGoodsDTO();
				String strNumber = (String) jtable.getValueAt(row, 0);
				dto.setGoodsNumber(Integer.parseInt(strNumber));

				DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
				boolean ok = goodsDAO.deleteGoods(dto);

				if (!ok) {
					System.out.println("상품 삭제 오류!");
				} else {
					DepotHistoryDTO historyDTO = new DepotHistoryDTO();
					historyDTO.setGoodsName((String) jtable.getValueAt(row, 1));
					historyDTO.setHistoryDetail("삭제");
					historyDTO.setGoodsAmount((Integer) jtable.getValueAt(row, 4));
					historyDTO.setManagerId(DepotMainFrame.managerId);
					historyDTO.setManagerName(DepotMainFrame.managerName);

					DepotHistoryDAO historyDAO = new DepotHistoryDAO();
					boolean okHistory = historyDAO.insertHistory(historyDTO);

					if (okHistory) {
						refreshTable();
					}
				}
			}
		}
	}

}
