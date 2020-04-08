package programmers.level2;

import java.util.*;

// Heap - 라면공장
// Programmers Lv.2
public class NoodleFactory {

    public static int solution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        int n = dates.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
        int idx = 0;

        for (int i = 0; i < k; i++) {
            if( idx < n && i == dates[idx] ) {
                pq.add(supplies[idx++]);
            }

            if( stock == 0 ) {
                stock += pq.poll();
                answer += 1;
            }

            stock --;
        }

        return answer;
    }

    public static void main(String[] args) {
        int stock = 4; int[] dates = {4,10,15}; int[] supplies = {20,5,10}; int k = 30;

        System.out.println(solution(stock, dates, supplies, k));
    }
}
