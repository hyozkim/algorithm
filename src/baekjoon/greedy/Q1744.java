package baekjoon.greedy;

import java.util.ArrayList;
import java.util.Collections;

// 수 묶기( Q1744 )
// Backjoon - Greedy

// 수열에 수는 중복없이 1개만 있다고 가정
public class Q1744 {
    public static int solution( int[] arr ) {

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        int oneCnt = 0;
        int zeroCnt = 0;
        for(int i=0; i<arr.length; i++) {
            if( arr[i] == 1 ) { // 1인 수 count
                oneCnt ++;
            } else if( arr[i] > 1 ) {   // 서로 곱할 2 이상인 수 plus add
                plus.add(arr[i]);

            } else if( arr[i] < 0 ) {   // 서로 곱할 음수 minus add
                minus.add(arr[i]);

            } else if( arr[i] == 0 ) {  // 음수가 만약 홀수개라면 곱할 제로 수 count
                zeroCnt ++;
            }
        }

        if( plus.size() % 2 == 1 ) {
            plus.add(1);
        }
        if( minus.size() % 2 == 1 ) {
            minus.add(zeroCnt >= 1 ? 0 : 1);
        }

        Collections.sort(plus);
        Collections.sort(minus);

        int answer = oneCnt;
        //System.out.println(plus);
        //System.out.println(minus);
        for( int i=plus.size()-1; i>=0; i-=2 ) {
            answer += plus.get(i) * plus.get(i-1);
        }
        for( int i=0; i<minus.size(); i+=2 ) {
            answer += minus.get(i) * minus.get(i+1);
        }

        return answer;
    }

    public static void main(String[] args) {
        /*
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        */

        int[] arr = {-1,2,1,3};

        System.out.println(solution(arr));
    }
}
