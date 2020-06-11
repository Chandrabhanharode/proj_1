package com.cognis.app.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.cognis.app.dao.VendorDao;
import com.cognis.app.exception.NotAValidTokenException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.AddressModel;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.model.VendorModel;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;
import com.cognis.app.web.utils.Utils;

@Path("/vendorService")
public class VendorService extends AppBaseService {

	private static final Logger logger = Logger.getLogger(VendorService.class);

	@GET
    @Path("addVendor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(@QueryParam("address1") String address1, @QueryParam("address2") String address2,
			@QueryParam("userId") Integer userId, @QueryParam("city") String city,
			@QueryParam("location") String location, @QueryParam("postalcode") String postalcode,
			@QueryParam("apiToken") String apiToken,@QueryParam("panNo") String panNo,@QueryParam("otherCardNo") String otherCardNo) throws Exception {

		logger.info(" Inside addAddress() ");
		AddressModel addressModel = new AddressModel();
		addressModel.setAddress1(address1);
		addressModel.setAddress2(address2);
		addressModel.setUserId(userId);
		addressModel.setCity(city);
		addressModel.setPostalcode(postalcode);
				
		VendorModel vendorModel = new VendorModel();
		vendorModel.setPanNo(panNo);
		vendorModel.setOtherCardNo(otherCardNo);
		vendorModel.setApiToken(apiToken);
		vendorModel.setAddress(addressModel);
		logger.info(" Inside Save Vendor() ");
		
		try {
			if(!Utils.isValidToken(vendorModel.getApiToken())) {
				throw new NotAValidTokenException();
			}	
			VendorDao vendorDao = (VendorDao) AppBeanFactory.getBean("vendorDao");
			vendorDao.addVendor(vendorModel);
			vendorModel.setStatus(true); 
			vendorModel.setMessage(SuccessCode.SUCCESSFULLY_ADDED_MSG.getSuccessCode());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}
		String jsonOutput = serializeToJSONString(vendorModel);
		return Response.status(200).entity(jsonOutput).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVendor(VendorModel vendorModel) throws Exception {
		logger.info("getVendor() called...");
		
		String jsonOutput = null;
		try {
			if(!Utils.isValidToken(vendorModel.getApiToken())) {
				throw new NotAValidTokenException();
			}	
			VendorDao vendorDao = (VendorDao) AppBeanFactory.getBean("vendorDao");
			List<VendorModel> vendorDtls = vendorDao.getVendorByVendorID(vendorModel);
			vendorModel=vendorDtls.get(0);
			vendorModel.setStatus(true);
			vendorModel.setMessage(SuccessCode.SUCCESSFULLY_GET_VENDOR_MSG.getSuccessCode());
		   jsonOutput = serializeToJSONString(vendorModel);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(505).entity(serializeToJSONString("error")).build();
		}
		
		return Response.status(200).entity(jsonOutput).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@QueryParam("address1") String address1, @QueryParam("address2") String address2,
			@QueryParam("userId") Integer userId, @QueryParam("city") String city,
			@QueryParam("location") String location, @QueryParam("postalcode") String postalcode,
			@QueryParam("apiToken") String apiToken,@QueryParam("panNo") String panNo,@QueryParam("otherCardNo") String otherCardNo) throws Exception {

		logger.info(" Inside addAddress() ");
		AddressModel addressModel = new AddressModel();
		addressModel.setAddress1(address1);
		addressModel.setAddress2(address2);
		addressModel.setUserId(userId);
		addressModel.setCity(city);
		addressModel.setPostalcode(postalcode);
				
		VendorModel vendorModel = new VendorModel();
		vendorModel.setPanNo(panNo);
		vendorModel.setOtherCardNo(otherCardNo);
		vendorModel.setApiToken(apiToken);
		vendorModel.setAddress(addressModel);
		String jsonString=null;
		try {
		if(!Utils.isValidToken(vendorModel.getApiToken())) {
			throw new NotAValidTokenException();
		}		
		VendorDao vendorDao = (VendorDao) AppBeanFactory.getBean("vendorDao");	
		vendorDao.updateVendorByVendorID(vendorModel);
		vendorModel.setStatus(true);
		vendorModel.setMessage(SuccessCode.SUCCESSFULLY_UPDATED_MSG.getSuccessCode());
		jsonString=serializeToJSONString(vendorModel);
		}catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(505).entity(serializeToJSONString(error)).build();
		}
		return Response.status(200).entity(jsonString).build();
	}

}
