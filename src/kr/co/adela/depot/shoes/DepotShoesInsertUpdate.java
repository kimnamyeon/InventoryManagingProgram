package kr.co.adela.depot.shoes;

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
import javax.swing.SwingConstants;

import kr.co.adela.depot.DepotMainFrame;
import kr.co.adela.depot.model.DepotGoodsDAO;
import kr.co.adela.depot.model.DepotGoodsDTO;
import kr.co.adela.depot.model.DepotHistoryDAO;
import kr.co.adela.depot.model.DepotHistoryDTO;

public class DepotShoesInsertUpdate extends JPanel implements ActionListener {

	DepotMainFrame fMain;
	JPanel pEmpty, pEmpty2, pNorth, pCenter, pSouth, pName, pDetail, pPrice, pAmount, pLocation, pCategory;
	JLabel lblTitle;
	JTextField tfName, tfDetail, tfPrice, tfAmount, tfLocation;
	JComboBox<String> cbCategory;
	JButton btnSubmit, btnCancle;
	String managerId, managerName;
	int goodsNumber;
	boolean isInsert;
	private String imgOk = "res/ok.png";
	private String imgOkClick = "res/okClick.png";
	private String imgCancel = "res/cancel.png";
	private String imgCancelClick = "res/cancelClick.png";
	
	public DepotShoesInsertUpdate(DepotMainFrame fMain, boolean isInsert, DepotGoodsDTO dto) {
		this.fMain = fMain;
		setLayout(new BorderLayout());
		
		// 회원 정보 가져오기
		managerId = DepotMainFrame.managerId;
		managerName = DepotMainFrame.managerName;
		
		this.isInsert = isInsert;

		if(isInsert) {
			lblTitle = new JLabel("상품 추가");
		} else {
			lblTitle = new JLabel("상품 수정");
			if(dto != null) {
				this.goodsNumber = dto.getGoodsNumber();				
			}
		}
		
		lblTitle.setFont(new Font("굴림", Font.BOLD, 25));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pEmpty = new JPanel();
		pEmpty.setBackground(Color.decode("#BDBDBD"));
		pEmpty2 = new JPanel();
		pEmpty2.setBackground(Color.decode("#BDBDBD"));
		pName = new JPanel();
		pName.setBackground(Color.decode("#BDBDBD"));
		pDetail = new JPanel();
		pDetail.setBackground(Color.decode("#BDBDBD"));
		pPrice = new JPanel();
		pPrice.setBackground(Color.decode("#BDBDBD"));
		pAmount = new JPanel();
		pAmount.setBackground(Color.decode("#BDBDBD"));
		pLocation = new JPanel();
		pLocation.setBackground(Color.decode("#BDBDBD"));
		pCategory = new JPanel();
		pCategory.setBackground(Color.decode("#BDBDBD"));
		pNorth = new JPanel();
		pCenter = new JPanel();
		pCenter.setBackground(Color.decode("#BDBDBD"));
		pSouth = new JPanel();
		pSouth.setBackground(Color.decode("#BDBDBD"));
		pCenter.setLayout(new GridLayout(16, 1));
		
		JLabel lblName = new JLabel("상품명 ");
		JLabel lblDetail = new JLabel("상품설명 ");
		JLabel lblPrice = new JLabel("가격 ");
		JLabel lblAmount = new JLabel("수량 ");
		JLabel lblLocation = new JLabel("상품위치 ");
		JLabel lblCategory = new JLabel("카테고리 ");
		
		tfName = new JTextField(20);
		tfDetail = new JTextField(20);
		tfPrice = new JTextField(20);
		tfAmount = new JTextField(20);
		tfLocation = new JTextField(20);
		String[] cbString = {"의류", "신발", "가방", "악세사리"};
		cbCategory = new JComboBox<>(cbString);
		cbCategory.setSelectedIndex(1);
		
		pNorth.add(lblTitle);
		pNorth.setBackground(Color.decode("#BDBDBD"));
		add(pNorth, BorderLayout.NORTH);
		pName.add(lblName);
		pName.add(tfName);
		pCenter.add(pName);
		pDetail.add(lblDetail);
		pDetail.add(tfDetail);
		pCenter.add(pDetail);
		pPrice.add(lblPrice);
		pPrice.add(tfPrice);
		pCenter.add(pPrice);
		pAmount.add(lblAmount);
		pAmount.add(tfAmount);
		pCenter.add(pAmount);
		pLocation.add(lblLocation);
		pLocation.add(tfLocation);
		pCenter.add(pLocation);
		pCategory.add(lblCategory);
		pCategory.add(cbCategory);
		pCenter.add(pCategory);
		add(pCenter, BorderLayout.CENTER);
		
		ImageIcon ok = new ImageIcon(imgOk);
		ImageIcon okClick = new ImageIcon(imgOkClick);
		ImageIcon cancel = new ImageIcon(imgCancel);
		ImageIcon cancelClick = new ImageIcon(imgCancelClick);
		btnSubmit = new JButton(ok);
		btnSubmit.setBorderPainted(false);
		btnSubmit.setFocusPainted(false);
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setPressedIcon(okClick);
		btnSubmit.addActionListener(this);
		btnCancle = new JButton(cancel);
		btnCancle.setBorderPainted(false);
		btnCancle.setFocusPainted(false);
		btnCancle.setContentAreaFilled(false);
		btnCancle.setPressedIcon(cancelClick);
		btnCancle.addActionListener(this);
		pSouth.add(btnSubmit);
		pSouth.add(btnCancle);
		add(pSouth, BorderLayout.SOUTH);
		
		if(isInsert == false) {
			tfName.setText(dto.getGoodsName());
			tfDetail.setText(dto.getGoodsDetail());
			tfPrice.setText("" + dto.getGoodsPrice());
			tfAmount.setText("" + dto.getGoodsAmount());
			tfLocation.setText(dto.getGoodsLocation());
			cbCategory.setSelectedItem(dto.getFkCategoryName());
		}
	}
	
