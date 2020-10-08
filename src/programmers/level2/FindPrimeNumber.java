package programmers.level2;

import java.util.HashSet;
import java.util.Set;

// Brute Force - 소수 찾기
// Programmers Lv.2
public class FindPrimeNumber {
    static Set<Integer> set = new HashSet<>();
    public static int solution(String numbers) {
        boolean[] visit = new boolean[numbers.length()];
        dfs(numbers.toCharArray(), visit, new StringBuilder());

        return set.size();
    }

    public static void dfs(char[] numbers, boolean[] visit, StringBuilder number) {
        if( isPrimeNumber(stoi(number.toString())) ) {
            set.add(stoi(number.toString()));
        }

        for(int i=0; i<numbers.length; i++) {
            if( !visit[i] ) {
                visit[i] = true;
                number.append(numbers[i]+"");
                dfs(numbers, visit, number);
                number.setLength(number.length()-1);
                visit[i] = false;
            }
        }
    }

    public static int stoi(String s) {
        if( s.equals("") )
            return -1;
        return Integer.parseInt(s);
    }

    public static boolean isPrimeNumber(int num) {
        if( num == 0 || num == 1 )
            return false;

        for(int i=2; i*i<num; i++) {
            if( num % i == 0 )
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String numbers = "17";
        //String numbers = "011";

        System.out.println(solution(numbers));
    }
}
