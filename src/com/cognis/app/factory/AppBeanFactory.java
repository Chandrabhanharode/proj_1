package com.cognis.app.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.cognis.app.web.rest.AppBaseService;

public class AppBeanFactory {
	private static ApplicationContext ctx = null;

	private AppBeanFactory() {

	}

	public static Object getBean(String beanName) {

		if (ctx == null) {
			ctx = new FileSystemXmlApplicationContext(
					AppBaseService.class.getResource("../../../../../../applicationContext-jdbc.xml").toString());

		}
		return ctx.getBean(beanName);
	}

}
