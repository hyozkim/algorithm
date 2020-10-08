package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 완전탐색 - Queue 사용하기
 *
 * DSLR
 */
public class Q9019 {
    static class Command {
        int num;
        String status;
        public Command(int num, String status) {
            this.num = num;
            this.status = status;
        }
    }

    static final int MAX = 10001;
    static int asis;
    static int tobe;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = stoi(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] inputs = br.readLine().split(" ");
            asis = stoi(inputs[0]);
            tobe = stoi(inputs[1]);

            boolean[] visit = new boolean[MAX];
            Queue<Command> q = new LinkedList<>();
            q.add(new Command(asis, ""));
            visit[asis] = true;

            System.out.println(bfs(q,visit));
        }
    }

    public static String bfs(Queue<Command>q, boolean[] visit) {
        String answer = "";

        while( !q.isEmpty() ) {
            Command command = q.poll();
            int now = command.num;
            String status = command.status;

            if( now == tobe ) {
                answer = status;
                break;
            }

            // D
            int next = (now*2) % 10000;
            if( !visit[next] ) {
                visit[next] = true;
                q.add(new Command(next, status+"D"));
            }

            // S
            next = (now-1) < 0 ? 9999 : now-1;
            if( !visit[next] ) {
                visit[next] = true;
                q.add(new Command(next, status+"S"));
            }

            // L
            next = (now/1000) + (now%1000)*10;
            next %= 10000;
            if( !visit[next] ) {
                visit[next] = true;
                q.add(new Command(next, status+"L"));
            }

            // R
            next = (now%10) * 1000 + (now/10);
            next %= 10000;
            if( !visit[next] ) {
                visit[next] = true;
                q.add(new Command(next, status +"R"));
            }
        }

        return answer;
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
