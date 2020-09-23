package programmers.level3;

import java.util.Arrays;

/***
 * 숫자 게임 - Lv.3
 * Summer/Winter Coding (~2018)
 */
public class NumberGame {
    public static void main(String[] args) {
        int[] A = {5,1,3,7}; int[] B = {2,2,6,8};
        //int[] A = {2,2,2,2}; int[] B = {1,1,1,1};

        System.out.println(solution(A,B));
    }

    public static int solution(int[] A, int[] B) {
        int answer = 0;
        int n = A.length;

        Arrays.sort(A);
        Arrays.sort(B);

        int a_index = 0;
        int b_index = 0;
        for (int i = 0; i < n; i++) {
            if( A[a_index] >= B[b_index] ) {
                b_index++;
            } else {
                a_index++;
                b_index++;

                answer ++;
            }
        }

        return answer;
    }

    /*
    static int answer = 0;
    public static int solution(int[] A, int[] B) {
        int n = A.length;
        boolean[] Acheck = new boolean[n];
        boolean[] Bcheck = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if( A[i] < B[j] ) {
                    Acheck[i] = true;
                    Bcheck[j] = true;
                    dfs(A, B, Acheck, Bcheck, i+1, n, 1);
                    Acheck[i] = false;
                    Bcheck[j] = false;
                }
            }
        }

        return answer;
    }

    private static void dfs(int[] A, int[] B, boolean[] Acheck, boolean[] Bcheck, int index, int n, int sum) {
        if( index >= n ) {
            answer = Math.max(answer,sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ( !Acheck[i] && !Bcheck[j] && A[i] < B[j] ) {
                    Acheck[i] = true;
                    Bcheck[j] = true;
                    dfs(A, B, Acheck, Bcheck, i+1, n, sum+1);
                    Acheck[i] = false;
                    Bcheck[j] = false;
                }
            }
        }
    }
    */
}
