package memcached.sample;

import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class Sample1 {

	public static void main(String[] args) throws Exception {
		MemcachedClient c = new MemcachedClient(new InetSocketAddress("192.168.33.11", 11211));
		
		c.set("someKey", 3600, 12);
		Object myObject = c.get("someKey");
		System.out.print(myObject);
	}

}
