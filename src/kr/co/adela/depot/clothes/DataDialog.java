package kr.co.adela.depot.clothes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
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

public class DataDialog extends JDialog implements ActionListener{
//	private MainFrame mF;
	private ClothesMain cM;
	private JTextField textField;
	private JPanel pCenter,pNorth, pSouth, pTitle, pName, pDetail, pPriceAmount,
					pLocation, pCategory;
	private JLabel lblName,lblDetail,lblPrice,
				   lblAmount,lblLocation,lblCategory,
				   lblTitle;
	private JTextField tfName,tfDetail,tfPrice,
					   tfAmount,tfLocation,tfCategory;
	private JButton btnOk, btnCancel;
	private JComboBox<String> cbCategory;
	public DataDialog(ClothesMain cM, String string, boolean b) {
		this.cM = cM;
		setSize(600,350);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setBackground(Color.decode("#BDBDBD"));
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
		//숫자만 입력할 수 있게 제한
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
		tfCategory = new JTextField(10);
		String[] strCategory = {"의류", "가방", "신발","악세사리"};
		cbCategory = new JComboBox<>(strCategory);
		//dialog 센터
		pCenter = new JPanel();
		pCenter.setBackground(Color.decode("#BDBDBD"));
		getContentPane().add(pCenter,BorderLayout.CENTER);
		pCenter.setLayout(null);
		//dialog 남쪽
		pSouth = new JPanel();
		getContentPane().add(pSouth,BorderLayout.SOUTH);
		pSouth.setBackground(Color.decode("#BDBDBD"));
		//dialog 센터에 타이틀 라벨 붙여버림
		pTitle = new JPanel();
		pTitle.setBackground(Color.decode("#BDBDBD"));
		pTitle.setBounds(12, 2, 560, 41);
		pCenter.add(pTitle);
		lblTitle = new JLabel("추가 Dialog!!!!");
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		pTitle.add(lblTitle);
		
		
		//dialog 센터에 상품명 패널 붙임
		pName = new JPanel(new FlowLayout());
		pName.setBounds(12, 41, 560, 41);
		pName.setBackground(Color.decode("#BDBDBD"));
		pCenter.add(pName);
//		pName.setBackground(Color.RED);
		pName.add(lblName); pName.add(tfName);
		//dialog 센터에 상품설명 패널 붙임
		pDetail = new JPanel(new FlowLayout());
		pDetail.setBackground(Color.decode("#BDBDBD"));
		pDetail.setBounds(12, 80, 560, 41);
		pCenter.add(pDetail);
		pDetail.add(lblDetail); pDetail.add(tfDetail);
		//dialog 센터에 가격 및 수량 패널 붙임
		pPriceAmount = new JPanel(new FlowLayout());
		pPriceAmount.setBackground(Color.decode("#BDBDBD"));
		pPriceAmount.setBounds(12, 119, 560, 41);
		pCenter.add(pPriceAmount);
		pPriceAmount.add(lblPrice); pPriceAmount.add(tfPrice);
		pPriceAmount.add(lblAmount); pPriceAmount.add(tfAmount);
		//dialog 센터에 저장위치 패널 붙임
		pLocation = new JPanel(new FlowLayout());
		pLocation.setBackground(Color.decode("#BDBDBD"));
		pLocation.setBounds(12, 159, 560, 41);
		pCenter.add(pLocation);
		pLocation.add(lblLocation); pLocation.add(tfLocation);
		//dialog 센터에 카테고리 패널 붙임
		pCategory = new JPanel(new FlowLayout());
		pCategory.setBackground(Color.decode("#BDBDBD"));
		pCategory.setBounds(12, 198, 560, 41);
		pCenter.add(pCategory);
		pCategory.add(lblCategory); pCategory.add(cbCategory);
		//button 장착
		btnOk = new JButton("확인");
		btnCancel = new JButton("취소");
		pSouth.add(btnOk); pSouth.add(btnCancel);
		
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosed(e);
				dispose();
			}
		});
		setVisible(true);
	}//constructor
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel) {
			this.setVisible(false);
			dispose();
		}else if(e.getSource() == btnOk) {
			checkValue();
			String name = tfName.getText();
			String detail = tfDetail.getText();
			int price = Integer.parseInt(tfPrice.getText());
			int amount = Integer.parseInt(tfAmount.getText());
			String location = tfLocation.getText();
			String category = (String) cbCategory.getSelectedItem();
			//DTO에 데이터 저장
			DTOclothes dtoc = new DTOclothes();
			dtoc.setName(name);
			dtoc.setDetail(detail);
			dtoc.setPrice(price);
			dtoc.setAmount(amount);
			dtoc.setLocation(location);
			dtoc.setCategory(category);
			dtoc.setManagerId(DepotMainFrame.managerId);
			dtoc.setManagerName(DepotMainFrame.managerName);
			//추가 
			DAOclothes daoc = new DAOclothes();
			boolean ok = daoc.insertGoods(dtoc);
			if(ok) {
				daoc.realignment();
				cM.updateTable();
				this.setVisible(false);
				dispose();
			}
			String history = "상품 추가";
			dtoc.setHistoryDetail(history);
			System.out.println("@@@@datadialog history" + history);
			daoc.insertHistory(dtoc);
		}//if-btnok
	}//actionperform
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
