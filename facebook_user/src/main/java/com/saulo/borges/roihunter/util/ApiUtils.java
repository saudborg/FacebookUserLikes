package com.saulo.borges.roihunter.util;

import java.util.Locale;

public class ApiUtils {

	protected static String buildUrlJson(String apiUrl, String method, String params, Param... extraParams) {
		String url = String.format(Locale.ENGLISH, "%s%s/json?%s", apiUrl, method, params);
		url = addExtraParams(url, extraParams);
		url = url.replace(' ', '+');
		return url;
	}
	
	public static String buildUrl(String apiUrl, String method, String params, Param... extraParams) {
		String url = String.format(Locale.ENGLISH, "%s%s?%s", apiUrl, method, params);
		url = addExtraParams(url, extraParams);
		url = url.replace(' ', '+');
		return url;
	}
	
	private static String addExtraParams(String base, Param... extraParams) {
		for (Param param : extraParams) {
			base += "&" + param.name + (param.value != null ? "=" + param.value : "");
		}
		return base;
	}
}
