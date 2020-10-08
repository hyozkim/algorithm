package programmers.level2;

/***
 * 문자열 압축 - Lv2
 * 2020 카카오 블라인드 테스트
 */
public class StringZip {
    public static int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length()/2; i++) {
            int len = s.length();
            int pos = 0;
            for ( ; pos+i <= s.length(); ) {
                String unit = s.substring(pos,pos + i);
                int count = 1;
                pos += i;

                for( ; pos+i <= s.length(); ) {
                    if( unit.equals(s.substring(pos, pos+i)) ) {
                        count ++;
                        pos += i;
                    } else
                        break;
                }

                if( count > 1 ) {
                    len -= i*(count-1);
                    if( count < 10 )
                        len += 1;
                    else if( count < 100 )
                        len += 2;
                    else if( count < 1000 )
                        len += 3;
                    else
                        len += 4;
                }
            }

            answer = Math.min(answer, len);
        }

        return answer;
    }

    public static void main(String[] args) {
        //String s = "aabbaccc"; // 7
        //String s = "ababcdcdababcdcd"; // 9
        //String s = "abcabcdede"; // 8
        //String s = "abcabcabcabcdededededede"; // 14
        //String s = "xababcdcdababcdcd"; // 17
        String s = "aaaaaaaaaa"; // 3

        System.out.println(solution(s));

    }
}
