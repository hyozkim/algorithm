package programmers.level2;

/***
 * 피보나치 수 - Lv.2
 * 연습문제
 */
public class FibonacciNumber {
    static int[] fibo;
    public static int solution(int n) {
        fibo = new int[n+1];
        fibo[0] = 0;
        fibo[1] = 1;

        int answer = fibonacci(n);

        return answer;
    }

    public static int fibonacci(int n) {
        if( n <= 1 ) {
            return n;
        } else {
            if( fibo[n] > 0 ) {
                return fibo[n] ;
            }
            fibo[n] = fibonacci(n-1) % 1234567 + fibonacci(n-2) % 1234567;
            return fibo[n] % 1234567;
        }
    }

    public static void main(String[] args) {
        int n = 3;
        // int n = 5;
        System.out.println(solution(n));
    }
}
