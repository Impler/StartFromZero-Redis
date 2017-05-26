package com.study.redis._02datatype;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Lists {

	public static void main(String[] args) {

		Jedis jedis = new Jedis("localhost");
		
		System.out.println("lpush: " + jedis.lpush("mylist", "v1", "v2", "v3"));
		System.out.println("lpush: " + jedis.rpush("mylist", "v4"));
		long length = jedis.llen("mylist");
		System.out.println("list length: " + length);
		System.out.print("lindex: ");
		for(int i=0; i<length; i++){
			System.out.print(jedis.lindex("mylist", i) + ", ");
		}
		System.out.println();
		List<String> values = jedis.lrange("mylist", 0, length);
		System.out.println("range: " + values);
		jedis.del("mylist");
		jedis.close();
	}

}
