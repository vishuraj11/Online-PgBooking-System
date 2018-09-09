package com.pg.book;

public class Owner {

	private String owner_name;
	private String owner_pgname;
	private String owner_email;
	private String owner_mobile;
	private String owner_pass;
	private String owner_city;
	private String owner_state; 
	
	public String getOwner_name() {
		return owner_name;
	}




	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}




	public String getOwner_pgname() {
		return owner_pgname;
	}




	public void setOwner_pgname(String owner_pgname) {
		this.owner_pgname = owner_pgname;
	}




	public String getOwner_email() {
		return owner_email;
	}




	public void setOwner_email(String owner_email) {
		this.owner_email = owner_email;
	}




	public String getOwner_mobile() {
		return owner_mobile;
	}




	public void setOwner_mobile(String owner_mobile) {
		this.owner_mobile = owner_mobile;
	}




	public String getOwner_pass() {
		return owner_pass;
	}




	public void setOwner_pass(String owner_pass) {
		this.owner_pass = owner_pass;
	}




	public String getOwner_city() {
		return owner_city;
	}




	public void setOwner_city(String owner_city) {
		this.owner_city = owner_city;
	}




	public String getOwner_state() {
		return owner_state;
	}




	public void setOwner_state(String owner_state) {
		this.owner_state = owner_state;
	}


   Owner()
   {
	   
   }

	
	
	
	
	public Owner(String owner_name, String owner_pgname, String owner_mobile, String owner_email, String owner_pass,
			String owner_city, String owner_state) {
		this.owner_name=owner_name;
		this.owner_pgname=owner_pgname;
		this.owner_email=owner_email;
		this.owner_mobile=owner_mobile;
		this.owner_pass=owner_pass;
		this.owner_city=owner_city;
		this.owner_state=owner_state;
	}

}
