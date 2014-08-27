package com.ebay.local.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.ebay.local.dto.AvailabilityResponse;

public class TestAvailabilityService {

	@Test
	public void testAvailableSuccessMacys() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		AvailaibilityService avail = new AvailaibilityService();

		AvailabilityResponse availabilityResponse = avail.getAvailability(
				"150", "1384193");
		assertTrue(availabilityResponse.getResponse().isAvailable());
		assertEquals("success", availabilityResponse.getResponse().getStatus());
		assertEquals("150", availabilityResponse.getResponse().getMerchantId());
		assertEquals("1384193", availabilityResponse.getResponse().getSku());
		assertEquals("macys.com", availabilityResponse.getResponse()
				.getMerchantName());
	}

	@Test
	public void testAvailableSuccessToysrus() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		AvailaibilityService avail = new AvailaibilityService();

		AvailabilityResponse availabilityResponse = avail.getAvailability(
				"250", "33308206");
		assertTrue(availabilityResponse.getResponse().isAvailable());
		assertEquals("success", availabilityResponse.getResponse().getStatus());
		assertEquals("250", availabilityResponse.getResponse().getMerchantId());
		assertEquals("33308206", availabilityResponse.getResponse().getSku());
		assertEquals("toysrus.com", availabilityResponse.getResponse()
				.getMerchantName());

	}

	@Test
	public void testAvailableToysrusNoAvailability() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		AvailaibilityService avail = new AvailaibilityService();

		AvailabilityResponse availabilityResponse = avail.getAvailability(
				"250", "379308256");
		assertFalse(availabilityResponse.getResponse().isAvailable());
		assertEquals("success", availabilityResponse.getResponse().getStatus());
		assertEquals("250", availabilityResponse.getResponse().getMerchantId());
		assertEquals("379308256", availabilityResponse.getResponse().getSku());
		assertEquals("toysrus.com", availabilityResponse.getResponse()
				.getMerchantName());

	}

	@Test
	public void testAvailableInvalidSiteId() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		AvailaibilityService avail = new AvailaibilityService();

		AvailabilityResponse availabilityResponse = avail.getAvailability(
				"2150", "379308256");
		assertFalse(availabilityResponse.getResponse().isAvailable());
		assertEquals("error", availabilityResponse.getResponse().getStatus());
		assertEquals("2150", availabilityResponse.getResponse().getMerchantId());
	}

	@Test
	public void testAvailableInvalidSiteIdString() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		AvailaibilityService avail = new AvailaibilityService();

		AvailabilityResponse availabilityResponse = avail.getAvailability(
				"21er50", "379308256");
		assertFalse(availabilityResponse.getResponse().isAvailable());
		assertEquals("error", availabilityResponse.getResponse().getStatus());
		assertEquals("21er50", availabilityResponse.getResponse()
				.getMerchantId());
	}

	@Test
	public void testAvailableInvalidSiteIdNegativeNumber() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		AvailaibilityService avail = new AvailaibilityService();

		AvailabilityResponse availabilityResponse = avail.getAvailability(
				"-150", "37930887987256");
		assertFalse(availabilityResponse.getResponse().isAvailable());
		assertEquals("error", availabilityResponse.getResponse().getStatus());
		assertEquals("-150", availabilityResponse.getResponse().getMerchantId());
	}

}
