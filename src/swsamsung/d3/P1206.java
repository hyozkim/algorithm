package swsamsung.d3;

import java.io.*;
import java.util.*;

public class P1206 {
    public static int solution(int[] buildings) {
        int n = buildings.length;

        int sum = 0;
        for (int i = 2; i < n-2; i++) {
            int x = buildings[i];

            int max_height = Math.max(buildings[i-2], Math.max(buildings[i-1], Math.max(buildings[i+1], buildings[i+2])));

            sum += x - max_height > 0 ? x - max_height : 0;
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            String input = br.readLine();
            int n = Integer.parseInt(input);

            //for (int j = 0; j < n; j++) {
            String inputs = br.readLine();
            int[] buildings = Arrays.stream(inputs.split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();

            //System.out.println(Arrays.toString(buildings));
            System.out.println("#"+(i+1) + " " + solution(buildings));
            //}
        }
    }
}
