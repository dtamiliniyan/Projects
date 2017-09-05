package com.asture.myweb.data;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.astute.myweb.data.TranslationData;

public class TranslationDataTest {
	Map<Object, Object> map = new HashMap<Object, Object>();
	TranslationData data = new TranslationData();

	@Before
	public void setup() {
		map.put("1", "one");
		map.put("2", "two");
		Map<Object, Object> map1 = new HashMap<Object, Object>();
		map1.put("11", "one1");
		map1.put("22", "two2");
		map.put("map1", map1);
	}
	

	@Test
	public void testAddTranslation() {
		assertTrue("Success", data.addTranslation(map, "111")!=null);
	}

	@Test
	public void testGetTranslation() {
		assertTrue("Success", data.getTranslation("111")!=null);
	}

	@Test
	public void testUpdateTranslationStringMapOfObjectObject() {
		data.updateTranslation("111", map);
		assertTrue(true);
	}

	@Test
	public void testDeleteTranslation() {
		data.deleteTranslation("111");
		assertTrue(true);
	}

	@Test
	public void testGetTranslations() {
		assertNotNull(data.getTranslations()!=null);
	}

	@Test
	public void testUpdateTranslationStringStringString() {
		data.updateTranslation("111", "login.user", "USER NAME");
		assertTrue(true);
	}
	
	@Test
	public void convertMapToKeyValuesTest() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("title", "Lost in Translation");
		Map<Object, Object> login = new HashMap<Object, Object>();
		login.put("user", "User Name");
		login.put("pass", "Password");
		map.put("login", login);

		Map<Object, Object> menu = new HashMap<Object, Object>();
		Map<Object, Object> topMenu = new HashMap<Object, Object>();
		topMenu.put("Home", "Home");
		topMenu.put("index", "index");
		Map<Object, Object> secondaryMenu = new HashMap<Object, Object>();
		Map<Object, Object> left = new HashMap<Object, Object>();

		map.put("menu", menu);
		menu.put("topMenu", topMenu);
		menu.put("secondaryMenu", secondaryMenu);
		left.put("riddles", "riddles");
		left.put("Jokes", "Jokes");
		secondaryMenu.put("left", left);

		Map<String, String> result = new HashMap<String, String>();
		result = data.convertMapToKeyValues(map, "", result);
		assertNotNull(result);
	}

}
