package programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/***
 * 실패율
 * 2019 카카오 블라인트 코딩테스트
 */
public class FailPercentage {
    static class Stage {
        int index;
        double rate;
        public Stage(int i, double r) {
            this.index = i;
            this.rate = r;
        }
    }

    static Comparator<Stage> comp = new Comparator<Stage>() {
        @Override
        public int compare(Stage o1, Stage o2) {
            if( o1.rate == o2.rate )
                return o1.index - o2.index;
            return o2.rate - o1.rate > 0 ? 1 : -1;
        }
    };

    public static int[] solution(int N, int[] stages) {
        ArrayList<Stage> answers = new ArrayList<>();

        for (int s_level = 1; s_level <= N; s_level++) {
            int a = 0;
            int b = 0;
            for (int i = 0; i < stages.length; i++) {
                if( s_level <= stages[i] ) b ++;
                if( s_level == stages[i] ) a ++;
            }

            if( b == 0 )	answers.add(new Stage(s_level,0));
            else 			answers.add(new Stage(s_level,(double) a/b));
        }

        //for(Stage stage : answers)  System.out.println(stage.index + " " + stage.rate);
        Collections.sort(answers,comp);

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = answers.get(i).index;
        }

        return answer;
    }

    public static void main(String[] args) {
        //int N = 5; int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int N = 4; int[] stages = {4,4,4,4,4};
        //int N = 4; int[] stages = {2, 1, 1, 1, 1};

        System.out.println(Arrays.toString(solution(N,stages)));
    }
}
