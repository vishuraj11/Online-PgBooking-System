package com.pg.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



@Repository
public class DAOLayer {

	private JdbcTemplate jd;
	
	
	@Autowired
	public void setJd(JdbcTemplate jd) {
		this.jd = jd;
	}



	public int addQuery(Owner owner) {
		
		String qury="insert into owner_detail values(?,?,?,?,?,?,?)";
		Object xx[]={owner.getOwner_name(),owner.getOwner_pgname(),owner.getOwner_mobile(),owner.getOwner_email(),owner.getOwner_pass(),owner.getOwner_city(),owner.getOwner_state()};
		int x=jd.update(qury, xx);
		return x;
	}



	public int loginQuery(Owner owner) {
		
		String qry="select * from owner_detail where owner_email=? and owner_pass=?";
		Object obj[]={owner.getOwner_email(),owner.getOwner_pass()};
	    	  RowMapper<String> rm = new 	
					RowMapper<String>() {
				@Override
				public String mapRow(ResultSet arg0, int arg1) throws SQLException {
					String owner_email=arg0.getString("owner_email");
					String owner_pass=arg0.getString("owner_pass");
					
					
					return owner_email+""+owner_pass;
				}
			};
			
			List<String> x=jd.query(qry, obj, rm);
			int xx=0;
			if(x.size()!=0)
			{
				 xx=1;
				 System.out.println("inside string");
			}
			
			
			
	     
		return xx;
	}


	 
	
	public int addpgQuery(PGDetails pg) {
		String qury="insert into pg_details(pg_name,pg_type,pg_avail,pg_facility,pg_share,pg_price,pg_address,pg_area,pg_city,pg_state,pg_lat,pg_lag,pg_image1,pg_image2,pg_image3) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object xx[]={pg.getPg_name(),pg.getPg_type(),pg.getPg_avail(),pg.getPg_facility(),pg.getPg_share(),pg.getPg_price(),pg.getPg_address(),pg.getPg_area(),pg.getPg_city(),pg.getPg_state(),pg.getPg_lat(),pg.getPg_lag(),pg.getPg_image1(),pg.getPg_image2(),pg.getPg_image3()};
		int x=jd.update(qury, xx);
		return x;
		
		
	}
	


	public List<PGDetails> areaQuery(String pg_city) {
		String qry="select distinct pg_area from pg_details where pg_city=?";
		Object x[]={pg_city};
		RowMapper<PGDetails> rm=new HelperClass();
		List<PGDetails> li=jd.query(qry, x, rm);
		
		return li;
	}



	public List<PGDetails> pglistQuery(String pg_list) {
		String qry="select pg_id,pg_name,pg_type,pg_share,pg_price,pg_address,pg_image1 from pg_details where pg_area=? or pg_city=?";
		Object x[]={pg_list,pg_list};
		RowMapper<PGDetails> rm = new 	
				RowMapper<PGDetails>() {
			@Override
			public PGDetails mapRow(ResultSet arg0, int arg1) throws SQLException {
				int pg_id=arg0.getInt("pg_id");
				String pg_name=arg0.getString("pg_name");
				String pg_type=arg0.getString("pg_type");
				int pg_share=arg0.getInt("pg_share");
				int pg_price=arg0.getInt("pg_price");
				String pg_address=arg0.getString("pg_address");
				String pg_image1=arg0.getString("pg_image1");
				
				PGDetails pg=new PGDetails();
				pg.setPg_id(pg_id);
				pg.setPg_name(pg_name);
				pg.setPg_type(pg_type);
				pg.setPg_share(pg_share);
				pg.setPg_price(pg_price);
				pg.setPg_address(pg_address);
				pg.setPg_image1(pg_image1);
				
				
				return pg;
			}
		};
		
		
		List<PGDetails> li=jd.query(qry, x, rm);
		return li;
	}



	public boolean addUserDataBase(UserRegister usr) {
		boolean bl=false;//email,
		String query="Insert into usertable(name,email,mobile,state,city,gender,password,adhar) values(?,?,?,?,?,?,?,?)";
		Object []xx= {usr.getName(),usr.getEmail(),usr.getMobile(),usr.getState(),usr.getCity(),usr.getGender(),usr.getPassword(),usr.getAdhar()};
		int row=jd.update(query, xx);
		if(row!=0)
		{
			bl=true;
		}
		return bl;
	}



