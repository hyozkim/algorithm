package programmers.level3;

import java.util.*;

// 재귀(효율성) - 줄 서는 방법
// Programmers Lv.3
public class WayLineUp {
    private static long recursiveFactorial(int n, ArrayList<Integer> line) {
        if( n == 1 ) {
            line.add(1);
            return 1;
        }
        line.add(n);

        return recursiveFactorial(n-1, line) * n;
    }

    private static int[] calculate(int n, long factorial, long k, ArrayList<Integer> line, int[] ans, int index) {

        if( n > 0 ) {
            factorial = factorial / n;

            int i = (int) (k/factorial); // 해당하는 index 찾기
            ans[index] = line.remove(i);

            k = k % factorial; // 다음 스텝을 위해 k factorial 나머지
            calculate(n-1, factorial, k, line, ans, index+1); // 다음 index ++, n 은 --
        }

        return ans;
    }

    public static int[] solution(int n, long k) {

        ArrayList<Integer> line = new ArrayList<>();
        long factorial = recursiveFactorial(n, line); // 전체 경우의 수
        Collections.sort(line);

        k--;

        return calculate(n, factorial, k, line, new int[n], 0);
    }

    public static void main(String[] args) {
        //int n = 3; int k = 5;
        int n = 4; int k = 8;

        System.out.println(Arrays.toString(solution(n,k)));
    }
}
