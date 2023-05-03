package actor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 节点抽象类，对应一个进程
 */
public abstract class Actor {
	
	private Node node;
	
	private String name;
	
	private final BlockingQueue<Message> mailbox = new LinkedBlockingQueue<>();

	private Thread actorThread;
	
	public Node getNode() {
		return node;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

    public void start() {
        actorThread = new Thread(() -> {
        	ActorSystem.setThreadLocalActor(this);
            for(;;) {
                try {
                    Message message = mailbox.take();
                    try {
                    	System.out.println(message.toString());  // 日志打印
                    	handleMessage(message);
                    } catch (Exception e) {
                    	e.printStackTrace();
                    }
                } catch (InterruptedException ignore) {
                    // ignore
                }
            }
        });

        actorThread.start();
    }

    public void act(Message msg) {
        mailbox.offer(msg);
    }
    
    protected abstract void handleMessage(Message message);
}
