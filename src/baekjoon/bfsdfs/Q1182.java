package baekjoon.bfsdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 부분수열의 합
public class Q1182 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int sum = Integer.parseInt(input[1]);

        int[] a = new int[n];
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(input2[i]);
        }

        System.out.println(solution(n, sum, a));
    }

    private static int solution(int n, int s, int[] a) {
        int ans = 0;

        for (int i = 1; i < (1 << n); i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                // System.out.println((i & (1 << j)));
                if ((i & (1 << j)) != 0 ) {
                    sum += a[j];
                }
            }
            if( sum == s ) {
                ans += 1;
            }
        }

        return ans;
    }
}
