package baekjoon.dp02.sol01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 팰린드롬?
 *
 * DP2 문제풀이 1
 */
public class Q10942 {
    static int[][] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = stoi(br.readLine());
        int[] a = new int[n+1];
        String[] input = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            a[i] = stoi(input[i-1]);
        }

        d = new int[n+1][n+1];
        solve(a,n);

        StringBuilder sb = new StringBuilder();
        int m = stoi(br.readLine());
        for (int i = 0; i < m; i++) {
            String[] inputs = br.readLine().split(" ");
            int x = stoi(inputs[0]);
            int y = stoi(inputs[1]);

            sb.append(d[x][y]+"\n");
            //sb.append(go(a,x,y)+"\n");
        }
        System.out.println(sb.toString());
    }

    private static int go(int[] a, int i, int j) {
        if( i == j ) {
            return 1;
        } else if( i+1 == j ) {
            if (a[i] == a[j]) return 1;
            else return 0;
        }

        if( d[i][j] > 0 ) return d[i][j];
        if( a[i] != a[j] ) return d[i][j] = 0; // memorization
        else return d[i][j] = go(a, i+1, j-1);
    }

    public static void solve(int[] a, int n) {
        // 1. 1자리
        for (int i = 1; i <= n; i++)
            d[i][i] = 1;

        // 2. 2자리
        for (int i = 1; i <= n-1; i++) {
            if( a[i] == a[i+1] ) {
                d[i][i + 1] = 1;
            }
        }
        // 3. 3자리 이상
        for (int k = 3; k <= n; k++) {
            for (int i = 1; i <= n-k+1; i++) {
                int j = i+k-1; // k길이 만큼 떨어진 index
                if( a[i] == a[j] && d[i+1][j-1] == 1 ) { // 문자열이 같고, [i-1][j+1] 가 팰린드롬이라면
                    d[i][j] = 1;
                }
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}