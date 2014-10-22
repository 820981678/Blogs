package com.glogs.entity.blog;

import java.util.Date;

/**
 * 文章实体
 * 
 * @ClassName: Blog 
 * @author huxiaohuan 
 * @date 2014年10月11日 上午9:15:42 
 * @version V1.0
 */
public class Blog {
	
	/**
	 * 文章数据库表名
	 */
	public static final String DB_NAME = "Blog";
	
	/**
	 * 内容数据库表名
	 */
	public static final String CONTENT_DB_NAME = "blog_text";
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 文章标题
	 */
	private String title;
	
	/**
	 * 文章概述
	 */
	private String overview;
	
	/**
	 * 文章标签关联id
	 */
	private Integer btagId;
	
	/**
	 * 保存文章标签 名称(该字段只用于 页面显示数据使用)
	 */
	private String btagName;
	
	/**
	 * 文章类型, 对应BType枚举
	 */
	private Integer btype;
	
	/**
	 * 发布人id
	 */
	private Integer userId;
	
	/**
	 * 发布人姓名
	 */
	private String userName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 最后修改时间
	 */
	private Date updateTime;
	
	/**
	 * 点击量
	 */
	private Integer checkNum;
	
	/**
	 * 状态 对应BlogState枚举
	 */
	private Integer state;

	/**
	 * id
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * id
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 文章标题
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 文章标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 文章概述
	 * 
	 * @return
	 */
	public String getOverview() {
		return overview;
	}

	/**
	 * 文章概述
	 * 
	 * @param overview
	 */
	public void setOverview(String overview) {
		this.overview = overview;
	}

	/**
	 * 文章标签关联id
	 * 
	 * @return
	 */
	public Integer getBtagId() {
		return btagId;
	}

	/**
	 * 文章标签关联id
	 * 
	 * @param btagId
	 */
	public void setBtagId(Integer btagId) {
		this.btagId = btagId;
	}
	
	/**
	 * 保存文章标签 名称(该字段只用于 页面显示数据使用)
	 * 
	 * @return
	 */
	public String getBtagName() {
		return btagName;
	}

	/**
	 * 保存文章标签 名称(该字段只用于 页面显示数据使用)
	 * 
	 * @return
	 */
	public void setBtagName(String btagName) {
		this.btagName = btagName;
	}
	
	/**
	 * 发布人姓名
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 发布人姓名
	 * 
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 文章类型, 对应BType枚举
	 * 
	 * @return
	 */
	public Integer getBtype() {
		return btype;
	}

	/**
	 * 文章类型, 对应BType枚举
	 * 
	 * @param btype
	 */
	public void setBtype(Integer btype) {
		this.btype = btype;
	}

	/**
	 * 发布人id
	 * 
	 * @return
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 发布人id
	 * 
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 创建时间
	 * 
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 最后修改时间
	 * 
	 * @return
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 最后修改时间
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 点击量
	 * 
	 * @return
	 */
	public Integer getCheckNum() {
		return checkNum;
	}

	/**
	 * 点击量
	 * 
	 * @param checkNum
	 */
	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}

	/**
	 * 状态 对应BlogState枚举
	 * 
	 * @return
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 状态 对应BlogState枚举
	 * 
	 * @param state
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
}
