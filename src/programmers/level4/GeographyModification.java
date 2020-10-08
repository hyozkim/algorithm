package programmers.level4;

import java.util.Arrays;

// 지형 편집
// Summer/Winter Coding (~2018)
public class GeographyModification {
    public static void main(String[] args) {
        //int[][] land = {{1, 2}, {2, 3}}; int P = 3; int Q = 2;
        int[][] land = {{4, 4, 3}, {3, 2, 2}, {2, 1, 0}}; int P = 5; int Q = 3;

        System.out.println(solution(land, P, Q));
    }

    public static long solution(int[][] land, int P, int Q) {
        long low = Arrays.stream(land)
                .flatMapToInt(innerArray -> Arrays.stream(innerArray))
                .min()
                .getAsInt();

        long high = Arrays.stream(land)
                .flatMapToInt(innerArray -> Arrays.stream(innerArray))
                .max()
                .getAsInt();

        long mid = 0;
        while( low <= high ) {
            mid = (low + high) / 2;

            if(low == high) break;

            long ret1 = calculate(land, mid, P, Q);
            long ret2 = calculate(land, mid+1, P, Q);

            if( ret1 == ret2 ) break;

            if( ret1 < ret2 ) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return calculate(land, mid, P, Q);
    }

    private static long calculate(int[][] land, long height, int P, int Q) {
        long ret = 0;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land.length; j++) {
                if (height > land[i][j]) {
                    ret += (height - land[i][j]) * P;
                } else if (height < land[i][j]) {
                    ret += (land[i][j] - height) * Q;
                }
            }
        }

        return ret;
    }
}
