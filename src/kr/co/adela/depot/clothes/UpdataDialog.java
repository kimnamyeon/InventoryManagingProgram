package kr.co.adela.depot.clothes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.co.adela.depot.DepotMainFrame;
import kr.co.adela.depot.model.DAOclothes;
import kr.co.adela.depot.model.DTOclothes;

public class UpdataDialog extends JDialog implements ActionListener {
	private ClothesMain cM;
	private DTOclothes dtoc;
	private JLabel lblName,lblDetail,lblPrice,
				   lblAmount,lblLocation,lblCategory,
				   lblTitle;
	private JPanel pCenter,pNorth, pSouth, pTitle,
				   pName, pDetail, pPriceAmount,
	               pLocation, pCategory;
	private JTextField tfName,tfDetail,tfPrice,
	                   tfAmount,tfLocation,tfCategory;
	private JComboBox<String> cbCategory;
	private JButton btnOk, btnCancel;
	private int upNumber;
	public UpdataDialog(ClothesMain cM, String string, boolean b,DTOclothes dtoc) {
		this.cM = cM;
		this.dtoc = dtoc;
		
		setSize(600,350);
		getContentPane().setLayout(new BorderLayout());
		//label 네이밍
		lblName = new JLabel("상 품 명");
		lblDetail = new JLabel("상품 설명");
		lblDetail.setBounds(95, 8, 52, 15);
		lblPrice = new JLabel("가 격");
		lblAmount = new JLabel("수 량");
		lblLocation = new JLabel("저장 위치 ");
		lblCategory = new JLabel("카테 고리 ");
		//textfield 
		tfName = new JTextField(30);
		tfDetail = new JTextField(30);
		tfDetail.setBounds(152, 5, 336, 21);
		tfPrice = new JTextField(10);
		tfPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c))||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		tfAmount = new JTextField(10);
		tfAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c))||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		tfLocation = new JTextField(10);
		String[] strCategory = {"의류", "가방", "신발","악세사리"};
		cbCategory = new JComboBox<>(strCategory);
		//dialog 센터
		pCenter = new JPanel();
		pCenter.setBackground(Color.decode("#BDBDBD"));
		getContentPane().add(pCenter,BorderLayout.CENTER);
		pCenter.setLayout(null);
		//dialog 남쪽
		pSouth = new JPanel();
		pSouth.setBackground(Color.decode("#BDBDBD"));
		getContentPane().add(pSouth,BorderLayout.SOUTH);
		//dialog 센터에 타이틀 라벨 붙여버림
		pTitle = new JPanel();
		pTitle.setBounds(12, 2, 560, 41);
		pTitle.setBackground(Color.decode("#BDBDBD"));
		pCenter.add(pTitle);
		lblTitle = new JLabel("수정 Dialog");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		pTitle.add(lblTitle);
		
		
		//dialog 센터에 상품명 패널 붙임
		pName = new JPanel(new FlowLayout());
		pName.setBounds(12, 41, 560, 41);
		pCenter.add(pName);
		pName.setBackground(Color.decode("#BDBDBD"));
		pName.add(lblName); pName.add(tfName);
		//dialog 센터에 상품설명 패널 붙임
		pDetail = new JPanel(new FlowLayout());
		pDetail.setBounds(12, 80, 560, 41);
		pCenter.add(pDetail);
		pDetail.setBackground(Color.decode("#BDBDBD"));
		pDetail.add(lblDetail); pDetail.add(tfDetail);
		//dialog 센터에 가격 및 수량 패널 붙임
		pPriceAmount = new JPanel(new FlowLayout());
		pPriceAmount.setBounds(12, 119, 560, 41);
		pCenter.add(pPriceAmount);
		pPriceAmount.setBackground(Color.decode("#BDBDBD"));
		pPriceAmount.add(lblPrice); pPriceAmount.add(tfPrice);
		pPriceAmount.add(lblAmount); pPriceAmount.add(tfAmount);
		//dialog 센터에 저장위치 패널 붙임
		pLocation = new JPanel(new FlowLayout());
		pLocation.setBounds(12, 159, 560, 41);
		pCenter.add(pLocation);
		pLocation.setBackground(Color.decode("#BDBDBD"));
		pLocation.add(lblLocation); pLocation.add(tfLocation);
		//dialog 센터에 카테고리 패널 붙임
		pCategory = new JPanel(new FlowLayout());
		pCategory.setBounds(12, 198, 560, 41);
		pCenter.add(pCategory);
		pCategory.setBackground(Color.decode("#BDBDBD"));
		pCategory.add(lblCategory); pCategory.add(cbCategory);
		//button 장착
		btnOk = new JButton("확인");
		btnCancel = new JButton("취소");
		pSouth.add(btnOk); pSouth.add(btnCancel);
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		// dto-> textfield set
		tfName.setText(dtoc.getName());
		tfDetail.setText(dtoc.getDetail());
		//jtable의 int형을 string으로 변환, 왜why? textField는 숫자 안들어감  
		Object intPrice = dtoc.getPrice();
		String strPrice = intPrice.toString();
		tfPrice.setText(strPrice);
		Object intAmount = dtoc.getAmount();
		String strAmount = intAmount.toString();
		tfAmount.setText(strAmount);
		tfLocation.setText(dtoc.getLocation());
		//카테고리 콤보박스 비교
		//	cbPhone.setSelectedItem(tel1);
		String sCategory = dtoc.getCategory();
		cbCategory.setSelectedItem(sCategory);
		//upNumber는 변수에만 저장, 이거 이용해서 update 쿼리 작성할 것
		upNumber = dtoc.getNumber();
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
			}
		});
		setVisible(true);
	}//constructor


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOk) {
			checkValue();
			String name = tfName.getText();
			String detail = tfDetail.getText();
			int price = Integer.parseInt(tfPrice.getText());
			int amount = Integer.parseInt(tfAmount.getText());
			String location = tfLocation.getText();
			String category = (String)cbCategory.getSelectedItem();
			
			dtoc.setName(name);
			dtoc.setDetail(detail);
			dtoc.setPrice(price);
			dtoc.setAmount(amount);
			dtoc.setLocation(location);
			dtoc.setCategory(category);
			dtoc.setManagerId(DepotMainFrame.managerId);
			dtoc.setManagerName(DepotMainFrame.managerName);
			
			DAOclothes daoc = new DAOclothes();
			boolean ok = daoc.updateGoods(dtoc);
			if(ok) {
				cM.updateTable();
				this.setVisible(false);
				System.out.println("수정성공?");
			} 
			String history = "상품 수정";
			dtoc.setHistoryDetail(history);
			System.out.println("@@@@datadialog history" + history);
			daoc.insertHistory(dtoc);
		}
		else if(e.getSource() == btnCancel) {
			dispose();
		}
	}//actionPerformed
	public void checkValue() {
		if(tfName.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "상품명을 입력하세요^^");
				tfName.requestFocus();
			}
		if(tfDetail.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "상품 설명을 입력하세요^^");
			tfDetail.requestFocus();
		}
		if(tfPrice.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "가격을 입력하세요^^");
			tfPrice.requestFocus();
		}
		if(tfAmount.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "수량을 입력하세요^^");
			tfAmount.requestFocus();
		}
		if(tfLocation.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "상품 위치를 입력하세요^^");
			tfLocation.requestFocus();
		}
	}//checkValue

}//end
