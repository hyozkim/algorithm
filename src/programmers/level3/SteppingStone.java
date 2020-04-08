package programmers.level3;
import java.util.*;

public class SteppingStone {
    // 기준 : 바위 사이의 최소 거리
    public static boolean check(int [] a, int n, int d, int mid) {
        int cnt = 0;
        int last = 0;

        for(int i=0; i<a.length; i++) {
            int gap = (i != a.length ?  a[i] - last : d - last );
            if( gap < mid ) {
                cnt ++;
            } else {
                last = a[i];
            }
        }

        return cnt > n ? true : false;
    }

    public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        Arrays.sort(rocks);

        int l = 1;
        int r = distance;
        while( l <= r ) {
            int mid = (l+r)/2;

            if( check(rocks,n,distance, mid) ) {
                r = mid - 1;

            } else {
                l = mid + 1;
                answer = mid;

            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int distance = 25; int[] rocks = {2, 14, 11, 21, 17}; int n = 2;
        System.out.println(solution(distance,rocks,n));
    }
}
