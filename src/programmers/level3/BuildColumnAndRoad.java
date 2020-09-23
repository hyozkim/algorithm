package programmers.level3;

import java.util.Arrays;
import java.util.PriorityQueue;

/***
 * 기둥과 보 설치 - Lv3
 * 2020 카카오 블라인드 테스트
 */
public class BuildColumnAndRoad {
    private static final int COLUMN = 0;
    private static final int BOARD = 1;

    private static final int CONSTRUCT = 1;
    private static final int DESTRUCT = 0;

    private static boolean[][] columns, boards;

    static class Structure implements Comparable<Structure> {
        int x;
        int y;
        int type;

        public Structure(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;

        }

        // x, y좌표가 모두 같은 경우 기둥(0)이 보(1)보다 앞에 오면 됩니다.
        @Override
        public int compareTo(Structure o) {
            if (this.x == o.x) {
                if (this.y == o.y)
                    return this.type - o.type;
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }

    // back_wall 기둥: 1, 보: 2
    private static PriorityQueue<Structure> buildFrame(int n, int[][] bulid_frame) {
        PriorityQueue<Structure> pq = new PriorityQueue<>();

        for (int[] frame : bulid_frame) {
            int _x = frame[0] + 1; // x
            int _y = frame[1] + 1; /// y
            int _type = frame[2]; // 기둥 or 보
            int _cmd = frame[3]; // 설치 or 제거

            if (_cmd == CONSTRUCT) { //  설치
                if (_type == COLUMN && canBuildColumn(_x, _y)) { // 기둥 설치
                    columns[_x][_y] = true;
                    pq.add(new Structure(_x, _y, COLUMN));

                }
                if (_type == BOARD && canBuildBoard(_x, _y)) { // 보 설치
                    boards[_x][_y] = true;
                    pq.add(new Structure(_x, _y, BOARD));
                }

            } else if (_cmd == DESTRUCT) { // 제거
                if( _type == COLUMN ) {
                    columns[_x][_y] = false;
                } else if( _type == BOARD ) {
                    boards[_x][_y] = false;
                }

                if( canDestruct(n) ) {
                    if( _type == COLUMN ) {
                        pq.removeIf(structure -> structure.x == _x && structure.y == _y && structure.type == COLUMN);
                        columns[_x][_y] = false;
                    } else if( _type == BOARD ) {
                        pq.removeIf(structure -> structure.x == _x && structure.y == _y && structure.type == BOARD);
                        boards[_x][_y] = false;
                    }
                    continue;
                }

                if( _type == COLUMN ) {
                    columns[_x][_y] = true;
                } else if( _type == BOARD ) {
                    boards[_x][_y] = true;
                }
            }
        }

        return pq;
    }

    private static boolean canBuildColumn(int x, int y) {
        return (y == 1 || boards[x][y] || boards[x - 1][y] || columns[x][y - 1]);
    }

    private static boolean canBuildBoard(int x, int y) {
        return (columns[x][y - 1] || columns[x + 1][y - 1] || (boards[x + 1][y] && boards[x - 1][y]));
    }

    private static boolean canDestruct(int n) {
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                if (columns[i][j] && !canBuildColumn(i, j)) { // 제거
                    return false;
                }
                if (boards[i][j] && !canBuildBoard(i, j)) { // 제거
                    return false;
                }

            }
        }
        return true;
    }

    public static int[][] solution(int n, int[][] build_frame) {
        columns = new boolean[n + 2][n + 2];
        boards = new boolean[n + 2][n + 2];

        //int buildResults = buildFrame(n, build_frame);
        PriorityQueue<Structure> buildResults = buildFrame(n, build_frame);

        return queue2intArray(buildResults);
    }

    private static int[][] queue2intArray(PriorityQueue<Structure> pq) {
        int[][] answers = new int[pq.size()][3];
        int n = 0;
        while (!pq.isEmpty()) {
            Structure structure = pq.remove();

            answers[n][0] = structure.x - 1;
            answers[n][1] = structure.y - 1;
            answers[n][2] = structure.type;

            //System.out.println(Arrays.toString(answers[n]));
            n++;
        }

        return answers;
    }

    private static int[][] returnAnswer(int structureCount, int n) {
        int index = 0;
        int[][] answer = new int[structureCount][3];

        for (int i = 1; i <= n + 1; ++i) {
            for (int j = 1; j <= n + 1; ++j) {
                if (columns[i][j]) answer[index++] = new int[]{i - 1, j - 1, COLUMN};
                if (boards[i][j]) answer[index++] = new int[]{i - 1, j - 1, BOARD};
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        //int n = 5; int[][] build_frame = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}};
        int n = 5;
        int[][] build_frame = {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};

        Arrays.stream(solution(n, build_frame)).forEach(array -> System.out.println(Arrays.toString(array)));
    }
}
