package com.ebay.local.factoryPattern;

import com.ebay.local.interfaces.Scrapper;
import com.ebay.local.scrappers.MacysScrapper;
import com.ebay.local.scrappers.ToysrursScrapper;

public class MerchantsFactory {

	public static Scrapper getScrapperSite(int siteId, String productId) {
		Scrapper scrapper = null;

		switch (siteId) {

		case 150:
			scrapper = new MacysScrapper(productId);
			break;
		case 250:
			scrapper = new ToysrursScrapper(productId);
			break;
			
		 default: 

		}

		return scrapper;
	}
}
