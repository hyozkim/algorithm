package baekjoon.dfs;

/***
 * 완전탐색 - 재귀호출
 *
 * N-Queen
 */
import java.util.Scanner;

public class Q9663 {
    static boolean[][] a;
    static int n;
    static boolean[] check_col;
    static boolean[] check_dig;
    static boolean[] check_dig2;

    public static boolean check(int row, int col) {
        // |
        if (check_col[col]) {
            return false;
        }
        // 왼쪽 위 대각선
        if (check_dig[row+col]) {
            return false;
        }
        // /
        if (check_dig2[row-col+n]) {
            return false;
        }

        return true;
    }

    public static int calc(int row) {
        if (row == n) {
            return 1;
        }

        int cnt = 0;
        for (int col=0; col<n; col++) {
            if (check(row, col)) {
                check_dig[row+col] = true;
                check_dig2[row-col+n] = true;
                check_col[col] = true;
                a[row][col] = true;
                cnt += calc(row+1);
                check_dig[row+col] = false;
                check_dig2[row-col+n] = false;
                check_col[col] = false;
                a[row][col] = false;
            }
        }

        return cnt;
    }

    public static int solution(int N) {
        n = N;
        a = new boolean[15][15];
        check_col = new boolean[15];
        check_dig = new boolean[40];
        check_dig2 = new boolean[40];

        return calc(0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(solution(n));
    }
}

