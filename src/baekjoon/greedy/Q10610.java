package baekjoon.greedy;

import java.util.*;

// 30( Q10610 )
// Backjoon - Greedy
public class Q10610 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.nextLine().toCharArray();
        int sum = 0;
        for (int i=0; i<s.length; i++) {
            sum += s[i] - '0';
        }
        Arrays.sort(s);

        // 30의 배수 : 1) 0으로 끝남
        //             2) 모든 자리수를 더한 값이 3의 배수
        if( s[0] == '0' && sum % 3 == 0 ) {
            for(int i=s.length-1; i>=0; i--) {
                System.out.print(s[i]);
            }
        } else {
            System.out.println("-1");
        }
    }


}
