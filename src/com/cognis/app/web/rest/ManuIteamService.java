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

import com.cognis.app.dao.ManuIteamDao;
import com.cognis.app.exception.NotAValidTokenException;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.ManuIteamModel;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;
import com.cognis.app.web.utils.Utils;

@Path("manuItemService")
public class ManuIteamService extends AppBaseService {

	public static Logger logger = Logger.getLogger(ManuIteamService.class);

	@GET
	@Path("/addMenu")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addManuIteam(@QueryParam("price") String price, @QueryParam("description") String description,
			@QueryParam("activeInactive") String activeInactive, @QueryParam("startTime") String startTime,
			@QueryParam("endTime") String endTime, @QueryParam("userId") Integer userId,
			@QueryParam("saloonServiceId") Integer saloonServiceId, @QueryParam("apiToken") String apiToken)
			throws Exception {
		ManuIteamModel manuIteamModel = new ManuIteamModel();
		manuIteamModel.setPrice(price);
		manuIteamModel.setDescription(description);
		manuIteamModel.setActiveInactive(activeInactive);
		manuIteamModel.setStartTime(startTime);
		manuIteamModel.setEndTime(endTime);
		manuIteamModel.setUserId(userId);
		manuIteamModel.setSaloonServiceId(saloonServiceId);
		manuIteamModel.setApiToken(apiToken);
		manuIteamModel.setActiveInactive(activeInactive);
		String jsonString = null;

		try {
			if (!Utils.isValidToken(manuIteamModel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			ManuIteamDao manuIteamObj = (ManuIteamDao) AppBeanFactory.getBean("manuitemdaoimpl");
			manuIteamObj.addManuIteam(manuIteamModel);
			manuIteamModel.setStatus(true);
			manuIteamModel.setMessage(SuccessCode.SUCCESSFULLY_ADD_MANU_ITEAM.getSuccessCode());
			jsonString = serializeToJSONString(manuIteamModel);
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
	public Response getManuIteam(@QueryParam("userId") Integer userId, @QueryParam("apiToken") String apiToken)
			throws Exception {

		ManuIteamModel manuIteamModel = new ManuIteamModel();
		manuIteamModel.setUserId(userId);
		manuIteamModel.setApiToken(apiToken);
		String jsonString = null;

		try {
			if (!Utils.isValidToken(manuIteamModel.getApiToken())) {
				throw new NotAValidTokenException();
			}
			ManuIteamDao manuIteamObj = (ManuIteamDao) AppBeanFactory.getBean("manuitemdaoimpl");
			List<ManuIteamModel> manuList = manuIteamObj.getManuIteamList(manuIteamModel);
			jsonString = serializeToJSONString(manuList);
		} catch (Exception e) {
			e.printStackTrace();
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			jsonString = serializeToJSONString(error);
			return Response.status(400).entity(jsonString).build();
		}

		return Response.status(200).entity(jsonString).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateManuIteam(@QueryParam("price") String price, @QueryParam("description") String description,
			@QueryParam("activeInactive") String activeInactive, @QueryParam("startTime") String startTime,
			@QueryParam("endTime") String endTime, @QueryParam("userId") Integer userId,
			@QueryParam("saloonServiceId") Integer saloonServiceId, @QueryParam("apiToken") String apiToken)
			throws Exception {
		ManuIteamModel manuIteamModel = new ManuIteamModel();
		manuIteamModel.setPrice(price);
		manuIteamModel.setDescription(description);
		manuIteamModel.setActiveInactive(activeInactive);
		manuIteamModel.setStartTime(startTime);
		manuIteamModel.setEndTime(endTime);
		manuIteamModel.setUserId(userId);
		manuIteamModel.setSaloonServiceId(saloonServiceId);
		manuIteamModel.setApiToken(apiToken);
		manuIteamModel.setActiveInactive(activeInactive);
		String jsonString = null;

		try {
			ManuIteamDao manuIteamObj = (ManuIteamDao) AppBeanFactory.getBean("manuitemdaoimpl");
			manuIteamObj.updateManuItem(manuIteamModel);
			manuIteamModel.setStatus(true);
			manuIteamModel.setMessage(SuccessCode.SUCCESSFULLY_UPDATED_MSG.getSuccessCode());
			jsonString = serializeToJSONString(manuIteamModel);
		} catch (Exception e) {
			e.printStackTrace();
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			jsonString = serializeToJSONString(error);
			return Response.status(400).entity(jsonString).build();
		}

		return Response.status(200).entity(jsonString).build();
	}

}
