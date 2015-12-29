package com.ld.practice.mq;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 消息队列
 */
public class MqDemo {
	
	private static List<Queue<?>> queueCache = new LinkedList<>();	// 队列缓冲池
	
	private static int offerMaxQueue = 1024;	// 缓冲池最大消息数
	
	public static void main(String[] args) {
		new Thread() {	// 检出线程，如果缓冲池没有消息，会等待
			@Override
			public void run() {
				while (true) {
					String ip = null;
					try {
						int size = queueCache.size();
						if (size == 0) {
							queueCache.wait();	// 队列缓存池没有消息，等待。。。。
						}
						
						Queue<?> queue = queueCache.remove(0);
//						if (ipLock(queueStr)) {		// 如果有锁，重新加入队列缓冲
//							queueCache.add(queue);
//							continue;
//						} else {
//							 处理消息
//						}
						
						size = queueCache.size();
						if (size < offerMaxQueue && size >= 0) {
							queueCache.notifyAll(); //在队列缓存池不超过最大值的前提下，假若检入正在等待中，那么那么让他们排队检入。
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
//						unIpLock(queueStr);	//检出该消息队列的锁
					}
				}
			}
		}.start();
		
		new Thread() {	// 检入线程
			public void run() {
				synchronized (queueCache) {
					while(true){
						Integer size = queueCache.size();
						if(size >= offerMaxQueue){
							try {
								queueCache.wait();
								continue; // 继续执行等待中的检入任务。
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}//IF

						if(size <= offerMaxQueue && size > 0){
							queueCache.notifyAll();
						}
						break; //检入完毕
					} //while
				}
			};
		}.start();
	}
	
	public void queue() {
		
	}

}
