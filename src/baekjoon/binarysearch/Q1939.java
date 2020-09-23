package baekjoon.binarysearch;

import java.util.*;

/* 중량 제한 */
/* 최적화 문제 */

// dfs가 추가되어 혼란스럽다 - difficult(어렵다)
// 최대값을 구해야 하는데, 이분 탐색 방법을 활용해 구현하려는 생각을 떠올리기가 어렵다.

public class Q1939 {
    static int n;
    static int m;
    static ArrayList<Bridge> [] bridges;
    static boolean [] visited;

    static int end;

    public static boolean go(int next, int mid) {
        if(visited[next])
            return false;

        visited[next] = true;
        if( next == end )
            return true;

        for( Bridge bridge : bridges[next] ) {
            if( bridge.c >= mid ) {
                if( go(bridge.s, mid) ) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        bridges = (ArrayList<Bridge>[]) new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            bridges[i] = new ArrayList<>();
        }

        int max = 0;
        while( m-- > 0 ) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int c = sc.nextInt();

            bridges[s].add(new Bridge(e,c));
            bridges[e].add(new Bridge(s,c));
            max = Math.max(max, c);
        }

        int st = sc.nextInt();
        end = sc.nextInt();

        int ans = 0;
        int l = 1;
        int r = max;
        while( l <= r ) {
            int mid = (l+r)/2;

            visited = new boolean[n+1];
            if( go(st,mid) ) {
                ans = mid;
                l = mid + 1;

            } else {
                r = mid - 1;
            }
        }

        System.out.println(ans);
    }

    static class Bridge {
        int s;
        int c;
        public Bridge(int s, int c) {
            this.s = s;
            this.c = c;
        }
    }

}