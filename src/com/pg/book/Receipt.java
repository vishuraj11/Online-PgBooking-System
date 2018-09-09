package com.pg.book;

public class Receipt {
	int pg_id;
	String pg_name;
	int pg_share,pg_price;
	String pg_address,book_name,book_mobile,book_date,email;
	
	
	public Receipt(int pg_id, String pg_name,int pg_share, int pg_price, String pg_address, String book_name,
			String book_mobile, String book_date) {
		
		this.pg_id = pg_id;
		this.pg_name = pg_name;
		this.pg_share = pg_share;
		this.pg_price = pg_price;
		this.pg_address = pg_address;
		this.book_name = book_name;
		this.book_mobile = book_mobile;
		this.book_date = book_date;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPg_id() {
		return pg_id;
	}
	public void setPg_id(int pg_id) {
		this.pg_id = pg_id;
	}
	public String getPg_name() {
		return pg_name;
	}
	public void setPg_name(String pg_name) {
		this.pg_name = pg_name;
	}
	public int getPg_share() {
		return pg_share;
	}
	public void setPg_share(int pg_share) {
		this.pg_share = pg_share;
	}
	public int getPg_price() {
		return pg_price;
	}
	public void setPg_price(int pg_price) {
		this.pg_price = pg_price;
	}
	public String getPg_address() {
		return pg_address;
	}
	public void setPg_address(String pg_address) {
		this.pg_address = pg_address;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_mobile() {
		return book_mobile;
	}
	public void setBook_mobile(String book_mobile) {
		this.book_mobile = book_mobile;
	}
	public String getBook_date() {
		return book_date;
	}
	public void setBook_date(String book_date) {
		this.book_date = book_date;
	}
	public Receipt() {
		
		// TODO Auto-generated constructor stub
	}
	
	
}
