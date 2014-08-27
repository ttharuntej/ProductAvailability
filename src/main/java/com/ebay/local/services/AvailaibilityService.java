package com.ebay.local.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtilsBean;

import redis.clients.jedis.Jedis;

import com.ebay.local.cache.JedisCache;
import com.ebay.local.dto.AvailabilityResponse;
import com.ebay.local.factoryPattern.MerchantsFactory;
import com.ebay.local.interfaces.Scrapper;
import com.ebay.local.merchants.Merchants;
import com.ebay.local.utils.Utils;

@Path("/availability")
public class AvailaibilityService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public AvailabilityResponse getAvailability(
			@QueryParam("siteId") String merchantId,
			@QueryParam("sku") String sku) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		AvailabilityResponse availabilityResponse = new AvailabilityResponse();
		String key = merchantId + "#@#" + sku;
		JedisCache cache = JedisCache.getInstance();
		Jedis jedis = cache.getJedis();
		jedis.expire(key, 5);

		if (Utils.isInteger(merchantId)
				&& Merchants.isValidmerchantId(Integer.parseInt(merchantId))) {
			// Check in Cache

			if (!jedis.exists(key)) {
				System.out.println("Scrapping Webpage:" + key);
				// Scrapper webScrapper =
				// WebsitesFactory.getScrapperSite(Integer.parseInt(merchantId),
				// sku);
				Scrapper webScrapper = Merchants.getScrapperObject(Integer
						.parseInt(merchantId));
				webScrapper.setSKU(sku);
				AvailabilityResponse.Response response = new AvailabilityResponse.Response();
				response.setAvailable(webScrapper.scrape());
				response.setMerchantId(merchantId);
				response.setMerchantName(Merchants.getSiteName(Integer
						.parseInt(merchantId)));
				response.setStatus("success");
				response.setSku(sku);
				response.setMessage("Your Item is available.");
				response.setStatusCode(200);
				availabilityResponse.setResponse(response);
				Map<String, String> properties = Utils.serialize(response);
				jedis.hmset(key, properties);
			} else {
				System.out.println("Building from cache" + key);
				Map<String, String> responseProperties = jedis.hgetAll(key);
				AvailabilityResponse.Response response = Utils
						.deserialize(responseProperties);
				availabilityResponse.setResponse(response);
			}
		} else {

			if (!jedis.exists(key)) {
				AvailabilityResponse.Response response = new AvailabilityResponse.Response();
				response.setStatus("error");
				response.setSku("-00000");
				response.setMerchantName("Invalid Name");
				response.setMerchantId(merchantId);
				response.setMessage("Invalid Site Id.");
				response.setStatusCode(400);
				availabilityResponse.setResponse(response);

				Map<String, String> properties = Utils.serialize(response);
				jedis.hmset(key, properties);
			} else {
				Map<String, String> responseProperties = jedis.hgetAll(key);

				AvailabilityResponse.Response response = Utils
						.deserialize(responseProperties);
				availabilityResponse.setResponse(response);

			}

		}

		return availabilityResponse;
	}

}
