package kr.co.adela.depot.bag;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import kr.co.adela.depot.DepotMainFrame;
import kr.co.adela.depot.model.DepotHistoryDAO;
import kr.co.adela.depot.model.DepotHistoryDTO;
import kr.co.adela.depot.model.ProjectDAO;
import kr.co.adela.depot.model.ProjectDTO;

public class ProjectUpdate extends JPanel implements ActionListener {

	DepotMainFrame f;
	JPanel pName, pDatail, pPrice, pAmount, pLocation, pWest, pCenter, pCategory;
	JTextField tfName, tfDatail, tfPrice, tfAmount, tfLocation;
	JComboBox<String> cbSch;
	JLabel lblName, lblDatail, lblPrice, lblAmount, lblLocation, lblInsert, lblMark;
	JButton btnInsert, btnUpdate, btnCancel, btnAccessory, btnWear, btnShouse, btnBag, btnCheck, btnManager;
	JComboBox<String> cbCategory;
	String goodsNumber;
	private String imgInUp = "res/inUp.png";
	private String imgInUpClick = "res/inUpClick.png";
	private String imgCancel = "res/cancel.png";
	private String imgCancelClick = "res/cancelClick.png";
	boolean inUp;

	public ProjectUpdate(DepotMainFrame f, boolean inUp, ProjectDTO dto) {
		this.f = f;
		this.inUp = inUp;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout()); // << setBounds 를 사용하기에 잘못된 예
		// setLayout(null); // setBounds 를 사용하려면 setLayout에 null 값을 지정해 주어야 한다.
		// 수정 카테고리 하나하나를 패널을 이용해 생성 붙여준다.

		pWest = new JPanel();
		pWest.setBackground(Color.decode("#BDBDBD"));
		//pWest.setPreferredSize(new Dimension(200, 150));
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

		pCenter = new JPanel();
		pCenter.setBackground(Color.decode("#BDBDBD"));
		pCenter.setLayout(null);
		// pCenter.setBackground(Color.WHITE);

		lblInsert = new JLabel("상품 추가/수정");
		lblInsert.setForeground(new Color(138, 43, 226));
		lblInsert.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblName = new JLabel("상 품 명 : ");
		lblName.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblName.setForeground(new Color(138, 43, 226));
		lblDatail = new JLabel("상품 내용 :  ");
		lblDatail.setForeground(new Color(138, 43, 226));
		lblDatail.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblPrice = new JLabel("상품 가격 : ");
		lblPrice.setForeground(new Color(138, 43, 226));
		lblPrice.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblAmount = new JLabel("상품 수량 : ");
		lblAmount.setForeground(new Color(138, 43, 226));
		lblAmount.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblLocation = new JLabel("상품 위치 : ");
		lblLocation.setForeground(new Color(138, 43, 226));
		lblLocation.setFont(new Font("맑은 고딕", Font.BOLD, 30));

		lblInsert.setBounds(361, 49, 400, 50);
		lblName.setBounds(280, 150, 200, 50);
		lblDatail.setBounds(280, 230, 200, 50);
		lblPrice.setBounds(280, 310, 200, 50);
		lblAmount.setBounds(280, 390, 200, 50);
		lblLocation.setBounds(280, 470, 200, 50);

		pCenter.add(lblInsert);
		pCenter.add(lblName);
		pCenter.add(lblDatail);
		pCenter.add(lblPrice);
		pCenter.add(lblAmount);
		pCenter.add(lblLocation);

		pName = new JPanel();
		pName.setOpaque(false);
		pDatail = new JPanel();
		pDatail.setOpaque(false);
		pPrice = new JPanel();
		pPrice.setOpaque(false);
		pAmount = new JPanel();
		pAmount.setOpaque(false);
		pLocation = new JPanel();
		pLocation.setOpaque(false);
		pCategory = new JPanel();
		pCategory.setOpaque(false);

