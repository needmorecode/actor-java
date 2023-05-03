package actor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import io.netty.util.internal.logging.FormattingTuple;
import io.netty.util.internal.logging.MessageFormatter;

/**
 * Actor之间发送的消息
 * 多个节点之间发送消息，会被序列化
 */
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static final String LOG_FORMAT = "[time:{}, srcActor:{}, destActor:{}]command:{},params:{}";

	private String command;
	
	private String srcNode;
	
	private String srcActor;
	
	private String destNode;
	
	private String destActor;
	
	private Object[] params;
	
	public Message(String command, String srcNode, String srcActor, String destNode, String destActor, Object... params) {
		this.command = command;
		this.srcNode = srcNode;
		this.srcActor = srcActor;
		this.destNode = destNode;
		this.destActor = destActor;
		this.params = params;
	}
	
	public String getCommand() {
		return command;
	}

	public String getSrcNode() {
		return srcNode;
	}

	public String getSrcActor() {
		return srcActor;
	}
	
	public String getDestNode() {
		return destNode;
	}
	
	public String getDestActor() {
		return destActor;
	}

	public Object[] getParams() {
		return params;
	}
	
	public String toString() {
		FormattingTuple tuple = MessageFormatter.arrayFormat(LOG_FORMAT, new Object[] {ActorSystem.currTimestamp(), srcActor, destActor, command, params});
		return tuple.getMessage();
	}
	
	
}
