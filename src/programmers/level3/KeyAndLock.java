package programmers.level3;

/***
 * 자물쇠와 열쇠 - Lv3
 * 2020 카카오 블라이드 공채
 */
public class KeyAndLock {
    public static boolean solution(int[][] key, int[][] lock) {
        int n = key.length;
        int m = (n-1)*2 + lock.length;

        int[][] map = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = -1;
                if( i >= (n-1) && i < m-(n-1)
                    && j >= (n-1) && j < m-(n-1) ) {
                    map[i][j] = lock[i - (n - 1)][j - (n - 1)];
                }
                // System.out.print(map[i][j]+ " ");
            }
            //System.out.println();
        }

        // 1. 이동
        for (int i = 0; i < m-(n-1); i++) {
            for (int j = 0; j < m-(n-1); j++) {
                if( find(i,j,key,map) ) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean find(int i, int j, int[][] key, int[][] map) {
        int n = key.length;
        int[][] rotate = copy(key);

        // 2. 회전
        for (int x = 0; x < 4; x++) {
            int[][] tempMap = copy(map);
            rotate = rotateKey(rotate);

            for (int k = i; k < i+n; k++) {
                for (int l = j; l < j+n; l++) {
                    if( tempMap[k][l] == -1 ) // -1인 경우 map 밖이니까 패쓰
                        continue;
                    if( tempMap[k][l] == 1 && tempMap[k][l] == rotate[k-i][l-j]) // 열쇠돌기와 자물쇠 돌기가 만나선 안된다
                        break;
                    if( tempMap[k][l] == 0 ) // 맞물리지 않으면서 Key가 들어가는 경우
                        tempMap[k][l] = rotate[k-i][l-j];
                }
            }

            if( open(tempMap, n) )
                return true;

        }

        return false;
    }

    private static boolean open(int[][] map, int n) {
        int m = map.length;
        for (int i = (n-1); i < m-(n-1); i++) {
            for (int j = (n-1); j < m-(n-1); j++) {
                if( map[i][j] == -1 ) continue;
                if( map[i][j] == 0 )
                    return false;
            }
        }

        return true;
    }


    private static int[][] rotateKey(int[][] key) {
        int n = key.length;
        int[][] result = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                result[c][n-1-r] = key[r][c]; // 회전(rotate) 함수의 핵심
            }
        }
        return result;
    }

    private static int[][] copy(int[][] arr) {
        int n = arr.length;
        int[][] ret = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ret[i][j] = arr[i][j];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}; int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};

        System.out.println(solution(key,lock));
    }
}
