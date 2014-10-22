package com.glogs.freemarkfunc;

import java.util.List;

import com.glogs.init.cache.GlobalCache;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 转换Btag 自定义方法
 * 
 * @author Administrator
 *
 */
public class QueryBtag implements TemplateMethodModel {

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List arg0) throws TemplateModelException {
		int tagid = Integer.parseInt(arg0.get(0).toString());
		return GlobalCache.getBtagById(tagid);
	}

}
