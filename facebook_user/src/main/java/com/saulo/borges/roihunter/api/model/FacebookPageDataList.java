package com.saulo.borges.roihunter.api.model;

import java.util.List;

public class FacebookPageDataList {

	private List<FacebookPage> data;
	
	private FacebookPaging paging;

	public FacebookPaging getPaging() {
		return paging;
	}

	public void setPaging(FacebookPaging paging) {
		this.paging = paging;
	}

	public List<FacebookPage> getData() {
		return data;
	}

	public void setData(List<FacebookPage> data) {
		this.data = data;
	}
}
