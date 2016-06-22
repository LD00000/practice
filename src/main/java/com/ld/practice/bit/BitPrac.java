package com.ld.practice.bit;

/**
 * 位操作
 */
public class BitPrac {
	
	/**
	 * 判断是否是偶数
	 */
	public void findOuShu() {
		for (int i = 0; i < 100; i++) {
			if ((i & 1) == 0)
				System.out.println(i);
		}
	}
	
	/**
	 * 异或交换两数
	 * 
	 * @param a
	 * @param b
	 */
	public void swap(int a, int b) {
		if (a != b) {
			a ^= b;
			b ^= a;
			a ^= b;
		}
		System.out.println(a + "  " + b);
	}
	
	/**
	 * 变换符号，取反加1
	 * 
	 * @param a
	 */
	public void SignRversal(int a) {
		System.out.println((~a) + 1);
	}
	
	/**
	 * 求绝对值
	 * 
	 * @param i
	 */
	public void MyAbs(int i) {
		int sign = i >> 31;	// 取符号位, -1为负数，0为正数
		System.out.println((i ^ sign) - sign);
	}
	
	public static void main(String[] args) {
		BitPrac bitPrac = new BitPrac();
		
//		bitPrac.findOuShu();
		
//		bitPrac.swap(1, 2);
		
		bitPrac.SignRversal(22);
	}

}
