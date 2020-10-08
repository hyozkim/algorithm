package baekjoon.bitmask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/***
 * 부분집합의 합2
 * 완전 탐색
 */
public class Q1208 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        int m = n / 2;
        n = n - m;

        //System.out.println("n: " + n + ", m: " + m);
        for (int i = 0; i < (1 << n); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == (1<<j)) {
                    sum += a[j];
                }
            }
            list1.add(sum);
        }

        for (int i = 0; i < (1 << m); i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) == (1<<j)) {
                    sum += a[j+n];
                }
            }
            list2.add(sum);
        }

        Collections.sort(list1);
        Collections.sort(list2, (o1,o2) -> o2-o1);

        n = (1<<n);
        m = (1<<m);

        int count = 0;
        int i = 0; int j = 0;
        while( i < n && j < m ) {
            int x = list1.get(i);
            int y = list2.get(j);
            if( x + y == s ) {
                int c1 = 1;
                int c2 = 1;
                i ++;
                j ++;
                while( i < n && list1.get(i) == list1.get(i-1) ) {
                    c1++;
                    i ++;
                }
                while( j < m && list2.get(j) == list2.get(j-1) ) {
                    c2++;
                    j ++;
                }

                count += (c1 * c2);
            } else if ( x + y < s ) {
                i ++;
            } else {
                j ++;
            }
        }

        if( s == 0 ) count -= 1;

        System.out.println(count);
    }
}
