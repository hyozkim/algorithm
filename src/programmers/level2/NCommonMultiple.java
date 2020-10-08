package programmers.level2;

/***
 * N개의 최소공배수 - Lv.2
 * 연습문제
 */
public class NCommonMultiple {
    public static int solution(int[] arr) {
        int answer = arr[0];

        for (int i = 0; i < arr.length; i++) {
            answer = lcm(answer, arr[i]);
        }

        return answer;
    }

    // 최소 공배수
    public static int lcm(int a, int b) {
        return (a*b) / gcd(a,b);
    }

    // 최대 공약수
    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static void main(String[] args) {
        int[] arr = {2,6,8,14};
        //int[] arr = {1,2,3};

        System.out.println(solution(arr));
    }
}
