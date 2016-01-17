package memcached.sample;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import net.spy.memcached.MemcachedClient;

public class Sample1 {

	public static void main(String[] args) throws Exception {
		List<InetSocketAddress> list = new ArrayList<>();
		list.add(new InetSocketAddress("192.168.33.11", 11211));
		list.add(new InetSocketAddress("192.168.33.11", 11212));
		MemcachedClient c = new MemcachedClient(list);
		
		c.set("someKey", 3600, 12);
		Object myObject = c.get("someKey");
		System.out.println(myObject);
		
		c.shutdown();
	}

}
