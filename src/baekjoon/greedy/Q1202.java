package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/***
 * 보석 도둑
 * Greedy
 */
public class Q1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = stoi(input[0]);
        int k = stoi(input[1]);

        ArrayList<Jewel> jewels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] str_jewel = br.readLine().split(" ");
            int w = stoi(str_jewel[0]);
            int v = stoi(str_jewel[1]);
            jewels.add(new Jewel(w,v));
        }

        ArrayList<Integer> bags = new ArrayList<>();
        for (int j = 0; j < k; j++) {
            bags.add(stoi(br.readLine()));
        }

        Collections.sort(jewels); // 무게 오름차순, 가격 내림차순
        Collections.sort(bags); // 무게 오름차순

        // Print
        //for(Jewel jewel : jewels) System.out.println(jewel.w + " " + jewel.v);
        //System.out.println("bags: " + bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> (o2-o1));
        int i = 0; long sum = 0L;
        for (int j = 0; j < k; j++) {
            while( i < n && jewels.get(i).w <= bags.get(j) ) {
                pq.add(jewels.get(i).v);
                i++;
            }

            if( !pq.isEmpty() ) {
                sum += pq.poll();
            }
        }
        System.out.println(sum);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

    static class Jewel implements Comparable<Jewel> {
        int w ;
        int v ;
        public Jewel(int w, int v) {
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.w - o.w;
        }
    }
}
