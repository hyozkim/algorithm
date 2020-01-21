package leetcode.medium;

public class leet_19 {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode temp = new ListNode(0);
        temp.next = head;

        ListNode first = head;
        do {
            // System.out.print(head.val +"(" + size +")"+ " -> ");
            first = first.next;
            size ++;
        } while( first != null) ;


        size -= n;
        first = temp;
        while( size > 0 ) {
            size --;
            first = first.next;
        }
        first.next = first.next.next;

        // return은 temp.next로
        // Call By Reference
        return temp.next;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);

        ListNode result = removeNthFromEnd(listNode,2);

        do {
            System.out.print(result.val +"(" + 0 +")"+ " -> ");
            result = result.next;

        } while( result != null) ;

    }
}
