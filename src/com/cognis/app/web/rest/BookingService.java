package com.cognis.app.web.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.cognis.app.dao.BookingDao;
import com.cognis.app.factory.AppBeanFactory;
import com.cognis.app.model.BookingModel;
import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.model.ServiceProviderDtlsModel;
import com.cognis.app.web.utils.ErrorCode;
import com.cognis.app.web.utils.SuccessCode;

@Path("BookingService")
public class BookingService extends AppBaseService {

	public static Logger logger = Logger.getLogger(BookingService.class);

	@GET
	@Path("/addBooking")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBooking(@QueryParam("customerId") Integer customerId,
			@QueryParam("serviceProvideId") Integer serviceProvideId, @QueryParam("paymentStatus") String paymentStatus,
			@QueryParam("bookingStatus") String bookingStatus, @QueryParam("bookingRate") String bookingRate,
			@QueryParam("bookingStartTime") String bookingStartTime,
			@QueryParam("bookingEndTime") String bookingEndTime, @QueryParam("bookingDate") String bookingDate,
			@QueryParam("apiToken") String apiToken) {

		BookingModel bookingModel = new BookingModel();
		bookingModel.setApiToken(apiToken);
		bookingModel.setCustomerId(customerId);
		bookingModel.setPaymentStatus(paymentStatus);
		bookingModel.setServiceProvideId(serviceProvideId);
		bookingModel.setPaymentStatus(paymentStatus);
		bookingModel.setBookingRate(bookingRate);
		bookingModel.setBookingStartTime(bookingStartTime);
		bookingModel.setBookingEndTime(bookingEndTime);
		bookingModel.setBookingDate(bookingDate);
		String jsonString = null;

		try {

			BookingDao bookingdaoObj = (BookingDao) AppBeanFactory.getBean("bookingDaoImpl");
			bookingdaoObj.addBooking(bookingModel);
			bookingModel.setStatus(true);
			bookingModel.setMessage(SuccessCode.SUCCESSFULLY_ADD_BOOKING.getSuccessCode());
			jsonString = serializeToJSONString(bookingModel);
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
	public Response getBookingDtls(@QueryParam("serviceProvideId") Integer serviceProvideId,
			@QueryParam("serviceId") Integer serviceId, @QueryParam("bookingDate") String bookingDate) {
		logger.info(" Inside getBookingDtls() ");
		BookingModel bookingModel = new BookingModel();
	
		bookingModel.setServiceProvideId(serviceProvideId);
		bookingModel.setServiceId(serviceId);
		bookingModel.setBookingDate(bookingDate);
		logger.info("   		BookingModel    " + bookingModel.toString());
		String jsonString = null;

		try {
			BookingDao bookingdaoObj = (BookingDao) AppBeanFactory.getBean("bookingDaoImpl");
			ServiceProviderDtlsModel bookingList = bookingdaoObj.getBookingList(bookingModel);
			bookingList.setStatus(true);
			bookingList.setMessage(SuccessCode.SUCCESSFULLY_GET_BOOKIG_SLOT.getSuccessCode());
			jsonString = serializeToJSONString(bookingList);
		} catch (Exception e) {
			e.printStackTrace();
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			jsonString = serializeToJSONString(error);
			return Response.status(400).entity(error).build();
		}

		return Response.status(200).entity(jsonString).build();
	}
}
