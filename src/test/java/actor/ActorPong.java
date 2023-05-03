package actor;

import actor.Actor;
import actor.ActorSystem;
import actor.Message;

/**
 *	消息反弹Actor
 */
public class ActorPong extends Actor {
	
	/**
	 * 处理不同类型的消息
	 */
	@Override
	public void handleMessage(Message msg) {
		switch(msg.getCommand()) {
			case "pong":
				pong(msg);
				break;
			default:
		}
	}
	
	/**
	 * 消息反弹
	 */
	private void pong(Message msg) {
		//System.out.println("pong:" + msg.getParams()[0]);
		ActorSystem.send(msg.getSrcNode(), msg.getSrcActor(), "receivePong", msg.getParams());
	}
	
}
