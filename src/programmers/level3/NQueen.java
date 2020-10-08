package programmers.level3;

/***
 * N-Queen - Lv.3
 * 연습문제
 *
 * 시간복잡도 O(N^2)
 */
public class NQueen {
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
        a = new boolean[12][12];
        check_col = new boolean[12];
        check_dig = new boolean[24];
        check_dig2 = new boolean[24];

        return calc(0);
    }

    public static void main(String[] args) {
        int n = 4;

        System.out.println(solution(n));
    }
}
