/* Copyright (c) 2020 COGNIS| All Rights Reserved*/
package com.cognis.app.web.rest;

/* -------------------------- Change Log ----------------------------------------
##   DD/MM/YY       -User-              -TaskRef-            -ShortDescription-
1    19/03/20       Sushil               Initial Version
---------------------------------------------------------------------------------
*/
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * This class is extends of {@code Application}
 * @author Cognis Solution
 */
public class CognisApplication extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> setOfService = new HashSet<Class<?>>();
		setOfService.add(VendorService.class);
		setOfService.add(UserService.class);
		setOfService.add(LoginService.class);
		setOfService.add(AddressService.class);
		setOfService.add(CustomerService.class);
		setOfService.add(ServiceProviderService.class);
		setOfService.add(SaloonService.class);
		setOfService.add(ManuIteamService.class);
		setOfService.add(BookingService.class);
		setOfService.add(VendorAndServiceListService.class);
		setOfService.add(FileUploadService.class);
		return setOfService;
	}
}