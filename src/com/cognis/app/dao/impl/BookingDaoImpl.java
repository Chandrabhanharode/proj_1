package com.cognis.app.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.cognis.app.dao.AppBaseDao;
import com.cognis.app.dao.BookingDao;
import com.cognis.app.model.AvailableTimeSlot;
import com.cognis.app.model.BookingModel;
import com.cognis.app.model.ServiceProviderDtlsModel;

public class BookingDaoImpl extends AppBaseDao implements BookingDao {

	@Override
	public void addBooking(BookingModel bookingModel) {
		
		getJdbcTemplate().update(ADD_BOOKING,
				new Object[] {bookingModel.getCustomerId(),bookingModel.getServiceProvideId(),bookingModel.getBookingRate(),
						bookingModel.getBookingStatus(),bookingModel.getPaymentStatus(),bookingModel.getBookingStartTime(),
						bookingModel.getBookingEndTime(),bookingModel.getBookingDate() });
		
	}

	@Override
	public ServiceProviderDtlsModel getBookingList(BookingModel bookingModel) {

		List<ServiceProviderDtlsModel> ServiceProviderManu=getJdbcTemplate().query(GET_BOOKING,
				new Object[] { bookingModel.getServiceProvideId(),bookingModel.getServiceId() },new BookingDtlRowMapper());
		
		List<AvailableTimeSlot> timeSlotList=new ArrayList<AvailableTimeSlot>();
		
		int stime=Integer.parseInt((ServiceProviderManu.get(0).getStartDate().split(":"))[0]);
		int etime=Integer.parseInt((ServiceProviderManu.get(0).getEndDate().split(":"))[0]);
		for(int i=stime;i<etime-1;i++) {
		int count=	getJdbcTemplate().queryForInt(COUNT_AVABILE_TIME,
					new Object[] {i+":00",i+1+":00",
							bookingModel.getBookingDate() });
		AvailableTimeSlot slot=new AvailableTimeSlot();
		slot.setStartTime(i+":00");
		slot.setEndTime(i+1+":00");
		slot.setAvaibleCapacity(String.valueOf(((Integer.parseInt(ServiceProviderManu.get(0).getCapacity()))-count)));
		timeSlotList.add(slot);
		}
		
		ServiceProviderManu.get(0).setSlot(timeSlotList);
		
		return ServiceProviderManu.get(0);
	}

	class BookingDtlRowMapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowmapper) throws SQLException {
			
			ServiceProviderDtlsModel model=new ServiceProviderDtlsModel();
			 
			model.setServicePorviderId(rs.getInt("SERVICE_PROVIDER_ID"));
			model.setServicePorviderName(rs.getString("SERVICE_PROVIDER_NAME"));
			model.setServiceId(rs.getInt("SERVICE_ID"));
			model.setServiceName(rs.getString("SERVICE_NAME"));
			model.setPrice(rs.getString("PRICE"));
			model.setCapacity(rs.getString("SEATING_CAPACITY"));
			model.setStartDate(rs.getString("START_TIME"));
			model.setEndDate(rs.getString("END_TIME"));
			return model;
		}
		
	}
	
}
