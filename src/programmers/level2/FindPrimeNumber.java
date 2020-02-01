package programmers.level2;

import java.util.HashSet;

// Brute Force - 소수 찾기
// Programmers Lv.2
public class FindPrimeNumber {
    public static boolean isPrime(int a) {
        if( a == 0 || a == 1 )
            return false;
        for(int i = 2; i <= a-1; i++) {
            if( a % i == 0 )
                return false;
        }

        return true;
    }

    public static void permutation(String prefix, String numbers,HashSet<Integer> set) {
        int n = numbers.length();
        if( !"".equals(prefix) )
            set.add(Integer.parseInt(prefix));

        for (int i = 0; i < n; i++) {
            permutation(prefix+numbers.charAt(i), numbers.substring(0,i) + numbers.substring(i+1,n), set);
        }
    }

    public static int solution(String numbers) {
        int answer = 0;

        HashSet<Integer> set = new HashSet<Integer>();
        permutation("", numbers, set);

        for( Integer num : set ) {
            if( isPrime(num) )
                answer ++;
        }

        return answer;
    }

    public static void main(String[] args) {
        //String numbers = "17";
        String numbers = "011";

        System.out.println(solution(numbers));
    }
}