		pCenter.add(pName);
		pCenter.add(pDatail);
		pCenter.add(pPrice);
		pCenter.add(pAmount);
		pCenter.add(pLocation);
		pCenter.add(pCategory);
		pCategory.setLayout(null);
		tfName = new JTextField(30);
		tfName.setBorder(new LineBorder(Color.decode("#9370DB"), 5));
		tfName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		tfName.setBounds(12, 5, 340, 45);
		tfName.setOpaque(false);
		tfDatail = new JTextField(20);
		tfDatail.setBorder(new LineBorder(Color.decode("#9370DB"), 5));
		tfDatail.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		tfDatail.setBounds(12, 5, 340, 45);
		tfDatail.setOpaque(false);
		tfPrice = new JTextField(20);
		tfPrice.setBorder(new LineBorder(Color.decode("#9370DB"), 5));
		tfPrice.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		tfPrice.setBounds(12, 5, 340, 45);
		tfPrice.setOpaque(false);
		tfAmount = new JTextField(20);
		tfAmount.setBorder(new LineBorder(Color.decode("#9370DB"), 5));
		tfAmount.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		tfAmount.setBounds(12, 5, 340, 45);
		tfAmount.setOpaque(false);
		tfLocation = new JTextField(20);
		tfLocation.setBorder(new LineBorder(Color.decode("#9370DB"), 5));
		tfLocation.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		tfLocation.setBounds(12, 5, 340, 45);
		tfLocation.setOpaque(false);

		pName.setBounds(450, 150, 360, 60);
		pDatail.setBounds(450, 230, 360, 60);
		pPrice.setBounds(450, 310, 360, 60);
		pAmount.setBounds(450, 390, 360, 60);
		pLocation.setBounds(450, 470, 360, 60);
		pName.setLayout(null);

		pName.add(tfName);
		pDatail.setLayout(null);
		pDatail.add(tfDatail);
		pPrice.setLayout(null);
		pPrice.add(tfPrice);
		pAmount.setLayout(null);
		pAmount.add(tfAmount);
		pLocation.setLayout(null);
		pLocation.add(tfLocation);

		ImageIcon inUp2 = new ImageIcon(imgInUp);
		ImageIcon inUpClick = new ImageIcon(imgInUpClick);
		ImageIcon cancel = new ImageIcon(imgCancel);
		ImageIcon cancelClick = new ImageIcon(imgCancelClick);
		btnInsert = new JButton(inUp2);
		btnInsert.setBorderPainted(false);
		btnInsert.setFocusPainted(false);
		btnInsert.setContentAreaFilled(false);
		btnInsert.setPressedIcon(inUpClick);
		btnCancel = new JButton(cancel);
		btnCancel.setBorderPainted(false);
		btnCancel.setFocusPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setPressedIcon(cancelClick);

		btnInsert.setBounds(400, 570, 150, 50);
		btnCancel.setBounds(550, 570, 100, 50);

		pCenter.add(btnInsert);
		pCenter.add(btnCancel);

		String[] strCategory = { "가방", "신발", "의류", "악세사리" };
		cbCategory = new JComboBox<>(strCategory);
		pCategory.setBounds(250, 570, 100, 50);
		pCategory.add(cbCategory);

		//add(pWest, BorderLayout.WEST);
		add(pCenter, BorderLayout.CENTER);
		
		JPanel border = new JPanel();
		border.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		border.setBounds(237, 132, 600, 417);
		border.setOpaque(false);
		pCenter.add(border);

		btnInsert.addActionListener(this);
		btnCancel.addActionListener(this);

		// DB 수정 버튼클릭시 자동 생성

