package com.education.classroom.core.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息处理器
 * @Class Name MessagerProcessor
 * @author 张永生
 * @Create In 2016年5月3日
 * @param <T>
 */
public class MessagerProcessor<T> extends Thread {
	
	private static Logger Logger = LoggerFactory.getLogger(MessagerProcessor.class);
	
	LinkedBlockingQueue<T> queue;
	ProcessorHandler<T> helper;
	boolean isRunning = true;
	private ExecutorService messagerProcessorPool;

	public MessagerProcessor(LinkedBlockingQueue<T> queue, ProcessorHandler<T> helper, ExecutorService executor) {
		this.queue = queue;
		this.helper = helper;
		this.messagerProcessorPool = executor;
	}

	public void run() {
		while (this.isRunning){
			process();
		}
	}

	/**
	 * 开启线程处理任务
	 * 2016年5月3日
	 * By 张永生
	 */
	private void process() {
		T commands = null;
		try {
			commands = this.queue.take();
			if((commands != null) && (this.messagerProcessorPool != null)) {
				this.messagerProcessorPool.execute(new WorkerJob(commands));
			}
		} catch (InterruptedException e) {
			Logger.warn("messager Process stoped!");
		} catch (Exception ex) {
			Logger.error("messager Process error!", ex);
		}
	}

	/**
	 * 停止线程
	 * 2016年5月3日
	 * By 张永生
	 */
	public void stopProcess() {
		this.isRunning = false;
		try {
			if (this.messagerProcessorPool != null) {
				this.messagerProcessorPool.shutdown();
			}
			this.messagerProcessorPool = null;
			interrupt();
		} catch (Exception ex) {
			Logger.error(ex.toString());
		}
	}
	
	/**
	 * 待处理的任务对象
	 * @Class Name WorkerJob
	 * @author 张永生
	 * @Create In 2016年5月3日
	 */
	class WorkerJob implements Runnable {

		T commands;

		public WorkerJob(T commands) {
			this.commands = commands;
		}

		public void run() {
			if (this.commands != null) {
				MessagerProcessor.this.helper.run(this.commands);
			}
		}
	}
}
