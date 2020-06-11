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

import com.cognis.app.dao.ServiceProviderDao;
import com.cognis.app.exception.NotAValidTokenException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.AddressModel;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.model.ServiceProviderModel;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;
import com.cognis.app.web.utils.Utils;

@Path("serviceProviderService")
public class ServiceProviderService extends AppBaseService {

	public static Logger logger = Logger.getLogger(ServiceProviderService.class);

	@GET
	@Path("/addServiceProvider")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addServiceProvider(@QueryParam("address1") String address1, @QueryParam("address2") String address2,
			@QueryParam("userId") Integer userId, @QueryParam("city") String city,
			@QueryParam("location") String location, @QueryParam("postalcode") String postalcode,
			@QueryParam("apiToken") String apiToken, @QueryParam("gstNumber") String gstNumber,
			@QueryParam("businessRegisistrationId") String businessRegisistrationId,
			@QueryParam("serviceProviderId") Integer serviceProviderId,
			@QueryParam("seatingCapacity") String seatingCapacity) throws Exception {

		AddressModel addressModel = new AddressModel();
		addressModel.setAddress1(address1);
		addressModel.setAddress2(address2);
		addressModel.setUserId(userId);
		addressModel.setCity(city);
		addressModel.setApiToken(apiToken);
		addressModel.setPostalcode(postalcode);
		ServiceProviderModel serviceProvider = new ServiceProviderModel();
		serviceProvider.setAddress(addressModel);
		serviceProvider.setGstNumber(gstNumber);
		serviceProvider.setSeatingCapacity(seatingCapacity);
		serviceProvider.setServiceProviderId(serviceProviderId);
		serviceProvider.setBusinessRegisistrationId(businessRegisistrationId);
		logger.info("addServiceProvider() called...");

		String jsonString = null;

		try {
			if (!Utils.isValidToken(serviceProvider.getApiToken())) {
				throw new NotAValidTokenException();
			}
			ServiceProviderDao serviceProviderObj = (ServiceProviderDao) AppBeanFactory.getBean("serviceproviderdao");
			serviceProviderObj.addServiceProviderJdbc(serviceProvider);
			serviceProvider.setStatus(true);
			serviceProvider.setMessage(SuccessCode.SUCCESSFULLY_ADDED_MSG.getSuccessCode());
			jsonString = serializeToJSONString(serviceProvider);
		} catch (Exception e) {
			e.printStackTrace();
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			jsonString = serializeToJSONString(error);
			return Response.status(400).entity(jsonString).build();
		}

		return Response.status(200).entity(jsonString).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getServiceProvider(@QueryParam("userId") Integer userId, @QueryParam("apiToken") String apiToken) {
        
		ServiceProviderModel serviceProvider = new ServiceProviderModel();
		serviceProvider.setApiToken(apiToken);
		serviceProvider.setUserId(userId);
		
		logger.info("addServiceProvider() called...");

		String jsonString = null;

		try {
			if (!Utils.isValidToken(serviceProvider.getApiToken())) {
				throw new NotAValidTokenException();
			}
			ServiceProviderDao serviceProviderObj = (ServiceProviderDao) AppBeanFactory.getBean("serviceproviderdao");
			List<ServiceProviderModel> servicePorviderList = serviceProviderObj.getservicePorviderJdbc(serviceProvider);
			serviceProvider = servicePorviderList.get(0);
			serviceProvider.setStatus(true);
			serviceProvider.setMessage(SuccessCode.SUCCESSFULLY_GET_SERVICE_PROVIDER_MSG.getSuccessCode());
			jsonString = serializeToJSONString(serviceProvider);
		} catch (Exception e) {
			e.printStackTrace();
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(400).entity(error).build();
		}

		return Response.status(200).entity(jsonString).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateServiceProvidr(@QueryParam("address1") String address1, @QueryParam("address2") String address2,
			@QueryParam("userId") Integer userId, @QueryParam("city") String city,
			@QueryParam("location") String location, @QueryParam("postalcode") String postalcode,
			@QueryParam("apiToken") String apiToken, @QueryParam("gstNumber") String gstNumber,
			@QueryParam("businessRegisistrationId") String businessRegisistrationId,
			@QueryParam("serviceProviderId") Integer serviceProviderId,
			@QueryParam("seatingCapacity") String seatingCapacity) throws Exception {

		AddressModel addressModel = new AddressModel();
		addressModel.setAddress1(address1);
		addressModel.setAddress2(address2);
		addressModel.setUserId(userId);
		addressModel.setCity(city);
		addressModel.setApiToken(apiToken);
		addressModel.setPostalcode(postalcode);
		ServiceProviderModel serviceProvider = new ServiceProviderModel();
		serviceProvider.setAddress(addressModel);
		serviceProvider.setGstNumber(gstNumber);
		serviceProvider.setSeatingCapacity(seatingCapacity);
		serviceProvider.setServiceProviderId(serviceProviderId);
		serviceProvider.setBusinessRegisistrationId(businessRegisistrationId);

		logger.info("updateServiceProvidr() called..");

		String jsonString = null;
		try {
			if (!Utils.isValidToken(serviceProvider.getApiToken())) {
				throw new NotAValidTokenException();
			}
			ServiceProviderDao serviceProviderObj = (ServiceProviderDao) AppBeanFactory.getBean("serviceproviderdao");
			serviceProviderObj.updateServiceProvider(serviceProvider);
			serviceProvider.setStatus(true);
			serviceProvider.setMessage(SuccessCode.SUCCESSFULLY_UPDATED_MSG.getSuccessCode());
			jsonString = serializeToJSONString(serviceProvider);
		} catch (Exception e) {
			e.printStackTrace();
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(400).entity(error).build();
		}

		return Response.status(200).entity(jsonString).build();
	}
}
