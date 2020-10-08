package baekjoon.dfs;

import java.util.Scanner;

/***
 * 완전탐색 - 재귀호출
 *
 * 1,2,3 더하기
 */
public class Q9095 {
    static int n;
    static int answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            n = sc.nextInt();

            answer = 0;
            dfs(0);
            System.out.println(answer);
        }
    }

    public static void dfs(int num) {
        if( num > 10 || n == num ) {
            if( num > 10 )
                return;
            // System.out.println(num);
            answer ++;
            return;
        }

        dfs(num+1);
        dfs(num+2);
        dfs(num+3);
    }
}
