package com.glogs.util;

/**
 * 记录公共使用的字符变量
 */
public class PublicKey {
	
	/**
	 * request中记录请求开始时间
	 */
	public static final String ACTION_STARTTIME = "startTime";
	
	/**
	 * request中记录请求结束时间
	 */
	public static final String ACTION_ENDTIME = "endTime";
	
	/**
	 * request中记录请求路径
	 */
	public static final String ACTION_PATH = "actionPath";
	
	/**
	 * 请求日志记录  入
	 */
	public static final String LOG_ACTION_IN = "<<<  ";
	
	/**
	 * 请求日志记录  出
	 */
	public static final String LOG_ACTION_OUT = ">>>  ";
	
	/**
	 * 日志开始
	 */
	public static final String LOG_TITLE = "|---------------------------------------------------------";
	
	/**
	 * 放行html请求 配置文件key
	 */
	public static final String DISCHARGED_HTML = "discharged.html";
	
}
