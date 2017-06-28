package com.ld.algorithms.string;

public class StringAlog {

    /**
     * 反转字符数组
     *
     * @param arr
     * @param begin
     * @param end
     */
    public static void swap(char[] arr, int begin, int end) {
        while (begin < end) {
            char temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;
            begin++;
            end--;
        }
    }

    /**
     * 反转字符串内的单词
     *
     * @param str
     */
    public static String swapStr(String str) {
        char[] arr = str.toCharArray();
        swap(arr, 0, arr.length - 1);
        int begin = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                swap(arr, begin, i - 1);
                begin = i + 1;
            } else if (i == arr.length - 1) {
                swap(arr, begin, i);
            }
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        // 反转字符串内的单词
        String str1 = "Hello World";
        System.out.println(swapStr(str1));
    }

}
