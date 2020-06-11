package com.cognis.app.web.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Utils {
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm aa";
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
	private static final SimpleDateFormat dateFormatDDMMYY = new SimpleDateFormat("dd/MM/yyyy");
  
	public static final Set<String> setOftoken=new HashSet<String>();
	static {
		setOftoken.add("G6943LMReKj");
	}
	// Genrate Tracking Number
	public static String GenrateTrackingNumber() {

		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		long xxx = (long) Math.floor(Math.random() * 10000);
		String tracking = simpleDateFormat.format(new Date()) + xxx;

		return tracking;
	}

	public static String getSecurityToken() {

		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

	 	public static String formatDate(Date date) {

		if (date != null) {
			return simpleDateFormat.format(date);
		}
		return null;

	}

	public static Date parseDate(String date) {

		if (date != null) {
			try {
				return simpleDateFormat.parse(date);
			} catch (ParseException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("date " + date);
			}
		}
		return null;

	}
	
	public static Date parseDateInDDMMYY(String date) {

		if (date != null) {
			try {
				return dateFormatDDMMYY.parse(date);
			} catch (ParseException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("date " + date);
			}
		}
		return null;

	}
	
	public static String encodeString(String value) {
		String enCodedValue= null;
		try {
			enCodedValue = Base64.getEncoder().encodeToString( value.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enCodedValue;
	}
	
	public static String decodeString(String value) {
		 // Decode
        byte[] base64decodedBytes = Base64.getDecoder().decode(value);
        String output="";
		
        try {
			System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
			output = new String(base64decodedBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return output;
	}
	
	public static String genarateToken() {
		
		UUID uuid = UUID.randomUUID();
		
		return uuid.toString();
	}
	public static void setToken(String token) {
		
		setOftoken.add(token);
		
	}
	
	public static boolean isValidToken(String token)
	{
		return setOftoken.contains(token);
	}
}
