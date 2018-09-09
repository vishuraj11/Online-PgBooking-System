package com.pg.book;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sun.rmi.log.LogOutputStream;


//@SessionAttributes("uname")

@Controller
public class MainController {

		 private ServiceLayer s1;
		 private PGDetails pg;
		 final String sender="vishuraj078@gmail.com";
		 final String subject="pgbook";
		 String message="payment successfull";
		
		 @Autowired
		public void setS1(ServiceLayer s1) {
			this.s1 = s1;
		}

//	################	 PG Registration Method  ######################
		 
		@RequestMapping("Owner")
		public ModelAndView ownerRegister(@RequestParam("reg_pg_name") String owner_name,@RequestParam("reg_pg_pgname") String owner_pgname,@RequestParam("reg_pg_mobile") String owner_mobile,@RequestParam("reg_pg_email") String owner_email,@RequestParam("reg_pg_pass") String owner_pass,@RequestParam("reg_pg_city") String owner_city,@RequestParam("reg_pg_state") String owner_state)
		{
			
			Owner owner=new Owner(owner_name,owner_pgname,owner_mobile,owner_email,owner_pass,owner_city,owner_state);
			int x=s1.OwnerAddData(owner);
			ModelAndView mv=new ModelAndView();
			message="Registration Successful"+"\n"+"Your Id Is: "+owner_email+"\nPassword is: "+owner_pass;
			if(x!=0)
			{
				mv.addObject("add","data is inserted");
				s1.sendEmail(sender,owner_email, subject, message);
				mv.setViewName("/index.jsp");	
			}
			else
			{
				mv.setViewName("/WEB-INF/error.html");
			}
			return mv;
			
		}// end ownerRegister() Method

		
//	################## PG Provider Login Method #####################
		@RequestMapping("OwnerLogin")
		public ModelAndView ownerLogin(@RequestParam("owner_email") String owner_email,@RequestParam("owner_pass") String owner_pass,HttpSession session)
		{
			ModelAndView mv=new ModelAndView();
			Owner owner=new Owner();
			owner.setOwner_email(owner_email);
			owner.setOwner_pass(owner_pass);
			int x=s1.OwnerLogin(owner);
			if(x!=0)
			{
				 session.setAttribute("uname", owner_email); // put user name in session 
				 session.setAttribute("upas", owner_pass);
				mv.addObject("login","login successfully");
				mv.setViewName("/pgDetails.jsp");	
			}
			else
			{
				mv.setViewName("/WEB-INF/error.jsp");
			}
			
			return mv;
		} // end ownerLogin() method
		

 //	############## Image Upload Method + Other PG Rooms  Details
		
