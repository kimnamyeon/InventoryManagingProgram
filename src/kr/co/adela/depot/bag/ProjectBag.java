package kr.co.adela.depot.bag;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import kr.co.adela.depot.DepotMainFrame;
import kr.co.adela.depot.bag.ProjectBag;
import kr.co.adela.depot.model.DepotHistoryDAO;
import kr.co.adela.depot.model.DepotHistoryDTO;
import kr.co.adela.depot.model.ProjectDAO;
import kr.co.adela.depot.model.ProjectDTO;

public class ProjectBag extends JPanel implements ActionListener {
	DepotMainFrame f;
	JPanel pNorth, pWest, pCenter, pSouth;
	JLabel lblMark;
	JButton btnSearch, btnAccessory, btnWear, btnShouse, btnBag, btnCheck, btnManager, btnInsert, btnUpdate, btnDelete;
	JTextField tfSearch;
	JComboBox<String> cbSch;
	public static JTable jtable;
	DefaultTableModel tableModel; // 새로고침 역할
	Vector vt, selectDate;
	private String imgInsert = "res/btnInsert.png";
	private String imgInsertClick = "res/btnInsertClick.png";
	private String imgUpdate = "res/btnUpdate.png";
	private String imgUpdateClick = "res/btnUpdateClick.png";
	private String imgDelete = "res/btnDelete.png";
	private String imgDeleteClick = "res/btnDeleteClick.png";

	// 생성자
	public ProjectBag(DepotMainFrame f) {
		this.f = f;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		pWest = new JPanel();
		pWest.setBackground(Color.WHITE);
		// pWest.setPreferredSize(new Dimension(200, 150));

		pCenter = new JPanel();
		pCenter.setBackground(Color.decode("#BDBDBD"));
		
		pSouth = new JPanel();
		pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pSouth.setBackground(Color.decode("#BDBDBD"));

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

		// 윈하는 좌표에 버튼을 생성
		pSouth.add(btnInsert);
		pSouth.add(btnUpdate);
		pSouth.add(btnDelete);

		pNorth = new JPanel();
		pNorth.setBackground(Color.gray);

		// pNorth.setLocation(50, 50); //윈도우 창이 생기는 좌표
		// pNorth.setPreferredSize(new Dimension(1175, 60)); // 윈도우실행 창 크기
		String[] strCbSch = { "악세사리", "의류", "신발", "가방" };
		cbSch = new JComboBox<>(strCbSch);
		btnSearch = new JButton("검색");
		tfSearch = new JTextField(20);

		pNorth.add(cbSch);
		pNorth.add(tfSearch);
		pNorth.add(btnSearch);

		pWest.setLayout(new GridLayout(7, 1));
		lblMark = new JLabel("Mark");
		btnAccessory = new JButton("악세사리");
		btnWear = new JButton("의류");
		btnShouse = new JButton("신발");
		btnBag = new JButton("가방");
		btnCheck = new JButton("이력 조회");
		btnManager = new JButton("관리자 목록");

		pWest.add(lblMark);
		pWest.add(btnAccessory);
		pWest.add(btnWear);
		pWest.add(btnShouse);
		pWest.add(btnBag);
		pWest.add(btnCheck);
		pWest.add(btnManager);
		
		ProjectDAO projectDAO = new ProjectDAO();
		Vector cols = new Vector();
		cols.add("가방 번호");
		cols.add("가방종류(이름)");
		cols.add("가방 세부 사항");
		cols.add("가방 가격");
		cols.add("가방 수량");
		cols.add("가방 위치");
		cols.add("입고 일자");
		cols.add("카테고리");
		vt = projectDAO.Projectlist();
		System.out.println(vt);
		jtable = new JTable(vt, cols);
		jtable.setOpaque(false);
		jtable.setShowGrid(false);
		JScrollPane pane = new JScrollPane(jtable) {
			@Override
			public void setBorder(Border border) {}
		};
		pane.setOpaque(false);
		pane.getViewport().setOpaque(false);
		pane.setPreferredSize(new Dimension(1000, 590));
		pCenter.add(pane);
		pCenter.setBackground(Color.decode("#BDBDBD"));
		add(pCenter, BorderLayout.CENTER);
		add(pSouth, BorderLayout.SOUTH);
		// DB 상품정보를 화면에 출력 ProjectBag 에 넣어주어 ProjectMain 에서 projectBag 화면을 출력.
	}
	
