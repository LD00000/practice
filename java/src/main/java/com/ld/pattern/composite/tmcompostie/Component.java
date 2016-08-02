package com.ld.pattern.composite.tmcompostie;

/**
 * 为对象的组合提供统一的接口
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

    protected abstract Component add(Component component);

    protected abstract void remove(Component component);

    protected abstract void display(int depth);

}
