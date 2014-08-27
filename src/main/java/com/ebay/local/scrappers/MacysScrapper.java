package com.ebay.local.scrappers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebay.local.interfaces.Scrapper;

public class MacysScrapper implements Scrapper {
//http://www1.macys.com/shop/product/seventeen-mariposa-decorative-pillow-set?ID=1384193
	private static final Logger logger = LoggerFactory
			.getLogger(MacysScrapper.class);

//	static MacysScrapper macys = new MacysScrapper("1384193");
//	public static void main(String args[]){
//		macys.scrape();
//	}
	
	private String url = "http://www1.macys.com/shop/product/seventeen-mariposa-decorative-pillow-set?ID=";
	public String getUrl() {
		return url;
	}
	private void setUrl(String url) {
		this.url = url;
	}
	public MacysScrapper()
	{
	  
	}
	private String productId;
	
	public MacysScrapper(String productId)
	{
		this.setUrl(this.getUrl()+productId);
	}
	 
	public  boolean scrape()
	{
		logger.info("Trying to scrape Macys:{}", this.getUrl());
		Document doc;
		try {
			doc = Jsoup.connect(this.getUrl()+this.getProductId()).get();
			Elements av = doc.getElementsByClass("addToBagButton");
			
			if(av.hasClass("addToBagButton"))
					return true;
			 

		} catch ( Exception e) {
		System.out.println("Error:"+ e);
		}
		return false;

	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setSKU(String sku) {
		this.productId =sku;
		
	}
	
}
