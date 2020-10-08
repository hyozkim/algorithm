package baekjoon.dp01.sol04;

import java.util.Scanner;

/***
 * DP 1 문제풀이 4
 *
 * 암호코드
 */
public class Q2011 {
    static long answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // DFS
        //answer = 0;
        //dfs(s.toCharArray(), 0);
        //System.out.println(answer % 1000000);

        // DP
        char[] a = new char[s.length()+1];
        for (int i = 1; i <= s.length(); i++) {
            a[i] = s.charAt(i-1);
        }

        System.out.println(solution(a));
    }

    static final int MOD = 1000000;
    public static long solution(char[] a) {
        int n = a.length-1;

        long[] d = new long[n+1];
        d[0] = 1L;
        for (int i = 1; i <= n; i++) {
            int x = a[i] - '0';
            if( x >= 1 && x<= 9 ) {
                d[i] += d[i-1]; // 1개 자리수
                d[i] %= MOD;
            }

            if( i == 1 ) continue;
            if( a[i-1] == '0' ) continue;
            x = 10*(a[i-1]-'0') + (a[i]-'0');
            if( x >= 10 && x <= 26 ) {
                d[i] += d[i-2]; // 2개 자리수
                d[i] %= MOD;
            }
        }

        return d[n] % MOD;
    }

    public static void dfs(char[] a, int index) {
        if( index >= a.length ) {
            answer ++;
            answer %= 1000000;
            return ;
        }

        if( index + 1 < a.length && stoi((""+a[index] + a[index+1])) >= 10 && stoi((""+a[index] + a[index+1])) <= 26 )
            dfs(a, index+2);
        dfs(a, index+1);
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
