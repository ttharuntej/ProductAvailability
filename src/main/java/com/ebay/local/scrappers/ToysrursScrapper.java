package com.ebay.local.scrappers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebay.local.interfaces.Scrapper;

public class ToysrursScrapper implements Scrapper {
	private static final Logger logger = LoggerFactory
			.getLogger(ToysrursScrapper.class);

	public static void main(String[] args) {

		ToysrursScrapper scrp = new ToysrursScrapper("33308206");
		System.out.println(scrp.scrape());

	}

	private static final String IN_STOCK = "IS";
	private String url = "http://www.toysrus.com/product/index.jsp?productId=";

	public String getUrl() {
		return url;
	}

	private void setUrl(String url) {
		this.url = url;
	}

	public ToysrursScrapper()
	{
	  
	}
	private String productId;
	public ToysrursScrapper(String productId) {
		this.setUrl(this.getUrl() + productId);

	}

	public boolean scrape() {
		logger.info("Trying to scrape Toysrus and url is :{}", this.getUrl());
		Document doc;
		try {
			doc = Jsoup.connect(this.getUrl()+this.productId).get();
			Element av = doc.getElementById("eligibility");
			String stockValue = av.children().get(1).attr("value");
			logger.info("Received stock value from toysrus:{}", stockValue);
			if (stockValue.equals(IN_STOCK))
				return true;

		} catch (Exception e) {

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
		this.productId = sku;
		
	}

}
