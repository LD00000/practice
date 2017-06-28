package com.ld.practice.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出 
 * 
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * java.lang.OutOfMemoryError: Java heap space
 * Dumping heap to java_pid14524.hprof ...
 * Heap dump file created [27982863 bytes in 0.178 secs]
 */
public class HeapOOM {

    static class OOMObject{
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

}
