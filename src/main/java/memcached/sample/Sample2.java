package memcached.sample;

import net.spy.memcached.ConnectionFactoryBuilder.Locator;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.DefaultHashAlgorithm;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.spring.MemcachedClientFactoryBean;
import net.spy.memcached.transcoders.SerializingTranscoder;

public class Sample2 {

	public static void main(String[] args) throws Exception {
		SerializingTranscoder transcoder = new SerializingTranscoder();
		transcoder.setCompressionThreshold(1024);
		
		MemcachedClientFactoryBean factory = new MemcachedClientFactoryBean();
		factory.setServers("192.168.33.11:11211");
		factory.setProtocol(Protocol.BINARY);
		factory.setTranscoder(transcoder);
		factory.setOpTimeout(1000);
		factory.setTimeoutExceptionThreshold(1998);
		factory.setHashAlg(DefaultHashAlgorithm.KETAMA_HASH);
		factory.setLocatorType(Locator.CONSISTENT);
		factory.setFailureMode(FailureMode.Redistribute);
		factory.setUseNagleAlgorithm(false);
		factory.setMaxReconnectDelay(1000);
		factory.afterPropertiesSet();
		
		MemcachedClient c;
		
		TestModel testModel = new TestModel();
		testModel.setParam1("aaa");
		testModel.setParam2("bbb");
		
		try{
			while(true){
				c = (MemcachedClient) factory.getObject();
				c.set("testModel", 3600, testModel);
				TestModel myObject = (TestModel) c.get("testModel");
				System.out.println(myObject);
				Thread.sleep(1000);
			}
		}finally{
			factory.destroy();
		}
	}

}
