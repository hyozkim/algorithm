package baekjoon.dp02.sol03;

import java.util.Arrays;
import java.util.Scanner;

/***
 * 자두 나무
 * 다이나믹 프로그래밍
 *
 * DP2 문제풀이 3 동영상
 */
public class Q2240 {
    static int N,M;
    static int[] a;
    static int[][] d;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        a = new int[N+1];
        for(int i=1; i<=N; i++) { a[i] = sc.nextInt(); }

        //System.out.println(N + " " + M);
        //System.out.println(Arrays.toString(a));
        d = new int[N+1][M+1];
        for(int i=0; i<=N; i++) {
            Arrays.fill(d[i], -1);
        }

        System.out.println(Math.max(go(1,0), go(1,1)));
    }

    public static int go(int sec, int turn) {
        if( sec == N+1 && turn <= M ) return 0; // 전체 T초 초과
        if( turn > M ) return 0; // 최대 움직임 초과
        if( d[sec][turn] != -1 ) {
            return d[sec][turn]; // Memo
        }

        // 자두 위치(not move 혹은 move 이기 때문에)
        int where = turn % 2 + 1;

        // go(sec+1, turn+1) : 움직임 O
        // go(sec+1, turn)   : 움직임 X
        d[sec][turn] = Math.max(go(sec+1, turn+1), go(sec+1, turn)) + (where == a[sec] ? 1 : 0);
        // 가장 아이러니한 부분 where !!!!

        return d[sec][turn];
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
