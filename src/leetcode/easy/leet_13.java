package leetcode.easy;

public class leet_13 {
    public static int getValue(char ch) {
        return ch == 'I' ?  1 : ch == 'V' ?  5 : ch == 'X' ?  10 : ch == 'L' ?  50 : ch == 'C' ?  100 : ch == 'D' ?  500 : 1000;
    }

    public static int romanToInt(String s) {
        int answer = 0;

        int prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = getValue(s.charAt(i));
            if( curr < prev ) { // prev 값이 curr 값보다 크다면 (prev-curr)값을 +
                answer -= prev;
                answer += (prev-curr);
            } else {            // prev 값이 curr 값보다 작으면 계속 curr값 +
                answer += curr;
            }

            prev = curr;
        }
        return answer;
    }

    public static void main(String[] args) {
        //String input = "III";
        //String input = "IV";
        //String input = "IX";
        //String input = "LVIII";
        //String input = "MCMXCIV";
        String input = "D";

        System.out.println(romanToInt(input));
    }
}
