package com.ld.pattern.factory.method.store;

import com.ld.pattern.factory.method.pizza.NYStyleCheesePizza;
import com.ld.pattern.factory.method.pizza.NYStyleClamPizza;
import com.ld.pattern.factory.method.pizza.NYStylePepperoniPizza;
import com.ld.pattern.factory.method.pizza.Pizza;

public class NYStylePizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza;
		if ("cheese".equals(type)) {
			pizza = new NYStyleCheesePizza();
		} else if ("clam".equals(type)) {
			pizza = new NYStyleClamPizza();
		} else if ("pepperoni".equals(type)) {
			pizza = new NYStylePepperoniPizza();
		} else {
			pizza = null;
		}
		
		return pizza;
	}

}