		@RequestMapping(value="pgDeatils",method=RequestMethod.POST)
		public ModelAndView addPgDetails(@RequestParam("pg_name") String pg_name,@RequestParam("pg_type") String pg_type,@RequestParam("pg_avail") int pg_avail,@RequestParam("pg_facility") String pg_facility,@RequestParam("pg_share") int pg_share,@RequestParam("pg_price") int pg_price,@RequestParam("pg_address") String pg_address,@RequestParam("pg_area") String pg_area,@RequestParam("pg_city") String pg_city,@RequestParam("pg_state") String pg_state,@RequestParam("pg_lat") String pg_lat,@RequestParam("pg_lag") String pg_lag,@RequestParam("pg_image1") CommonsMultipartFile file,@RequestParam("pg_image2") CommonsMultipartFile file2,@RequestParam("pg_image3") CommonsMultipartFile file3, HttpSession session)throws Exception
		{
			String uploadPath = "C:\\Users\\rajendra\\Desktop\\onlinepgbooking\\Online_PGBooking\\WebContent\\image";
			String pg_image1=file.getOriginalFilename();
			String pg_image2=file2.getOriginalFilename();
			String pg_image3=file3.getOriginalFilename();
			System.out.println(pg_image1);
			byte[] bytes = file.getBytes();
			byte[] bytes2 = file2.getBytes();
			byte[] bytes3= file3.getBytes();
			BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(uploadPath + File.separator + pg_image1)));
			stream.write(bytes);
			BufferedOutputStream stream2 =new BufferedOutputStream(new FileOutputStream(new File(uploadPath + File.separator + pg_image2)));
			stream2.write(bytes2);
			BufferedOutputStream stream3 =new BufferedOutputStream(new FileOutputStream(new File(uploadPath + File.separator + pg_image3)));
			stream3.write(bytes3);
			stream.flush();
			stream.close();
			stream2.close();
			stream3.close();
			
			ModelAndView mv=new ModelAndView();
			PGDetails pg = new PGDetails(pg_name,pg_type,pg_avail,pg_facility,pg_share,pg_price,pg_address,pg_area,pg_city,pg_state,pg_lat,pg_lag,pg_image1,pg_image2,pg_image3);
			int x=s1.addPgDeatils(pg);
			if(x!=0)
			{			
				mv.addObject("add","Insert Data Successfully");
				mv.setViewName("/pgDetails.jsp");	
			}
			else
			{
				mv.setViewName("/WEB-INF/error.jsp");
			}
			
			
			
			return mv;
		}
		
	// If Use Click on Delhi Page-- Than leand up here -- 1st Page "index.jsp"
		
		@RequestMapping("/img_delhi")
		public ModelAndView getData(@RequestParam("pg_city") String pg_city)
		{
			ModelAndView mv=new ModelAndView();
			System.out.println(pg_city);
			List<PGDetails> li=s1.areaList(pg_city);
			if(li.size()!=0)
			{
				mv.addObject("arealist",li);
				mv.setViewName("/arealist.jsp");	
			}
			else
			{
				mv.setViewName("/WEB-INF/error.jsp");
				
			}
			return mv;
		}
		
		
	// If Use Click on Delhi Page-- Than leand up here -- 2st Page (list of area) "arealist.jsp"
		@RequestMapping("pgList")
		public ModelAndView getPgList(@RequestParam("arealist") String pg_list)
		{
			
			ModelAndView mv=new ModelAndView();
			
			
			List<PGDetails> li=s1.pglist(pg_list);
			if(li.size()!=0)
			{
				mv.addObject("pglist",li);
				mv.setViewName("/pglist.jsp");	
			}
			else
			{
				mv.setViewName("/WEB-INF/error.jsp");
				
			}
			return mv;
			
			
		}// end method
		
		
// ################### Searching Method Function ########################		
		@RequestMapping("menu_search")
		public ModelAndView getPgList_ByMenuSearch( @RequestParam("search_area") String area_name){
			ModelAndView mv = new ModelAndView();
			//MainController mc = new MainController();
			List<PGDetails> li=s1.pglist(area_name);
			if(li.size()!=0)
			{
				mv.addObject("pglist",li);
				mv.setViewName("/pglist.jsp");	
			}
			else
			{
				mv.setViewName("/WEB-INF/error.jsp");
				
			}
			return mv;
			
			
			//return mc.getPgList(area_name);
		}
		
		
		
