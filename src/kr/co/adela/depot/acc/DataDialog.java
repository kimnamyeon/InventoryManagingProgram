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

public class DataDialog extends JDialog implements ActionListener {
	private AccPanel ap;
	private JPanel pNorth, pSouth, pCenter, pTitle, pName, pDetail, pPrice, pAmount, pLocation, pCategory;
	private JLabel lblName, lblDetail, lblPrice, lblAmount, lblLocation, lblCategory, lblTitle;
	private JTextField tfDia, tfName, tfDetail, tfPrice, tfAmount, tfLocation, tfCategory;
	private JButton btnOk, btnCancel;
	private JComboBox<String> cbCategory;
	public DataDialog(AccPanel ap, String str, boolean b) {
		this.ap = ap;
		setSize(600, 350);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		lblName = new JLabel("상품명");
		lblDetail  = new JLabel("상품설명"); 
		lblDetail.setBounds(95, 8, 52, 15);
		lblPrice = new JLabel("가격");
		lblAmount = new JLabel("수량");
		lblLocation = new JLabel("저장위치");
		lblCategory = new JLabel("카테고리");
		
		tfName = new JTextField(30);
		tfDetail = new JTextField(30);
		tfDetail.setBounds(152, 5, 336, 21);
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
		tfPrice = new JTextField(10);
		// 숫자만 입력할 수 있도록 설정!
		tfPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c)) || c==KeyEvent.VK_BACK_SPACE || c ==KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
			
		});
		tfLocation = new JTextField(10);
		tfCategory = new JTextField(10);
		String[] strCategory = {"악세사리", "가방", "신발", "의류"};
		cbCategory = new JComboBox<>(strCategory);
		//dialog North
		//dialog South
		pSouth = new JPanel();
		pSouth.setBackground(Color.decode("#BDBDBD"));
		getContentPane().add(pSouth, BorderLayout.SOUTH);
		//dialog Center
		pCenter = new JPanel();
		pCenter.setBackground(Color.decode("#BDBDBD"));
		pCenter.setLayout(null);
		getContentPane().add(pCenter, BorderLayout.CENTER);
		//Center에 Title 패널 생성	
			pTitle = new JPanel();
			pTitle.setBackground(Color.decode("#BDBDBD"));
			pTitle.setBounds(12, 9, 560, 41);
			pCenter.add(pTitle);
			lblTitle = new JLabel("추가 팝업창");
			lblTitle.setFont(new Font("맑은  고딕", Font.BOLD, 15));
			pTitle.add(lblTitle);
			
			pName = new JPanel();
			pName.setBounds(12, 41, 560, 41);
			pCenter.add(pName);
			pName.setBackground(Color.decode("#BDBDBD"));
			pName.add(lblName);
			pName.add(tfName);
			
			pDetail = new JPanel(new FlowLayout());
			pDetail.setBackground(Color.decode("#BDBDBD"));
			pDetail.setBounds(12, 80, 560, 41);
			pCenter.add(pDetail);
			pDetail.add(lblDetail);
			pDetail.add(tfDetail);
			
			pAmount = new JPanel(new FlowLayout());
			pAmount.setBackground(Color.decode("#BDBDBD"));
			pAmount.setBounds(12, 119, 560, 41);
			pCenter.add(pAmount);
			pAmount.add(lblPrice);
			pAmount.add(tfPrice);
			pAmount.add(lblAmount);
			pAmount.add(tfAmount);
			
			pLocation = new JPanel(new FlowLayout());
			pLocation.setBackground(Color.decode("#BDBDBD"));
			pLocation.setBounds(12, 159, 560, 41);
			pCenter.add(pLocation);
			pLocation.add(lblLocation);
			pLocation.add(tfLocation);
			
			pCategory = new JPanel(new FlowLayout());
			pCategory.setBackground(Color.decode("#BDBDBD"));
			pCategory.setBounds(12, 198, 560, 41);
			pCenter.add(pCategory);
			pCategory.add(lblCategory);
			pCategory.add(cbCategory);
			
			btnOk = new JButton("확인");
			btnCancel = new JButton("취소");
			pSouth.add(btnOk);
			pSouth.add(btnCancel);
			
			btnOk.addActionListener(this);
			btnCancel.addActionListener(this);
			
			this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				dispose();
			}
			});
			setVisible(true);
			
	} //constructor
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==btnCancel) {
			this.setVisible(false);
			dispose();
		} else if (e.getSource() ==  btnOk) {
			checkValue();
			String name = tfName.getText();
			String detail = tfDetail.getText();
			int price = Integer.parseInt(tfPrice.getText());
			int amount = Integer.parseInt(tfAmount.getText());
			String location = tfLocation.getText();
			String category = (String) cbCategory.getSelectedItem();
			
			GoodsDTO dtoAcc = new GoodsDTO();
			dtoAcc.setGoodsName(name);
			dtoAcc.setGoodsDetail(detail);
			dtoAcc.setGoodsPrice(price);
			dtoAcc.setGoodsAmount(amount);
			dtoAcc.setGoodsLocation(location);
			dtoAcc.setFkCategoryName(category);
			//dao
			GoodsDAO dao = new GoodsDAO();
			boolean ok = dao.insertGoods(dtoAcc);
			if(ok) {
				ap.updateTable();
				dispose();
			}
		}
	}

	private void checkValue() {
		if(tfName.getText().length() == 0 ) {
			JOptionPane.showMessageDialog(this, "상품명을 입력하십시오.");
			tfName.requestFocus();
		}
		if(tfDetail.getText().length() == 0) {
			JOptionPane.showConfirmDialog(this, "상품 설명을 입력하십시오.");
			tfDetail.requestFocus();
		}
		if(tfPrice.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "가격을 입력하십시오.");
			tfPrice.requestFocus();
		}
		if(tfAmount.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "수량을 입력하십시오.");
			tfAmount.requestFocus();
		}
		if(tfLocation.getText().length() == 0) {
			JOptionPane.showMessageDialog(this, "상품 위치를 입력하십시오.");
			tfLocation.requestFocus();
		}
		
	} // checKValue
}
