package com.ld.pattern.composite.aqcompostie;

/**
 * 对象统一接口
 */
public abstract class Component {
	
	protected String name;
	
	public Component() {}
	
	public Component(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void display(int depth);

}
