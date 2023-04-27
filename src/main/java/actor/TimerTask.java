package actor;

/**
 * 定时任务
 */
public class TimerTask implements Comparable<TimerTask>{
	
	/**
	 * 执行时间（毫秒）
	 */
	private long execTime;
	
	/**
	 * 具体任务
	 */
	private Runnable task;
	
	public TimerTask(long execTime, Runnable task) {
		this.execTime = execTime;
		this.task = task;
	}
	
	/**
	 * 是否过期
	 */
	public boolean isExpired() {
		return System.currentTimeMillis() >= execTime;
	}

	@Override
	public int compareTo(TimerTask task) {
		return Long.compare(this.execTime, task.execTime);
	}
	
	public Runnable getTask() {
		return task;
	}
	
	/**
	 * 定时任务执行
	 */
	public void run() {
		task.run();
	}
	
	public long getExecTime() {
		return this.execTime;
	}
}
