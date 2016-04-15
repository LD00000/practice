package com.ld.practice.prac;

/**
 * 打印200以内的素数 
 */
public class sushu {
	
	public static void main(String[] args) {
		int i, j;
		for (j = 2; j <= 200; j++) {
			for (i = 2; i <= j/2; i++) {	
				if (j % i == 0) {	
					break;	// 如果能整除，不是素数，直接跳出
				}	
			}
			if (i > j/2)
				System.out.println(j);
		}
	}

}
