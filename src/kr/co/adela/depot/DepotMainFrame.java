package kr.co.adela.depot;

import javax.swing.JFrame;

import kr.co.adela.depot.acc.AccPanel;
import kr.co.adela.depot.bag.ProjectBag;
import kr.co.adela.depot.bag.ProjectManager;
import kr.co.adela.depot.bag.ProjectUpdate;
import kr.co.adela.depot.clothes.ClothesMain;
import kr.co.adela.depot.clothes.HistoryPanel;
import kr.co.adela.depot.shoes.DepotShoes;
import kr.co.adela.depot.shoes.DepotShoesInsertUpdate;

public class DepotMainFrame extends JFrame {
	
	public static DepotMenu pMenu;
	public static DepotSearch pSearch;
	public static LoginPanel pLogin;
	public static InitPanel pInit;
	public static ClothesMain pClothes;
	public static DepotShoes pShoes;
	public static DepotShoesInsertUpdate pShoesInsert;
	public static ProjectBag pBag;
	public static ProjectUpdate pBagInsert;
	public static AccPanel pAcc;
	public static HistoryPanel pHistory;
	public static ProjectManager pManager;
	
	public static String managerId = "";
	public static String managerName = "";
	public static String searchCategory = "%";

	
	public DepotMainFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("아델라 창고 관리 프로그램");
		setSize(1280, 720);
				
		pMenu = new DepotMenu(this);
		pSearch = new DepotSearch(this);
		pLogin = new LoginPanel(this);
		pInit = new InitPanel(this);
		pClothes = new ClothesMain(this);
		pShoes = new DepotShoes(this);
		pShoesInsert = new DepotShoesInsertUpdate(this, true, null);
		pBag = new ProjectBag(this);
		pBagInsert = new ProjectUpdate(this, false, null);
		pAcc = new AccPanel(this);
		pHistory = new HistoryPanel(this);
		pManager = new ProjectManager(this);

		// 로그인 화면을 보여줌 (첫 화면)
		add(pLogin);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DepotMainFrame();
	}

}
