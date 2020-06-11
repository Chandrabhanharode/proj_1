package com.cognis.app.web.rest;

import java.util.List;

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

import com.cognis.app.dao.CustomerDao;
import com.cognis.app.exception.NotAValidTokenException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.AddressModel;
import com.cognis.app.model.BaseModel;
import com.cognis.app.model.CustomerModel;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;
import com.cognis.app.web.utils.Utils;

@Path("customerService")
public class CustomerService extends AppBaseService{

	public static Logger logger=Logger.getLogger(CustomerService.class);
	
	@GET
	@Path("/addUserDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCustomerDtls(@QueryParam("address1") String address1, @QueryParam("address2") String address2,
			@QueryParam("userId") Integer userId, @QueryParam("city") String city,
			@QueryParam("location") String location, @QueryParam("postalcode") String postalcode,
			@QueryParam("apiToken") String apiToken,@QueryParam("customurId") Integer customurId) {
		
		logger.info(" Inside addCustomerDtls() ");	
		AddressModel addressModel = new AddressModel();
		addressModel.setAddress1(address1);
		addressModel.setAddress2(address2);
		addressModel.setUserId(userId);
		addressModel.setCity(city);
		addressModel.setPostalcode(postalcode);
		CustomerModel customermodel = new CustomerModel();
		customermodel.setCustomurId(customurId);
		customermodel.setAddress(addressModel);
		customermodel.setUserId(userId);
		customermodel.setApiToken(apiToken);
		logger.info("customermodel   "+  customermodel.toString());
		String jsonString=null;
		
		try {
			if(!Utils.isValidToken(customermodel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			CustomerDao addcustomerObj = (CustomerDao) AppBeanFactory.getBean("customerdao");
			addcustomerObj.addcustomerJdbc(customermodel);
			customermodel.setStatus(true); 
			customermodel.setMessage(SuccessCode.SUCCESSFULLY_ADDED_MSG.getSuccessCode());
			jsonString=serializeToJSONString(customermodel);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}
		
		return Response.status(200).entity(jsonString).build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerDtls(@QueryParam("apiToken") String apiToken,@QueryParam("userId") Integer userId) {
		
		logger.info("addUserDtls() called....");		
		CustomerModel customermodel = new CustomerModel();
		customermodel.setUserId(userId);
		customermodel.setApiToken(apiToken);
		String jsonString=null;
		
		try {
			if(!Utils.isValidToken(customermodel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			CustomerDao getcustomerObj = (CustomerDao) AppBeanFactory.getBean("customerdao");
			List<CustomerModel> customerDtls=getcustomerObj.getCustomerDtls(customermodel);
			customermodel=customerDtls.get(0);
			customermodel.setStatus(true); 
			customermodel.setMessage(SuccessCode.SUCCESSFULLY_GET_CUSTOMER_MSG.getSuccessCode());
			jsonString=serializeToJSONString(customermodel);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}
		
		return Response.status(200).entity(jsonString).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@QueryParam("address1") String address1, @QueryParam("address2") String address2,
			@QueryParam("userId") Integer userId, @QueryParam("city") String city,
			@QueryParam("location") String location, @QueryParam("postalcode") String postalcode,
			@QueryParam("apiToken") String apiToken,@QueryParam("customurId") Integer customurId) {
		
		logger.info(" Inside updateCustomer() ");	
		AddressModel addressModel = new AddressModel();
		addressModel.setAddress1(address1);
		addressModel.setAddress2(address2);
		addressModel.setUserId(userId);
		addressModel.setCity(city);
		addressModel.setPostalcode(postalcode);
		CustomerModel customermodel = new CustomerModel();
		customermodel.setCustomurId(customurId);
		customermodel.setAddress(addressModel);
		customermodel.setUserId(userId);
		customermodel.setApiToken(apiToken);
		logger.info("customermodel   "+  customermodel.toString());
		
		String jsonString=null;
		
		try {
			if(!Utils.isValidToken(customermodel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			CustomerDao addcustomerObj = (CustomerDao) AppBeanFactory.getBean("customerdao");
			addcustomerObj.updateCustomeJdbc(customermodel);
			
			customermodel.setStatus(true); 
			customermodel.setMessage(SuccessCode.SUCCESSFULLY_UPDATED_MSG.getSuccessCode());
			jsonString=serializeToJSONString(customermodel);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}
		
		return Response.status(200).entity(jsonString).build();
	}
	
}
