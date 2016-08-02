package com.ld.pattern.factory.simple;

import com.ld.pattern.factory.simple.pizza.CheesePizza;
import com.ld.pattern.factory.simple.pizza.ClamPizza;
import com.ld.pattern.factory.simple.pizza.PepperoniPizza;
import com.ld.pattern.factory.simple.pizza.Pizza;

public class SimplePizzaFactory {
	
	public Pizza createPizza(String type) {
		Pizza pizza = null;
		if ("cheese".equals(type)) {
			pizza = new CheesePizza();
		} else if ("clam".equals(type)) {
			pizza = new ClamPizza();
		} else if ("pepperoni".equals(type)) {
			pizza = new PepperoniPizza();
		}
		
		return pizza;
	}

}
