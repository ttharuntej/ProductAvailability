package com.ebay.local.cache;

import redis.clients.jedis.Jedis;

public class JedisCache {
  private Jedis jedis;

  public static class JedisCacheSingleton {
    public static JedisCache instance = new JedisCache();

  }

  private JedisCache() {
    setJedis(new Jedis("localhost"));

  }

  public static JedisCache getInstance() {
    return JedisCacheSingleton.instance;
  }

  public Jedis getJedis() {
    return jedis;
  }

  public void setJedis(Jedis jedis) {
    this.jedis = jedis;
  }

}