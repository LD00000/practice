package com.ld.algorithms.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 把一个数组中的值存入二叉树中，然后进行4种遍历
 */
public class BinTree {

    /**
     * 节点
     */
    private static class Node {
        int date;
        Node leftChild;
        Node rightChild;

        public Node(int val) {
            this.date = val;
            this.leftChild = null;
            this.rightChild = null;
        }

        /**
         * 插入子节点
         *
         * @param root
         * @param data
         */
        public void insert(Node root, int data) {
            if (data > root.date) {    // 右节点都比根节点大
                if (root.rightChild == null) {
                    root.rightChild = new Node(data);
                } else {
                    insert(root.rightChild, data);
                }
            } else {    // 左节点都比根节点小
                if (root.leftChild == null) {
                    root.leftChild = new Node(data);
                } else {
                    insert(root.leftChild, data);
                }
            }
        }
    }

    /**
     * 先序遍历
     */
    public static void preOrder(Node tree) {
        if (tree == null)
            return;
        System.out.print(tree.date + ", ");
        preOrder(tree.leftChild);
        preOrder(tree.rightChild);
    }

    /**
     * 中序遍历
     */
    public static void inOrder(Node tree) {
        if (tree == null)
            return;
        inOrder(tree.leftChild);
        System.out.print(tree.date + ", ");
        inOrder(tree.rightChild);
    }

    /**
     * 后序遍历
     */
    public static void postOrder(Node tree) {
        if (tree == null)
            return;
        postOrder(tree.leftChild);
        postOrder(tree.rightChild);
        System.out.print(tree.date + ", ");
    }

    /**
     * 层序遍历
     */
    public static void levelTravel(Node tree) {
        if (tree == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.date + ", ");
            if (temp.leftChild != null)
                queue.add(temp.leftChild);
            if (temp.rightChild != null)
                queue.add(temp.rightChild);
        }
    }

    public static void main(String[] args) {
        // 创建二叉树
        int[] array = {12,76,35,22,16,48,90,46,9,40};
        Node binTree = new Node(array[0]);
        for (int i = 1; i < array.length; i++) {
            binTree.insert(binTree, array[i]);
        }

        System.out.println("先序遍历");
        preOrder(binTree);
        System.out.println();

        System.out.println("中序遍历");
        inOrder(binTree);
        System.out.println();

        System.out.println("后序遍历");
        postOrder(binTree);
        System.out.println();

        System.out.println("层序遍历");
        levelTravel(binTree);
    }
}
