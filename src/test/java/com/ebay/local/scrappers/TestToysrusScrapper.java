package com.ebay.local.scrappers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestToysrusScrapper {

	@Test
	public void TestAvailableProduct() {
		ToysrursScrapper toysrursScrapper = new ToysrursScrapper("33308206");
		assertTrue(toysrursScrapper.scrape());
	}

	@Test
	public void TestNotAvailableProduct() {
		ToysrursScrapper macysScrapper = new ToysrursScrapper("138ew4193");
		assertFalse(macysScrapper.scrape());
	}
	
	@Test
	public void TestNullProduct() {
		ToysrursScrapper macysScrapper = new ToysrursScrapper(null);
		assertFalse(macysScrapper.scrape());
	}
}
