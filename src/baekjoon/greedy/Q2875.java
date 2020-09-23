package baekjoon.greedy;

import java.util.Scanner;

// 대회 or 인턴( Q2875 )
// Backjoon - Greedy
public class Q2875 {
    public static int solution( int[] a ) {
        int team = 0;

        int w = a[0]/2;
        int m = a[1];
        int i = a[2];

        int wRemain = a[0]%2;
        int mRemain = 0;

        if( w > m ) {
            team = m;
            wRemain += (w-m) * 2;

        } else {
            team = w;
            mRemain += m-w;

        }

        int remains = mRemain + wRemain;
        int cnt = 0;
        while( remains + 3*cnt < i ) {
            team --;
            cnt ++;
        }

        return team;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /* 백준 정답
        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();
        int ans = 0;
        while (m >= 2 && n >= 1 && m+n >= k+3) {
            ans += 1;
            m -= 2;
            n -= 1;
        }
        System.out.println(ans);
        */

        int n = 3;
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        // int[] arr = {6,3,2};

        System.out.println(solution(arr));
    }
}
