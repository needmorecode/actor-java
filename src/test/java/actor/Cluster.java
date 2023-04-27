package actor;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 集群节点配置（也可以提取到配置文件中）
 */
public class Cluster {
	
	public static final Map<String, InetSocketAddress> config = new HashMap<>();
	
	static {
		config.put("nodeA", new InetSocketAddress("127.0.0.1", 7001));
		config.put("nodeB", new InetSocketAddress("127.0.0.1", 7002));
	}
	
	public static Map<String, InetSocketAddress> getConfig() {
		return config;
	}

}
