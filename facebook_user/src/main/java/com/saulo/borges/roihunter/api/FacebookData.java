package com.saulo.borges.roihunter.api;

import java.util.List;

public class FacebookData<T> {

	private List<T> data;
	
	private FacebookPaging paging;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public FacebookPaging getPaging() {
		return paging;
	}

	public void setPaging(FacebookPaging paging) {
		this.paging = paging;
	}
}
