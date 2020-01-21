package backjoon.greedy;

import java.util.Scanner;

// 병든 나이트( Q1783 )
// Backjoon - Greedy
//
public class Q1783 {
    static long solution(long h, long w) {
        if( h == 1 )
            return 1;
        else if( h == 2 )
            return Math.min(4,(w+1)/2); // ?
        else {
            if( w >= 7 )
                return w - 2; // ?
            else
                return Math.min(4,w); // ??
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long h = sc.nextLong(); long w = sc.nextLong();

        System.out.println(solution(h,w));
    }
}
