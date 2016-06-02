package com.saulo.borges.roihunter.api;

import java.io.Serializable;
import java.util.List;

public class FacebookUser implements Serializable {

	private static final long serialVersionUID = -2083886374988478523L;

	private String id;

	private String name;
	
	private FacebookData<FacebookPage> likes;
	
	public FacebookUser() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FacebookUser [id=" + id + ", name=" + name + "]";
	}

	public List<FacebookPage> getLikes() {
		return likes.getData();
	}

	public void setLikes(FacebookData<FacebookPage> likes) {
		this.likes = likes;
	}
	
	public FacebookData<FacebookPage> getData(){
		return likes;
	}


}
