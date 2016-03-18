package com.ld.practice.java8;

public interface DefaultMethodForInterface {
	
	void aMethod();
	
	/**
	 * Java 8准许我们在接口中增加一个通过default关键字修饰的非抽象的方法. 这个特性被我们称为扩展方法</br>
	 * 
	 * @param i
	 * @return
	 */
	default double sqrt(int i) {
		return Math.sqrt(i);
	}
	
}
