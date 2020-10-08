package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 완전탐색 - 재귀호출
 *
 * 암호만들기
 */
public class Q1759 {
    static int L, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        L = stoi(inputs[0]);
        C = stoi(inputs[1]);

        String[] passwd = br.readLine().split(" ");
        Arrays.sort(passwd);

        dfs(passwd, "", 0);
    }

    public static void dfs(String[] passwd, String pw, int count) {
        if( L == pw.length() ) {
            if (check(pw)) {
                System.out.println(pw);
            }
            return ;
        }

        if( count >= passwd.length )
            return;

        dfs(passwd, pw + passwd[count], count + 1);
        dfs(passwd, pw, count + 1);
    }

    public static boolean check(String pw) {
        int mo = 0;
        int ja = 0;
        for( char ch : pw.toCharArray() ) {
            if( ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                mo ++;
            else
                ja ++;
        }

        return mo >= 1 && ja >= 2;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }

}
