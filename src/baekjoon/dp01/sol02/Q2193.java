package baekjoon.dp01.sol02;

import java.util.Scanner;

// 이친수 ( Q2193 )
// Backjoon - Dp
public class Q2193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        /* solution2 */
        long[][] d = new long[n+1][2];
        d[1][0] = 0;
        d[1][1] = 1;
        // d[i][0] = i자리에 0이 올 때, 1과 0 모두 올 수 있다.
        // d[i][1] = i자리에 1이 올 때, 0만 올 수 있다.
        for (int i = 2; i <= n; i++) {
            d[i][0] = d[i-1][1] + d[i-1][0];
            d[i][1] = d[i-1][0];
        }

        System.out.println(d[n][0] + d[n][1]);

		/* solution1
		long[] d = new long[n+1];
		d[1] = 1;
		if( n>= 2 )
			d[2] = 1;
		for(int i=3; i<=n; i++) {
			d[i] = d[i-1] + d[i-2];
		}

		System.out.println(d[n]);
		*/
    }
}

