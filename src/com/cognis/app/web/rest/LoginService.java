package com.cognis.app.web.rest;



import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.cognis.app.dao.LoginDao;
import com.cognis.app.exception.UnverifiedAccount;
import com.cognis.app.exception.UserNotFountException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.Link;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.model.UserModel;
import com.cognis.app.web.utils.Constants;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;

@Path("loginService")
public class LoginService extends AppBaseService {

	public static Logger logger=Logger.getLogger(LoginService.class);
	
	@GET  
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@QueryParam("phone") String phone, @QueryParam("password") String password) throws Exception{
		
		   UserModel usermodel = new UserModel();
		   usermodel.setPhone(phone);
		   usermodel.setPassword(password);
		   logger.info("   usermodel    " + usermodel.toString());
		String jsonString=null;
		try{
		LoginDao loginObj = (LoginDao) AppBeanFactory.getBean("logindao");
		List<UserModel> userList=loginObj.loginJdbc(usermodel);
	
			if(userList.size()>0) {
				UserModel userResult=new UserModel();
				userResult=userList.get(0);
				if(("V").equals(userResult.getVerificationStatus())&&(userResult.getRole().getRoleId()==0)) {
					Link link=new Link(Constants.POST, "/customerService ");
					userResult.setNextLink(link);
	
					List<Link> list=new ArrayList<Link>();
					list.add(new Link(Constants.GET, "/customerService "));
					list.add(new Link(Constants.PUT, "/customerService "));
					list.add(new Link(Constants.POST, "/addressService "));
					list.add(new Link(Constants.GET, "/addressService "));
					list.add(new Link(Constants.GET, "/addressService "));
					
					userResult.setStatus(true); 
					userResult.setStatusCode(Constants.STATUSCODE);
					userResult.setMessage(SuccessCode.SUCCESSFULLY_LOGIN_MSG.getSuccessCode());
					loginObj.updateToken(userResult);
					jsonString=serializeToJSONString(userResult);
				}else if((userResult.getRole().getRoleId()==2)) {
					Link link=new Link(Constants.POST, "/vendorService");
					userResult.setNextLink(link);
					
					List<Link> list=new ArrayList<Link>();
					list.add(new Link(Constants.GET, "/vendorService "));
					list.add(new Link(Constants.PUT, "/vendorService "));
					list.add(new Link(Constants.POST, "/addressService "));
					list.add(new Link(Constants.GET, "/addressService "));
					list.add(new Link(Constants.PUT, "/addressService "));
					userResult.setLinks(list);
					
					userResult.setStatus(true); 
					userResult.setStatusCode(Constants.STATUSCODE);
					userResult.setMessage(SuccessCode.SUCCESSFULLY_LOGIN_MSG.getSuccessCode());
					loginObj.updateToken(userResult);
					jsonString=serializeToJSONString(userResult);
				}else if((userResult.getRole().getRoleId()==1)) {
					Link link=new Link(Constants.POST, "/servicePorviderService");
					userResult.setNextLink(link);
						
					List<Link> list=new ArrayList<Link>();
					list.add(new Link(Constants.GET, "/serviceProviderService "));
					list.add(new Link(Constants.PUT, "/serviceProviderService "));
					list.add(new Link(Constants.POST, "/addressService "));
					list.add(new Link(Constants.GET, "/addressService "));
					list.add(new Link(Constants.GET, "/addressService "));
					userResult.setLinks(list);
					
					userResult.setStatus(true); 
					userResult.setStatusCode(Constants.STATUSCODE);
					userResult.setMessage(SuccessCode.SUCCESSFULLY_LOGIN_MSG.getSuccessCode());
					loginObj.updateToken(userResult);
					jsonString=serializeToJSONString(userResult);
				}
				else{
					throw new UnverifiedAccount();
				}
			}else{
				throw new UserNotFountException();
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}
		return Response.status(200).entity(jsonString).build();
	}
	
}
