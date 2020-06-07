package swsamsung.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 원판 돌리기 - 성공
 */
public class P17822 {

    public static int solution(int[][] wonpan, int[][] commands) {

        for( int[] cmd : commands ) {
            //System.out.println("cmd: " + Arrays.toString(cmd));
            int x = cmd[0];
            int d = cmd[1];
            int k = cmd[2];

            // 1. x배수, 회전
            for (int i = 1; i < wonpan.length; i++) {
                if( i % x == 0 ) {
                    //System.out.println("회전 전 " + Arrays.toString(wonpan[i]));
                    if( d == 0 ) {
                        rotateClockWise(wonpan[i], k);
                    } else if( d == 1 ) {
                        rotateCounterClockWise(wonpan[i], k);
                    }
                    //System.out.println("회전 후 " + Arrays.toString(wonpan[i]));
                }
            }

            // 2. 인접한 같은 수 모두 찾기
            boolean isHorizonChanged = false;
            int[][] board = new int[wonpan.length][wonpan[0].length];
            for (int i = 1; i < wonpan.length; i++) {
                if( checkHorizon(wonpan[i], board[i]) ) // 옆으로 같은 숫자
                    isHorizonChanged = true;
            }

            boolean isVerticalChanged = checkVertical(wonpan, board); // 아래로 같은 숫자

            // 3-1. 인접한 같은 수가 있는 경우, 지우기( -1 )
            // 3-2. 인접한 같은 수가 없는 경우, 평균보다 작은거 +1 / 평균보다 큰거 -1
            if( isHorizonChanged || isVerticalChanged )
                fillVacant(wonpan,board);
            else
                setWonpan(wonpan);

            //print(wonpan);
            //System.out.println();
        }

        return totalSum(wonpan);
    }

    private static void rotateClockWise(int[] wonpan, int k) {
        if( k % wonpan.length == 0 )
            return;

        k %= wonpan.length;

        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = wonpan[wonpan.length-k+i];
        }

        for (int i = wonpan.length-1; i >= k; i--) {
            wonpan[i] = wonpan[i-k];
        }

        for (int i = 0; i < k; i++) {
            wonpan[i] = temp[i];
        }
    }

    private static void rotateCounterClockWise(int[] wonpan, int k) {
        if( k % wonpan.length == 0 )
            return;

        k %= wonpan.length;

        int[] temp = new int[k];
        for (int i = 0; i < k; i++) {
            temp[i] = wonpan[i];
        }

        for (int i=0; i < wonpan.length-k; i++) {
            wonpan[i] = wonpan[i+k];
        }

        for (int i = wonpan.length-k; i < wonpan.length; i++) {
            wonpan[i] = temp[i-wonpan.length+k];
        }
    }

    private static int totalSum(int[][] wonpan) {
        int sum = 0;
        for (int i = 1; i < wonpan.length; i++) {
            for (int j = 0; j < wonpan[i].length; j++) {
                if( wonpan[i][j] != -1 ) {
                    sum += wonpan[i][j];
                }
            }
        }
        return sum;
    }

    private static void setWonpan(int[][] wonpan) {
        int cnt = 0;
        double average = 0;
        for (int i = 1; i < wonpan.length; i++) {
            for (int j = 0; j < wonpan[i].length; j++) {
                if( wonpan[i][j] != -1 ) {
                    average += wonpan[i][j];
                    cnt++;
                }
            }
        }

        average /= cnt;

        for (int i = 1; i < wonpan.length; i++) {
            for (int j = 0; j < wonpan[i].length; j++) {
                if( wonpan[i][j] != -1 ) {
                    if (wonpan[i][j] > average) {
                        //System.out.println("평균보다 큼: " + wonpan[i][j] + " " + average);
                        wonpan[i][j] -= 1;
                    } else if (wonpan[i][j] < average) {
                        //System.out.println("평균보다 작음: " + wonpan[i][j] + " " + average);
                        wonpan[i][j] += 1;
                    }
                }
            }
        }
    }

    private static void fillVacant(int[][] wonpan, int[][] board) {
        for (int i = 1; i < wonpan.length; i++) {
            for (int j = 0; j < wonpan[i].length; j++) {
                if( board[i][j] == 1 )
                    wonpan[i][j] = -1;
            }
        }
    }

    private static boolean checkVertical(int[][] wonpan, int[][] board) {
        boolean isChanged = false;
        for (int i = 0; i < wonpan[0].length; i++) {
            for (int j = 1; j < wonpan.length-1; j++) {
                if( (wonpan[j][i] != -1 && wonpan[j+1][i] != -1) && wonpan[j][i] == wonpan[j+1][i] ) {
                    isChanged = true;
                    board[j][i] = 1;
                    board[j+1][i] = 1;
                }
            }
        }
        return isChanged;
    }

    private static boolean checkHorizon(int[] wonpan, int[] board) {
        boolean isChanged = false;
        // 첫, 끝 부분 체크
        if( wonpan[0] != -1 && wonpan[wonpan.length-1] != -1 && wonpan[0] == wonpan[wonpan.length-1] ) {
            isChanged = true;
            board[0] = 1;
            board[wonpan.length-1] = 1;
        }

        for (int i = 0; i < wonpan.length-1; i++) {
            if( (wonpan[i] != -1 && wonpan[i+1] != -1) && wonpan[i] == wonpan[i+1] ) {
                isChanged = true;
                board[i] = 1;
                board[i+1] = 1;
            }
        }

        return isChanged;
    }

    private static void print(int[][] wonpan) {
        for (int i = 1; i < wonpan.length; i++) {
            for (int j = 0; j < wonpan[i].length; j++) {
                System.out.print(wonpan[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        int T = Integer.parseInt(inputs[2]);

        int[][] wonpan = new int[N+1][M];

        for (int i = 1; i <= N; i++) {
            wonpan[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
            //System.out.println(Arrays.toString(wonpan[i]));
        }

        int[][] cmd = new int[T][3];
        for (int i = 0; i < T; i++) {
            cmd[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)).toArray();
            //System.out.println("cmd: " + Arrays.toString(cmd[i]));
        }

        System.out.println(solution(wonpan,cmd));
    }
}

