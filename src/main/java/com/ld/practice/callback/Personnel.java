package com.ld.practice.callback;

/**
 * 模拟员工
 */
public class Personnel {
	public void doSomething(CallBack callBack, String task) {
		System.out.println("总经理要你做" + task);
		String result = "做完了";
		callBack.backResult(result);
	}	
}
