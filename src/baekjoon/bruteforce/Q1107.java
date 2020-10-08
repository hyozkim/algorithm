package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 리모컨
 * 완전탐색
 */
public class Q1107 {
    static boolean[] broken = new boolean[10];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        int k = stoi(br.readLine());

        broken = new boolean[10];
        String[] brokenNums = br.readLine().split(" ");
        // System.out.println(brokenNums.length);

        for (int i = 0; i < k; i++) {
            broken[stoi(brokenNums[i])] = true;
        }

        int ans = Math.abs(n - 100);

        // 완전탐색
        for (int i = 0; i <= 1000000; i++) {
            int c = i;
            int len = possible(c);
            if( len > 0 ) { // 우선, 숫자로 이동 가능한 조건일때
                int press = Math.abs(c - n);

                ans = Math.min(ans, len+press);
            }
        }

        System.out.println(ans);
    }

    private static int possible(int c) {
        int len = 0;
        if( c == 0 ) {
            if( broken[c] )
                return 0;
            else return 1;
        }

        while( c > 0 ) {
            if( broken[c%10] )
                return 0;
            len += 1;
            c /= 10;
        }

        return len;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
