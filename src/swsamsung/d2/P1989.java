package swsamsung.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1989 {

    public static int solution(String word) {
        int n = word.length();

        boolean isPalindrome = true;
        for (int i = 0; i < n/2; i++) {
            if( word.charAt(i) != word.charAt(n-i-1) ) {
                isPalindrome = false;
            }
        }

        return isPalindrome ?  1 : 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(input);
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            System.out.println("#"+(i+1) + " " + solution(word));
        }
    }
}
