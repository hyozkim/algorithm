package programmers.level2;

// 점프와 순간이동
// Summer/Winter Coding (~2018)
public class JumpAndTeleport {
    public static void main(String[] args) {
        //int n = 5;
        //int n = 6;
        int n = 5000;

        System.out.println(solution(n));
    }

    public static int solution(int n) {
        int ans = 0;


        while( n >= 1 ) {
            ans += (n%2);
            n /= 2;
        }

        // 이동: K 칸 앞으로 점프
        // 순간이동: (현재까지 온 거리) x 2

        // 5
        // 1 1 1 1 1 = 5
        // 1 1 점프(4) 1 = 3
        // 1 점프(2) 점프(4) 1 = 2

        return ans;
    }
}
