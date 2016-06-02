package com.saulo.borges.roihunter.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

import javax.ws.rs.core.Response;

import com.saulo.borges.roihunter.exception.AppException;

/**
 * This class represents a help class which helps to build the url in the
 * correct form
 * 
 * @author sauloborges
 *
 */
public class ApiUtils {

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

	public static String encodeUrl(String url) throws AppException {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 500,
					"Error while encoding the url.");
		}
	}
}
