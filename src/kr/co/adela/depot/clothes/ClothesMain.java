package kr.co.adela.depot.clothes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import kr.co.adela.depot.DepotMainFrame;
import kr.co.adela.depot.model.DAOclothes;
import kr.co.adela.depot.model.DTOclothes;

public class ClothesMain extends JPanel implements ActionListener,MouseListener{
	private DepotMainFrame mF;
	private ClothesMain cM;
	private DataDialog dD;
	private UpdataDialog uD;
	private JPanel pList, pSearch, pListSouth, pCenter, pEast;
	private JTextField textSearch;
	private JLabel lblName,lblSearch;
	private JComboBox<String> category;
	public static JTable tblClothes;
	private Vector data,titles,selectData;
	private JScrollPane scrollPane;
	private JButton btnInsert,btnUpdate,btnDelete,btnSearch, btnTest;
	private boolean isOpenDialog = false;
	private DefaultTableModel model;
	private String imgInsert = "res/btnInsert.png";
	private String imgInsertClick = "res/btnInsertClick.png";
	private String imgUpdate = "res/btnUpdate.png";
	private String imgUpdateClick = "res/btnUpdateClick.png";
	private String imgDelete = "res/btnDelete.png";
	private String imgDeleteClick = "res/btnDeleteClick.png";
	
	public ClothesMain(DepotMainFrame mF) {
		this.mF = mF;
		setLayout(new BorderLayout());
		setBackground(Color.BLACK);
		//east에 여백 넣기
		pEast = new JPanel();
		pEast.setBackground(Color.decode("#BDBDBD"));
		//pEast.setOpaque(false);
		add(pEast,BorderLayout.EAST);
		//테이블 패널
		pList = new JPanel();
		add(pList,BorderLayout.CENTER);
		pList.setOpaque(false);
		pList.setLayout(new BorderLayout());

		//테이블 패널 - jtable 붙이기
		showData();
		titles = new Vector();
		titles.add("No"); titles.add("상품명");
		titles.add("상품설명"); titles.add("가격");
		titles.add("수량"); titles.add("위치");
		titles.add("날짜"); titles.add("카테고리");
		//model 생성
		model = new DefaultTableModel(data, titles) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblClothes = new JTable(model); 
		tblClothes.addMouseListener(this);
		tblClothes.setOpaque(false);
		tblClothes.setShowGrid(false);
		scrollPane = new JScrollPane(tblClothes) {
			@Override
			public void setBorder(Border border) {}
		};
		scrollPane.setPreferredSize(new Dimension(1000, 590));
		//테이블 투명
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		pCenter = new JPanel();
		setBackground(Color.decode("#BDBDBD"));
		pCenter.setOpaque(false);
		pCenter.add(scrollPane);
		pList.add(pCenter);
		//테이블 패널 - 버튼 붙이기(추가, 수정, 삭제)
		pListSouth = new JPanel();
		pListSouth.setBackground(Color.decode("#BDBDBD"));
		pListSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//pListSouth.setOpaque(false);
		pList.add(pListSouth,BorderLayout.SOUTH);
		ImageIcon insert = new ImageIcon(imgInsert);
		ImageIcon insertClick = new ImageIcon(imgInsertClick);
		ImageIcon update = new ImageIcon(imgUpdate);
		ImageIcon updateClick = new ImageIcon(imgUpdateClick);
		ImageIcon delete = new ImageIcon(imgDelete);
		ImageIcon deleteClick = new ImageIcon(imgDeleteClick);
		
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
		
		btnTest = new JButton("test");
		btnTest.addActionListener(this);

		//pListSouth.add(btnTest);

		pListSouth.add(btnInsert);
		pListSouth.add(btnUpdate);
		pListSouth.add(btnDelete);
	}//constructor

//	@Override
//	protected void paintComponent(Graphics g) {
//		g.drawImage(bgImage, 0, 0, getWidth(),getHeight(),this);
//		setOpaque(false);
//		super.paintComponent(g);
//	}
	//테이블 전체 데이터 출력
	public void showData() {
		DAOclothes daoc = new DAOclothes();
		data = daoc.showClothesData();
	}
	
	//테이블 업데이트
	public void updateTable() {
		showData();
		model = new DefaultTableModel(data, titles);
		tblClothes.setModel(model);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert) {
			dD = new DataDialog(this,"상품 추가!", true);
			System.out.println("추가버튼");
		}else if (e.getSource() == btnUpdate) {
			System.out.println("수정버튼");
			int upNumber = (int)selectData.get(0);
			String upName = (String) selectData.get(1);
			String upDetail = (String) selectData.get(2);
			int upPrice = (int) selectData.get(3);
			int upAmount = (int) selectData.get(4);
			String upLocation = (String) selectData.get(5);
			String upCategory = (String) selectData.get(7);
			DTOclothes dtoc = new DTOclothes();
			dtoc.setNumber(upNumber);
			dtoc.setName(upName);
			dtoc.setDetail(upDetail);
			dtoc.setPrice(upPrice);
			dtoc.setAmount(upAmount);
			dtoc.setLocation(upLocation);
			dtoc.setCategory(upCategory);	
			uD = new UpdataDialog(this,"상품 수정!",true,dtoc);
		}else if(e.getSource() == btnDelete) {
			int result = JOptionPane.showConfirmDialog
			(this, "정말 삭제하시겠습니까?", "삭제", JOptionPane.OK_CANCEL_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				System.out.println("확인");
				System.out.println("@@@selectData = " + selectData);
				int deleteNum = (int) selectData.get(0);
				System.out.println("@@@deleteNum = " + deleteNum);
				DTOclothes dtoc = new DTOclothes();
				dtoc.setNumber(deleteNum);
				DAOclothes daoc = new DAOclothes();
				boolean ok = daoc.deleteGoods(deleteNum,dtoc);
				if(ok) {
					daoc.realignment();
					updateTable();
				}
				String history = "상품 삭제";
				dtoc.setHistoryDetail(history);
				String delName = (String) selectData.get(1);
				int delAmount = (int) selectData.get(4);
				dtoc.setName(delName);
				dtoc.setAmount(delAmount);
				dtoc.setManagerId(DepotMainFrame.managerId);
				dtoc.setManagerName(DepotMainFrame.managerName);
				daoc.deleteHistory(dtoc);
			}else{
				System.out.println("취소");
			}
		}
		
	}//action perform

	@Override
	public void mouseClicked(MouseEvent e) {
		int index = tblClothes.getSelectedRow();
		System.out.println("index = " + index);
		selectData = (Vector)data.get(index);
		System.out.println("v = " + selectData);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}//main
