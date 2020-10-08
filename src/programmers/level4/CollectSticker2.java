package programmers.level4;

// 스티커 모으기(2)
// Summer/Winter Coding(~2018)

// 유사 문제 Q9465, 2019 programmers job fair P03
public class CollectSticker2 {
    public static void main(String[] args) {
        int sticker[] = {14, 6, 5, 11, 3, 9, 2, 10};
        //int sticker[] = {1, 3, 2, 5, 4};

        System.out.println(solution(sticker));
    }

    public static int solution(int sticker[]) {
        int n = sticker.length;
        int[][] dp = new int[n][2];
        int[][] dp2 = new int[n][2];

        // 첫번째를 안 뜯은 경우
        dp[0][0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + sticker[i]); //  case: cutting
            dp[i][1] = dp[i-1][0];    // case: not cutting
        }

        //System.out.println(Math.max(dp[n-1][0], dp[n-1][1]));

        // 첫번째를 뜯은 경우
        dp2[0][0] = sticker[0];
        sticker[n-1] = 0;
        for (int i = 1; i < n; i++) {
            dp2[i][0] = Math.max(dp2[i-1][0], dp2[i-1][1] + sticker[i]); //  case: cutting
            dp2[i][1] = dp2[i-1][0];    // case: not cutting
        }

        //System.out.println(Math.max(dp2[n-1][0], dp2[n-1][1]));

        return Math.max(Math.max(dp[n-1][0], dp[n-1][1]) , Math.max(dp2[n-1][0], dp2[n-1][1]));
    }
}
