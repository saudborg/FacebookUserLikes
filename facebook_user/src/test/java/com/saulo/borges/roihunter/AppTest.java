package com.saulo.borges.roihunter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.saulo.borges.roihunter.api.model.FacebookPage;
import com.saulo.borges.roihunter.api.model.FacebookPageDataList;
import com.saulo.borges.roihunter.api.model.FacebookPicture;
import com.saulo.borges.roihunter.api.model.FacebookPictureData;
import com.saulo.borges.roihunter.api.model.FacebookPicturePageData;
import com.saulo.borges.roihunter.api.model.FacebookUser;
import com.saulo.borges.roihunter.entity.FacebookUserEntity;
import com.saulo.borges.roihunter.exception.AppException;
import com.saulo.borges.roihunter.service.FacebookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@WebAppConfiguration
@TestPropertySource(locations = "classpath:test.properties")
public class AppTest {

	@Autowired
	private FacebookService service;

	private FacebookUser entity;

	@Before
	public void setup() {
		this.entity = new FacebookUser();
		entity.setId("0");
		entity.setName("Test");
		entity.setGender("unknow");
		FacebookPictureData pictureData = new FacebookPictureData();
		FacebookPicture picture = new FacebookPicture();
		picture.setUrl("www.google.com.br");
		pictureData.setData(picture);
		entity.setPicture(pictureData);

		FacebookPage page1 = new FacebookPage();
		page1.setDescription("Description Text");
		page1.setId("0");
		page1.setName("Test Page");
		FacebookPicturePageData pictureEventData = new FacebookPicturePageData();
		FacebookPicture picture2 = new FacebookPicture();
		picture2.setUrl("www.facebook.com.br");
		pictureEventData.setData(picture2);
		page1.setPicture(pictureEventData);

		List<FacebookPage> data = new ArrayList<FacebookPage>();
		FacebookPageDataList list = new FacebookPageDataList();
		list.setData(data);
		entity.setLikes(list);
	}

	@Test
	public void saveEntity() throws AppException {
		// save
		service.saveUser(entity);

		// verify if it saved
		FacebookUserEntity userEntity = service.findById("0");
		assertEquals(entity.getId(), userEntity.getId());
		assertEquals(entity.getName(), userEntity.getName());
		assertEquals(entity.getGender(), userEntity.getGender());

	}

}
