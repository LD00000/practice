package com.ld.practice.callback;

public class Main {
	public static void main(String[] args) {
		Personnel personnel = new Personnel();
		new Manager(personnel);
	}
}
