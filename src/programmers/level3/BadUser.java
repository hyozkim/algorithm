package programmers.level3;

import java.util.*;

class Solution {
    private static boolean check(String a, String b) {
        char[] char_a = a.toCharArray();
        char[] char_b = b.toCharArray();

        for (int i = 0; i < char_a.length; i++) {
            if( char_b[i] == '*' ) continue;

            if( char_a[i] != char_b[i] ) {
                return false;
            }
        }
        return true;
    }

    static Set<ArrayList<String>> answer = new HashSet<>();
    private static void dfs(int n, int m, String[] user_id, String[] banned_id, boolean[] visit_u, boolean[] visit_b, ArrayList<String> arrList) {
        if( arrList.size() >= banned_id.length ) { // dfs-백트래킹
            Collections.sort(arrList);
            answer.add(arrList);
            return;
        }

        for (int i = 0; i < n; i++) {
            if( !visit_u[i] ) {
                for (int j = 0; j < m; j++) {
                    if( user_id[i].length() != banned_id[j].length() )
                        continue;

                    if (!visit_b[j] && check(user_id[i], banned_id[j])) {
                        visit_u[i] = true;
                        visit_b[j] = true;
                        arrList.add(user_id[i]);
                        dfs(n, m, user_id, banned_id, visit_u, visit_b, arrList);
                        arrList.remove(user_id[i]);
                        visit_u[i] = false;
                        visit_b[j] = false;
                    }
                }
            }
        }
    }

    public static int solution(String[] user_id, String[] banned_id) {
        int n = user_id.length; int m = banned_id.length;

        boolean[] visit_u = new boolean[n];
        boolean[] visit_b = new boolean[m];
        ArrayList<String> arrList = new ArrayList<String>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if( user_id[i].length() != banned_id[j].length() )
                    break;

                if( !visit_u[i] && !visit_b[j] && check(user_id[i], banned_id[j]) ) {
                    visit_u[i] = true;
                    visit_b[j] = true;
                    arrList.add(user_id[i]);
                    dfs(n,m,user_id, banned_id, visit_u, visit_b, arrList);
                    arrList.remove(user_id[i]);
                    visit_u[i] = false;
                    visit_b[j] = false;
                }
            }
        }

        return answer.size();
    }

    public static void main(String[] args) {
        //String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" }; String[] banned_id = {"fr*d*", "abc1**"};
        //String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"}; String[] banned_id = {"*rodo", "*rodo", "******"};
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"}; String[] banned_id = {"fr*d*", "*rodo", "******", "******"};

        System.out.println(solution(user_id,banned_id));
    }

}