	public void insertGoods() {
		String name = tfName.getText();
		String detail = tfDetail.getText();
		int price = Integer.parseInt(tfPrice.getText());
		int amount = Integer.parseInt(tfAmount.getText());
		String location = tfLocation.getText();
		String category = (String)cbCategory.getSelectedItem();
		
		// DTO에 담는다
		DepotGoodsDTO dto = new DepotGoodsDTO();
		dto.setGoodsName(name);
		dto.setGoodsDetail(detail);
		dto.setGoodsPrice(price);
		dto.setGoodsAmount(amount);
		dto.setGoodsLocation(location);
		dto.setFkCategoryName(category);
		
		// 가입
		DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
		boolean ok = goodsDAO.insertGoods(dto);
		
		if(!ok) {
			System.out.println("상품 추가 오류!");
		}
	}
	
	public void updateGoods() {
		DepotGoodsDTO dto = new DepotGoodsDTO();
		dto.setGoodsNumber(goodsNumber);
		dto.setGoodsName(tfName.getText());
		dto.setGoodsDetail(tfDetail.getText());
		int price = Integer.parseInt(tfPrice.getText());
		dto.setGoodsPrice(price);
		int amount = Integer.parseInt(tfAmount.getText());
		dto.setGoodsAmount(amount);
		dto.setGoodsLocation(tfLocation.getText());
		dto.setFkCategoryName("" + cbCategory.getSelectedItem());
		
		// 가입
		DepotGoodsDAO goodsDAO = new DepotGoodsDAO();
		boolean ok = goodsDAO.updateGoods(dto);
		
		if(!ok) {
			System.out.println("상품 수정 오류!");
		}
	}
	
	public void insertHistory() {
		String historyStat = "";
		
		if(isInsert) {
			historyStat = "추가";
		}
		else {
			historyStat = "수정";
		}
		
		String goodsName = tfName.getText();
		String strAmount = tfAmount.getText();
		int goodsAmount = Integer.parseInt(strAmount);
		
		// DTO에 담는다
		DepotHistoryDTO dto = new DepotHistoryDTO();
		dto.setGoodsName(goodsName);
		dto.setHistoryDetail(historyStat);
		dto.setGoodsAmount(goodsAmount);
		dto.setManagerId(managerId);
		dto.setManagerName(managerName);
		
		DepotHistoryDAO historyDAO = new DepotHistoryDAO();
		boolean ok = historyDAO.insertHistory(dto);
		
		if(ok) {
			fMain.remove(this);
			DepotMainFrame.pShoes.setBounds(200, 50, 1064, 632);
			fMain.getContentPane().add(DepotMainFrame.pShoes);
			DepotMainFrame.pShoes.refreshTable();
			fMain.invalidate();
			fMain.validate();
			fMain.repaint();
		} else {
			System.out.println("이력 추가 오류!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSubmit && isInsert) {
			insertGoods();
			insertHistory();
		} else if(e.getSource() == btnSubmit && isInsert == false) {
			updateGoods();
			insertHistory();
		} else if(e.getSource() == btnCancle) {
			fMain.remove(this);
			DepotMainFrame.pShoes.setBounds(200, 50, 1064, 632);
			fMain.getContentPane().add(DepotMainFrame.pShoes);
			fMain.invalidate();
			fMain.validate();
			fMain.repaint();
		}
	}
	
}
