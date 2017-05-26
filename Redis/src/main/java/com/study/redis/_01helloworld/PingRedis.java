package com.study.redis._01helloworld;

import redis.clients.jedis.Jedis;

public class PingRedis {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1");
		System.out.println("ping: " + jedis.ping());
		jedis.close();
	}
}
