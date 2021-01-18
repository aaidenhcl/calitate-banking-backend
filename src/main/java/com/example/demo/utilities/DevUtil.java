package com.example.demo.utilities;

public class DevUtil {

	private static Boolean isDev = true;

	public static Boolean getIsDev() {
		return isDev;
	}

	public static void setIsDev(Boolean isDev) {
		DevUtil.isDev = isDev;
	}
	
}
