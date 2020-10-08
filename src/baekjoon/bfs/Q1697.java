package baekjoon.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/***
 * 완전탐색 - Queue 사용하기
 *
 * 숨바꼭질
 */
public class Q1697 {
    public static void main(String[] args) {
        // 1) X-1
        // 2) X+1
        // 3) 2X
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); int m = sc.nextInt();

        System.out.println(solution(n, m));
    }

    static final int MAX = 100001;
    private static int solution(int n, int m) {
        int[] dist = new int[MAX];
        boolean[] visit = new boolean[MAX];
        Queue<Integer> q = new LinkedList<>();

        dist[n] = 0;
        visit[n] = true;
        q.add(n);

        while (!q.isEmpty()) {
            int now = q.poll();
            // System.out.println("now: " + now);

            if( (now+1) < MAX ) {
                if (!visit[now + 1]) {
                    visit[now + 1] = true;
                    q.add(now + 1);
                    dist[now + 1] = dist[now] + 1;
                }
            }

            if( (now-1) >= 0 ) {
                if (!visit[now - 1]) {
                    visit[now - 1] = true;
                    q.add(now - 1);
                    dist[now - 1] = dist[now] + 1;
                }
            }

            if( (2*now) < MAX ) {
                if (!visit[2 * now]) {
                    visit[2 * now] = true;
                    q.add(2 * now);
                    dist[2 * now] = dist[now] + 1;
                }
            }
        }

        return dist[m];
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
