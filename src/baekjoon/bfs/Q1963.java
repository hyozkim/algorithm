package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 완전탐색 - Queue 사용하기
 *
 * 소수경로
 */
public class Q1963 {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = stoi(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] inputs = br.readLine().split(" ");

            System.out.println(solution(inputs[0], inputs[1]));
        }
    }

    static final int MAX = 10000;
    static final int MIN = 1000;
    private static int solution(String s1, String s2) {
        boolean[] check = new boolean[MAX+1];
        int[] dist = new int[MAX+1];
        Queue<String> q = new LinkedList<>();

        check[stoi(s1)] = true;
        q.add(s1);

        // 1) prime 배열 만들기
        boolean[] prime = makePrimeArray();

        while( !q.isEmpty() ) {
            String now = q.remove();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= 9; j++) {
                    // 2) 해당 자리 index 수 변경
                    String next = change(i, (char)('0'+j), new StringBuilder(now));
                    if( stoi(next) < MIN ) { continue; } // 1000보다 작으면 4자리수 만족 X -> 거름

                    // 3) 소수 && 이전 경우 없음(최초의 경우)
                    if( prime[stoi(next)] && !check[stoi(next)] ) {
                        check[stoi(next)] = true;
                        dist[stoi(next)] = dist[stoi(now)] + 1;
                        q.add(next);
                    }
                }
            }
        }

        return dist[stoi(s2)];
    }

    private static String change(int i, char j, StringBuilder sb) {
        sb.setCharAt(i, j);

        return sb.toString();
    }

    private static boolean[] makePrimeArray() {
        boolean[] prime = new boolean[MAX+1];

        prime[0] = prime[1] = false;
        for(int i=2; i<MAX; i+=1) {
            prime[i] = true;
        }

        //2 부터 숫자를 키워가며 배수들을 제외(false 할당)
        for(int i=2; i*i<MAX; i+=1) {
            for(int j=i*i; j<MAX; j+=i) {
                prime[j] = false;        //2를 제외한 2의 배수 false
            }
        }

        return prime;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
