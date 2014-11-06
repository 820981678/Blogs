package com.glogs.service.user;

import com.connection.db.DBException;
import com.glogs.entity.account.User;

/**
 * 用户db服务
 * 
 * @ClassName: BlogService 
 * @author huxiaohuan 
 * @date 2014年10月11日 下午2:02:39 
 * @version V1.0
 */
public interface UserService {
	
	/**
	 * 根据用户名密码 查询用户
	 * 
	 * @param name
	 * @param pwd
	 * @return
	 */
	User query_name_pwd(String name,String pwd) throws DBException;
	
	/**
	 * 根据用户名查询用户
	 * 
	 * @param name
	 * @return
	 */
	User query_name(String name) throws DBException;
	
}
