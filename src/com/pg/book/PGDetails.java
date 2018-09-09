package com.pg.book;

public class PGDetails {
    private int pg_id;
	private String pg_name;
	private String pg_type;
	private int pg_avail;
	private String pg_facility;
	private int pg_share;
	private int pg_price;
	private String pg_address;
	private String pg_area;
	private String pg_city;
	private String pg_state;
	private String pg_lat;
	private String pg_lag;
	private String pg_image1;
	private String pg_image2;
	private String pg_image3;
	
	
	
	




	public void setPg_id(int pg_id) {
		this.pg_id = pg_id;
	}




	public int getPg_id() {
		return pg_id;
	}




	public PGDetails(int pg_id,String pg_name, String pg_type, int pg_avail, String pg_facility, int pg_share,
			int pg_price, String pg_address, String pg_area, String pg_city, String pg_state,String pg_lat,String pg_lag, String pg_image1,String pg_image2,String pg_image3) {
		this.pg_name=pg_name;
		this.pg_type=pg_type;
		this.pg_avail=pg_avail;
		this.pg_facility=pg_facility;
		this.pg_share=pg_share;
		this.pg_price=pg_price;
		this.pg_address=pg_address;
		this.pg_area=pg_area;
		this.pg_city=pg_city;
		this.pg_state=pg_state;
		this.pg_lat=pg_lat;
		this.pg_lag=pg_lag;
		this.pg_image1=pg_image1;
		this.pg_image2=pg_image2;
		this.pg_image3=pg_image3;
		this.pg_id=pg_id;
	
	}
	




	public PGDetails(String pg_name, String pg_type, int pg_avail, String pg_facility, int pg_share, int pg_price,
			String pg_address, String pg_area, String pg_city, String pg_state,String pg_lat,String pg_lag, String pg_image1, String pg_image2,
			String pg_image3) {
		super();
		this.pg_name = pg_name;
		this.pg_type = pg_type;
		this.pg_avail = pg_avail;
		this.pg_facility = pg_facility;
		this.pg_share = pg_share;
		this.pg_price = pg_price;
		this.pg_address = pg_address;
		this.pg_area = pg_area;
		this.pg_city = pg_city;
		this.pg_state = pg_state;
		this.pg_lat=pg_lat;
		this.pg_lag=pg_lag;
		this.pg_image1 = pg_image1;
		this.pg_image2 = pg_image2;
		this.pg_image3 = pg_image3;
	}




	public String getPg_lat() {
		return pg_lat;
	}




	public void setPg_lat(String pg_lat) {
		this.pg_lat = pg_lat;
	}




	public String getPg_lag() {
		return pg_lag;
	}




	public void setPg_lag(String pg_lag) {
		this.pg_lag = pg_lag;
	}




	public PGDetails() {
		// TODO Auto-generated constructor stub
	}




	public String getPg_name() {
		return pg_name;
	}




	public void setPg_name(String pg_name) {
		this.pg_name = pg_name;
	}




	public String getPg_type() {
		return pg_type;
	}




	public void setPg_type(String pg_type) {
		this.pg_type = pg_type;
	}




	public int getPg_avail() {
		return pg_avail;
	}




	public void setPg_avail(int pg_avail) {
		this.pg_avail = pg_avail;
	}




	public String getPg_facility() {
		return pg_facility;
	}




	public void setPg_facility(String pg_facility) {
		this.pg_facility = pg_facility;
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




	public String getPg_area() {
		return pg_area;
	}




	public void setPg_area(String pg_area) {
		this.pg_area = pg_area;
	}




	public String getPg_city() {
		return pg_city;
	}




	public void setPg_city(String pg_city) {
		this.pg_city = pg_city;
	}




	public String getPg_state() {
		return pg_state;
	}




	public void setPg_state(String pg_state) {
		this.pg_state = pg_state;
	}




	




	public String getPg_image1() {
		return pg_image1;
	}




	public void setPg_image1(String pg_image1) {
		this.pg_image1 = pg_image1;
	}




	public String getPg_image2() {
		return pg_image2;
	}




	public void setPg_image2(String pg_image2) {
		this.pg_image2 = pg_image2;
	}




	public String getPg_image3() {
		return pg_image3;
	}




	public void setPg_image3(String pg_image3) {
		this.pg_image3 = pg_image3;
	}




	public PGDetails(String pg_city) {
		
		this.pg_city = pg_city;
	}
       
	
	
	
	
}
