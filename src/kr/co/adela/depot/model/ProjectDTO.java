package kr.co.adela.depot.model;

import java.sql.Date;

public class ProjectDTO {
	    ProjectDTO dtoc;
		private String manager_id;
		private String manager_pwd;
		private String manager_pwdchk;
		private int manager_age;
		private String manager_sex;
		private String manager_addr;
		private String manager_email;
		private String manager_phone;
		private String manager_rank;
		private Date manager_Joindate;
		
		// getter, setter 관리자
		public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getManager_pwd() {
		return manager_pwd;
	}

	public void setManager_pwd(String manager_pwd) {
		this.manager_pwd = manager_pwd;
	}

	public String getManager_pwdchk() {
		return manager_pwdchk;
	}

	public void setManager_pwdchk(String manager_pwdchk) {
		this.manager_pwdchk = manager_pwdchk;
	}

	public int getManager_age() {
		return manager_age;
	}

	public void setManager_age(int manager_age) {
		this.manager_age = manager_age;
	}

	public String getManager_sex() {
		return manager_sex;
	}

	public void setManager_sex(String manager_sex) {
		this.manager_sex = manager_sex;
	}

	public String getManager_addr() {
		return manager_addr;
	}

	public void setManager_addr(String manager_addr) {
		this.manager_addr = manager_addr;
	}

	public String getManager_email() {
		return manager_email;
	}

	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}

	public String getManager_phone() {
		return manager_phone;
	}

	public void setManager_phone(String manager_phone) {
		this.manager_phone = manager_phone;
	}

	public String getManager_rank() {
		return manager_rank;
	}

	public void setManager_rank(String manager_rank) {
		this.manager_rank = manager_rank;
	}

	public Date getManager_Joindate() {
		return manager_Joindate;
	}

	public void setManager_Joindate(Date manager_Joindate) {
		this.manager_Joindate = manager_Joindate;
	}
	// 상품 -----
	private String goods_number;
	private String goods_name;
	private String goods_detail;
	private String goods_price;
	private String goods_amount;
	private String goods_location;
	private String goods_date;
	private String fk_category_name;
	
	
	public String getFk_category_name() {
		return fk_category_name;
	}

	public void setFk_category_name(String fk_category_name) {
		this.fk_category_name = fk_category_name;
	}
	public String getGoods_number() {
		return goods_number;
	}

	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}

	public String getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}

	public String getGoods_amount() {
		return goods_amount;
	}

	public void setGoods_amount(String goods_amount) {
		this.goods_amount = goods_amount;
	}

	public String getGoods_location() {
		return goods_location;
	}

	public void setGoods_location(String goods_location) {
		this.goods_location = goods_location;
	}

	public String getGoods_date() {
		return goods_date;
	}

	public void setGoods_date(String goods_date) {
		this.goods_date = goods_date;
	}


	@Override
	public String toString() {
		return "ProjectDTO [manager_id=" + manager_id + ", manager_pwd" + manager_pwd + 
				", manager_pwdchk" + manager_pwdchk + ", manager_age" + manager_age + 
				", manager_sex" + manager_sex + " manager_addr" + manager_addr + 
				", manager_email" + manager_email + ", manager_phone" + manager_phone + ", "
				+ "manager_rank" + manager_rank + ", manager_Joindate" + manager_Joindate + 
				", good_number=" + goods_number + ", goods_name=" + goods_name + ", "
				+ "goods_detail=" + goods_detail + ",goods_price=" + goods_price + 
				", goods_amount=" + goods_amount + ", goods_location=" + goods_location + 
				", goods+date=" + goods_date + "]";
	}
} // end
