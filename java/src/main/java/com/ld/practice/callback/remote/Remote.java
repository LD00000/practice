package com.ld.practice.callback.remote;

public class Remote {

    public void executeMessage(String message, Callback callback) {
        for (int i = 0; i < 2000000; i++) {

        }
        System.out.println(message + " by remote");
        callback.execute("done... by remote");
    }

}
