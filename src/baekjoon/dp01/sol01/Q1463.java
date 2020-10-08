package baekjoon.dp01.sol01;

import java.util.*;

// 1로 만들기 ( Q1463 )
// Backjoon - Dp
public class Q1463 {
    static int [] d ;

    public static int go(int n) {
        if( n == 1 ) return 0;
        if( d[n] > 0 ) return d[n];

        d[n] = go(n-1) + 1;
        if( n % 2 == 0 ) {
            int temp = go(n/2) + 1;
            if( d[n] > temp )	d[n] = temp;
        }
        if( n % 3 == 0 ) {
            int temp = go(n/3) + 1;
            if( d[n] > temp )	d[n] = temp;
        }

        return d[n];
    }

	/* Top-down
	public static int go(int n) {
		if( n == 1 ) return 0;
		if( d[n] > 0 ) return d[n];

		d[n] = go(n-1) + 1;
		if( n % 2 == 0 ) {
			int temp = go(n/2) + 1;
			if( d[n] > temp )	d[n] = temp;
			System.out.println("[% 2] " + Arrays.toString(d));
		}
		if( n % 3 == 0 ) {
			int temp = go(n/3) + 1;
			if( d[n] > temp )	d[n] = temp;
			System.out.println("[% 3] " + Arrays.toString(d));
		}
		System.out.println("[ALL] " + Arrays.toString(d));
		return d[n];
	}
	*/

	/* Bottom-up
	public static int go(int n) {
		d[1] = 0;
		for(int i=2; i<=n; i++) {
			d[i] = d[i-1] + 1;
			if( i%2 == 0 && d[i] > d[i/2] + 1 )
				d[i] = d[i/2] + 1;
			if( i%3 == 0 && d[i] > d[i/3] + 1 )
				d[i] = d[i/3] + 1;
		}

		System.out.println(Arrays.toString(d));
		return d[n];
	}
	*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d = new int[n+1];

        // Top-down
        System.out.println(go(n));

        // Bottom-up
        //System.out.println(go(n));
    }

}

