package com.saulo.borges.roihunter.api;

import java.io.Serializable;

public class FacebookPicture implements Serializable{
	
	private static final long serialVersionUID = -1981906045481808465L;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "FacebookPicture [url=" + url + "]";
	}
	
	

}
