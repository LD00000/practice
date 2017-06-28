package com.ld.practice.callback;

/**
 * 模拟总经理
 */
public class Manager implements CallBack {
    /**
     *
     * @param personnel 传入员工类对象
     */
    public Manager(Personnel personnel) {
        personnel.doSomething(this, "整理文件");
    }

    /**
     * 当员工完成总经理让他做的事后通过该方法通知总经理
     * @param result 事情结果
     */
    @Override
    public void backResult(String result) {
        System.out.println("事情" + result);
    }
}
