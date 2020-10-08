package programmers.level4;

/***
 * 단어 퍼즐
 *
 * 2017 탑스 다운
 */
public class PuzzleWord {
    public static void main(String[] args) {
        String[] strs = {"ba", "na", "n", "a"}; String t = "banana";
        //String[] strs = {"app", "ap", "p", "l", "e", "ple", "pp"}; String t = "apple";
        //String[] strs = {"ba", "an", "nan", "ban", "n"}; String t = "banana";

        System.out.println(solution(strs,t));
    }

    public static int solution(String[] strs, String t) {
        int len = t.length();
        int[] dp = new int[len+1];
        for (int i = 1; i <= len; i++) {
            for(String str : strs) {
                if( (i - str.length()) < 0 ) continue;
                if( str.equals(t.substring(i - str.length(), i)) ) {
                    if( (i-str.length()) == 0 ) {
                        dp[i] = 1;
                        continue;
                    }
                    if( dp[i-str.length()] > 0 ) {
                        dp[i] = (dp[i] == 0) ?
                                dp[i-str.length()] + 1 : Math.min(dp[i], dp[i-str.length()]+1);
                    }
                }
            }
        }

        if( dp[t.length()] == 0 )
            return -1;
        return dp[t.length()];
    }
}
