package com.ld.practice.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda 表达式
 */
public class LambdaExpression {
	
	
	/* 一个简单的 Lambda 例子 */
	private List<String> names = Arrays.asList("A", "B", "C", "D");
	
	/**
	 * 旧的方式
	 */
	public void oldMethod() {
		Collections.sort(names, new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
	}
	
	/**
	 * Lambda 表达式写法
	 */
	public void lambdaMethod() {
		Collections.sort(names, (a, b) -> b.compareTo(a));
	}
	
	
	
	/* Functional Interfaces(函数化接口) */
	/**
	 * @FunctionalInterface 接口只能定义一个抽象方法，用于满足 Lambda 表达式
	 */
	@FunctionalInterface
	interface Converter<F, T> {
		T convert(F from);
	}
	
	@SuppressWarnings("unused")
	public void functionalInterfaceMethod() {
		// 旧的实现方式
		Converter<String, Integer> oldConverter = new LambdaExpression.Converter<String, Integer>() {
			
			@Override
			public Integer convert(String from) {
				return Integer.valueOf(from);
			}
		};
		
		// Lambda 实现方式
		Converter<String, Integer> lambdaConverter = (from) -> Integer.valueOf(from);
	}
	
	
	/*  */
	
}
