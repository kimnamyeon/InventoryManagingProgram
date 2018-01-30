package kr.co.adela.depot.model;

public class DepotHistoryDTO {

	private int historyNumber;
	private String historyDetail;
	private String managerId;
	private String managerName;
	private String updateDate;
	private String goodsName;
	private int goodsAmount;
	
	public int getHistoryNumber() {
		return historyNumber;
	}
	public void setHistoryNumber(int historyNumber) {
		this.historyNumber = historyNumber;
	}
	public String getHistoryDetail() {
		return historyDetail;
	}
	public void setHistoryDetail(String historyDetail) {
		this.historyDetail = historyDetail;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(int goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

}
