package com.pg.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class ServiceLayer {
     
	private DAOLayer dao;
	

	private JavaMailSender mailSender;
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void sendEmail(String sender,String recipientAddress, String subject,String message)
	{
		//ModelAndView mav=new ModelAndView();
		System.out.println("from: " + sender);
		System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        
        SimpleMailMessage email=new SimpleMailMessage();
        //email.setFrom("vishuraj078@gmail.com");
        email.setFrom(sender);
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        
        
		mailSender.send(email);
	
		//mav.setViewName("/WEB-INF/Result.jsp");
		
		//return mav;
		
	}
	
	
	@Autowired
	public void setDao(DAOLayer dao) {
		this.dao = dao;
	}



	public int OwnerAddData(Owner owner) {
		
       int x=dao.addQuery(owner);
		return x;
	}



	public int OwnerLogin(Owner owner) {
		int x=dao.loginQuery(owner);
		
		return x;
	}



	public int addPgDeatils(PGDetails pg) {
		
		int x=dao.addpgQuery(pg);
		
		
		return x;
	}



	public List<PGDetails> areaList(String pg_city) {
		
		return dao.areaQuery(pg_city);
	}



	public List<PGDetails> pglist(String pg_list) {
				
		
		return dao.pglistQuery(pg_list);
	}



	public boolean addUser(UserRegister usr) {
		
		
		return dao.addUserDataBase(usr);
	}


	public boolean loginValidate(String email, String password) {
		
		
		return dao.userValidate(email,password);
	}



	public List<PGDetails> getFullDetails(int pg_id) {
		
		return dao.fullDetails(pg_id);
	}



	public boolean insertUserBook(BookUser book) {
		// TODO Auto-generated method stub
		System.out.println("service");
		return dao.insertUserBookDao(book);
	}



	public List<PGDetails> getpgdetails(int pg_id) {
		// TODO Auto-generated method stub
		return dao.getpgdetailsQuery(pg_id);
	}
	public List<Receipt> findData(int pg_id, String book_adhar) {
		// TODO Auto-generated method stub
		return dao.findDataQuery(pg_id,book_adhar);
	}
	public List<Owner> findPg() {
		// TODO Auto-generated method stub
		return dao.findPgQuery();
	}
	/*public int bookReceipt(List<Receipt> li) {
		// TODO Auto-generated method stub
		return dao.bookReceiptQuery(li);
	}*/
	
/*	public List<UserRegister> getDetailsToUpadateUserData(String email) {
		// TODO Auto-generated method stub
		return dao.getDetailsToUpadateUserDataFromDB(email);
	}*/
	

	//This is used to remove that PG --- Admin
		public boolean deletePg(String email) {
			// TODO Auto-generated method stub
			return dao.deletePgQuery(email);
		}
		
		// This is used to get all User details --- Admin
		public List<UserRegister> findUserDetails() {
			// TODO Auto-generated method stub
			return dao.findUserDetailsQuery();
		}
		
		//This is used to remove that User --- Admin
		public boolean deleteUser(String email) {
			// TODO Auto-generated method stub
			return dao.deleteUserDetailsQuery(email);
		}


	
}
