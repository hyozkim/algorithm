package baekjoon.dp;

// 카드 구매하기 ( Q11052 )
// Backjoon - Dp
import java.util.*;

public class Q11052 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int []a = new int[n+1];
        int []d = new int[n+1];
        for(int i=1; i<=n; i++) {
            a[i] = sc.nextInt();
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=i; j++) {
                d[i] = Math.max(d[i], a[j]+d[i-j]);
            }
        }

        System.out.println(d[n]);
    }
}
