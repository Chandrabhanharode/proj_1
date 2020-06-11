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

import com.cognis.app.dao.AddressDao;
import com.cognis.app.exception.AddressNotFoundException;
import com.cognis.app.exception.NotAValidTokenException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.AddressModel;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;
import com.cognis.app.web.utils.Utils;

@Path("addressService")
public class AddressService extends AppBaseService {

	public static Logger logger = Logger.getLogger(AddressService.class);

	@GET
	@Path("/saveAddress")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAddress(@QueryParam("address1") String address1, @QueryParam("address2") String address2,
			@QueryParam("userId") Integer userId, @QueryParam("city") String city,
			@QueryParam("location") String location, @QueryParam("postalcode") String postalcode,
			@QueryParam("apiToken") String apiToken) throws Exception {

		logger.info(" Inside addAddress() ");
		AddressModel addressModel = new AddressModel();
		addressModel.setAddress1(address1);
		addressModel.setAddress2(address2);
		addressModel.setUserId(userId);
		addressModel.setCity(city);
		addressModel.setApiToken(apiToken);
		addressModel.setPostalcode(postalcode);
		logger.info("   AddressModel    " + addressModel.toString());
		logger.info("   apiToken    " + apiToken);
		String jsonString = null;
		try {
			if (!Utils.isValidToken(addressModel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			AddressDao addressObj = (AddressDao) AppBeanFactory.getBean("addressdao");
			addressObj.addAddress(addressModel);
			addressModel.setStatus(true);
			addressModel.setMessage(SuccessCode.SUCCESSFULLY_ADDED_MSG.getSuccessCode());
			jsonString = serializeToJSONString(addressModel);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}
		return Response.status(200).entity(jsonString).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAddress(@QueryParam("userId") Integer userId, @QueryParam("apiToken") String apiToken)
			throws Exception {

		logger.info(" Inside getAddress() ");
		AddressModel addressModel = new AddressModel();

		addressModel.setUserId(userId);
		addressModel.setApiToken(apiToken);
		String jsonString = null;

		try {
			if (!Utils.isValidToken(addressModel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			AddressDao addressObj = (AddressDao) AppBeanFactory.getBean("addressdao");
			List<AddressModel> addressList = addressObj.getAddressJdbc(addressModel);

			if (addressList.size() > 0) {
				AddressModel userResult = new AddressModel();
				userResult = addressList.get(0);
				userResult.setStatus(true);
				userResult.setMessage(SuccessCode.SUCCESSFULLY_GET_ADDRESSS_MSG.getSuccessCode());
				jsonString = serializeToJSONString(userResult);
			} else {
				throw new AddressNotFoundException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}
		return Response.status(200).entity(jsonString).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAddress(@QueryParam("address1") String address1, @QueryParam("address2") String address2,
			@QueryParam("userId") Integer userId, @QueryParam("city") String city,
			@QueryParam("location") String location, @QueryParam("postalcode") String postalcode,
			@QueryParam("apiToken") String apiToken) throws Exception {

		logger.info(" Inside updateAddress() ");
		AddressModel addressModel = new AddressModel();
		addressModel.setAddress1(address1);
		addressModel.setAddress2(address2);
		addressModel.setUserId(userId);
		addressModel.setCity(city);
		addressModel.setApiToken(apiToken);
		addressModel.setPostalcode(postalcode);
		logger.info("   AddressModel    " + addressModel.toString());
		logger.info("   apiToken    " + addressModel.getApiToken());
		String jsonString = null;

		try {
			if (!Utils.isValidToken(addressModel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			AddressDao addressupdateObj = (AddressDao) AppBeanFactory.getBean("addressdao");
			addressupdateObj.updateAddress(addressModel);
			addressModel.setStatus(true);
			addressModel.setMessage(SuccessCode.SUCCESSFULLY_UPDATED_MSG.getSuccessCode());
			jsonString = serializeToJSONString(addressModel);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}
		return Response.status(200).entity(jsonString).build();
	}

}
