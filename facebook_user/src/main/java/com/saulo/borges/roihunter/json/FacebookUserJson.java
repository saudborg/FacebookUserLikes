package com.saulo.borges.roihunter.json;

import java.io.Serializable;

import com.saulo.borges.roihunter.entity.FacebookUserEntity;

public class FacebookUserJson implements Serializable {

	private static final long serialVersionUID = 1081851242165015641L;

	private String id;

	private String name;

	private String gender;

	private String imageUrl;

	public FacebookUserJson() {
	}

	public FacebookUserJson(FacebookUserEntity entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
		this.gender = entity.getGender();
		this.imageUrl = entity.getImageUrl();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "FacebookUserJson [id=" + id + ", name=" + name + ", gender=" + gender + ", imageUrl=" + imageUrl + "]";
	}
	
	

}
