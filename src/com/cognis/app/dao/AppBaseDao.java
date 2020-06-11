package com.cognis.app.dao;

import java.sql.SQLException;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

public class AppBaseDao extends NamedParameterJdbcDaoSupport {


	public void getDb() throws SQLException {
		System.out.println(getDataSource());
	}

	 

}
