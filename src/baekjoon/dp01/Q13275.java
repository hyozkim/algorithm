package baekjoon.dp01;

import java.util.Scanner;

/***
 * 가장 긴 팰린드롬 부분 문자열
 * 프로그래머스 - 가장 긴 팰린드롬(Palindrome) 과 유사
 *
 * 백준 - DP(다이나믹 프로그래밍)
 */
public class Q13275 {

    public static int palindromeMaxLength(String s, int left, int right) {
        int L = left; int R = right;
        while( L >=0 && R < s.length() && s.charAt(L) == s.charAt(R) ) {
            L--;
            R++;
        }

        return R-L-1;
    }

    public static int solution(String s) {
        int length = s.length();

        //if (length == 0)   return 0;
        //if (length == 1)   return 1;

        //int left_max = 1; int right_max = 1;
        int len = 1;
        for (int i = length; i >= 0; i--) {
            //left_max = findMax(s.substring(0,idx),left_max);
            //right_max = findMax(s.substring(idx,length),right_max);

            len = Math.max(len, palindromeMaxLength(s,i,i));
            len = Math.max(len, palindromeMaxLength(s,i,i+1));
        }

        return len;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        System.out.println(solution(s));
    }
}
