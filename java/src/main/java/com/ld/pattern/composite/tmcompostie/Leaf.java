package com.ld.pattern.composite.tmcompostie;

/**
 * 叶子节点
 */
public class Leaf extends Component {

    public Leaf() {
        super();
    }

    public Leaf(String name) {
        super(name);
    }

    @Override
    protected Component add(Component component) {
        throw new RuntimeException("not support method");
    }

    @Override
    protected void remove(Component component) {
        throw new RuntimeException("not support method");
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
    }
}
