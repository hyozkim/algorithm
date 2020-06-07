package swsamsung.d3;

import java.io.*;

public class P9480 {
    private static boolean checkAllAlphabetContains(int[] alphabet) {
        int flag = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if( alphabet[i] >= 1 )
                flag += 1;
        }
        return flag == 26 ? true : false;
    }

    public static void dfs(int depth, int[][] alphabet, int[] check, int cnt) {
        if( cnt >= depth ) {
            if( checkAllAlphabetContains(check) ) {
                ++answer;
            }
            return;
        }

        fillAlphabet(check,alphabet[cnt]);
        dfs(depth, alphabet, check, cnt+1);

        removeAlphabet(check,alphabet[cnt]);
        dfs(depth, alphabet, check, cnt+1);
    }

    private static void fillAlphabet(int[] check, int[] alphabet) {
        for (int i = 0; i < alphabet.length; i++) {
            if( alphabet[i] > 0 )
                check[i] ++;
        }
    }

    private  static void removeAlphabet(int[] check, int[] alphabet) {
        for (int i = 0; i < alphabet.length; i++) {
            if( alphabet[i]-1 >= 0 )
                check[i] --;
        }
    }

    private static void initAlphabet(int[] alphabet, String word) {
        char[] toChar = word.toCharArray();
        for(char ch: toChar) {
            alphabet[ch-'a'] ++;
        }
    }

    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            String[] words = new String[m];
            int[][] alphabet = new int[m][26];
            for (int j = 0; j < m; j++) {
                words[j] = br.readLine();
                initAlphabet(alphabet[j],words[j]);
            }

            answer = 0;
            dfs(m, alphabet, new int[26], 0);
            System.out.println("#" + (i+1) + " " + answer);
        }
        br.close();
    }
}