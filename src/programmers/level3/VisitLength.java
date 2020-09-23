package programmers.level3;

/***
 * 방문 길이 - Lv.3
 * Summer/Winter Coding(~2018)
 */
public class VisitLength {
    static class Block {
        boolean U;
        boolean L;
        boolean D;
        boolean R;
        public Block(boolean U, boolean L, boolean D, boolean R) {
            this.U = U;
            this.L = L;
            this.D = D;
            this.R = R;
        }
    }

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static int solution(String dirs) {
        int answer = 0;

        Block[][] visit = new Block[11][11]; // -5 -4 -3 -2 -1 0 1 2 3 4 5

        init(visit);
        int x = 5; int y = 5; int k = 0;
        for(char dir : dirs.toCharArray()) {
            if (dir == 'U') k = 0;
            else if (dir == 'D') k = 1;
            else if (dir == 'L') k = 2;
            else if (dir == 'R') k = 3;

            int nx = x + dx[k];
            int ny = y + dy[k];

            if (!boundary(nx, ny))
                continue;

            if( dir == 'U' && !visit[x][y].U && !visit[nx][ny].D ) {
                visit[x][y].U = true;
                visit[nx][ny].D = true;
                answer ++;
            } else if( dir == 'L' && !visit[x][y].L && !visit[nx][ny].R ) {
                visit[x][y].L = true;
                visit[nx][ny].R = true;
                answer ++;
            } else if( dir == 'D' && !visit[x][y].D && !visit[nx][ny].U ) {
                visit[x][y].D = true;
                visit[nx][ny].U = true;
                answer ++;
            } else if( dir == 'R' && !visit[x][y].R && !visit[nx][ny].L ) {
                visit[x][y].R = true;
                visit[nx][ny].L = true;
                answer ++;
            }

            x = nx;
            y = ny;
            //System.out.println("[" + index + "] " + x + " " + y + " - " + answer);
        }

        return answer;
    }

    private static void init(Block[][] blocks) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                blocks[i][j] = new Block(false,false,false,false);
            }
        }
    }

    private static boolean boundary(int x, int y) {
        return x>=0 && x<=10 && y>=0 && y<=10;
    }

    public static void main(String[] args) {
        //String dirs = "ULURRDLLU";
        String dirs = "LULLLLLLU";

        System.out.println(solution(dirs));
    }

}
