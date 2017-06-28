package com.ld.pattern.composite.aqcompostie;

/**
 * 避免在Leaf中实现没有必要地接口，从而使得设计符合SRP原则（对象职责单一原则）
 */
public class Leaf extends Component{

    public Leaf() {}

    public Leaf(String name) {
        super(name);
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
    }

}
