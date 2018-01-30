package kr.co.adela.depot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;

import kr.co.adela.depot.model.MemberDAO;
import kr.co.adela.depot.model.MemberDTO;

public class LoginPanel extends JPanel implements ActionListener {
	
	private DepotMainFrame mf;;
	private JPanel pSignin;
	private JLabel lblId, lblPwd, lblTitle,lblUser,lblHouse; 
	private JTextField tfId;
	private JPasswordField pfPwd;
	private JButton btnSignin, btnSignUp;
	private JLabel label;
	private String imJoinBtn = "res/join.png";
	private String imUser = "res/user.png";
	private String imPwd = "res/password.png";
	private String imOk = "res/ok.png";
	private String imOkPress ="res/ok_press.png";
	private String imJoinPress = "res/join_press.png";
	private String imHouse = "res/warehouse.png";
	private String signIn = "res/signIn.png";
	private String signInClick = "res/signInClick.png";
	private String signInMouse = "res/signInMouse.png";
	private String back = "res/back.png";
	private String front = "res/front.png";
	private String background = "res/background.png";
	private String imJoinMouse = "res/joinMouse.png";
	Image bgImage;
	private JSeparator separator_1;
	private JSeparator separator_2;
	
	public LoginPanel(DepotMainFrame mf) {
		this.mf = mf;
		bgImage = new ImageIcon(background).getImage();
		setLayout(null);
		
		
		//setBackground(Color.WHITE);
//		ImageIcon imgFront = new ImageIcon(front);
		JPanel pSignin = new JPanel();
		
		pSignin.setBounds(399, 46, 469, 533);
		pSignin.setOpaque(false);
		add(pSignin);
		pSignin.setLayout(null);
		
		//ID 텍스트필드
		tfId = new JTextField() {
			public void setBorder(Border border) {}
		};
		tfId.setOpaque(false);
		tfId.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		tfId.setBounds(146, 272, 249, 37);
		pSignin.add(tfId);
		tfId.setColumns(10);

		//PWD 텍스트필드
		pfPwd = new JPasswordField() {
			public void setBorder(Border border) {}
		};
		pfPwd.setOpaque(false);
		pfPwd.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		pfPwd.setColumns(10);
		pfPwd.setBounds(146, 351, 249, 37);
		pSignin.add(pfPwd);
		
		ImageIcon imgSignIn = new ImageIcon(signIn);
		ImageIcon imgOkPress = new ImageIcon(signInClick);
		ImageIcon imgSignInMouse = new ImageIcon(signInMouse);
		btnSignin = new JButton(imgSignIn);
		btnSignin.setBounds(29, 414, 428, 87);
		btnSignin.setBorderPainted(false);
		btnSignin.setFocusPainted(false);
		btnSignin.setContentAreaFilled(false);
		btnSignin.setPressedIcon(imgOkPress);
		btnSignin.setRolloverIcon(imgSignInMouse);
		btnSignin.addActionListener(this);
		pSignin.add(btnSignin);

		ImageIcon imgJoin = new ImageIcon(imJoinBtn);
		ImageIcon imgJoinPress = new ImageIcon(imJoinPress);
		ImageIcon imgJoinMouse = new ImageIcon(imJoinMouse);
		btnSignUp = new JButton(imgJoin);
		btnSignUp.setBounds(364, 490, 75, 63);
		btnSignUp.setBorderPainted(false);
		btnSignUp.setFocusPainted(false);
		btnSignUp.setContentAreaFilled(false);
		btnSignUp.setPressedIcon(imgJoinPress);
		btnSignUp.setRolloverIcon(imgJoinMouse);
		btnSignUp.addActionListener(this);
		pSignin.add(btnSignUp);
		
		
		lblTitle = new JLabel("Welcome to Adela");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblTitle.setBounds(105, 151, 290, 100);
		pSignin.add(lblTitle);

		//icon label
		ImageIcon imgIconUser = new ImageIcon(imUser);
		lblUser = new JLabel("",imgIconUser,JLabel.CENTER);
		lblUser.setBounds(42, 253, 116, 69);
		pSignin.add(lblUser);
		ImageIcon imgIconPwd = new ImageIcon(imPwd);
		lblPwd = new JLabel("",imgIconPwd,JLabel.CENTER);
		lblPwd.setBounds(42, 344, 116, 56);
		pSignin.add(lblPwd);
		ImageIcon imgIconHouse = new ImageIcon(imHouse);
		lblHouse = new JLabel("",imgIconHouse,JLabel.CENTER);
		lblHouse.setBounds(135, 52, 200, 124);
		pSignin.add(lblHouse);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(57, 245, 366, 2);
		pSignin.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(57, 332, 366, 2);
		pSignin.add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(57, 414, 366, 2);
		pSignin.add(separator_2);
		
//		JLabel lblFront = new JLabel("",imgFront,JLabel.CENTER);
//		lblFront.setBounds(0, 0, 469, 628);
//		pSignin.add(lblFront);
//		lblFront.setOpaque(false);
		
		

	}//생성자 끝
//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(bgImage, 0, 0, getWidth(),getHeight(),this);
		setOpaque(false);
		super.paintComponent(g);
	}
	public void removeInputData() {
		tfId.setText("");
		pfPwd.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == btnSignUp ) {
			mf.remove(this);
			JoinPanel jP = new JoinPanel(mf, this);
			jP.setBounds(0, 0, 1280, 720);
			mf.getContentPane().add(jP);
			mf.invalidate();
			mf.validate();
			mf.repaint();
		} else if(e.getSource() == btnSignin) {
			String id = tfId.getText();
			String pwd = pfPwd.getText();
			
			if(id.length() == 0) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
				return;
			}
			
			MemberDTO memberDTO = new MemberDTO(); 
			memberDTO.setId(id);
			memberDTO.setPwd(pwd);
			MemberDAO memberDAO = new MemberDAO();
			boolean ok = memberDAO.loginCheck(memberDTO);
			
			if( ok ) {
				mf.remove(this);
				mf.getContentPane().setLayout(null);
				// 메뉴 패널
				DepotMainFrame.pMenu.setBounds(0, 0, 200, 682);
				DepotMainFrame.pMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4, Color.decode("#7030A0")));
				mf.getContentPane().add(DepotMainFrame.pMenu);
				DepotMainFrame.pMenu.setManagerName();
				
				// 검색 패널
				DepotMainFrame.pSearch.setBounds(200, 0, 1064, 50);
				mf.getContentPane().add(DepotMainFrame.pSearch);
				
				// 컨텐츠 패널
				DepotMainFrame.pInit.setBounds(200, 50, 1064, 632);
				mf.getContentPane().add(DepotMainFrame.pInit);
				mf.invalidate();
				mf.validate();
				mf.repaint();
				
				
			} else {
				JOptionPane.showMessageDialog(this, "회원 정보가 올바르지 않습니다.");
			}
			
		}
	}
}//end






