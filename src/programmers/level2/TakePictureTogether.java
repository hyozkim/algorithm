package programmers.level2;

import java.util.ArrayList;

/***
 * 단체사진 찍기 - Lv.2
 * 2017 카카오코드 본선
 *
 * 전역변수로 선언하지만 함수 안에서 new ArrayList<>() 생성하면 풀린다..
 * 메모리 관련 문제인듯.. Heap & Stack 관련 지식
 */
public class TakePictureTogether {
    //  {A, C, F, J, M, N, R, T} 8명
    // = 같음, < 미만, > 초과
    // 0~6 간격

    static ArrayList<String> list;
    public static int solution(int n, String[] data) {
        list = new ArrayList<>();

        String[] members = {"A", "C", "F", "J", "M", "N", "R", "T"};
        boolean[] visit = new boolean[members.length];
        dfs(members, visit, members.length, "", 0);

        // System.out.println(list);

        int answer = 0;
        for(String picture : list) {
            if( check(picture, data) ) {
                answer ++;
            }
        }

        return answer;
    }

    public static boolean check(String picture, String[] data) {
        //System.out.println("picture: " + picture);
        for(String s : data) {
            char comp = s.charAt(3);
            int value = s.charAt(4) - '0';

            int a = picture.indexOf(s.charAt(0));
            int b = picture.indexOf(s.charAt(2));
            int dist = (int) (Math.abs(a-b)-1);

            //System.out.println(a + " " + b);
            //System.out.println(value);
            if( comp == '=' ) {
                if( value != dist ) {
                    return false;
                }
            } else if( comp == '>' ) {
                if( value >= dist ) {
                    return false;
                }

            } else if( comp == '<' ) {
                if( value <= dist ) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void dfs(String[] members, boolean[] visit, int n , String member, int cnt) {
        if( cnt >= n ) {
            list.add(member);
            return;
        }

        for (int i = 0; i < members.length; i++) {
            if( !visit[i] ) {
                visit[i] = true;
                dfs(members,visit,n,member+members[i], cnt+1);
                visit[i] = false;
            }

        }
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
