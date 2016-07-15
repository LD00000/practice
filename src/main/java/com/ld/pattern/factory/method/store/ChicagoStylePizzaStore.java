package com.ld.pattern.factory.method.store;

import com.ld.pattern.factory.method.pizza.ChicagoStyleCheesePizza;
import com.ld.pattern.factory.method.pizza.ChicagoStyleClamPizza;
import com.ld.pattern.factory.method.pizza.ChicagoStylePepperoniPizza;
import com.ld.pattern.factory.method.pizza.Pizza;

public class ChicagoStylePizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza;
		if ("cheese".equals(type)) {
			pizza = new ChicagoStyleCheesePizza();
		} else if ("clam".equals(type)) {
			pizza = new ChicagoStyleClamPizza();
		} else if ("pepperoni".equals(type)) {
			pizza = new ChicagoStylePepperoniPizza();
		} else {
			pizza = null;
		}
		
		return pizza;
	}

}