		ProjectDAO projectDAO = new ProjectDAO();
		ProjectDTO dtoUpdate = projectDAO.updateGoods();
		// DTO >> 화면
		if (inUp) {
			boolean ok = projectDAO.updateGoods(dtoUpdate);
			goodsNumber = dto.getGoods_number();
			tfName.setText(dto.getGoods_name());
			tfDatail.setText(dto.getGoods_detail());
			tfPrice.setText(dto.getGoods_price());
			tfAmount.setText(dto.getGoods_amount());
			tfLocation.setText(dto.getGoods_location());
		}

	}
	public void insertHistory() { // 이력화면 
		String historyStat = "";
		
		if(inUp) {
			historyStat = "수정";
		}
		else {
			historyStat = "추가";
		}
		
		String goodsName = tfName.getText();
		String strAmount = tfAmount.getText();
		int goodsAmount = Integer.parseInt(strAmount);
		
		// DTO에 담는다
		DepotHistoryDTO dto = new DepotHistoryDTO();
		dto.setGoodsName(goodsName);
		dto.setHistoryDetail(historyStat);
		dto.setGoodsAmount(goodsAmount);
		dto.setManagerId(DepotMainFrame.managerId); // 
		dto.setManagerName(DepotMainFrame.managerName);
		
		DepotHistoryDAO historyDAO = new DepotHistoryDAO();
		boolean ok = historyDAO.insertHistory(dto);
		
		if(ok) {
			f.remove(this);
			DepotMainFrame.pBag.setBounds(200, 50, 1064, 632);
			f.add(DepotMainFrame.pBag);
			DepotMainFrame.pBag.showBag();
			f.invalidate();
			f.validate();
			f.repaint();
		} else {
			System.out.println("이력 추가 오류!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == btnCancel ) {
			f.remove(this);
			DepotMainFrame.pBag.setBounds(200, 50, 1064, 632);
			f.add(DepotMainFrame.pBag);
			DepotMainFrame.pBag.showBag();
			f.invalidate();
			f.validate();
			f.repaint();
			//System.out.println("취소");
			
		} else if ( e.getSource() == btnInsert ) {
			if(inUp == false) {
				ProjectInsert();
				insertHistory(); //
			} else {
				ProjectUpdateBtn();
				insertHistory();
			}
		}
	}

	private void ProjectInsert() {
		//System.out.println("추가/수정 버튼 !!");

		String goods_name = tfName.getText();
		String goods_detail = tfDatail.getText();
		String goods_price = tfPrice.getText();
		String goods_amount = tfAmount.getText();
		String goods_location = tfLocation.getText();
		String fk_category_name = (String) cbCategory.getSelectedItem();
		String goods_number = goodsNumber;
		// dto 에 담기!!!
		ProjectDTO dto = new ProjectDTO();
		dto.setGoods_number(goodsNumber);
		dto.setGoods_name(goods_name);
		dto.setGoods_detail(goods_detail);
		dto.setGoods_price(goods_price);
		dto.setGoods_amount(goods_amount);
		dto.setGoods_location(goods_location);
		dto.setFk_category_name(fk_category_name);
		
		ProjectDAO projectDAO = new ProjectDAO();
		boolean ok = projectDAO.ProjectInsert(dto);
	}
		/*if (ok == true) {
			f.remove(this);
			DepotMainFrame.pBag.setBounds(200, 50, 1064, 632);
			f.add(DepotMainFrame.pBag);
			DepotMainFrame.pBag.showBag();
			f.invalidate();
			f.validate();
			f.repaint();
		}
	}*/

	private void ProjectUpdateBtn() {
		ProjectDTO dto = new ProjectDTO();
		dto.setGoods_name(tfName.getText());
		dto.setGoods_detail(tfDatail.getText());
		dto.setGoods_price(tfPrice.getText());
		dto.setGoods_amount(tfAmount.getText());
		dto.setGoods_location(tfLocation.getText());
		dto.setFk_category_name((String)cbCategory.getSelectedItem());
		dto.setGoods_number(goodsNumber);
		ProjectDAO projectDAO = new ProjectDAO();
		boolean ok = projectDAO.updateGoods(dto);
		
		//System.out.println(ok);
		
		/*if (ok == true) {
			f.remove(this);
			DepotMainFrame.pBag.setBounds(200, 50, 1064, 632);
			f.add(DepotMainFrame.pBag);
			DepotMainFrame.pBag.showBag();
			f.invalidate();
			f.validate();
			f.repaint();
		}*/
	}
}

