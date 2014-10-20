package com.glogs.entity.blog;

/**
 * 博客文章标签实体
 * 
 * @ClassName: BlogType 
 * @author huxiaohuan 
 * @date 2014年10月9日 下午4:47:53 
 * @version V1.0
 */
public class BTag {
	
	/**
	 * 对应数据库表
	 */
	public static final String BD_NAME = "BTag";
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * 标签名称
	 */
	private String tagName;

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
	 * 标签名称
	 * 
	 * @return
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * 标签名称
	 * 
	 * @param tagName
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
