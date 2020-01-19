package leetcode.easy;

public class leet_9 {
    public static boolean isPalindrome(int x) {
        // 음수 -> false
        // 0이 아니고, 끝자리수가 0 -> false
        if( x < 0 && ( x%10==0 && x!=0 ) )
            return false;

        int reverseNumber = 0;
        while( x > reverseNumber ) { // 중간까지 체크하면  된다.
            reverseNumber = reverseNumber * 10 + x % 10;
            x /= 10;
        }
        // System.out.println("x = " + x + ", reverseNumber = " + reverseNumber);
        // System.out.println("x = " + x + ", reverseNumber/10 = " + reverseNumber/10);

        return reverseNumber == x || x == reverseNumber/10; // 짝수개 or 홀수개
    }

    public static void main(String[] args) {
        int x = 121;
        //int x = -121;
        //int x = 10;
        //int x = 2147483647;

        System.out.println(isPalindrome(x));
    }

}
