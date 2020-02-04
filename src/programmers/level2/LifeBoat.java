package programmers.level2;

import java.util.Arrays;

// Greedy - 구명보트
// Programmers Lv.2
public class LifeBoat {
    public static int solution(int[] people, int limit) {
        int n = people.length;

        Arrays.sort(people);
        int i = 0, j = n-1;
        for(; i<j; j-- ) {
            if( people[i] + people[j] <= limit ) {
                i++;
            }
        }
        return n-i;
    }

    public static void main(String[] args) {
        int[] people = {70,50,80,50}; int limit = 100;
        //int[] people = {70,50,80}; int limit = 100;
        //int[] people = {50,70,50,40}; int limit = 200;


        System.out.println(solution(people,limit));
    }
}
