package baekjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 공항
 *
 * MST(Union-Find)
 * 상호배타적 집합(Disjoint Set)
 */
public class Q10775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num_of_gate = stoi(br.readLine());
        int num_of_airplane = stoi(br.readLine());

        int[] p = new int[num_of_gate+1];
        for (int i = 1; i <= num_of_gate; i++) {
            p[i] = i;
        }

        int answer = 0;
        for (int i = 0; i < num_of_airplane; i++) {
            System.out.println(Arrays.toString(p));

            int gi = stoi(br.readLine());

            int docking = find(p,gi);
            if( docking == 0 )
                break;

            union(p, docking, docking-1);
            answer += 1;
        }

        System.out.println(answer);
    }

    private static int find(int[] p, int u) {
        if( p[u] == u )
            return u;

        return p[u] = find(p,p[u]);
    }

    static void union(int[] p, int a, int b) {
        int x = find(p,a);
        int y = find(p,b);

        p[x] = y;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
