package leetcode.easy;

public class leet_7 {
    public static int reverse(int x) {
        if( x == 0 ) return 0;

        long answer = 0;
        while( x != 0 ) {
            answer += x % 10;
            answer *= 10;
            x /= 10;
        }
        answer /= 10;

        if( answer > Integer.MAX_VALUE )    return 0;
        if( answer < Integer.MIN_VALUE )    return 0;

        return (int) answer;
    }

    public static void main(String[] args) {
        //int n = 123;
        //int n = -123;
        // int n = 120;
        //int n = 1534236469;
        //int n = 0;
        //int n = -2147483648;
        int n = -2147483412;

        System.out.println(reverse(n));
    }
}
