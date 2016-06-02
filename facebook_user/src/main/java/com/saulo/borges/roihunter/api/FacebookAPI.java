package com.saulo.borges.roihunter.api;

import static com.saulo.borges.roihunter.util.ApiUtils.buildUrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
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

	private JSONObject getResponse(String uri) throws IOException {
		String raw = requestHandler.get(uri);
		return new JSONObject(raw);
	}

	public FacebookUser getUserData(String fb_id, String token) throws JSONException, Exception {
		String uri = buildUrl(API_URL, fb_id, String.format("access_token=%s", token),
				Param.name("fields").value("id,name,likes"));
		JSONObject jsonResponse = getResponse(uri);
		FacebookUser facebookUser = gson.fromJson(jsonResponse.toString(), FacebookUser.class);

		if (facebookUser.getId() == null)
			throw new Exception(jsonResponse.getString(jsonResponse.getJSONObject("error").getString("message")));

		FacebookPaging paging = facebookUser.getData().getPaging();
		while (paging != null && paging.getNext() != null) {
			JSONObject pages = getResponse(paging.getNext());
			
			/**
			 * TODO : TENTAR REFAZER ##########################################################
			 */
			FacebookData data = gson.fromJson(pages.toString(), FacebookData.class);

			Iterator<LinkedTreeMap<String, String>> iterator = data.getData().iterator();
			while (iterator.hasNext()) {
				LinkedTreeMap<String, String> entity = iterator.next();
				FacebookPage page = new FacebookPage(entity.get("id"), entity.get("name"));
				facebookUser.getLikes().add(page);
			}
			
			/**
			 *  ################################################################################
			 */
			paging = data.getPaging();
		}

		return facebookUser;
	}

	public static void main(String[] args) throws JSONException, Exception {

		String a = "{created_time=2015-11-18T16:04:26+0000, name=The LAD Bible, id=199098633470668}";
		// String b = "{\"created_time\"=\"2015-11-18T16:04:26+0000\",
		// \"name\"=\"The LAD Bible\", \"id\"=\"199098633470668\"}";
		Gson gson = new Gson();
		String json = gson.toJson(a);
		// FacebookPage fromJson = gson.fromJson(json, FacebookPage.class);

		FacebookAPI api = new FacebookAPI();
		api.getUserData("100001878037970",
				"EAACEdEose0cBAA4MrZBI93FEsJjVOBEkDNu28P7Mjdkr2bYOUdZC2Td94wQCfrS4TPHjTICzBJx39l5fjabGEl96I7kgKCh0MM10oYaP0rPGsyxbee98k8CA8IaGPe3YLqdaq8vldCPFdRNkQ7e3ug2VrXBOvUDB0eggarsAZDZD");
	}

}