package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 동전 0
 * Greedy
 */
public class Q11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = stoi(input[0]);
        int value = stoi(input[1]);

        int[] money = new int[n];
        for (int i = 0; i < n; i++) {
            money[i] = stoi(br.readLine());
        }

        int answer = 0;
        for (int i = n-1; i >= 0; i--) {
            answer += value / money[i];
            value %= money[i];
        }
        System.out.println(answer);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
