package programmers.level2;

import java.util.PriorityQueue;

/***
 * 프린터 - Lv.2
 * 스택/큐
 */
public class Printer {
    public static void main(String[] args) {
        //int[] priorities = {2, 1, 3, 2}; int location = 2;
        int[] priorities = {1, 1, 9, 1, 1, 1}; int location = 0;

        System.out.println(solution(priorities, location));
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        int n = priorities.length;

        PriorityQueue<Integer> pq = new PriorityQueue<>((i1,i2) -> i2-i1);
        for (int i = 0; i < n; i++) {
            pq.add(priorities[i]);
        }

        // for( Order order : pq ) { System.out.println(order.ch + " " + order.prior); }

        boolean[] visit = new boolean[n];
        int count = 0;
        int index = 0;
        while( true ) {
            if( !visit[index] && pq.peek() <= priorities[index] ) {
                count ++;
                visit[index] = true;
                pq.poll();

                if( index == location ) {
                    answer = count;
                    break;
                }
            }

            index ++;
            if( index >= n ) {
                index = 0;
            }
        }

        return answer;
    }
}
