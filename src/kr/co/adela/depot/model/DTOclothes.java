package kr.co.adela.depot.model;

public class DTOclothes {

	@Override
	public String toString() {
		return "DTOclothes [number=" + number + ", name=" + name + ", detail=" + detail + ", price=" + price
				+ ", amount=" + amount + ", location=" + location + ", date=" + date + ", category=" + category
				+ ", historyDetail=" + historyDetail + "]";
	}
	private int number;
	private String name;
	private String detail;
	private int price;
	private int amount;
	private String location;
	private String date;
	private String category;
	private String historyDetail;
	private String managerId;
	private String managerName;
	
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
	
	public String getHistoryDetail() {
		return historyDetail;
	}
	public void setHistoryDetail(String historyDetail) {
		this.historyDetail = historyDetail;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


}
