package programmers.level2;

/***
 * 멀쩡한 사각형 - Lv.2
 * Summer/Winter Coding(2019)
 *
 * 최대공약수 문제
 */
public class RegularRectangle {
    public static void main(String[] args) {
        int w = 8; int h = 12;

        System.out.println(solution(w,h));
    }

    public static long solution(int w, int h) {
        return ((long) w * (long) h) - (long) (w + h - gcd(w,h));
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
