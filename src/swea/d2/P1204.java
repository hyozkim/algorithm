package swea.d2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1204 {

    public static int solution(String[] inputs) {
        int[] scores = new int[101];

        for (String input : inputs) {
            int value = Integer.parseInt(input);
            scores[value] += 1;
        }
        //System.out.println(Arrays.toString(scores));

        return getModeValue(scores);
    }
    private static int getModeValue(int[] scores) {
        int ret = 0;
        int mode = 0;
        for (int i = 0; i < scores.length; i++) {
            if( scores[i] >= mode ) {
                mode = scores[i];
                ret = i;
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(input);
        for (int i = 0; i < n; i++) {
            int index = Integer.parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");
            System.out.println("#"+(index) + " " + solution(inputs));
        }
    }
}
