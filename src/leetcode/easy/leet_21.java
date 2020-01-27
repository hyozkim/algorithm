package leetcode.easy;

import leetcode.medium.leet_19;

public class leet_21 {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if( l1 == null ) return l2;
        if( l2 == null ) return l1;

        ListNode result = new ListNode(-1);
        ListNode currNode = result;

        while( l1 != null && l2 != null ) {
            if( l1.val <= l2.val ) {
                currNode.next = l1;
                currNode = l1;
                l1 = l1.next;

            } else {
                currNode.next = l2;
                currNode = l2;
                l2 = l2.next;

            }

            if( l1 == null ) {
                currNode.next = l2;
            } else {
                currNode.next = l1;
            }
        }

        return result.next;
    }

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1);
        listNode1.next = new ListNode(2);
        listNode1.next.next = new ListNode(4);

        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);

 /*
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = null;
*/
        ListNode result = mergeTwoLists(listNode1,listNode2);

        do {
            System.out.print(result.val +" "+ " -> ");
            result = result.next;

        } while( result != null) ;
    }

}
