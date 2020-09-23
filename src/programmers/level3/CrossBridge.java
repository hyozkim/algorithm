package programmers.level3;

/***
 * 징검 다리 건너기 - Lv.3
 * 2019 카카오 개발자 겨울 인턴십
 */
public class CrossBridge {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}; int k = 3;

        System.out.println(solution(stones, k));
    }

    /***
     * 그리디 풀이
     */
    public static int solution(int[] stones, int k) {
        int min = Integer.MAX_VALUE;

        int len = stones.length;
        for (int i = 0; i <= (len-k); ) {
            int index = 0;
            int max = stones[i];

            for (int j = i+1; j < i+k; j++) {
                if( stones[j] >= max ) {
                    max = stones[j];
                    index = j;
                }
            }

            if( index == 0 ) {
                i++;
            } else {
                i = (index+1);
            }

            min = Math.min(min,max);
        }

        return min;
    }

    /***
     * 이분탐색 풀이
     *
    public static int solution(int[] stones, int k) {
        int answer = 0;

        int low = 0;
        int high = Integer.MAX_VALUE;

        while( low <= high ) {
            int mid = (low + high) / 2;

            // System.out.println("mid : " + mid);
            if( canCross(stones, k, mid) ) {
                low = mid + 1;
                answer = mid;

            } else {
                high = mid - 1;

            }
        }

        return answer;
    }

    private static boolean canCross(int[] stones, int k, int friends) {
        int count = 0;

        for(int s : stones) {
            if( s - friends < 0 )
                count++;
            else
                count = 0;


            if( count >= k )
                return false;
        }

        return true;
    }
     */
}
