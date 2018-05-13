package kr.co.adela.depot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import kr.co.adela.depot.model.MemberDAO;
import kr.co.adela.depot.model.MemberDTO;
import javax.swing.SwingConstants;

public class JoinPanel extends JPanel implements ActionListener {
	private DepotMainFrame mf;
	private LoginPanel lp;
	
	private JPanel pJoin,pId,pPwd,pName,pAgeSex,pRank,pPhone,pAddr,pCate;
	private JLabel lblId, lblPwd, lblName, lblAge, lblSex, lblPosition, lblPhone, lblAddr, lblJoinDate, lblCategory, lblSignin; 
	private JTextField tfId, tfName, tfAge, tfPosition, tfPhone, tfAddr;
	private JPasswordField pfPwd;
	private JComboBox<String> cbCategory;
	private JRadioButton rbMan, rbWoman;
	private JButton btnCheck, btnOk, btnCancel;
	private String imgOk = "res/ok.png";
	private String imgOkClick = "res/okClick.png";
	private String cancel = "res/cancel.png";
	private String cancelClick = "res/cancelClick.png";
	private String idCheck = "res/idCheck.png";
	private String idCheckClick = "res/idCheckClick.png";
	Image bgImage;

	
	public JoinPanel(DepotMainFrame mf, LoginPanel lp) {
		this.mf = mf;
		this.lp = lp;
		bgImage = new ImageIcon("res/background.png").getImage();
		setLayout(null);
		
		pJoin = new JPanel();
		pJoin.setBounds(400, 64, 469, 546);
		pJoin.setOpaque(false);
		add(pJoin);
		pJoin.setLayout(null);
		pId = new JPanel();
		pId.setBounds(31, 114, 397, 36);
		pPwd = new JPanel();
		pPwd.setBounds(31,160,397,36);
		pName = new JPanel();
		pName.setBounds(31,206,397,36);
		pAgeSex = new JPanel();
		pAgeSex.setBounds(31,252,397,36);
		pRank = new JPanel();
		pRank.setBounds(31, 298, 397, 36);
		pPhone = new JPanel();
		pPhone.setBounds(31, 344, 397, 36);
		pAddr = new JPanel();
		pAddr.setBounds(31, 390, 397, 36);
		pCate = new JPanel();
		pCate.setBounds(31, 436, 397, 36);
		pId.setBorder(new LineBorder(Color.decode("#9370DB"), 3));
		pPwd.setBorder(new LineBorder(Color.decode("#9370DB"), 3));
		pName.setBorder(new LineBorder(Color.decode("#9370DB"), 3));
		pAgeSex.setBorder(new LineBorder(Color.decode("#9370DB"), 3));
		pRank.setBorder(new LineBorder(Color.decode("#9370DB"), 3));
		pPhone.setBorder(new LineBorder(Color.decode("#9370DB"), 3));
		pAddr.setBorder(new LineBorder(Color.decode("#9370DB"), 3));
		pCate.setBorder(new LineBorder(Color.decode("#9370DB"), 3));
		pId.setLayout(null);
		pPwd.setLayout(null);
		pName.setLayout(null);
		pAgeSex.setLayout(null);
		pRank.setLayout(null);
		pPhone.setLayout(null);
		pAddr.setLayout(null);
		pCate.setLayout(null);
		pId.setOpaque(false);
		pPwd.setOpaque(false);
		pName.setOpaque(false);
		pAgeSex.setOpaque(false);
		pRank.setOpaque(false);
		pPhone.setOpaque(false);
		pAddr.setOpaque(false);
		pCate.setOpaque(false);
		pJoin.add(pId);
		pJoin.add(pPwd);
		pJoin.add(pName);
		pJoin.add(pAgeSex);
		pJoin.add(pRank);
		pJoin.add(pPhone);
		pJoin.add(pAddr);
		pJoin.add(pCate);
		ImageIcon imgIdCheck = new ImageIcon(idCheck);
		ImageIcon imgIdCheckClick = new ImageIcon(idCheckClick);
		ImageIcon imgOk_ = new ImageIcon(imgOk);
		ImageIcon imgOkClick_ = new ImageIcon(imgOkClick);
		ImageIcon imgCancel = new ImageIcon(cancel);
		ImageIcon imgCancelClick = new ImageIcon(cancelClick);
		
		//카테고리
		JLabel lblCategory_1 = new JLabel("카테고리");
		lblCategory_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategory_1.setBounds(0, 0, 102, 36);
		pCate.add(lblCategory_1);
		lblCategory_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		
		//주소
		JLabel lblAddr_1 = new JLabel("주소");
		lblAddr_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddr_1.setBounds(0, 0, 102, 36);
		pAddr.add(lblAddr_1);
		lblAddr_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		tfAddr = new JTextField("주소를 입력하세요") {
			public void setBorder(Border border) {}
		};
		tfAddr.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tfAddr.setForeground(Color.WHITE);
		tfAddr.setOpaque(false);
		tfAddr.setBounds(110, 0, 278, 36);
		tfAddr.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				tfAddr.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		pAddr.add(tfAddr);
		tfAddr.setColumns(10);
		
		//연락처
		JLabel lblPhone_1 = new JLabel("연락처");
		lblPhone_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone_1.setBounds(0, 0, 102, 36);
		pPhone.add(lblPhone_1);
		lblPhone_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		tfPhone = new JTextField("전화번호를 입력하세요") {
			public void setBorder(Border border) {}
		};
		tfPhone.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tfPhone.setForeground(Color.WHITE);
		tfPhone.setOpaque(false);
		tfPhone.setBounds(110, 0, 278, 36);
		tfPhone.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				tfPhone.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		pPhone.add(tfPhone);
		tfPhone.setColumns(10);
		
		//직위
		JLabel lblPosition_1 = new JLabel("직위");
		lblPosition_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosition_1.setBounds(0, 0, 102, 36);
		pRank.add(lblPosition_1);
		lblPosition_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		tfPosition = new JTextField("직위를 입력하세요") {
			public void setBorder(Border border) {}
		};
		tfPosition.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tfPosition.setForeground(Color.WHITE);
		tfPosition.setOpaque(false);
		tfPosition.setBounds(110, 0, 278, 36);
		tfPosition.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				tfPosition.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		pRank.add(tfPosition);
		tfPosition.setColumns(10);
		
		//나이
		JLabel lblAge_1 = new JLabel("나이");
		lblAge_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge_1.setBounds(0, 0, 102, 36);
		pAgeSex.add(lblAge_1);
		lblAge_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		tfAge = new JTextField("ex)20") {
			public void setBorder(Border border) {}
		};
		tfAge.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tfAge.setForeground(Color.WHITE);
		tfAge.setOpaque(false);
		tfAge.setBounds(110, 0, 71, 36);
		tfAge.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				tfAge.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		pAgeSex.add(tfAge);
		tfAge.setColumns(10);
		
		JLabel label = new JLabel("성별");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(193, 0, 77, 36);
		pAgeSex.add(label);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		
		rbMan = new JRadioButton("남");
		rbMan.setBounds(292, 6, 45, 23);
		pAgeSex.add(rbMan);
		rbMan.setOpaque(false);
		
		rbWoman = new JRadioButton("여");
		rbWoman.setBounds(344, 6, 45, 23);
		pAgeSex.add(rbWoman);
		rbWoman.setOpaque(false);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rbMan);
		buttonGroup.add(rbWoman);
		
