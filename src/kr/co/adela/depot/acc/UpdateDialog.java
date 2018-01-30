package kr.co.adela.depot.acc;

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

import kr.co.adela.depot.model.GoodsDAO;
import kr.co.adela.depot.model.GoodsDTO;

public class UpdateDialog extends JDialog implements ActionListener{
	private AccPanel ap;
	private GoodsDTO dtoACC;
	private JLabel lblName, lblDetail, lblPrice, lblAmount, lblLocation, lblCategory, lblTitle;
	private JPanel pCenter, pNorth, pSouth, pTitle, pName, pDetail, pAmount, pLocation, pCategory;
	private JTextField tfName, tfDetail, tfPrice, tfAmount, tfLocation, tfCategory;
	private JComboBox<String> cbCategory;
	private JButton btnOk, btnCancel;
	private int upNumber;
	public UpdateDialog(AccPanel ap, String string, boolean b, GoodsDTO dtoAcc) {
		this.ap = ap;
		this.dtoACC = dtoAcc;
		
		setSize(600, 350);
		getContentPane().setLayout(new BorderLayout());
		
		lblName = new JLabel("상품명");
		lblDetail = new JLabel("상품설명");
		lblDetail.setBounds(95, 8, 52, 15);
		lblPrice = new JLabel("가격");
		lblAmount = new JLabel("수량");
		lblLocation = new JLabel("저장위치");
		lblCategory = new JLabel("카테고리");
		
		tfName = new JTextField(30);
		tfDetail = new JTextField(30);
		tfDetail.setBounds(152, 5, 336, 21);
		tfPrice = new JTextField(10);
		tfPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
		tfAmount = new JTextField(10);
		tfAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
		});
			tfLocation = new JTextField(10);
			String[] strCategory = {"악세사리", "가방", "신발", "가방"};
			cbCategory = new JComboBox<>(strCategory);
			
			pCenter = new JPanel();
			//pCenter.setBackground(Color.YELLOW);
			getContentPane().add(pCenter, BorderLayout.CENTER);
			pCenter.setLayout(null);
			
			pSouth = new JPanel();
			getContentPane().add(pSouth, BorderLayout.SOUTH);
		
			pTitle = new JPanel();
			pTitle.setBounds(12, 2, 560, 41);
			pCenter.add(pTitle);
			lblTitle = new JLabel("수정 팝업창");
			lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			pTitle.add(lblTitle);
			
			pName = new JPanel(new FlowLayout());
			pName.setBounds(12, 41, 560, 41);
			pCenter.add(pName);
			//pName.setBackground(Color.RED);
			pName.add(lblName);
			pName.add(tfName);
			pDetail = new JPanel(new FlowLayout());
			pDetail.setBounds(12, 80, 560, 41);
			pCenter.add(pDetail);
			//pDetail.setBackground(Color.BLUE);
			pDetail.add(lblDetail);
			pDetail.add(tfDetail);
			
			pAmount = new JPanel(new FlowLayout());
			pAmount.setBounds(12, 119, 560, 41);
			pCenter.add(pAmount);
			pAmount.add(lblPrice);
			pAmount.add(tfPrice);
			pAmount.add(lblAmount);
			pAmount.add(tfAmount);
			
			pLocation = new JPanel(new FlowLayout());
			pLocation.setBounds(12, 159, 560, 41);
			pCenter.add(pLocation);
			//pLocation.setBackground(Color.GREEN);
			pLocation.add(lblLocation);
			pLocation.add(tfLocation);
			
			pCategory = new JPanel(new FlowLayout());
			pCategory.setBounds(12, 198, 560, 41);
			//pCategory.setBackground(Color.PINK);
			pCenter.add(pCategory);
			pCategory.add(lblCategory);
			pCategory.add(cbCategory);
			
			btnOk = new JButton("확인");
			btnCancel = new JButton("취소");
			pSouth.add(btnOk);
			pSouth.add(btnCancel);
			btnOk.addActionListener(this);
			btnCancel.addActionListener(this);
			
			tfName.setText(dtoACC.getGoodsName());
			tfDetail.setText(dtoACC.getGoodsDetail());
			
			Object intPrice = dtoACC.getGoodsPrice();
			String strPrice = intPrice.toString();
			tfPrice.setText(strPrice);
			Object intAmount = dtoACC.getGoodsAmount();
			String strAmount = intAmount.toString();
			tfAmount.setText(strAmount);
			tfLocation.setText(dtoACC.getGoodsLocation());
			
			String strFkCategoryName = dtoACC.getFkCategoryName();
			cbCategory.setSelectedItem(strCategory);
			
			upNumber = dtoACC.getGoodsNumber();
			pCenter.setBackground(Color.decode("#BDBDBD"));
			pSouth.setBackground(Color.decode("#BDBDBD"));
			pTitle.setBackground(Color.decode("#BDBDBD"));
			pName.setBackground(Color.decode("#BDBDBD"));
			pDetail.setBackground(Color.decode("#BDBDBD"));
			pAmount.setBackground(Color.decode("#BDBDBD"));
			pLocation.setBackground(Color.decode("#BDBDBD"));
			pCategory.setBackground(Color.decode("#BDBDBD"));
			
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					super.windowClosed(e);
				}
			});
			setVisible(true);
	}//Constructor
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnOk) {
			checkValue();
			String name = tfName.getText();
			String detail = tfDetail.getText();
			int price = Integer.parseInt(tfPrice.getText());
			int amount = Integer.parseInt(tfAmount.getText());
			String location = tfLocation.getText();
			String category = (String)cbCategory.getSelectedItem();
			
			dtoACC.setGoodsName(name);
			dtoACC.setGoodsDetail(detail);
			dtoACC.setGoodsPrice(price);
			dtoACC.setGoodsAmount(amount);
			dtoACC.setGoodsLocation(location);
			dtoACC.setFkCategoryName(category);
			
			GoodsDAO daoAcc = new GoodsDAO();
			boolean ok = daoAcc.updateGoods(dtoACC);
			if(ok) {
				ap.updateTable();
				this.setVisible(false);
				System.out.println("수정성공");
			}
		}else if(e.getSource()==btnCancel) {
			dispose();
		}
	} //actionPerformed
	private void checkValue() {
		if(tfName.getText().length() == 0 ) {
			JOptionPane.showMessageDialog(this, "상품명을 입력하십시오.");
				tfName.requestFocus();
		}
		if(tfDetail.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "상품 설명을 입력하십시오.");
			tfDetail.requestFocus();
		}
		if(tfPrice.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "가격을 입력하십시오.");
			tfPrice.requestFocus();
		}
		if(tfAmount.getText().equals("")) {
			JOptionPane.showMessageDialog(this,  "수량을 입력하십시오.");
			tfAmount.requestFocus();
		}
		if(tfLocation.getText().length() == 0) {
			JOptionPane.showMessageDialog(this,  "상품 위치를 입력하십시오.");
			tfLocation.requestFocus();
		}//CheckValue
	}
	
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
