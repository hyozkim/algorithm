package leetcode.medium;

public class leet_19 {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 0;
        ListNode temp = head;
        do {
            // System.out.print(head.val +"(" + size +")"+ " -> ");
            temp = temp.next;
            size ++;
        } while( temp != null) ;

        System.out.println(size);

        int i=0;
        do {
            System.out.print(head.val +"(" + i +")"+ " -> ");
            if( i == size-n-1 ) {
                head.next = head.next.next;
            }

            head = head.next;

        } while( head != null) ;

        return head;
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
