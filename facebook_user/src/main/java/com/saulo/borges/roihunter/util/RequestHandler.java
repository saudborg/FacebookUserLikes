package com.saulo.borges.roihunter.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.client.methods.HttpPost;

public interface RequestHandler {

	public String getCharacterEncoding();

	public void setCharacterEncoding(String characterEncoding);

	public InputStream getInputStream(String uri) throws IOException;

	public String get(String uri) throws IOException;

	public String post(HttpPost data) throws IOException;

	String getWithHeader(String uri, Header[] headers) throws IOException;
}
