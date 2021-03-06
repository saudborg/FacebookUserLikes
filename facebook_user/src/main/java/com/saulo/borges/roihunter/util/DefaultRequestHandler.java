package com.saulo.borges.roihunter.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * This class I copied in one of my personal projects which I work with a lot of
 * public API's
 * 
 * @author sauloborges
 *
 */
public class DefaultRequestHandler implements RequestHandler {

	public static final String DEFAULT_CHARACTER_ENCODING = "UTF-8";
	private HttpClient client = HttpClientBuilder.create().build();
	private String characterEncoding;

	public DefaultRequestHandler(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public DefaultRequestHandler() {
		this(DEFAULT_CHARACTER_ENCODING);
	}

	@Override
	public String getCharacterEncoding() {
		return characterEncoding;
	}

	@Override
	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	private String readString(HttpResponse response) throws IOException {
		String str = IOUtils.toString(response.getEntity().getContent(), characterEncoding);
		if (str == null || str.trim().length() == 0) {
			return null;
		}
		return str.trim();
	}

	@Override
	public InputStream getInputStream(String uri) throws IOException {
		try {
			HttpGet get = new HttpGet(uri);
			return client.execute(get).getEntity().getContent();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public String get(String uri) throws IOException {
		try {
			HttpGet get = new HttpGet(uri);
			client = HttpClientBuilder.create().build();
			get.setHeader("Content-Type", "text/plain; charset=utf-8");
			return readString(client.execute(get));
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public String getWithHeader(String uri, Header[] headers) throws IOException {
		try {
			HttpGet get = new HttpGet(uri);
			get.setHeaders(headers);
			HttpResponse execute = client.execute(get);
			return readString(execute);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public String post(HttpPost data) throws IOException {
		try {
			return readString(client.execute(data));
		} catch (Exception e) {
			throw new IOException(e);
		}
	}
}
