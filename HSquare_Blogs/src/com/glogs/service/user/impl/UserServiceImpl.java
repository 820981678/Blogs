package com.glogs.service.user.impl;

import com.connection.db.DBException;
import com.connection.db.DBHandle;
import com.glogs.entity.account.User;
import com.glogs.service.user.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User query_name_pwd(String name, String pwd) throws DBException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(User.DB_NAME).append(" WHERE 1=1 ");
		sql.append(" AND NAME=? AND PASSWORD=?");
		
		Object[] params = {
			name,pwd
		};
		
		return DBHandle.queryFirst(sql.toString(), params, User.class);
	}

	@Override
	public User query_name(String name) throws DBException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(User.DB_NAME).append(" WHERE 1=1 ");
		sql.append(" AND NAME=?");
		
		Object[] params = {
			name
		};
		
		return DBHandle.queryFirst(sql.toString(), params, User.class);
	}
	
}
