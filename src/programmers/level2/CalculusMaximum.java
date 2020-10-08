package programmers.level2;

import java.util.ArrayList;

// 수식최대화
// 2020 카카오 인턴십 문제
public class CalculusMaximum {
    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        // String expression = "50*6-3*2";

        System.out.println(solution(expression));
    }

    static long answer = 0;
    static char[] calculus = {'*', '+', '-'};
    static ArrayList<Long> cNums = new ArrayList<>();
    static ArrayList<Character> cOps = new ArrayList<>();
    public static long solution(String expression) {
        makeNumberArray(expression.split("[^0-9$]"));
        makeOperationArray(expression.replaceAll("[0-9$]", "").toCharArray());
        // System.out.println(cNums);
        // System.out.println(cOps);

        dfs(new boolean[3], new char[3], 0);

        return answer;
    }

    /***
     * 3!(6가지) 연산 case
     * {- > + > *}, {+ > - > *}, {- > * > +}, {+ > * > -}, {* > - > +}, {* > + > -}
     *
     * @param visit
     * @param p
     * @param count
     */
    private static void dfs(boolean[] visit, char[] p, int count) {
        if( count == 3 ) {
            // System.out.println(Arrays.toString(p));
            ArrayList<Long> tempNums = new ArrayList<>(cNums);
            ArrayList<Character> tempOps = new ArrayList<>(cOps);

            for (int i = 0; i < p.length; i++) {
                for (int j = 0; j < tempOps.size(); j++) {
                    if( p[i] == tempOps.get(j) ) {
                        long res = calc(tempNums.remove(j), tempNums.remove(j), p[i]);
                        tempNums.add(j, res);
                        tempOps.remove(j);
                        j --;
                    }
                }
            }

            answer = Math.max(answer, Math.abs(tempNums.get(0)));
            return;
        }

        for (int i = 0; i < p.length; i++) {
            if( !visit[i] ) {
                visit[i] = true;
                p[i] = calculus[count];
                dfs(visit, p, count+1);
                visit[i] = false;
            }
        }
    }

    private static long calc(long num1, long num2, char op) {
        long ret = 0;

        if( op == '+') {
            ret = (num1 + num2);
        } else if( op == '-') {
            ret = (num1 - num2);
        } else if( op == '*') {
            ret = (num1 * num2);
        }

        return ret;
    }

    private static void makeNumberArray(String[] nums) {
        for(String num : nums) {
            cNums.add(stoi(num));
        }
    }

    private static void makeOperationArray(char[] calculus) {
        for(char op : calculus) {
            cOps.add(op);
        }
    }

    private static long stoi(String s) {
        return Long.parseLong(s);
    }
}
