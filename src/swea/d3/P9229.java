package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P9229 {
    public static int solution(int[] snacks, int m) {
        Arrays.sort(snacks);
        int max = 0;
        for (int i = snacks.length-1; i > 0; i--) {
            for (int j = i-1; j >= 0; j--) {
                if( snacks[i]+snacks[j] <= m ) {
                    max = Math.max(max, snacks[i]+snacks[j]);
                }
            }
        }

        return max == 0 ? -1 : max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        for (int i = 1; i <= num; i++) {
            String inputs = br.readLine();
            int n = Integer.parseInt(inputs.split(" ")[0]);
            int m = Integer.parseInt(inputs.split(" ")[1]);

            int [] snacks = Arrays.stream(br.readLine().split(" ")).mapToInt(str -> Integer.parseInt(str)).toArray();

            System.out.println("#"+i + " " + solution(snacks, m));
        }
    }
}
