package baekjoon.dp01.sol02;

// 오르막 수 ( Q11057 )
// Backjoon - Dp
import java.util.Scanner;

public class Q11057{
    public static void main(String[] args) {
        int mod = 10007;

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] d = new int[n+1][10];
        for(int i=0; i<=9; i++) {
            d[1][i] = 1;
        }

        for(int i=2; i<=n; i++) {
            for(int j=0; j<=9; j++) {
                for(int k=j; k<=9; k++) {
                    d[i][j] += d[i-1][k];
                }
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
