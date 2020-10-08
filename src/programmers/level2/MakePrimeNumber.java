package programmers.level2;

/***
 * 소수 만들기 - Lv.2
 * Summer/Winter Coding(~2018)
 */
public class MakePrimeNumber {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        //int[] nums = {1, 2, 7, 6, 4};

        System.out.println(solution(nums));
    }

    public static int solution(int[] nums) {
        int answer = 0;

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                for (int k = j+1; k < len; k++) {
                    if( isPrime(nums[i] + nums[j] + nums[k] ) ) {
                        answer ++;
                    }
                }
            }
        }

        return answer;
    }

    private static boolean isPrime(int a) {
        if( a == 0 || a == 1 )
            return false;
        for(int i = 2; i <= a-1; i++) {
            if( a % i == 0 )
                return false;
        }

        return true;
    }

}
