package com.ld.pattern.factory.simple.pizza;

public abstract class Pizza {
	
	public void prepare() {
		System.out.println("prepare...");
	}
	
	public void bake() {
		System.out.println("bake...");
	}
	
	public void cut() {
		System.out.println("cut...");
	}
	
	public void box() {
		System.out.println("box...");
	}

}
