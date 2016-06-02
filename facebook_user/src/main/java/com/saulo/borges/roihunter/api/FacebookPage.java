package com.saulo.borges.roihunter.api;

import java.io.Serializable;

public class FacebookPage implements Serializable{
	
	private static final long serialVersionUID = 3001362793258614357L;

	private String name;
	
	private String id;
	
	private String description;
	
	private FacebookPictureEventData picture;
	
	public FacebookPage() {
	}

	public FacebookPage(String id, String name) {
		this.id = id;
		this.name = name;
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
		return "FacebookPage [name=" + name + ", id=" + id + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FacebookPictureEventData getPicture() {
		return picture;
	}

	public void setPicture(FacebookPictureEventData picture) {
		this.picture = picture;
	}


}
