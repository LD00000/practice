package com.ld.pattern.composite.aqcompostie;

public class Program {

    public static void main(String[] args) {
        Composite root = new Composite("root");
        
        Composite branchA = new Composite("BranchA");
        Composite branchB = new Composite("BranchB");
        
        root.add(branchA);
        root.add(branchB);
        
        branchA.add(new Leaf("LeafA"));
        branchA.add(new Leaf("LeafB"));
        
        // 输出
        root.display(0);
    }

}
