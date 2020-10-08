package programmers.level4;

/***
 * 도둑질 - Lv.4
 * 프로그래머스 문제
 */
public class StealStuff {
    public static void main(String[] args) {
        //int[] money = {1,2,3,1};
        int[] money = {4,5,10,100,100};

        System.out.println(solution(money));
    }

    public static int solution(int[] money) {
        int n = money.length;

        int[] dp1 = new int[n]; // 첫번재 집 포함 O
        int[] dp2 = new int[n]; // 첫번째 집 포함 X

        dp1[0] = money[0];
        dp1[1] = money[0];
        dp2[1]= money[1];

        for (int i = 2; i < n; i++) {
            dp1[i] = Math.max(dp1[i-2] + money[i], dp1[i-1]);
            dp2[i] = Math.max(dp2[i-2] + money[i], dp2[i-1]);
        }

        return Math.max(dp1[n-2], dp2[n-1]);
    }
}
