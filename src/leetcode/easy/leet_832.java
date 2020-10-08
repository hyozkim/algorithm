package leetcode.easy;

public class leet_832 {
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] ret = new int[n][m];

        // 1. horizontally flip
        // 2. 0->1 , 1->0
        for(int j=0; j<n; j++) {
            int p = 0;
            for(int i=m-1; i>=0; i--) {
                if( A[j][i] == 0 )
                    ret[j][p++] = 1;
                else
                    ret[j][p++] = 0;
            }
        }

        return ret;
    }
}
