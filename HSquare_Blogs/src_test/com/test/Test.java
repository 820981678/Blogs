package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class Test extends TestCase {

	private Configuration cfg;

	public Configuration getCfg() {
		return cfg;
	}

	public void init() throws Exception {
		cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("D:/test"));
	}

	public static void main(String[] args) throws Exception {
		Test obj = new Test();
		obj.init();
		Map root = new HashMap();
		Template t = obj.getCfg().getTemplate("index.ftl");
		Writer out = new OutputStreamWriter(new FileOutputStream(
				"D:/test/TestInclude.html"), "utf-8");
		t.process(root, out);
		System.out.println("Successfull................");
	}

}
