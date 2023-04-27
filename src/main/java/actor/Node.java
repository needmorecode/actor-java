package actor;

import java.net.InetSocketAddress;

/**
 * 节点，对应一个进程，有独立的ip和端口
 */
public abstract class Node {
	
	/**
	 * 名字
	 * 需要是唯一的，按名字查找
	 */
	private String name;
	
	private InetSocketAddress address;
	
	public String getName() {
		return name;
	}

	public void setName(String nodeName) {
		name = nodeName;
	}

	public void setAddress(InetSocketAddress address) {
		this.address = address;
	}
}
