package com.ebay.local.scrappers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestMacysScrapper {

	@Test
	public void TestAvailableProduct() {
		MacysScrapper macysScrapper = new MacysScrapper("1384193");
		assertTrue(macysScrapper.scrape());
	}

	@Test
	public void TestNotAvailableProduct() {
		MacysScrapper macysScrapper = new MacysScrapper("138ew4193");
		assertFalse(macysScrapper.scrape());
	}
	
	@Test
	public void TestNullProduct() {
		MacysScrapper macysScrapper = new MacysScrapper(null);
		assertFalse(macysScrapper.scrape());
	}
}
