package com.study.redis._02datatype;

import redis.clients.jedis.Jedis;

public class Strings {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("ping: " + jedis.ping());
		
		System.out.println("set mykey: " + jedis.set("mykey", "myvalue"));
		System.out.println("get mykey: " + jedis.get("mykey"));
		System.out.println("del mykey: " + jedis.del("mykey"));
		jedis.close();
	}

}
