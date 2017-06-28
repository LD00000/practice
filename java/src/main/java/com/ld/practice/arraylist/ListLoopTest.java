package com.ld.practice.arraylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

/**
 * 测试Random Access List(随机访问列表)如 ArrayList 和 Sequence Access List(顺序访问列表)如 LinkedList </br>
 * 不同遍历算法的效率</br>
 * 结论：前者用循环，后者用迭代器
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ListLoopTest {

    /**
     * 初始化 list，添加n个元素
     *
     * @param list
     * @return
     */
    public static <T> List initList(List list, int n) {
        for (int i = 0; i < n; i++)
            list.add(i);
        return list;
    }

    /**
     * 遍历 list，判断是否实现 RandomAccess 接口来使用不同的遍历方法
     *
     * @param list
     */
    public static void accessList(List list) {
        long startTime = System.currentTimeMillis();

        if (list instanceof RandomAccess) {
            System.out.println("实现了 RandomAccess 接口...");
            for (int i = 0; i < list.size(); i++) {
                list.get(i);
            }
        } else {
            System.out.println("没实现 RandomAccess 接口...");
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                iterator.next();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("遍历时间：" + (endTime - startTime));
    }

    /**
     * loop 遍历 list
     */
    public static void accessListByLoop(List list) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("loop遍历时间：" + (endTime - startTime));
    }

    /**
     * foreach 遍历 list
     */
    public static void accessListByForeach(List list) {
        long startTime = System.currentTimeMillis();

        for (Object object : list) {

        }

        long endTime = System.currentTimeMillis();
        System.out.println("foreach遍历时间：" + (endTime - startTime));
    }

    /**
     * 迭代器遍历
     */
    public static void accessListByIterator(List list) {
        long startTime = System.currentTimeMillis();

        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
            iterator.next();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Iterator遍历时间：" + (endTime - startTime));
    }

    public static void main(String[] args) {
        ArrayList<Integer> aList = (ArrayList<Integer>) initList(new ArrayList<>(), 50000000);
        LinkedList<Integer> lList = (LinkedList<Integer>) initList(new LinkedList<>(), 80000);

        accessList(aList);
        accessList(lList);

        System.out.println("ArrayList");
        accessListByLoop(aList);
        accessListByForeach(aList);
        accessListByIterator(aList);

        System.out.println("LinkedList");
        accessListByLoop(lList);
        accessListByForeach(lList);
        accessListByIterator(lList);
    }

}
