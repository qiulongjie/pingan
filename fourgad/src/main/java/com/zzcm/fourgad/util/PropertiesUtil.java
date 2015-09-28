package com.zzcm.fourgad.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 资源文件解析工具
 * 
 * @author wancan
 *
 */
public class PropertiesUtil {

	public static Properties getProperty(String fileName) {
		Properties props = new Properties();
		try {
			props.load(new InputStreamReader(PropertiesUtil.class
					.getClassLoader().getResourceAsStream(fileName), "utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	public static void main(String[] args) {
		Properties p = PropertiesUtil.getProperty("interfaceParams.propertis");
		System.out.println(p.get("CITIES").toString());
		System.out.println(p.get("CHANNELS").toString());
	}
}