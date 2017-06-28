package com.ld.pattern.composite.aqcompostie;

import java.util.ArrayList;

public class Composite extends Component{

    private ArrayList<Component> children = new ArrayList<Component>();

    public Composite() {}

    public Composite(String name) {
        super(name);
    }

    public Component add(Component component) {
        this.children.add(component);
        return component;
    }

    public void remove(Component component) {
        this.children.remove(component);
    }

    @Override
    public void display(int depth) {
        // 构造输出
        StringBuilder depthBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            depthBuilder.append("-");
        }
        depthBuilder.append(name);
        System.out.println(depthBuilder);

        // 递归输出
        for (Component child : children) {
            child.display(depth + 1);
        }
    }

}
