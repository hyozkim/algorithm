package programmers.level2;

import java.util.Arrays;

/***
 * 최솟값 만들기 - Lv.2
 * 연습문제
 */
public class MakeMinimumSum {
    public static int solution(int []A, int []B)
    {
        int sum = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        for(int i=0; i<A.length; i++) {
            sum += A[i] * B[B.length-1-i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int []A = {1, 4, 2}; int []B = {5, 4, 4};
        // int []A = {1, 2}; int []B = {3, 4};

        System.out.println(solution(A,B));
    }
}
