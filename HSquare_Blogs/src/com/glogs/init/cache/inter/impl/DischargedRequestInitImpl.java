package com.glogs.init.cache.inter.impl;

import java.util.HashSet;
import java.util.Set;

import com.glogs.init.cache.GlobalCache;
import com.glogs.init.cache.inter.CacheInit;
import com.glogs.init.properties.PropertiesConfigurer;
import com.glogs.util.PublicKey;
import com.util.LogsUtil;

public class DischargedRequestInitImpl implements CacheInit {

	@Override
	public void init() {
		log.info(LogsUtil.PREFIX2 + "DischargedRequest Load ...");
		Set<String> set = new HashSet<String>();
		
		String value = PropertiesConfigurer.getStringValueByKey(PublicKey.DISCHARGED_HTML);
		for (String str : value.split(",")) {
			set.add(str);
		}
		
		GlobalCache.global_discharged = set;
		log.info(LogsUtil.PREFIX3 + "value: " + set);
		log.info(LogsUtil.PREFIX3 + "DischargedRequest Loading is complete");
	}
	
}
