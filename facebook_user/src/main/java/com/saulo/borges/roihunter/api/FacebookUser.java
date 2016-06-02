package com.saulo.borges.roihunter.api;

import java.io.Serializable;
import java.util.List;

public class FacebookUser implements Serializable {

	private static final long serialVersionUID = -2083886374988478523L;

	private String id;

	private String name;

	private String gender;

	private FacebookPictureData picture;

	private FacebookPageDataList likes;

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

	public List<FacebookPage> getLikes() {
		return likes.getData();
	}

	public void setLikes(FacebookPageDataList likes) {
		this.likes = likes;
	}

	public FacebookPageDataList getData() {
		return likes;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "FacebookUser [id=" + id + ", name=" + name + ", gender=" + gender + ", likes=" + likes + "]";
	}

	public FacebookPictureData getPicture() {
		return picture;
	}

	public void setPicture(FacebookPictureData picture) {
		this.picture = picture;
	}

}
