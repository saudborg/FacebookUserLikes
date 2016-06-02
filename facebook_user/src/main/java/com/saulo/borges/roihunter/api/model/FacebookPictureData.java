package com.saulo.borges.roihunter.api.model;

import java.io.Serializable;

public class FacebookPictureData implements Serializable{
	
	private static final long serialVersionUID = -2095782138948588981L;

	private FacebookPicture data;

	public FacebookPicture getData() {
		return data;
	}

	public void setData(FacebookPicture data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "FacebookPictureUser [data=" + data + "]";
	}
	
	

}