		// 성명
		JLabel lblName_1 = new JLabel("성명");
		lblName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblName_1.setBounds(0, 0, 102, 36);
		pName.add(lblName_1);
		lblName_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		tfName = new JTextField("이름을 입력하세요") {
			public void setBorder(Border border) {}
		};
		tfName.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tfName.setForeground(new Color(255, 255, 255));
		tfName.setOpaque(false);
		tfName.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				tfName.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		tfName.setBounds(110, 0, 275, 36);
		pName.add(tfName);
		tfName.setColumns(10);
		
		//PW 라벨
		JLabel lblPwd_1 = new JLabel("비밀번호");
		lblPwd_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd_1.setBounds(0, 0, 102, 36);
		pPwd.add(lblPwd_1);
		lblPwd_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		//PWD 텍스트필드
		pfPwd = new JPasswordField("") {
			public void setBorder(Border border) {}
		};
		pfPwd.setForeground(Color.WHITE);
		pfPwd.setOpaque(false);
		pfPwd.setBounds(110, 0, 275, 36);
		pPwd.add(pfPwd);
		pfPwd.setColumns(10);
		
		
		//ID 라벨		
		JLabel lblId_1 = new JLabel("아이디");
		lblId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblId_1.setBounds(0, 0, 102, 36);
		pId.add(lblId_1);
		lblId_1.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lblId_1.setBackground(Color.WHITE);
		//ID 텍스트필드
		tfId = new JTextField("아이디를 입력하세요") {
			public void setBorder(Border border) {}
		};
		tfId.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		tfId.setForeground(Color.WHITE);
		tfId.setBounds(110, 0, 175, 36);
		tfId.setOpaque(false);
		pId.add(tfId);
		tfId.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				tfId.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		tfId.setColumns(10);
		
