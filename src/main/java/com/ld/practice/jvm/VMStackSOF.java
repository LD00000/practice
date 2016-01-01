package com.ld.practice.jvm;

/**
 * 栈溢出
 * VM Args:-Xss108k
 * 
 * stack length:983
 * Exception in thread "main" java.lang.StackOverflowError
 * 	 at com.ld.practice.jvm.VMStackSOF.stackLeak(VMStackSOF.java:12)
 * 	 at com.ld.practice.jvm.VMStackSOF.stackLeak(VMStackSOF.java:13)
 *   ...
 */
public class VMStackSOF {
	
	private int stackLength = 1;
	
	public void stackLeak() {
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) {
		VMStackSOF sof = new VMStackSOF();
		try {
			sof.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length:" + sof.stackLength);
			throw e;	
		}
	}

}
