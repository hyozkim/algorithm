package backjoon.dp;

import java.util.*;

// 2xn 타일링 ( Q11726 )
// Backjoon - Dp
public class Q11726 {
    static int[] d;

    public static int go(int n) {
        d[0] = 1;
        d[1] = 1;
        for(int i=2; i<=n; i++) {
            d[i] = d[i-1] + d[i-2];
            d[i] %= 10007;
        }

        return d[n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d = new int[n+1];

        System.out.println(go(n));
    }
}