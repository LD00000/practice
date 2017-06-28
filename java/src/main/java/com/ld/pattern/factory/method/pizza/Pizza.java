package com.ld.pattern.factory.method.pizza;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {

    private String name;
    private String dough;
    private String sauce;

    private List<String> toppings = new ArrayList<String>();

    public void prepare() {
        System.out.println("prepare..." + name);
        System.out.println("dough" + dough);
        System.out.println("sauce" + sauce);
        for (String topping : toppings) {
            System.out.println("topping " + topping);
        }
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

    public String getName() {
        return name;
    }

}
