package com.pg.book;

public class BookUser {
	private String book_name,book_mobile,book_date,book_adhar,email;
	private int book_id;
	
	
	
	public BookUser(String book_name, String book_mobile, String book_date, String book_adhar, int book_id) {
		
		this.book_name = book_name;
		this.book_mobile = book_mobile;
		this.book_date = book_date;
		this.book_adhar = book_adhar;
		this.book_id = book_id;
	}
	
public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

public BookUser(String book_name, String book_mobile, String book_date, String book_adhar, String email) {
		
		this.book_name = book_name;
		this.book_mobile = book_mobile;
		this.book_date = book_date;
		this.book_adhar = book_adhar;
		this.email = email;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
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

	public String getBook_adhar() {
		return book_adhar;
	}

	public void setBook_adhar(String book_adhar) {
		this.book_adhar = book_adhar;
	}

	public BookUser(String book_name, String book_mobile, String book_date, String book_adhar) {
		
		this.book_name = book_name;
		this.book_mobile = book_mobile;
		this.book_date = book_date;
		this.book_adhar = book_adhar;
	}

	public BookUser() {
		// TODO Auto-generated constructor stub
	}
	

}
