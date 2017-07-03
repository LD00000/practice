package com.ld.algorithms.list;

/**
 * @author lidong9144@163.com 17-7-3.
 */
public class Reverse {

    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        //
        ListNode list1 = reverse.new ListNode(3, null);
        ListNode list2 = reverse.new ListNode(2, list1);
        ListNode list3 = reverse.new ListNode(1, list2);

        ListNode head = reverse.reverse1(list3);
        reverse.sout1(head);
    }

    public void sout1(ListNode head) {
        System.out.println(head.val);
        if (head.next != null) {
            sout1(head.next);
        }
    }

    // 单向链表
    public class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 反转单向链表
    public ListNode reverse1(ListNode node) {
        if (node.next == null) {
            return node;
        }
        ListNode next = node.next;
        ListNode newHead = reverse1(next);

        next.next = node;
        node.next = null;

        return newHead;
    }

}
