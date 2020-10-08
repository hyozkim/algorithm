package programmers.level1;

import java.util.Comparator;

/***
 * 이상한 문자 만들기 - Lv.1
 * 연습문제
 */
public class StrangeString {
    public static void main(String[] args) {
        String s = "try hello world";

        System.out.println(solution(s));
    }

    public static String solution(String s) {
        StringBuilder sb = new StringBuilder();

        int index = 0;
        char[] chArray = s.toCharArray();
        for(int i=0; i<chArray.length; i++) {
            if( chArray[i] == ' ' ) {
                sb.append(' ');
                index = 0;
                continue;
            }
            if( index % 2 == 0 ) {
                sb.append(Character.toUpperCase(chArray[i]));
            } else {
                sb.append(Character.toLowerCase(chArray[i]));
            }
            index ++;
        }

        return sb.toString();
    }


    static Comparator<Integer> comp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return 0;
        }
    };
}
