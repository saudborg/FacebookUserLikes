package com.saulo.borges.roihunter.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.saulo.borges.roihunter.api.FacebookPage;
import com.saulo.borges.roihunter.api.FacebookUser;

@Entity
public class FacebookUserEntity implements Serializable {

	private static final long serialVersionUID = -5399272199373908413L;

	@Id
	private String id;

	private String name;

	private String gender;

	private String imageUrl;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<FacebookPageEntity> pages;

	public FacebookUserEntity() {
	}

	public FacebookUserEntity(FacebookUser user) {
		this.id = user.getId();
		this.name = user.getName();
		this.pages = new ArrayList<FacebookPageEntity>();
		for (FacebookPage page : user.getLikes()) {
			pages.add(new FacebookPageEntity(page));
		}
		this.gender = user.getGender();
		this.imageUrl = user.getPicture() != null ? user.getPicture().getData().getUrl() : null;
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

	public List<FacebookPageEntity> getPages() {
		return pages;
	}

	public void setPages(List<FacebookPageEntity> pages) {
		this.pages = pages;
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

}
