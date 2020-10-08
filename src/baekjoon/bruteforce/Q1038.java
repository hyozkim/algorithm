package baekjoon.bruteforce;

import java.util.Scanner;

/***
 * 감소하는수
 * 완전탐색
 */
public class Q1038 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        if( n > 1022 ) { // 9876543210 이 1023번재 수이므로 그 이상의 경우는 없다!
            System.out.println("-1");
            return;
        }

        long num = 0;
        int count = 0;
        while( true ) {
            long tmp = num;
            long jump = 1;
            long prev = -1;
            boolean desc  = true;

            while( tmp != 0 ) {
                if( prev >= tmp % 10 ) {
                    desc = false;
                }

                if(!desc && tmp%10 > prev)
                    break;

                jump *= 10;
                prev = tmp % 10;
                tmp /= 10;
            }

            if( desc ) {
                if( count++ == n ) {
                    break;
                }
                num++;
            } else {
                jump /= 10;
                num += jump;
                num = num/jump*jump; // 다음 수로 점프, 뒷자리 0 초기화
            }
        }

        System.out.println(num);
    }
}
