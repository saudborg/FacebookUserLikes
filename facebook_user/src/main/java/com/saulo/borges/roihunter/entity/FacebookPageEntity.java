package com.saulo.borges.roihunter.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.saulo.borges.roihunter.api.FacebookPage;

@Entity
public class FacebookPageEntity implements Serializable {

	private static final long serialVersionUID = -5553806890354466808L;

	@Id
	private String id;

	private String name;

	private String description;

	private String image_url;

	public FacebookPageEntity() {
	}

	public FacebookPageEntity(FacebookPage page) {
		this.id = page.getId();
		this.name = page.getName();
		this.description = page.getDescription();
		this.image_url = page.getPicture() != null ? page.getPicture().getData().getUrl() : null;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

}
