package programmers.level3;

import java.util.Arrays;

// Greedy - 저울
// Programmers Lv.3
public class Scale {
    public static int solution(int[] weight) {
        int answer = 0;
        Arrays.sort(weight);
        answer += weight[0];

        for (int i = 1; i < weight.length; i++) {
            System.out.println(i + " " + answer + " "  + weight[i]);
            if( answer+1 < weight[i] ) break;
            answer += weight[i];
        }

        return answer+1;
    }

    public static void main(String[] args) {
        int[] weight = {3,1,6,2,7,30,1};
        //int[] weight = {3,1,1};

        System.out.println(solution(weight));
    }
}
