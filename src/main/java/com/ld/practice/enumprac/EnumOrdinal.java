package com.ld.practice.enumprac;

/**
 * ordinal() 得到枚举元素的序号
 */
public enum EnumOrdinal {
	
	Test1, 
	Test2,
	Test3;
	
	public static void main(String[] args) {
		System.out.println(EnumOrdinal.Test1.ordinal());
		System.out.println(EnumOrdinal.Test2.ordinal());
	}
	
}