	public void showBag() {
		ProjectDAO projectDAO = new ProjectDAO();
		Vector cols = new Vector();
		cols.add("가방 번호");
		cols.add("가방종류(이름)");
		cols.add("가방 세부 사항");
		cols.add("가방 가격");
		cols.add("가방 수량");
		cols.add("가방 위치");
		cols.add("입고 일자");
		cols.add("카테고리");
		vt = projectDAO.Projectlist();

		tableModel = new DefaultTableModel(vt, cols);
		jtable.setModel(tableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnInsert) {
			boolean inUp = false;
			f.remove(this);
			DepotMainFrame.pBagInsert = new ProjectUpdate(f, inUp, null);
			DepotMainFrame.pBagInsert.setBounds(200,50,1064,632);
			f.add(DepotMainFrame.pBagInsert);
			f.invalidate();
			f.validate();
			f.repaint();
			// System.out.println("추가");

		} else if (e.getSource() == btnUpdate) {
			int row = jtable.getSelectedRow();
			
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "상품을 선택해주세요.!!");
				return;
			}

			String str = (String) jtable.getValueAt(row, 0);
			String str1 = (String) jtable.getValueAt(row, 1);
			String str2 = (String) jtable.getValueAt(row, 2);
			String str3 = (String) jtable.getValueAt(row, 3);
			String str4 = (String) jtable.getValueAt(row, 4);
			String str5 = (String) jtable.getValueAt(row, 5);
			// System.out.println( str + " : " + str1 + str2 + str3 + str5);

			ProjectDTO dto = new ProjectDTO();
			dto.setGoods_number(str);
			dto.setGoods_name(str1);
			dto.setGoods_detail(str2);
			dto.setGoods_price(str3);
			dto.setGoods_amount(str4);
			dto.setGoods_location(str5);

			boolean inUp = true;

			f.remove(this);
			DepotMainFrame.pBagInsert = new ProjectUpdate(f, inUp, dto);
			DepotMainFrame.pBagInsert.setBounds(200,50,1064,632);
			f.add(DepotMainFrame.pBagInsert);
			f.invalidate();
			f.validate();
			f.repaint();
			// System.out.println("수정");

		} else if (e.getSource() == btnDelete) {
			int result = JOptionPane.showConfirmDialog(this, "삭제 하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
			if (result == 0) {
				String strNumber = (String) jtable.getValueAt(jtable.getSelectedRow(), 0);
				int number = Integer.parseInt(strNumber);

				ProjectDAO dao = new ProjectDAO();
				boolean ok = dao.deleteGoods(number);

				if (!ok) {
					System.out.println("상품 삭제 오류!");

				} else {
					DepotHistoryDTO historyDTO = new DepotHistoryDTO();
					historyDTO.setGoodsName((String) jtable.getValueAt(result, 1));
					historyDTO.setHistoryDetail("삭제");
					String strAmount = (String)jtable.getValueAt(result, 4);
					historyDTO.setGoodsAmount(Integer.parseInt(strAmount));
					historyDTO.setManagerId(DepotMainFrame.managerId);
					historyDTO.setManagerName(DepotMainFrame.managerName);

					DepotHistoryDAO historyDAO = new DepotHistoryDAO();
					boolean okHistory = historyDAO.insertHistory(historyDTO);

					if (okHistory) {
						showBag();
					}
				}

			}
		}
	}

}