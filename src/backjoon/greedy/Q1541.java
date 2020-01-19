package backjoon.greedy;

import java.util.Scanner;

// 잃어버린 괄호 ( Q1541 )
// Backjoon - Greedy
public class Q1541 {
    public static int calc(String input) {
        String[] inputs = input.split("\\+");
        int sum = 0;
        for( String i : inputs ) {
            sum += Integer.parseInt(i);
        }
        return sum;
    }

    public static int solution(String sik) {
        String[] siks = sik.split("\\-");

        int minResult = 0;
        for(int i=0; i<siks.length; i++) {
            int calcNum = calc(siks[i]);

            if( i== 0 ) calcNum *= -1;
            minResult -= calcNum;
        }

        return minResult;
    }

    public static void main(String[] args) {
        // String sik = "55-50+40";
        // String sik = "55-50+40-50+80+90-10+90";
        Scanner sc = new Scanner(System.in);
        String sik = sc.next();

        System.out.println(solution(sik));
    }
}
