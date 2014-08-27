package com.ebay.local.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ebay.local.dto.AvailabilityResponse;
import com.ebay.local.merchants.Merchants;

public class Utils {
  public static boolean isInteger(String s) {
    return isInteger(s, 10);
  }

  private static boolean isInteger(String s, int radix) {
    if (s.isEmpty())
      return false;
    for (int i = 0; i < s.length(); i++) {
      if (i == 0 && s.charAt(i) == '-') {
        if (s.length() == 1)
          return false;
        else
          continue;
      }
      if (Character.digit(s.charAt(i), radix) < 0)
        return false;
    }
    return true;
  }

  public static Map<String, String> serialize(AvailabilityResponse.Response response) {
    Map<String, String> responseProperties = new HashMap<String, String>();
    responseProperties.put("sku", response.getSku());
    responseProperties.put("availability", String.valueOf(response.isAvailable()));
    responseProperties.put("status", response.getStatus());
    responseProperties.put("merchantId", response.getMerchantId());
    responseProperties.put("merchantName", response.getMerchantName());
    responseProperties.put("message", response.getMessage());
    responseProperties.put("statusCode", String.valueOf(response.getStatusCode()));
    return responseProperties;

  }

  public static AvailabilityResponse.Response deserialize(Map<String, String> responseProperties) {
    AvailabilityResponse.Response response = new AvailabilityResponse.Response();

    boolean available = StringUtils.isEmpty(responseProperties.get("availability")) ? false : responseProperties.get(
        "availability").equalsIgnoreCase("true");

    response.setAvailable(available);
    response.setMerchantId(StringUtils.isEmpty(responseProperties.get("merchantId")) ? "null" : responseProperties
        .get("merchantId"));
    response.setMerchantName(StringUtils.isEmpty(responseProperties.get("merchantName")) ? "null" : responseProperties
        .get("merchantName"));
    response.setStatus(StringUtils.isEmpty(responseProperties.get("status")) ? "error" : responseProperties.get("status"));
    response.setSku(StringUtils.isEmpty(responseProperties.get("sku")) ? "null" : responseProperties.get("sku"));
    response.setMessage(StringUtils.isEmpty(responseProperties.get("message")) ? "null" : responseProperties.get("message"));
    response.setStatusCode((Integer.parseInt(responseProperties.get("statusCode"))));
    return response;
  }
}
