package com.pg.book;

public class UserRegister {
	
	private String name,email,mobile,state,city,gender,password,adhar;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdhar() {
		return adhar;
	}

	public void setAdhar(String adhar) {
		this.adhar = adhar;
	}

	public UserRegister(String name, String email, String mobile, String state, String city, String gender,
			String password, String adhar) {
		
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.state = state;
		this.city = city;
		this.gender = gender;
		this.password = password;
		this.adhar = adhar;
	}

	public UserRegister(String email, String mobile, String password) {
	
		this.email = email;
		this.mobile = mobile;
		this.password = password;
	}

	public UserRegister(String name, String email, String mobile, String state, String city, String adhar) {
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.state = state;
		this.city = city;
		this.adhar = adhar;
	}
	
	
	
	

}
