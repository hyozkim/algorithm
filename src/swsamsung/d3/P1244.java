package swsamsung.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P1244 {
    static int answer ;
    public static void dfs(String[] number, int depth, int k, int cnt) {
        if( cnt == k ) {
            String numString = Arrays.stream(number).collect(Collectors.joining());
            answer = Math.max(answer,Integer.parseInt(numString));
            return ;
        }

        for (int i = depth; i < number.length; i++) {
            for (int j = i+1; j < number.length; j++) {
                if( number[i].compareTo(number[j]) <= 0 ) {
                    swap(number, i, j);
                    dfs(number, i, k, cnt + 1);
                    swap(number, i, j);
                }
            }
        }

        for (int i = 0; i < number.length; i++) {
            for (int j = i + 1; j < number.length; j++) {
                swap(number, i, j);
                String numberString = Arrays.stream(number).collect(Collectors.joining());
                answer = Math.max(answer, Integer.parseInt(numberString));
                swap(number, i, j);
            }
        }

    }

    private static void swap(String[] number, int i, int j) {
        String tmp = number[i];
        number[i] = number[j];
        number[j] = tmp;
    }

    public static String solution(String[] number, int k) {
        answer = 0;
        dfs(number,0,k,0);

        return answer == 0 ? Stream.iterate("0", s -> ""+0).limit(number.length).collect(Collectors.joining()) : String.valueOf(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(input);
        for (int i = 0; i < n; i++) {
            String caseInput = br.readLine();
            String [] number = (caseInput.split(" ")[0].split(""));
            int k = Integer.parseInt(caseInput.split(" ")[1]);

            // System.out.println(Arrays.toString(number)+ " " + k );
            System.out.println("#"+(i+1) + " " + solution(number,k));
        }
    }


}
