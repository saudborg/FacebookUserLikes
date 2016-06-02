package com.saulo.borges.roihunter.api;

import static com.saulo.borges.roihunter.util.ApiUtils.buildUrl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.saulo.borges.roihunter.exception.AppException;
import com.saulo.borges.roihunter.util.DefaultRequestHandler;
import com.saulo.borges.roihunter.util.Param;
import com.saulo.borges.roihunter.util.RequestHandler;

public class FacebookAPI {

	private static final Logger log = LoggerFactory.getLogger(FacebookAPI.class);

	private RequestHandler requestHandler;

	private static final String API_HOST = "https://graph.facebook.com";
	private static final String API_VERSION = "/v2.6/";

	private static final String API_URL = API_HOST + API_VERSION;

	private static final Gson gson = new Gson();

	public FacebookAPI() {
		this.requestHandler = new DefaultRequestHandler();
	}

	private JSONObject getResponse(String uri) throws AppException {
		String raw;
		try {
			raw = requestHandler.get(uri);
		} catch (IOException e) {
			e.printStackTrace();
			throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 500, "Error while getting the url.");
		}
		return new JSONObject(raw);
	}

	public FacebookUser getUserData(String fb_id, String token) throws AppException {

		if (fb_id == null || token == null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400,
					"Please set the Facebook ID and the valid access token");
		}

		String encode;
		try {
			encode = URLEncoder.encode("likes{description,id,name,picture}", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 500, "Error while encoding the url.");
		}

		String uri = buildUrl(API_URL, fb_id, String.format("access_token=%s", token),
				Param.name("fields").value("name,id,gender,picture," + encode));
		JSONObject jsonResponse = getResponse(uri);
		FacebookUser facebookUser = gson.fromJson(jsonResponse.toString(), FacebookUser.class);

		if (facebookUser.getId() == null)
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 500,
					jsonResponse.getString(jsonResponse.getJSONObject("error").getString("message")));

		FacebookPaging paging = facebookUser.getData().getPaging();
		while (paging != null && paging.getNext() != null) {
			JSONObject pages = getResponse(paging.getNext());

			FacebookPageDataList data = gson.fromJson(pages.toString(), FacebookPageDataList.class);
			facebookUser.getLikes().addAll(data.getData());
			paging = data.getPaging();
		}

		return facebookUser;
	}

	public static void main(String[] args) throws JSONException, Exception {

		// String encode =
		// URLEncoder.encode("likes{description,id,name,picture}", "UTF-8");
		// String a =
		// "https://graph.facebook.com/v2.6/100001878037970?access_token=EAACEdEose0cBAKjo6BgPRwITX5pZC99FFp9ubcUBxC14sEaDrTkBU5ca6Ce74f3wRXL8QbGDsgtXx94sYHhxMZCj3J26gW0lfJoRVSXmDJZBFJe4XUq76UwyuZBR71xPJhVrIZB8RnqfZC17DgVWsf7kaSZBlzOQbZATosARmkb35wZDZD&fields=name,id,gender,picture,"
		// + encode;
		// System.out.println(a);
		FacebookAPI api = new FacebookAPI();
		api.getUserData("100001878037970",
				"EAACEdEose0cBALDgI2ef4dy5FZBiUztPCveVnZBkXCN2AMZBhMobOpSnSSdNmJmXcQUdW4xtrGZCiVvacZBmr1RZAds3Q84PPEfcl1ZAwxwZBKFQocJrqHC58v70i6CxaHRNZA2CTbGlKqRhHoWZBuufawCdrfSgxeXgf1ZApc7VTqzMwZDZD");
	}

}