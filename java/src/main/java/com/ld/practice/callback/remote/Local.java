package com.ld.practice.callback.remote;

public class Local implements Runnable, Callback {
	
	private Remote remote;
	private String message;
	
	public Local(Remote remote, String message) {
		super();
		this.remote = remote;
		this.message = message;
	}
	
	public void sendMessage() {
		System.out.println(Thread.currentThread().getName());
		Thread thread = new Thread(this);
		thread.start();
		System.out.println("信息已发送.... by local");
	} 

	@Override
	public void execute(Object... objects) {
		System.out.println(objects);
		System.out.println(Thread.currentThread().getName());
		Thread.interrupted();
	}

	@Override
	public void run() {
		remote.executeMessage(message, this);
	}
	
	public static void main(String[] args) {
		Local local = new Local(new Remote(), "haha");
		local.sendMessage();
	}

}
