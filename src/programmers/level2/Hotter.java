package programmers.level2;

import java.util.PriorityQueue;

// Heap - 더맵게
// Programmers Lv.2
public class Hotter {
    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            heap.offer((scoville[i]));
        }

        int answer = 0;
        while( heap.peek() < K ) {
            if( heap.size() < 2 )
                return -1;

            int scov1 = heap.poll();
            int scov2 = heap.poll();
            heap.offer(scov1 + 2*scov2);
            answer ++;
        }

        return answer;
    }

    public static void main(String[] args) {
        //int[] scoville = {1, 2, 3, 9, 10, 12}; int K = 7;
        int[] scoville = {2, 4, 5, 7, 10, 12}; int K = 100;

        System.out.println(solution(scoville,K));
    }
}