// Method For User Registration 	 	
		@RequestMapping("form_reg_user")
		public ModelAndView userRegistration(@RequestParam("reg_user_name") String name,@RequestParam("reg_user_email") String email,@RequestParam("reg_user_mobile") String mobile,@RequestParam("reg_user_state") String state, @RequestParam("reg_user_city") String city, @RequestParam("reg_user_gender") String gender,@RequestParam("reg_user_pass") String password,@RequestParam("reg_user_adhar") String adhar)
		{
			//System.out.println("hel");
			ModelAndView mav=new ModelAndView();
			UserRegister usr=new UserRegister(name,email,mobile,state,city,gender,password,adhar);
			//boolean bl=sl.addUser(usr.getName(),usr.getEmail(),usr.getMobile(),usr.getCity(),usr.getGender(),usr.getPassword(),usr.getAdhar());
			message="Registration Successful"+"\n"+"Your Id Is: "+email+"\nPassword is: "+password;
			
				
			
			boolean bl=s1.addUser(usr);
			if(bl)
			{
				s1.sendEmail(sender,email, subject, message);
				  mav.addObject("Key","insert sucssesfull");
				  mav.setViewName("/index.jsp");
			
			}
			else
			{
				
				 mav.setViewName("/WEB-INF/error.jsp");
				
			}
			
			return mav; 
							
		}// end userRegistration() method
		
	// User login methods	
		@RequestMapping("user_login_process")	
		public ModelAndView userLogin(@RequestParam("formInputUSERid") String email, @RequestParam("formInputUSERpass") String password, HttpSession session,HttpServletResponse response,HttpServletRequest request) throws IOException	
		{
			
			String id=request.getParameter("pg_id");
			String book_adhar=request.getParameter("pg_adhar");
			int pg_id=Integer.parseInt(id);
			System.out.println(pg_id+""+book_adhar);
			ModelAndView mav=new ModelAndView();
			boolean bl=s1.loginValidate(email,password);
			String pagename= (String)session.getAttribute("currentpage");
			if(bl)
			{
				  session.setAttribute("uname", email); // put user name in session 
				  session.setAttribute("upas", password);// put password in session 
				  
				  mav.addObject("uname", email);// put user name in scope variavle(notice board)
				  mav.addObject("upas", password);// put password name in variable(notice board)
				  
				  mav.addObject("Key","email");
				  
				/*  // calling db method to get al user details
				  List<UserRegister> l = s1.getDetailsToUpadateUserData(email);
				  
				  // Send it to notice bord so that we can use it
				  mav.addObject("userprofiledetais", l);
				  //To display user profile page we have user information 
				  mav.setViewName("/userprofile.jsp");*/
				 // mav.setViewName("/index.jsp");
				  if(session.getAttribute("uname")!=null && pg_id != 0 && book_adhar !=null)
					{
					    mav.addObject("id",pg_id);
					    mav.addObject("adhar",book_adhar);
					    mav.setViewName("/payment.jsp");	
					}
				  else
					  if(session.getAttribute("uname")!=null && pg_id == 0)
				  {
						  System.out.println("else");
						  mav.setViewName("/index.jsp");
				  }
				  
				 /*// String pagename= (String)session.getAttribute("currentpage");
					if(pagename == null)
					{
					   // pagename = "login.jsp";
					    pagename = (String)session.getAttribute("currentpage");
				     }
					//response.sendRedirect(pagename);
					mav.setViewName(pagename);	*/		
			}
			else
			{
				
				 mav.setViewName("/WEB-INF/error.jsp");
				
			}
			
			return mav;
		}// userLogin method ends
		
	 @RequestMapping("/logout")
	 public ModelAndView logOut(HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 ModelAndView mav=new ModelAndView();
		 session.invalidate();
		 mav.setViewName("/index.jsp");
		 return mav;
	 }
		
		
		
	// pgDetails.jsp page 
		@RequestMapping("PgMoreDetails")
		public ModelAndView getPage(@RequestParam("moredetails") String moredetails,@RequestParam("pg_id") int pg_id)
		{
			List<PGDetails>  li=new ArrayList<PGDetails>();
			ModelAndView mv=new ModelAndView();
			
			if(moredetails.equals("More Details"))
			{
			   li=s1.getFullDetails(pg_id); 	
				if(li.size()!=0)
				{
					 mv.addObject("fulllist",li);
					  mv.setViewName("/fullDetails.jsp");
				}
				else
				{
					mv.setViewName("/WEB-INF/error.jsp");
				}
				
			}
			else
			if(moredetails.equals("Book Now"))	
			{
				
			   li=s1.getpgdetails(pg_id);
			   int payable=0;
			   mv.addObject("list", li);
			   for(PGDetails price : li)
			   {
				  int pric= price.getPg_price();
				   payable=(pric*20)/100;
			   }
			   mv.addObject("list2", payable);
			   
				mv.setViewName("/userbook.jsp");
			}
			else
			{
				mv.setViewName("/WEB-INF/error.jsp");
			}
			
			return mv;
		}
		
	
		
	// Userbook.jsp page linked -- 
		// we have to check session here only
		
		@RequestMapping("/book_user")	
		public ModelAndView userLogin(@RequestParam("pg_id") int pg_id, @RequestParam("book_name") String book_name, @RequestParam("book_mobile") String book_mobile, @RequestParam("book_date") String book_date, @RequestParam("book_adhar") String book_adhar,@RequestParam("book_email") String email ,HttpSession session,HttpServletResponse response)	
		{
			System.out.println(pg_id+""+book_name);
			List<PGDetails>  li=new ArrayList<PGDetails>();
			ModelAndView mav=new ModelAndView();
			BookUser book=new BookUser(book_name,book_mobile,book_date,book_adhar,email);
			boolean bl=s1.insertUserBook(book);
			if(bl)
			{
				session.setAttribute("currentpage","userbook.jsp");
				// Checking the session value
				if(session.getAttribute("uname")!=null && pg_id != 0 && book_adhar !=null){
				  mav.addObject("id",pg_id);
				  mav.addObject("adhar",book_adhar);
				  mav.setViewName("/payment.jsp");
				}
				else
				if(session.getAttribute("uname")==null && pg_id != 0)
				{
//					//session.setAttribute("currentpage","payment.jsp");
//					String pagename= (String)session.getAttribute("currentpage");
//					if(pagename == null)
//					{
//					    pagename = "login.jsp";
//					   // pagename = (String)session.getAttribute("currentpage");
//					}
//					try {
//						response.sendRedirect(pagename);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					mav.addObject("id",pg_id);
					  mav.addObject("adhar",book_adhar);
				//	mav.addObject("re-Key","Login First"); // we have to show it
					mav.setViewName("/login.jsp");
					
				}	
			}
			else
			{
				 mav.setViewName("/WEB-INF/error.jsp");
			}
			return mav;
		}

	
		
		@RequestMapping("/userprofile")
		public ModelAndView ownerRegister( HttpSession session){
			ModelAndView mv = new ModelAndView();
			// Checking the session value
			if(session.getAttribute("uname")!=null && session.getAttribute("upas")!=null){
				mv.setViewName("userprofile.jsp");
			}
			else{
				mv.setViewName("/WEB-INF/error.jsp");
			}
			
			return mv;
		}
		@RequestMapping("/loginheader")
		public ModelAndView loginheader()
		{
			ModelAndView mv = new ModelAndView();
			  mv.addObject("id",0);
			  mv.addObject("adhar","null");
		//	mav.addObject("re-Key","Login First"); // we have to show it
			mv.setViewName("/login.jsp");
			return mv;
		}
		
		//payment request map
		
		@RequestMapping("/payment")
		public ModelAndView payment(@RequestParam("pg_id") int pg_id, @RequestParam("book_adhar") String book_adhar)
		{
			List<Receipt>  li=new ArrayList<Receipt>();
			ModelAndView mv=new ModelAndView();
			int payable=0;
			//meaning full massege
			   li=s1.findData(pg_id,book_adhar); 	
				if(li.size()!=0)
				{
					 mv.addObject("receipt", li);
					 for(Receipt price : li)
					   {
						 
						  int pric= price.getPg_price();
						   payable=(pric*20)/100;
					   }
					 for(Receipt em : li)
					   {
						 message="Dear,\n"+ em.getBook_name()+" Your PG has been booked Successfully \n"+ " Your PG Information is " +em.getPg_id()+" "+ em.getPg_name()+" " +em.getPg_address()+"\n Mobile :"+em.getBook_mobile()+ " and your paid amount is "+payable+" and";
						  String email= em.getEmail();
						  System.out.println(email);
						  s1.sendEmail(sender,email, subject, message);
						   
					   }
					 //int x=s1.bookReceipt(li);
					   mv.addObject("list2", payable);
					 
					 
					  mv.setViewName("/receipt.jsp");
				}
				else
				{
					mv.setViewName("/WEB-INF/error.jsp");
				}
				return mv;
				
			}
		
		
		
		
		// Admin Page method to show pg Details
				// on button click -- get all pg Details from DB
				@RequestMapping("/pgdetail")
				public ModelAndView adminShowDetail( HttpSession session )
				{
					ModelAndView mv=new ModelAndView();
					List<Owner> li=new ArrayList<Owner>();
					
					// Session 
					
					String session_value = (String) session.getAttribute("admin_session");
					
					if( session.getAttribute("admin_session")!=null && session_value.equals("qwerty") ){
					
							li=s1.findPg();
							if(li.size()!=0)
							{
								mv.addObject("pgowner", li);
								for(Owner ow:li)
								{
									System.out.println(ow.getOwner_email());
								}
								mv.setViewName("/admin.jsp");
							}
							else
							{
							mv.setViewName("/WEB-INF/error.jsp");
							}
					
					}
					else
					{
					mv.setViewName("/WEB-INF/error.jsp");
					}
					return mv;
				}

