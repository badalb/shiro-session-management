package com.test.shiro.memcached;

import java.io.IOException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class MemcachedUtil {

	private static final String memCachedHost = "<memcached_host_ip_here>";

	private static final String port = "<memcached_port>";

	private static MemcachedClient client;

	private static MemcachedClient getMemCachedClient() {
		if (client == null) {
			try {
				client = new MemcachedClient(
						AddrUtil.getAddresses(memCachedHost + ":" + port));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	public static void put(String key, Object value) {
		getMemCachedClient().set(key, 900, value);
	}

	public static Object get(String key) {
		Object obj = getMemCachedClient().get(key);
		return obj;
	}

	public static void remove(String key) {
		getMemCachedClient().delete(key);
	}

	public void destroy() {
		if (client != null) {
			client.shutdown();
		}
	}

}
