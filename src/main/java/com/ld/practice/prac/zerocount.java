package com.ld.practice.prac;

/**
 * 10000 阶乘末尾0的个数
 */
public class zerocount {
	
	public static void main(String[] args) {
		int count = 0;
		int n = 10000;
		while (n > 0) {
			int temp = n;
			n--;
			while (temp % 5 ==0) {
				count++;
				temp = temp / 5;
			}
		}
		System.out.println(count);
	}

}
