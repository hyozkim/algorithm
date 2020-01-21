package leetcode.medium;

public class leet_2 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode add(ListNode list, ListNode ret) {
//        System.out.println(list.val + " " + ret.val);

        if( list.next != null ) {
            ret.val += list.val;
            if( ret.val < 10 ) {
                ret.next = new ListNode(0);
                return add(list.next,ret.next);
            } else {
                ret.val %= 10;
                ret.next = new ListNode(1);
                return add(list.next,ret.next);
            }
        }
        else {
            ret.val += list.val;
            if( ret.val >= 10 ) {
                ret.val %= 10;
                ret.next = new ListNode(1);
            }
            return ret;
        }
    }

    public static ListNode add(ListNode l1, ListNode l2, ListNode ret) {
        //System.out.println(l1.val + " " + l2.val + " " + ret.val + " ");
        if( l1.next != null && l2.next != null ) {
            ret.val += l1.val + l2.val;
            if( ret.val < 10 ) {
                ret.next = new ListNode(0);
                return add(l1.next,l2.next,ret.next);
            } else {
                ret.val %= 10;
                ret.next = new ListNode(1);
                return add(l1.next,l2.next,ret.next);
            }
        }
        else if( l1.next != null ) {
            ret.val += l1.val + l2.val;
            if( ret.val < 10 ) {
                ret.next = new ListNode(0);
                return add(l1.next,ret.next);
            } else {
                ret.val %= 10;
                ret.next = new ListNode(1);
                return add(l1.next,ret.next);
            }
        }
        else if( l2.next != null ) {
            ret.val += l1.val + l2.val;
            if( ret.val < 10 ) {
                ret.next = new ListNode(0);
                return add(l2.next,ret.next);
            } else {
                ret.val %= 10;
                ret.next = new ListNode(1);
                return add(l2.next,ret.next);
            }
        }
        else {
            ret.val += l1.val + l2.val;
            if( ret.val >= 10 ) {
                ret.val %= 10;
                ret.next = new ListNode(1);
            }
            return ret;
        }

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(0);

        add(l1,l2,ret);

        return ret;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode ret = addTwoNumbers(l1,l2);
        System.out.println(ret.val + " " + ret.next.val + " " + ret.next.next.val );
    }
}
