package programmers.level4;

import java.util.Arrays;

/***
 * 사칙연산 - Lv.4
 * 찾아라 프로그래밍 마에스터
 */
public class FourRulesCalculation {
    public static void main(String[] args) {
        String[] arr = {"1", "-", "3", "+", "5", "-", "8"}; // 7 -> 4:3
        //String[] arr = {"5", "-", "3", "+", "1", "+", "2", "-", "4"}; // 9 -> 5:4

        System.out.println(solution(arr));
    }

    public static int solution(String[] arr) {
        int n = arr.length;
        int[] a = new int[n / 2 + 1];
        String[] x = new String[n / 2];

        int a_idx = 0;
        int x_idx = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                a[a_idx++] = stoi(arr[i]);
            } else {
                x[x_idx++] = arr[i];
            }
        }

        //System.out.println(Arrays.toString(a));
        //System.out.println(Arrays.toString(x));

        int[][] dp_max = new int[a.length][a.length];
        int[][] dp_min = new int[a.length][a.length];
        for (int[] d_min : dp_min) { Arrays.fill(d_min, 1000000); }
        for (int[] d_max : dp_max) { Arrays.fill(d_max, -1000000); }

        for (int i = 0; i < a.length; i++) {
            dp_max[i][i] = a[i];
            dp_min[i][i] = a[i];
        }

        for (int c = 0; c < a.length; c++) {
            for (int i = 0; i < a.length - c; i++) {
                int j = c + i;
                for (int k = i; k < j; k++) {
                    if ( x[k].equals("+") ) {
                        dp_max[i][j] = Math.max(dp_max[i][j], dp_max[i][k] + dp_max[k + 1][j]);
                        dp_min[i][j] = Math.min(dp_min[i][j], dp_min[i][k] + dp_min[k + 1][j]);
                    } else if ( x[k].equals("-") ) {
                        dp_max[i][j] = Math.max(dp_max[i][j], dp_max[i][k] - dp_min[k + 1][j]);
                        dp_min[i][j] = Math.min(dp_min[i][j], dp_min[i][k] - dp_max[k + 1][j]);
                    }
                }
            }
        }

        return dp_max[0][a.length - 1];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
