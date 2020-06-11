package com.cognis.app.web.rest;

import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.cognis.app.dao.UserDao;
import com.cognis.app.exception.NotAValidTokenException;
import com.cognis.app.exception.UnverifiedAccount;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.BaseModel;
import com.cognis.app.model.Link;
import com.cognis.app.model.RoleModel;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.model.UserModel;
import com.cognis.app.web.utils.Constants;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;
import com.cognis.app.web.utils.Utils;

@Path("userService")
public class UserService extends AppBaseService {

	public static Logger logger = Logger.getLogger(UserService.class);

	@GET
	@Path("/addUserdetailes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserDtls(@QueryParam("name") String name, @QueryParam("phone") String phone,
			@QueryParam("email") String email, @QueryParam("password") String password,
			@QueryParam("roleId") Integer roleId) throws Exception {

		RoleModel role = new RoleModel();
		role.setRoleId(roleId);
		UserModel usermodel = new UserModel();
		usermodel.setName(name);
		usermodel.setPhone(phone);
		usermodel.setEmail(email);
		usermodel.setPassword(password);
		usermodel.setRole(role);
		logger.info(" Inside addUserDtls() ");
		logger.info(" usermodel  " + usermodel.toString());
		String jsonString = null;
		UserDao addUserObj = (UserDao) AppBeanFactory.getBean("userDao");

		try {

			addUserObj.adduserdtlsJdbc(usermodel);
			usermodel.setStatus(true);
			usermodel.setMessage(SuccessCode.SUCCESSFULLY_ADDED_MSG.getSuccessCode());
			Link link = new Link(Constants.GET, "/rest/loginService ");
			usermodel.setNextLink(link);
			List<Link> lists = new ArrayList<Link>();
			lists.add(new Link(Constants.POST, "/rest/userService/validatetoken "));
			usermodel.setLinks(lists);
			jsonString = serializeToJSONString(usermodel);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(400).entity(serializeToJSONString(error)).build();
		}

		return Response.status(200).entity(jsonString).build();
	}

	@GET
	@Path("validatetoken")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateToken(@QueryParam("verificationCode") Integer verificationCode,
			@QueryParam("phone") String phone) throws Exception {
        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setVerificationCode(verificationCode);
		String jsonString = null;

		try {

			UserDao vlidateUserObj = (UserDao) AppBeanFactory.getBean("userDao");
			int validation = vlidateUserObj.validationJdbc(userModel);
			if (validation > 0) {
				UserModel responceResult = new UserModel();
				responceResult.setPhone(userModel.getPhone());
				responceResult.setStatus(true);
				responceResult.setMessage(SuccessCode.SUCCESSFULLY_VALIDTAE_MSG.getSuccessCode());
				jsonString = serializeToJSONString(responceResult);
			} else {
				throw new UnverifiedAccount();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(400).entity(serializeToJSONString(error)).build();
		}
		return Response.status(200).entity(jsonString).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUserDtls(@QueryParam("name") String name, @QueryParam("phone") String phone,
			@QueryParam("email") String email, @QueryParam("password") String password,
			@QueryParam("roleId") Integer roleId) throws Exception {

		RoleModel role = new RoleModel();
		role.setRoleId(roleId);
		UserModel usermodel = new UserModel();
		usermodel.setName(name);
		usermodel.setPhone(phone);
		usermodel.setEmail(email);
		usermodel.setPassword(password);
		usermodel.setRole(role);
		logger.info(" Inside addUserDtls() ");
		logger.info(" usermodel  " + usermodel.toString());
		System.out.println("addUserDtls() called....");

		UserDao addUserObj = (UserDao) AppBeanFactory.getBean("userDao");
		BaseModel bsmodel = new BaseModel();
		String jsonString = null;

		try {
			addUserObj.updateUserdtlsJdbc(usermodel);
			bsmodel.setStatus(true);
			bsmodel.setMessage(SuccessCode.SUCCESSFULLY_UPDATED_MSG.getSuccessCode());
			jsonString = serializeToJSONString(bsmodel);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(400).entity(serializeToJSONString(error)).build();
		}

		return Response.status(200).entity(jsonString).build();
	}

}