		btnCheck = new JButton(imgIdCheck);
		btnCheck.setBounds(298, 0, 87, 36);
		pId.add(btnCheck);
		btnCheck.setBorderPainted(false);
		btnCheck.setFocusPainted(false);
		btnCheck.setContentAreaFilled(false);
		btnCheck.setPressedIcon(imgIdCheckClick);
		btnCheck.addActionListener(this);
		//제목
		JLabel lblSignin = new JLabel("회원가입");
		lblSignin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignin.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblSignin.setBounds(78, 31, 317, 51);
		pJoin.add(lblSignin);
		//ID 중복확인 btn
		
		//확인&취소 버튼
		//ID 중복확인
		
		btnOk = new JButton(imgOk_);
		btnOk.setBounds(165, 484, 75, 51);
		btnOk.setBorderPainted(false);
		btnOk.setFocusPainted(false);
		btnOk.setContentAreaFilled(false);
		btnOk.setPressedIcon(imgOkClick_);
		pJoin.add(btnOk);
		btnOk.addActionListener(this);
		
		btnCancel = new JButton(imgCancel);
		btnCancel.setBounds(278, 484, 75, 51);
		btnCancel.setBorderPainted(false);
		btnCancel.setFocusPainted(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setPressedIcon(imgCancelClick);
		pJoin.add(btnCancel);
		btnCancel.addActionListener(this);
		
		
		String[] strCategory = new String[] {"의류", "가방", "신발", "악세사리"};
		cbCategory = new JComboBox<>(strCategory);
		cbCategory.setBounds(140, 445, 200, 21);
		pJoin.add(cbCategory);
		
	}//생성자 끝
	@Override
	public void paint(Graphics g) {
		g.drawImage(bgImage, 0, 0, getWidth(),getHeight(),this);
		setOpaque(false);
		super.paint(g);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCheck) {
			String id = tfId.getText();

			if(id.length() == 0) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
				return;
			}
			
			MemberDAO memberDAO = new MemberDAO();
			boolean ok = memberDAO.idCheck(id);
			
			if(!ok) {
				JOptionPane.showMessageDialog(this, "사용하셔도 괜찮은 아이디입니다.");
			} else {
				JOptionPane.showMessageDialog(this, "이미 사용 중인 아이디입니다.\n다른 아이디를 입력하세요.");
			}
		} else if(e.getSource() == btnCancel ) {
			mf.remove(this);
			mf.getContentPane().add(lp);
			mf.invalidate();
			mf.validate();
			mf.repaint();
		} else if(e.getSource() == btnOk) {
			joinMember();
		}
		
	}//actionPerformed
	
	private void joinMember() {
		System.out.println("회원가입 버튼 클릭");
		String id = tfId.getText();
		if( id.length() == 0 ) {
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
			tfId.requestFocus();
		}
		String pwd = pfPwd.getText();
		String name = tfName.getText();
		String strAge = tfAge.getText();
		int age = Integer.parseInt(strAge);
		String sex = "";
			if( rbMan.isSelected() ) {
				sex = "남";
			}
			if( rbWoman.isSelected()) {
				sex = "여";
			}
			System.out.println("sex=" + sex);
		String position = tfPosition.getText();
		String phone = tfPhone.getText();
		String addr = tfAddr.getText();
		String category = (String) cbCategory.getSelectedItem();
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setAge(age);
		dto.setSex(sex);
		dto.setPosition(position);
		dto.setPhone(phone);
		dto.setAddr(addr);
		dto.setCategory(category);
		System.out.println(dto);
		MemberDAO memberDAO = new MemberDAO();
		boolean ok = memberDAO.createMember(dto);
		if (ok) {
			JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다.");
			mf.remove(this);
			mf.getContentPane().add(DepotMainFrame.pLogin);
			mf.invalidate();
			mf.validate();
			mf.repaint();
		} else {
			JOptionPane.showMessageDialog(this, "오류 발생 : 다시 가입해주세요!!!");
		}
	}//joinMember
	
}//end






