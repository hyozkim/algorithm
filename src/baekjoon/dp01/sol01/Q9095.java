package baekjoon.dp01.sol01;

// 1,2,3 더하기 ( Q9095 )
// Backjoon - Dp
import java.util.Scanner;

public class Q9095 {
    static int [] d;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        d = new int[11];
        d[0] = 1;
        for(int i=1; i<=10; i++) {
            for(int j=1; j<=3; j++ ) {
                if( i-j >= 0 ) {
                    d[i] += d[i-j];
                }
            }
        }

        while( n-- > 0 ) {
            int s = sc.nextInt();
            System.out.println(d[s]);
        }
    }
}