	public boolean userValidate(String email, String password) {
		boolean bl=false;
		String query="select * from usertable where email=? or mobile=? and password=?";
		Object[] xx= {email,email,password};
		 RowMapper<UserRegister> rm = new 	
					RowMapper<UserRegister>() {
				@Override
				public UserRegister mapRow(ResultSet arg0, int arg1) throws SQLException {
					String email=arg0.getString("email");
					String mobile=arg0.getString("mobile");
					String password=arg0.getString("password");
					return new UserRegister(email, mobile,password);
				}
			};

		List<UserRegister> li=jd.query(query, xx,rm);
		
		if(li.size()!=0)
		{
			bl=true;
			System.out.println(" valid");
		}
		
		return bl;
	}



	public List<PGDetails> fullDetails(int pg_id) {
		String qry="select * from pg_details where pg_id=?";
		Object obj[]={pg_id};
		 RowMapper<PGDetails> rm = new 	
					RowMapper<PGDetails>() {
				@Override
				public PGDetails mapRow(ResultSet arg0, int arg1) throws SQLException {
					int pg_id=arg0.getInt("pg_id");
					String pg_name=arg0.getString("pg_name");
					String pg_type=arg0.getString("pg_type");
					int pg_avail=arg0.getInt("pg_avail");
					String pg_facility=arg0.getString("pg_facility");
					int pg_share=arg0.getInt("pg_share");
					int pg_price=arg0.getInt("pg_price");
					String pg_address=arg0.getString("pg_address");
					String pg_area=arg0.getString("pg_area");
					String pg_city=arg0.getString("pg_city");
					String pg_state=arg0.getString("pg_state");
					String pg_lat=arg0.getString("pg_lat");
					String pg_lag=arg0.getString("pg_lag");
					
					//String pg_image1=arg0.getString("C:\\Users\\Shanti\\Desktop\\Online_PGBooking_UI\\WebContent\\image\\"+"pg_image1");
					String pg_image1=arg0.getString("pg_image1");
					String pg_image2=arg0.getString("pg_image2");
					String pg_image3=arg0.getString("pg_image3");
					
					
					
					
					return new PGDetails(pg_id,pg_name,pg_type,pg_avail, pg_facility,pg_share,
							pg_price, pg_address,pg_area, pg_city, pg_state,pg_lat,pg_lag,pg_image1,pg_image2,pg_image3);
				}
			};
			
			
			List<PGDetails> li=jd.query(qry, obj,rm);
		return li;
	}



	public boolean insertUserBookDao(BookUser Book) {
		   System.out.println("dao");
		boolean bl=false;
	   String qry="insert into bookuser(book_name,book_mobile,book_date,book_adhar,email) values(?,?,?,?,?)";
	   Object xx[]={Book.getBook_name(),Book.getBook_mobile(),Book.getBook_date(),Book.getBook_adhar(),Book.getEmail()};
	   System.out.println(" condition");
	   int x=jd.update(qry, xx);
	   System.out.println(" condition");
	   if(x!=0)
	   {
		   System.out.println("if condition");
		   bl=true;
	   }
		
		return bl;
	}



	public List<PGDetails> getpgdetailsQuery(int pg_id) {
		
		return fullDetails(pg_id);
	}

	

	public boolean deletePgQuery(String email) {
		boolean bl=false;
		String qury="Delete From owner_detail where owner_email=?";
		Object[] xx={email};
		  int row=jd.update(qury, xx);
		  if(row!=0)
		  {
			  bl=true;
		  }
		return bl;
	}



	public List<UserRegister> findUserDetailsQuery() {
		// TODO Auto-generated method stub
		String qry="select * from usertable";
		RowMapper<UserRegister> rm = new 	
				RowMapper<UserRegister>() {
			@Override
			public UserRegister mapRow(ResultSet arg0, int arg1) throws SQLException {
				//name,email,mobile,state,city,gender,password,adhar;
				//String id=arg0.getString("userid");
				String name=arg0.getString("name");
				String mobile=arg0.getString("mobile");
				String state=arg0.getString("state");
				String city=arg0.getString("city");
				String gender=arg0.getString("gender");
				String password=arg0.getString("password");
				String adhar=arg0.getString("adhar");
				String email=arg0.getString("email");
				
				UserRegister ur=new UserRegister(name, email, mobile, state, city, gender, password, adhar);

				return ur;
			}
		};
		
		List<UserRegister> li=jd.query(qry, rm);
		return li;

	}



