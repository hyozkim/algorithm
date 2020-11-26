package swea.d3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class P1215 {
    private static boolean isPalindrome(String arr) {
        char[] toChar = arr.toCharArray();

        for (int i = 0; i < toChar.length/2; i++) {
            if( toChar[i] != toChar[toChar.length-i-1] )
                return false;
        }

        return true;
    }

    private static int getVerticalPalindrome(int n, String[] board) {
        int cnt = 0;

        char[][] toChar = new char[8][8];
        for (int i = 0; i < board.length; i++) {
            toChar[i] = board[i].toCharArray();
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <= 8-n; j++) {
                String str = "";
                for (int k = j; k < j+n; k++) {
                    str += "" + toChar[k][i];
                }
                cnt += isPalindrome(str) ? 1 : 0;
            }
        }

        return cnt;
    }

    private static int getHorizonPalindrome(int n, String[] board) {
        int cnt = 0;
        //System.out.println("getHorizonPalindrome: ");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <= 8-n; j++) {
                //System.out.println(board[i].substring(j,j+n));
                cnt += isPalindrome(board[i].substring(j,j+n)) ? 1 : 0;
            }
            //System.out.println();
        }
        return cnt;
    }

    public static int solution(int n, String[] board) {
        //for (int j = 0; j < 8; j++) { System.out.println(Arrays.toString(board[j])); }
        int vertical_cnt = getVerticalPalindrome(n,board);
        int horizon_cnt = getHorizonPalindrome(n, board);

        return vertical_cnt+horizon_cnt;
    }
    public static void main(String[] args) throws IOException {
        InputStream in;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 1; i++) {
            int n = Integer.parseInt(br.readLine());

            String[] board = new String[8];
            for (int j = 0; j < 8; j++) {
                board[j] = br.readLine();
            }
            System.out.println("#"+(i+1) + " " + solution(n, board));
        }
    }
}
