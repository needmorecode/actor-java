package actor;

import actor.Actor;
import actor.ActorSystem;
import actor.Message;

/**
 *	消息发送Actor
 */
public class ActorPing extends Actor {
	
	/**
	 * 处理不同类型的消息
	 */
	@Override
	public void handleMessage(Message msg) {
		switch(msg.getCommand()) {
			case "start":
				start(msg);
				break;
			case "ping":
				ping(msg);
				break;
			case "receivePong":
				receivePong(msg);
				break;
			default:
		}
	}
	
	/**
	 * Actor启动
	 */
	private void start(Message msg) {
		//System.out.println(System.currentTimeMillis() - ActorSystem.startTime);
		//System.out.println("start:" + msg.getParams()[0]);
		ActorSystem.sendSelf("ping", msg.getParams());
	}
	
	/**
	 * 发送消息
	 */
	private void ping(Message msg) {
		int interval = (int)msg.getParams()[0];
		//System.out.println(System.currentTimeMillis() - ActorSystem.startTime);
		//System.out.println("ping:" + interval);
		ActorSystem.send("nodeB", "pong", "pong", "msg");
		ActorSystem.sleep(interval, "ping", msg.getParams());
	}
	
	/**
	 * 接收反弹消息
	 */
	private void receivePong(Message msg) {
		//System.out.println(System.currentTimeMillis() - ActorSystem.startTime);
		//System.out.println("receivePong:" + msg.getParams()[0]);
	}
	
}
