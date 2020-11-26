package swea.d3;

import java.util.Arrays;
import java.util.Scanner;

public class P9778 {
    private static String calc(int sum, int[][] black_jack) {
        int cnt_2ne1_under = 0;
        int cnt_2ne1_up = 0;

        for (int i = 2; i <= 11; i++) {
            if( black_jack[i][0] > 0 ) {
                if (sum + i <= 21) {
                    cnt_2ne1_under += black_jack[i][0];
                }
                else if (sum + i > 21) {
                    cnt_2ne1_up += black_jack[i][0];
                }
            }
        }

        return cnt_2ne1_under <= cnt_2ne1_up ? "STOP" : "GAZUA";
    }

    public static String solution(int[][] black_jack, int[] cards) {
        int sum = Arrays.stream(cards).sum();

        return calc(sum, black_jack);
    }

    private static void init(int[][] black_jack) {
        for (int i = 2; i <= 11; i++) {
            if( i == 10 )
                black_jack[i][0] = 16;
            else
                black_jack[i][0] = 4;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
        for (int test_case = 1; test_case <= T; test_case++) {
            int[][] black_jack = new int[12][1];
            init(black_jack);

            int n = sc.nextInt();
            int[] cards = new int[n];
            for (int i = 0; i < n; i++) {
                cards[i] = sc.nextInt();
                black_jack[cards[i]][0]--;
            }

            //System.out.println(Arrays.toString(cards));
            //for (int i = 2; i <= 11; i++) { System.out.println(i + " " + Arrays.toString(black_jack[i])); }
            System.out.println("#"+test_case + " " + solution(black_jack, cards));
        }
    }
}
