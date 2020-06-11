package com.cognis.app.web.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AppBaseService {

	private static final Logger logger = Logger.getLogger(AppBaseService.class);

	protected ObjectMapper getJSONMapper() {

		ObjectMapper mapper = new ObjectMapper();

		// Don't include any NULL fields.
		mapper.setSerializationInclusion(Include.NON_NULL);

		// Set the date serializer to match ISO 8601
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		mapper.setDateFormat(dateFormat);

		mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		return mapper;
	}

	protected String getExceptionDetailAsJson(Throwable th) throws Exception {
		String exceptionJson = "";

		try {
			ObjectMapper mapper = getJSONMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			
			exceptionJson = mapper.writeValueAsString(th);
		} catch (Exception ex) {
			logger.log(Level.ERROR, ex.getMessage(), th);

		}

		return exceptionJson;
	}

	public String serializeToJSONString(Object baseModel)  {

		ObjectMapper mapper = getJSONMapper();
		
		try {
			return mapper.writeValueAsString(baseModel);
		} catch (Exception ex) {
			// May be error due to fields requested during serialization (bad request?)
			if (JsonMappingException.class == ex.getClass() && ex.getCause() != null
					&& ex.getCause().getClass() == IllegalArgumentException.class) {
				throw new IllegalArgumentException(ex.getCause().getMessage(), ex.getCause());
			}

			throw new IllegalArgumentException(ex.getMessage(), ex);

		}

	}
}