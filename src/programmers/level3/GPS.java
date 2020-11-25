package programmers.level3;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * GPS - Lv.3
 * 2017 카카오코드 본선
 *
 * DP (Bottom-Top)
 */
public class GPS {
    public static void main(String[] args) {
        int n = 7; int m = 10; int[][] edge_list = {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}; int k = 6; int[] gps_log = {1, 2, 3, 3, 6, 7};
        //int n = 7; int m = 10; int[][] edge_list = {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}; int k = 6; int[] gps_log = {1, 3, 3, 3, 3, 7};
        //int n = 7; int m = 10; int[][] edge_list = {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}; int k = 6; int[] gps_log = {1, 2, 4, 6, 5, 7};
        //int n = 7; int m = 8; int[][] edge_list = {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}; int k = 6; int[] gps_log = {1, 2, 3, 3, 6, 7};

        System.out.println(solution(n, m, edge_list, k, gps_log));
    }

    static final int INF = 987654321;
    public static int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int answer = 0;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            adj.get(edge_list[i][0]).add(edge_list[i][1]);
            adj.get(edge_list[i][1]).add(edge_list[i][0]);
        }
        //print(adj);

        int[][] dp = new int[k][n+1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][gps_log[0]] = 0;
        for (int i = 1; i < k; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j]);

                for(int p : adj.get(j)) {
                    dp[i][j] = Math.min(dp[i-1][p], dp[i][j]);
                }

                dp[i][j] += (gps_log[i] == j ? 0 : 1); // 오류 수정 지점
            }
        }

        return dp[k-1][gps_log[k-1]] >= INF ? -1 : dp[k-1][gps_log[k-1]];
    }

    private static void print(ArrayList<ArrayList<Integer>> adj) {
        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.print(adj.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}