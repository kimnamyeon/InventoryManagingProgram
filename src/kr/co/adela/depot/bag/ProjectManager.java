package kr.co.adela.depot.bag;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import kr.co.adela.depot.DepotMainFrame;
import kr.co.adela.depot.model.ProjectDAO;

public class ProjectManager extends JPanel implements ActionListener {
	DepotMainFrame f;
	JPanel pNorth, pWest, pCenter;
	JLabel lblMark;
	JButton btnSearch, btnAccessory, btnWear, btnShouse, btnBag, 
	        btnUpdate, btnManager;
	JTextField tfSearch;
	JComboBox<String> cbSch;
	JTable jtable;
	Vector vt;
	DefaultTableModel model;
	
	public ProjectManager(DepotMainFrame f) {
		this.f = f;
		setLayout(new BorderLayout());
		//setBackground(Color.WHITE);
		
		pWest = new JPanel();
		//pWest.setBackground(Color.WHITE);
		pWest.setPreferredSize(new Dimension(200, 150));
		
		pCenter = new JPanel();
		pCenter.setBackground(Color.decode("#BDBDBD"));
		pNorth = new JPanel();
		//pNorth.setBackground(Color.gray);
		
		//pNorth.setLocation(50, 50); //윈도우 창이 생기는 좌표
	    pNorth.setPreferredSize(new Dimension(1175, 60)); // 윈도우실행 창 크기
		String[] strCbSch = { "악세사리", "의류", "신발", "가방" };
		cbSch = new JComboBox<>(strCbSch);
		btnSearch = new JButton("검색");
		tfSearch = new JTextField(20);
        
		pNorth.add(cbSch);
		pNorth.add(tfSearch);
		pNorth.add(btnSearch);
		
		//add(pWest, BorderLayout.WEST);
		//add(pNorth, BorderLayout.NORTH);
		add(pCenter, BorderLayout.CENTER);
		
		
		pWest.setLayout(new GridLayout(7, 1));
		lblMark = new JLabel("Mark");
		btnAccessory = new JButton("악세사리");
	    btnWear = new JButton("의류");
    	btnShouse = new JButton("신발");
		btnBag = new JButton("가방");
		btnUpdate = new JButton("이력 조회");
		btnManager = new JButton("관리자 목록");
		
		pWest.add(lblMark);
		pWest.add(btnAccessory);
		pWest.add(btnWear);
		pWest.add(btnShouse);
		pWest.add(btnBag);
		pWest.add(btnUpdate);
		pWest.add(btnManager);
		
		btnBag.addActionListener(this);
		btnManager.addActionListener(this);
		
		ProjectDAO projectDAO = new ProjectDAO();
		Vector cols1 = new Vector();
		cols1.add("아이디");
		cols1.add("비밀번호");
		cols1.add("이름");
		cols1.add("나이");
		cols1.add("성별");
		cols1.add("직급");
		cols1.add("전화번호");
		cols1.add("주소");
		vt = projectDAO.Projectmanager();
		System.out.println(vt);
		jtable = new JTable(vt, cols1);
		jtable.setOpaque(false);
		jtable.setShowGrid(false);
		JScrollPane pane1 = new JScrollPane(jtable) {
			@Override
			public void setBorder(Border border) {}
		};
		pane1.setOpaque(false);
		pane1.getViewport().setOpaque(false);
		pane1.setPreferredSize(new Dimension(1000, 590));
		pCenter.add(pane1);
		add(pCenter);
	}
	
	public void showManager() {
		ProjectDAO projectDAO = new ProjectDAO();
		Vector cols1 = new Vector();
		cols1.add("아이디");
		cols1.add("비밀번호");
		cols1.add("이름");
		cols1.add("나이");
		cols1.add("성별");
		cols1.add("직급");
		cols1.add("전화번호");
		cols1.add("주소");
		vt = projectDAO.Projectmanager();
		model = new DefaultTableModel(vt, cols1);
		jtable.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == btnManager ) {
			f.remove(this);
			ProjectManager p = new ProjectManager(f);
			f.add(p);
			f.invalidate();
			f.validate();
			f.repaint();
			System.out.println("관리자");
			
		} else if ( e.getSource() == btnBag ) {
			f.remove(this);
			ProjectBag p = new ProjectBag(f);
			f.add(p);
			f.invalidate();
			f.validate();
			f.repaint();
			System.out.println("가방");
		}
		
	}
}
