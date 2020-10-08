package programmers.level1;

/***
 * 시저암호 - Lv.1
 * 연습문제
 */
public class SissorPassword {
    public static void main(String[] args) {
        String s = "AB"; int n = 1;
        //String s = "z"; int n = 1;
        //String s = "a B z"; int n = 4;

        System.out.println(solution(s,n));
    }

    public static String solution(String s, int n) {
        char[] ret = new char[s.length()];

        int i = 0;
        for(char ch : s.toCharArray()) {
            if( ch == 32 ) {
                ret[i++] = (char) 32;
                continue;
            }

            if( ch >= 'a' && ch <= 'z' ) {
                ret[i]  = (ch+n) > 122 ? (char) (ch+n-25-1) : (char) (ch+n);
            } else if( ch >= 'A' && ch <= 'Z' ) {
                ret[i] = (ch+n) > 90 ? (char) (ch+n-25-1) : (char) (ch+n);
            }
            i++;
        }

        String answer = "";
        for( char ch : ret ) {
            answer += ch;
        }
        return answer;
    }
}
