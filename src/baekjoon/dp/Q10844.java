package baekjoon.dp;

import java.util.Scanner;

// 쉬운 계단수 ( Q10844 )
// Backjoon - Dp
public class Q10844{
    public static void main(String[] args) {
        int mod = 1000000000;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][]d = new int[n+1][10];
        for(int i=1; i<=9; i++) {
            d[1][i] = 1;
        }

        for(int i=2; i<=n; i++) {
            for(int j=0; j<=9; j++) {
                if( j-1 >= 0 ) d[i][j] += d[i-1][j-1];
                if( j+1 <= 9 ) d[i][j] += d[i-1][j+1];
                d[i][j] %= mod;
            }
        }

        long answer = 0;
        for(int i=0; i<=9; i++) {
            answer += d[n][i];
        }
        System.out.println(answer%mod);
    }
}
