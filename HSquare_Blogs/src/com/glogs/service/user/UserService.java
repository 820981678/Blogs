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
	 * 登陆
	 * 
	 * @param name
	 * @param pwd
	 * @return	登陆成功的用户对象(登陆不成功返回null)
	 */
	User login(String name,String pwd) throws DBException;
	
	/**
	 * 根据用户名登陆
	 * 
	 * @param name
	 * @return
	 */
	User login_name(String name) throws DBException;
	
}