	public boolean deleteUserDetailsQuery(String email) {
		// TODO Auto-generated method stub
		boolean bl=false;
		String qury="Delete From usertable where email=?";
		Object[] xx={email};
		  int row=jd.update(qury, xx);
		  if(row!=0)
		  {
			  bl=true;
		  }
		return bl;
	}


	
	
	


	public List<Receipt> findDataQuery(int pg_id, String book_adhar) {
		String qry="select dictinct pg_id,pg_name,pg_share,pg_price,pg_address,book_name,book_mobile,book_date,email from pg_details p,bookuser b where p.pg_id=? and b.book_adhar=?";
		//Receipt re=null;
		
		Object x[]={pg_id,book_adhar};
		RowMapper<Receipt> rm = new 	
				RowMapper<Receipt>() {
			@Override
			public Receipt mapRow(ResultSet arg0, int arg1) throws SQLException {
				int pg_id=arg0.getInt("pg_id");
				String pg_name=arg0.getString("pg_name");
				
				int pg_share=arg0.getInt("pg_share");
				int pg_price=arg0.getInt("pg_price");
				String pg_address=arg0.getString("pg_address");
				
				String book_name=arg0.getString("book_name");
				String book_mobile=arg0.getString("book_mobile");
				String book_date=arg0.getString("book_date");
				String email=arg0.getString("email");
				
				
				Receipt rc=new Receipt();
				rc.setPg_id(pg_id);
				rc.setPg_name(pg_name);
				
				rc.setPg_share(pg_share);
				rc.setPg_price(pg_price);
				rc.setPg_address(pg_address);
				
				
				rc.setBook_name(book_name);
				rc.setBook_mobile(book_mobile);
				rc.setBook_date(book_date);
				rc.setEmail(email);
				
				return rc;
			}
		};
		
		
		List<Receipt> li=jd.query(qry, x, rm);
		return li;
		
	
	}



	public List<Owner> findPgQuery() {
		String qry="select * from owner_detail";
		RowMapper<Owner> rm = new 	
				RowMapper<Owner>() {
			@Override
			public Owner mapRow(ResultSet arg0, int arg1) throws SQLException {
				String owner_name=arg0.getString("owner_name");
				String owner_pgname=arg0.getString("owner_pgname");
				String owner_email=arg0.getString("owner_email");
				String owner_mobile=arg0.getString("owner_mobile");
				String owner_pass=arg0.getString("owner_pass");
				String owner_city=arg0.getString("owner_city");
				String owner_state=arg0.getString("owner_state");
				
				Owner on=new Owner();
				on.setOwner_name(owner_name);
				on.setOwner_pgname(owner_pgname);
				on.setOwner_email(owner_email);
				on.setOwner_mobile(owner_mobile);
				on.setOwner_pass(owner_pass);
				on.setOwner_city(owner_city);
				on.setOwner_state(owner_state);
				
				return on;
			}
		};
		
		
		List<Owner> li=jd.query(qry, rm);
		return li;
		
	}



	public int bookReceiptQuery(Receipt re) {
		int bl=0;
		   String qry="insert into bookdetails values(?,?,?,?,?,?,?,?)";
		   
		   Object obj[]={re.getPg_id(),re.getPg_name(),re.getPg_share(),re.getPg_price(),re.getPg_address(),re.getBook_mobile(),re.getBook_date(),re.getEmail()};
		   bl=jd.update(qry, obj);
		   
		 //  int x=jd.update(qry, obj);
		   
		   if(bl!=0)
		   {
			   bl=1;
		   }
			
			return bl;	}






/*	public List<UserRegister> getDetailsToUpadateUserDataFromDB(String email) {
		// TODO Auto-generated method stub
		String query="select * from usertable where email="+email;
		
		RowMapper< UserRegister> rowmap = new RowMapper<UserRegister>() {
			
			@Override
			public UserRegister mapRow(ResultSet arg0, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				String name =arg0.getString("name");
				String email =arg0.getString("email");
				String state =arg0.getString("state");
				String city =arg0.getString("city");
				String mobile =arg0.getString("mobile");
				String adhar =arg0.getString("adhar");
				UserRegister ur = new UserRegister(name, email, mobile, state, city, adhar);
				return ur;
			}
		};
		
		List<UserRegister> li=jd.query(query, rowmap);

		return li;
	}*/



	
	

}