//			Delete Method for Deleting PG Owner Records (Block)
				@RequestMapping("/delete")
				public ModelAndView DeletePgOwner(@RequestParam("email") String email)
				{
					ModelAndView mv=new ModelAndView();
					boolean bl=s1.deletePg(email);
					if(bl)
					{
						mv.setViewName("/admin.jsp");
					}
					else
					{
						mv.setViewName("/WEB-INF/error.jsp");
					}
					return mv;
				}
				
				
		// ============================================================================================
				
//				Display Method for All Users Records (Block) --- Admin
				
				@RequestMapping("/userdetail")
				public ModelAndView adminShowUserDetail( HttpSession session)
				{
					ModelAndView mv=new ModelAndView();
					List<UserRegister> li=new ArrayList<UserRegister>();
					
					String session_value = (String) session.getAttribute("admin_session");
					
					if( session.getAttribute("admin_session")!=null && session_value.equals("qwerty") ){
						
						li=s1.findUserDetails(); // function call 
						
						if(li.size()!=0)
						{
							mv.addObject("userdetails", li);
							for(UserRegister ur:li)
							{
								System.out.println(ur.getEmail());
							}
							mv.setViewName("/admin.jsp");
						}
						else
						{
						mv.setViewName("/WEB-INF/error.jsp");
						}
						
					}// sesssion if close
					else{
						mv.setViewName("/WEB-INF/error.jsp");
					}
					
					
					return mv;
				}

//			Delete Method for Deleting PG Owner Records (Block) --- Admin
				@RequestMapping("/delet")
				public ModelAndView DeleteUserAdmin(@RequestParam("email") String email)
				{
					ModelAndView mv=new ModelAndView();
					boolean bl=s1.deleteUser(email);
					if(bl)
					{
						mv.setViewName("/admin.jsp");
					}
					else
					{
						mv.setViewName("/WEB-INF/error.jsp");
					}
					return mv;
				}
				
				
				 //gotoadminpage
				@RequestMapping("/gotoadminpage")
				public ModelAndView adminLogin(@RequestParam("adminname") String adminname, @RequestParam("adminpass") String adminpass, HttpSession session ){
					ModelAndView mv = new ModelAndView();
					
					if(adminname.equals("admin") && adminpass.equals("admin")){
						session.setAttribute("admin_session", "qwerty");
						mv.setViewName("admin.jsp");
					}
					else{
						mv.setViewName("/WEB-INF/error.jsp");
					}
					
					return mv;
				}
				
				
		
} // main class end

