package actor;

import actor.ActorSystem;
import actor.Node;

/**
 * 模拟节点A
 */
public class NodeA extends Node {

	public static void main(String args[]) {
		ActorSystem.conf(Cluster.getConfig());
		ActorSystem.bindNode(NodeA.class, "nodeA");
		ActorSystem.start();
		ActorSystem.newActor(ActorPing.class, "ping1");
		ActorSystem.newActor(ActorPing.class, "ping2");
		// 启动两个ping actor，分别以1000和5000为参数，调用start命令
		ActorSystem.send("nodeA", "ping1", "start", 1000);
		ActorSystem.send("nodeA", "ping2", "start", 5000);
	}
}

