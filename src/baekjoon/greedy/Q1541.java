package baekjoon.greedy;

import java.util.Scanner;

// 잃어버린 괄호 ( Q1541 )
// Backjoon - Greedy
public class Q1541 {
    public static int calc(String input) {
        String[] inputs = input.split("\\+");
        int sum = 0;
        for (String s : inputs) {
            sum += stoi(s);
        }
        return sum;
    }

    public static int solution(String sik) {
        String[] siks = sik.split("\\-");

        int minResult = 0;
        for (int i = 0; i < siks.length; i++) {
            int calcNum = calc(siks[i]);

            if (i == 0) calcNum *= -1; // 1. 빼기로 변경!
            minResult -= calcNum; // 2. 빼기
        }

        return minResult;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        // String sik = "55-50+40";
        // String sik = "55-50+40-50+80+90-10+90";
        Scanner sc = new Scanner(System.in);
        String sik = sc.next();

        System.out.println(solution(sik));
    }
}
