package com.cognis.app.web.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.cognis.app.dao.SaloonServiceDao;
import com.cognis.app.exception.NotAValidTokenException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.model.SaloonServiceModel;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;
import com.cognis.app.web.utils.Utils;

@Path("saloonService")
public class SaloonService extends AppBaseService{

	public static Logger logger=Logger.getLogger(SaloonService.class);
	
	@GET
	@Path("/addSaloonService")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSaloonService(@QueryParam("serviceName") String serviceName, @QueryParam("description") String description,
			@QueryParam("apiToken") String apiToken) throws Exception {
	
	
		SaloonServiceModel serviceModel = new SaloonServiceModel();
		serviceModel.setServiceName(serviceName);
		serviceModel.setDescription(description);
		serviceModel.setApiToken(apiToken);
	logger.info("   addSaloonService() called..");
	String jsonString=null;
	
	try {
		if(!Utils.isValidToken(serviceModel.getApiToken())) {
			throw new NotAValidTokenException();
		}
		SaloonServiceDao saloonServiceObj= (SaloonServiceDao) AppBeanFactory.getBean("saloonservicedaoimpl");
		saloonServiceObj.addSaloonService(serviceModel);
		serviceModel.setImageUrl("image/HairCutting.jpg");
		serviceModel.setStatus(true);
		serviceModel.setMessage(SuccessCode.SUCCESSFULLY_ADD_SALOON_SERVICE.getSuccessCode());
		jsonString=serializeToJSONString(serviceModel);
	}catch (Exception e) {
		e.printStackTrace();
		SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
		jsonString=serializeToJSONString(error);
		return Response.status(400).entity(jsonString).build();
	}
	
	return Response.status(200).entity(jsonString).build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSaloonService(SaloonServiceModel saloonServiceModel) {
		
		logger.info("getSaloonService() called...");
		String jsonString=null;
		try {
			if(!Utils.isValidToken(saloonServiceModel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			SaloonServiceDao saloonServiceObj= (SaloonServiceDao) AppBeanFactory.getBean("saloonservicedaoimpl");
			List<SaloonServiceModel> result=saloonServiceObj.getSaloonService();
			SaloonServiceModel base=new SaloonServiceModel();
			jsonString=serializeToJSONString(result);
		}catch (Exception e) {
			e.printStackTrace();
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(400).entity(error).build();
		}
		
		
		return Response.status(200).entity(jsonString).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateSaloonService(@QueryParam("serviceName") String serviceName, @QueryParam("description") String description,
			@QueryParam("apiToken") String apiToken) throws Exception {
	
	
		SaloonServiceModel serviceModel = new SaloonServiceModel();
		serviceModel.setServiceName(serviceName);
		serviceModel.setDescription(description);
		serviceModel.setApiToken(apiToken);
		logger.info("updateSaloonService() called...");
		String jsonString=null;
		try {
			if(!Utils.isValidToken(serviceModel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			SaloonServiceDao saloonServiceObj= (SaloonServiceDao) AppBeanFactory.getBean("saloonservicedaoimpl");
			saloonServiceObj.updateSaloonService(serviceModel);
			serviceModel.setStatus(true);
			serviceModel.setMessage(SuccessCode.SUCCESSFULLY_UPDATED_MSG.getSuccessCode());
			jsonString=serializeToJSONString(serviceModel);
		}catch (Exception e) {
			e.printStackTrace();
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			jsonString=serializeToJSONString(error);
			return Response.status(400).entity(jsonString).build();
		}
		
		return Response.status(200).entity(jsonString).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSaloonService(SaloonServiceModel serviceModel) {
		
		logger.info("deleteSaloonService() called...");
		String jsonString=null;
		try {
			if(!Utils.isValidToken(serviceModel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			SaloonServiceDao saloonServiceObj= (SaloonServiceDao) AppBeanFactory.getBean("saloonservicedaoimpl");
			saloonServiceObj.deleteSaloonService(serviceModel);
			serviceModel.setStatus(true);
			serviceModel.setMessage(SuccessCode.SUCCESSFULLY_DELETE_SALOON_SERVICE.getSuccessCode());
			jsonString=serializeToJSONString(jsonString);
		}catch (Exception e) {
			e.printStackTrace();
			SaloonErrorModel error=new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			jsonString=serializeToJSONString(error);
			return Response.status(400).entity(jsonString).build();
		}
		
		return null;
		}
}
