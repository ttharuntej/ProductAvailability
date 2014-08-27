package com.ebay.local.merchants;

import java.util.HashMap;
import java.util.Map;

import com.ebay.local.interfaces.Scrapper;
import com.ebay.local.scrappers.MacysScrapper;
import com.ebay.local.scrappers.ToysrursScrapper;

public enum Merchants {

  MACYS(150, "macys.com", new MacysScrapper()), TOYSRUS(250, "toysrus.com", new ToysrursScrapper());
  private Integer id;
  private String url;
  private Scrapper scrapper;

  Merchants(Integer id, String name, Scrapper scrapper) {
    this.id = id;
    this.url = name;
    this.scrapper = scrapper;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Scrapper getScrapper() {
    return scrapper;
  }

  public void setScrapper(Scrapper scrapper) {
    this.scrapper = scrapper;
  }

  public String getName() {
    return url;
  }

  public void setName(String name) {
    this.url = name;
  }

  private static Map<Integer, String> map = new HashMap<Integer, String>();

  static {
    for (Merchants website : Merchants.values()) {
      map.put(website.getId(), website.getName());
    }
  }

  public static boolean isValidmerchantId(Integer merchantId) {
    return map.containsKey(merchantId);
  }

  public static String getSiteName(int merchantId) {
    return map.get(merchantId);
  }

  private static Map<Integer, Scrapper> scrapperObjects = new HashMap<Integer, Scrapper>();
  static {
    for (Merchants website : Merchants.values()) {
      scrapperObjects.put(website.getId(), website.getScrapper());
    }
  }

  public static  Scrapper getScrapperObject(Integer merchantId) {
    return scrapperObjects.get(merchantId);
  }
}
