package com.glogs.service.user.impl;

import java.util.Date;

import com.connection.db.DBException;
import com.connection.db.DBHandle;
import com.glogs.entity.account.User;
import com.glogs.service.user.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User login(String name, String pwd) throws DBException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(User.DB_NAME).append(" WHERE 1=1 ");
		sql.append(" AND NAME=? AND PASSWORD=?");
		
		Object[] params = {
			name,pwd
		};
		
		User user = DBHandle.queryFirst(sql.toString(), params, User.class);
		if(user != null){
			updateLoginTime(user.getId());
		}
		return user;
	}

	@Override
	public User login_name(String name) throws DBException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ").append(User.DB_NAME).append(" WHERE 1=1 ");
		sql.append(" AND NAME=?");
		
		Object[] params = {
			name
		};
		
		User user = DBHandle.queryFirst(sql.toString(), params, User.class);
		if(user != null){
			updateLoginTime(user.getId());
		}
		return user;
	}
	
	private void updateLoginTime(Integer id) throws DBException {
		StringBuffer sql_u = new StringBuffer();
		sql_u.append("UPDATE ").append(User.DB_NAME);
		sql_u.append(" SET LOGINTIME=? WHERE ID=?");
		Object[] params_u = {
				new Date(),id
		};
		DBHandle.exceute(sql_u.toString(), params_u);
	}
	
}
