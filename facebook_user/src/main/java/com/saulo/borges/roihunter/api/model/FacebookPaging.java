package com.saulo.borges.roihunter.api.model;

import java.io.Serializable;

public class FacebookPaging implements Serializable {

	private static final long serialVersionUID = -2212875491884935606L;

	private String next;

	public FacebookPaging() {
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

}
