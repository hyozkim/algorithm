package backjoon.greedy;


import java.util.*;

// ATM( Q11399 )
// Backjoon - Greedy
public class Q11399 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> time = new ArrayList<>();
        int n = sc.nextInt();
        for(int i=0; i<n; i++) {
            time.add(sc.nextInt());
        }

        int answer = 0;
        int sum = 0;
        Collections.sort(time);
        for( int i : time ) {
            sum += i;
            answer += sum;
        }

        System.out.println(answer);
    }

}
