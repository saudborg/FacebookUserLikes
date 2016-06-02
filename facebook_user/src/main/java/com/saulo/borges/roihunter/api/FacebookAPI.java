package com.saulo.borges.roihunter.api;

import static com.saulo.borges.roihunter.util.ApiUtils.buildUrl;

import java.io.IOException;

import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.saulo.borges.roihunter.api.model.FacebookPageDataList;
import com.saulo.borges.roihunter.api.model.FacebookPaging;
import com.saulo.borges.roihunter.api.model.FacebookUser;
import com.saulo.borges.roihunter.exception.AppException;
import com.saulo.borges.roihunter.util.ApiUtils;
import com.saulo.borges.roihunter.util.DefaultRequestHandler;
import com.saulo.borges.roihunter.util.Param;
import com.saulo.borges.roihunter.util.RequestHandler;
/**
 * Represents a call for Facebook's api
 * @author sauloborges
 *
 */
public class FacebookAPI {

	private RequestHandler requestHandler;

	private static final String API_HOST = "https://graph.facebook.com";
	private static final String API_VERSION = "/v2.6/";

	private static final String API_URL = API_HOST + API_VERSION;

	private static final Gson gson = new Gson();

	public FacebookAPI() {
		this.requestHandler = new DefaultRequestHandler();
	}

	private JSONObject getResponse(String uri) throws AppException {
		try {
			String raw = requestHandler.get(uri);
			return new JSONObject(raw);
		} catch (IOException e) {
			e.printStackTrace();
			throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), 500,
					"Error while getting the url.");
		}
	}

	public FacebookUser getUserData(String fb_id, String token) throws AppException {

		if (fb_id == null || token == null) {
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 400,
					"Please set the Facebook ID and the valid access token");
		}

		// Was needed this encode because the character '{' wasn't be accepted
		// in get url
		String encode = ApiUtils.encodeUrl("likes{description,id,name,picture}");

		String uri = buildUrl(API_URL, fb_id, String.format("access_token=%s", token),
				Param.name("fields").value("name,id,gender,picture," + encode));
		JSONObject jsonResponse = getResponse(uri);
		FacebookUser facebookUser = gson.fromJson(jsonResponse.toString(), FacebookUser.class);

		if (facebookUser.getId() == null)
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(), 500,
					jsonResponse.getString(jsonResponse.getJSONObject("error").getString("message")));

		// Pagination of liked pages
		FacebookPaging paging = facebookUser.getData().getPaging();
		while (paging != null && paging.getNext() != null) {
			JSONObject pages = getResponse(paging.getNext());

			FacebookPageDataList data = gson.fromJson(pages.toString(), FacebookPageDataList.class);
			facebookUser.getLikes().addAll(data.getData());
			paging = data.getPaging();
		}

		return facebookUser;
	}

}