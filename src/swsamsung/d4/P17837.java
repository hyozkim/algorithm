package swsamsung.d4;

import java.util.*;
import java.io.*;

/***
 * 새로운 게임2 - 성공
 *
 * 고민했던 부분
 * 1. 현재 말 위에 있는 말들 찾기 -> ArrayList 사용 -> index 고려
 * 2. 그 말들 또한 이동하는 것 구현 -> 현재 말 index 찾아서 적용
 */


public class P17837 {
    static ArrayList<Integer>[][] board = new ArrayList[12][12];

    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    static class Horse {
        int i;
        int x;
        int y;
        int d;
        public Horse(int i, int x, int y, int d) {
            this.i = i;
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public static int solution(int N, int K, int[][] map, List<Horse> horses) {
        int time = 0;

        while( true ) {
            time++;
            if( time > 1000 ) break;

            for (int i = 0; i < horses.size(); i++) {
                int x = horses.get(i).x;
                int y = horses.get(i).y;
                int d = horses.get(i).d;

                // 턴 차례 - 말 이동
                int nx = x + dx[d];
                int ny = y + dy[d];

                // move(horse,map,x,y,nx,ny,d);
                if( !boundary(nx,ny, map.length) || map[nx][ny] == 2 ) { // 파란색
                    d = changeDirection(d);
                    horses.get(i).d = d;

                    nx = x + dx[horses.get(i).d];
                    ny = y + dy[horses.get(i).d];
                }

                if( !boundary(nx,ny, map.length) || map[nx][ny] == 2 ) { // 파란색
                    // do nothing
                    //System.out.println("파란색! do Nothing");
                }

                else if( map[nx][ny] == 0 ) { // 흰색
                    //System.out.println("흰색! ");
                    //System.out.println(nx + " " + ny + ", 말의 index: " + i);

                    // 보드의 앞부분 부터 차례대로 다음 칸으로 이동
                    int idx = -1;
                    for (int j = 0; j < board[x][y].size(); j++) {
                        int curr_i = board[x][y].get(j);

                        // 앞에서부터 차례대로
                        if( i == curr_i ) { idx = j; }

                        if( idx == -1 ) continue;

                        horses.get(curr_i).x = nx; // 해당되는 horses index로 찾아 대입 -> 다음 턴에도 적용되어 있도록
                        horses.get(curr_i).y = ny; // 해당되는 horses index로 찾아 대입 -> 다음 턴에도 적용되어 있도록

                        board[nx][ny].add(curr_i);

                        // 말이 4개 이상 쌓이면 게임 종료
                        if( board[nx][ny].size() >= 4 )
                            return time;
                    }

                    // 이동되었다면 전 칸에 있던 말 제거
                    int size = board[x][y].size();
                    for (int j = idx; j < size; ++j) {
                        board[x][y].remove(board[x][y].size()-1);
                    }
                }

                else if( map[nx][ny] == 1 ) { // 빨간색
                    //System.out.println("빨간색! ");

                    // 현재 자리에서 현재 말의 위치! (역순 생각!!)
                    int idx = -1;
                    for (int j = board[x][y].size()-1; j >= 0; j--) {
                        int curr_i = board[x][y].get(j);

                        if( i == curr_i ) {
                            idx = j;
                            break;
                        }
                    }

                    // 역순으로 보드에서 다음 칸으로 말 이동
                    for (int j = board[x][y].size()-1; j >= idx; j--) {
                        int curr_i = board[x][y].get(j);

                        horses.get(curr_i).x = nx; // 해당되는 horses index로 찾아 대입 -> 다음 턴에도 적용되어 있도록
                        horses.get(curr_i).y = ny; // 해당되는 horses index로 찾아 대입 -> 다음 턴에도 적용되어 있도록

                        // **** 해당 for문이 돌때에도 중첩되어 적용된다 ****
                        board[nx][ny].add(curr_i);

                        // 말이 4개 이상 쌓이면 게임 종료
                        if( board[nx][ny].size() >= 4 )
                            return time;
                    }

                    // 이전 칸에 있던 말들 제거하기
                    int size = board[x][y].size();
                    for (int j = idx; j < size; ++j) {
                        board[x][y].remove(board[x][y].size()-1);
                    }
                }
            }
        }

        return -1;
    }

    private static void print(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < board[i][j].size(); k++) {
                    System.out.println("보드판 " + k + ", [" + i + ","+ j +"] " + "index: " + board[i][j].get(k));
                }

                //System.out.println(board[i][j].get(0).i + " " + board[i][j].get(0).x + board[i][j].get(0).y + board[i][j].get(0).d);
            }
        }
    }

    private static boolean boundary(int x, int y, int n) {
        return x>=0 && y>=0 && x<n && y<n;
    }

    private static int changeDirection(int d) {
        if( d == 1 )
            return 2;
        else if( d == 2 )
            return 1;
        else if( d == 3 )
            return 4;
        else
            return 3;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = br.readLine().split(" ");
        int N = Integer.parseInt(input1[0]);
        int K = Integer.parseInt(input1[1]);

        int[][] map = new int[N][N];
        int[][] cmd = new int[K][3];
        List<Horse> list = new ArrayList<>();

        init(br, N, K, map, cmd, list);

        System.out.println(solution(N,K,map,list));
    }

    private static void init(BufferedReader br, int N, int K, int[][] map, int[][] cmd, List<Horse> list ) throws IOException {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
            //System.out.println(Arrays.toString(map[i]));
        }

        for (int i = 0; i < K; i++) {
            cmd[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
            //System.out.println(Arrays.toString(cmd[i]));
            list.add(new Horse(i,cmd[i][0]-1,cmd[i][1]-1, cmd[i][2]));
            board[cmd[i][0]-1][cmd[i][1]-1].add(i);
        }

        //for(Horse horse:list) { System.out.println(horse.i + " " + horse.x + " " + horse.y + " " + horse.d); }

        //print(N);
    }
}
