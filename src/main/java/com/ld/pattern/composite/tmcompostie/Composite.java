package com.ld.pattern.composite.tmcompostie;

import java.util.ArrayList;

public class Composite extends Component {

    private ArrayList<Component> childen = new ArrayList<Component>();

    public Composite() {
    	super();
    }

    public Composite(String name) {
        super(name);
    }

    @Override
    protected Component add(Component component) {
        this.childen.add(component);
        return component;
    }

    @Override
    protected void remove(Component component) {
        this.childen.remove(component);
    }

    @Override
    protected void display(int depth) {
        // 构造输出
        StringBuilder depthBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            depthBuilder.append("-");
        }
        depthBuilder.append(name);
        System.out.println(depthBuilder.toString());

        // 递归输出
        for (Component child : childen) {
            child.display(depth + 1);
        }
    }

}
