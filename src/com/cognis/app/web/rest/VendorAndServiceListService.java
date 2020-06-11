package com.cognis.app.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.cognis.app.dao.VendorAndServiceListDao;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.model.ServiceProviderModel;
import com.cognis.app.model.VendorAndServiceListModel;
import com.cognis.app.model.VendorModel;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;

@Path("/vendorAndServiceListService")
public class VendorAndServiceListService extends AppBaseService {

	private static final Logger logger = Logger.getLogger(VendorAndServiceListService.class);

	@GET
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVendorAndServiceList(@QueryParam("searchBy") String searchBy,
			@QueryParam("filterBy") String filterBy) throws Exception {
		logger.info(" Inside getVendorAndServiceList() ");
		VendorAndServiceListModel model = null;
		try {
			// if(!Utils.isValidToken(model.getApiToken())) {
			// throw new NotAValidTokenException();
			// }
			VendorAndServiceListDao vendorAndServiceListDao = (VendorAndServiceListDao) AppBeanFactory
					.getBean("vendorAndServiceListDao");
			model = vendorAndServiceListDao.getVendorAndServiceList();
			logger.info("length = " + model.toString());
			model.setStatus(true);
			model.setMessage(SuccessCode.SUCCESSFULLY_GET_LIST_OF_SERVICES_AND_VENDOR_PROVIDER.getSuccessCode());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}
		String jsonOutput = serializeToJSONString(model);
		return Response.status(200).entity(jsonOutput).build();
	}

	@GET
	@Path("/getVendorAndService")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVendorAndServiceBySeach(@QueryParam("roleId") String roleId,
			@QueryParam("filterBy") String filterBy, @QueryParam("value") String value) throws Exception {

		logger.info(" Inside getVendorAndServiceBySeach() ");
		logger.info("Service              searchBy " + roleId + "    filterBy   " + filterBy + "  value " + value);
		String jsonOutput = null;
		ServiceProviderModel serviceProviderModel = null;
		VendorModel vendorModel = null;
		try {

			VendorAndServiceListDao vendorAndServiceListDao = (VendorAndServiceListDao) AppBeanFactory
					.getBean("vendorAndServiceListDao");
			if (roleId != null) {
				if (roleId.equals("1")) {
					serviceProviderModel = vendorAndServiceListDao.searchtVendor(filterBy, value);
					serviceProviderModel.setStatus(true);
					serviceProviderModel.setMessage(
							SuccessCode.SUCCESSFULLY_GET_SERVICES_PROVIDER.getSuccessCode());
					jsonOutput = serializeToJSONString(serviceProviderModel);
				}else {
					logger.info("In side else");
					vendorModel = vendorAndServiceListDao.searchtServiceprovider(filterBy, value);
					vendorModel.setStatus(true);
					vendorModel
							.setMessage(SuccessCode.SUCCESSFULLY_GET_FREELANCE_VENDOR.getSuccessCode());
					jsonOutput = serializeToJSONString(vendorModel);
				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		}

		return Response.status(200).entity(jsonOutput).build();
	}
}
