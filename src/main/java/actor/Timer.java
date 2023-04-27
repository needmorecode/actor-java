package actor;

import java.util.PriorityQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定时器
 */
public class Timer {
	
	/**
	 * 基于优先级队列实现的定时任务队列
	 */
	private static final PriorityQueue<TimerTask> timerTasks = new PriorityQueue<>();
	
	private static final ReentrantLock lock = new ReentrantLock();
	
	/**
	 * 唤醒阻塞条件一：队列非空
	 */
	private static final Condition notEmpty = lock.newCondition();
	
	/**
	 * 唤醒阻塞条件二：当前时刻有任务需要执行
	 */
	private static final Condition hasCurrTask = lock.newCondition();
	
	/**
	 * 添加新的定时任务
	 */
	public static void addTimeTask(TimerTask task) {
		lock.lock();
		if (timerTasks.isEmpty()) {
			notEmpty.signal();
		}
		TimerTask firstTask = timerTasks.peek();
		timerTasks.offer(task);
		if (firstTask != null && task.getExecTime() < firstTask.getExecTime()) {
			hasCurrTask.signal();
		}
		lock.unlock();
	}
	
	/**
	 * 启动定时器
	 */
	public static void start() {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> {
			while (true) {
				TimerTask firstTask;
				lock.lock();
				if (timerTasks.isEmpty()) {
					try {
						notEmpty.await();
					} catch (InterruptedException ignore) {
						// ignore
					}
				}
	    	   	firstTask = timerTasks.peek();
	    	   	long currDeadlineMillis = firstTask.getExecTime();
	    	   	long currTime = System.currentTimeMillis();
	    	   	long delay = currDeadlineMillis - currTime;
	    	   	if (delay > 0) {
	    	   		try {
						hasCurrTask.await(delay, TimeUnit.MILLISECONDS);
					} catch (InterruptedException ignore) {
						// ignore
					}
	    	   	} else {
	    	   		firstTask = timerTasks.poll();
	    	   	}
	    	   	lock.unlock();
	    	   	if (firstTask != null) {
	    	   		firstTask.run();
	    	   	}
			}
		});
	}

}
