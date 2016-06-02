package com.saulo.borges.roihunter.form;

import java.io.Serializable;

public class FacebookUserForm implements Serializable{

	private static final long serialVersionUID = 4846454474086223735L;

	private String FB_ID;

	private String access_token;

	public String getFB_ID() {
		return FB_ID;
	}

	public void setFB_ID(String fB_ID) {
		FB_ID = fB_ID;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public FacebookUserForm(String fB_ID, String access_token) {
		super();
		FB_ID = fB_ID;
		this.access_token = access_token;
	}

	public FacebookUserForm() {
	}

}
