package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;

import junit.framework.TestCase;

public class Test extends TestCase {
	
	public void adress() throws UnknownHostException{
		
		InetAddress address = InetAddress.getLocalHost();
		System.out.println(address.getHostName());
		System.out.println(address.getHostAddress());
		
		//获取字节形式的ip地址
		byte[] by = address.getAddress();
		System.out.println(Arrays.toString(by));
		
		System.out.println(address);
		
		
		//根据机器名获取InetAddress实例
		InetAddress i2 = InetAddress.getByName("JGKJ-2-PC");
		InetAddress i3 = InetAddress.getByName("192.168.2.129");
	}
	
	public void url() throws MalformedURLException{
		URL baidu = new URL("http://www.imooc.com");
		URL url_1 = new URL(baidu,"/index.html?username=tom");
		
		//获取协议
		System.out.println(url_1.getProtocol());
		//获取主机
		System.out.println(url_1.getHost());
		//获取端口
		System.out.println(url_1.getPort());
		//获取文件路径
		System.out.println(url_1.getPath());
		//获取文件名
		System.out.println(url_1.getFile());
		//获取相对路径
		System.out.println(url_1.getRef());
		//获取查询字符串
		System.out.println(url_1.getQuery());
	}
	
	/**
	 * 读取URL资源内容
	 * @throws MalformedURLException 
	 */
	public void huoqi() throws MalformedURLException{
		URL baidu = new URL("http://www.baidu.com");
		try {
			//获取该资源的输入流
			InputStream is = baidu.openStream();
			InputStreamReader isr = new InputStreamReader(is,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			
			String data = br.readLine();
			while(data != null){
				System.out.println(data);
				data = br.readLine();
			}
			
			br.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void _1(){
		
	}
	
}
