package programmers.level3;

import java.util.*;

// Greedy - 섬연결하기
// Programmers Lv.3
public class IslandConnect {
    public static int solution(int n, int[][] costs) {
        int answer = 0;

        int [][] bridge = new int[n][n];
        for(int i=0; i<costs.length; i++) {
            bridge[costs[i][0]][costs[i][1]] = bridge[costs[i][1]][costs[i][0]] = costs[i][2];
        }

        List<Integer> islands = new ArrayList<>();
        boolean[] visit = new boolean[n];
        islands.add(0);
        visit[0] = true;

        while( islands.size() < n ) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;

            for(int island=0; island<islands.size(); island++) {
                int i = islands.get(island);
                for(int j=0; j<n; j++) {
                    if( !visit[j] && i != j && bridge[i][j] > 0 && bridge[i][j] < min ) {
                        min = bridge[i][j];
                        minIdx = j;
                    }
                }
            }

            visit[minIdx] = true;
            islands.add(minIdx);
            answer += min;
        }

        return answer ;
    }

    public static void main(String[] args) {
        int n = 4; int[][] costs = {{0,1,1},{0,2,5},{1,2,2},{1,3,1},{2,3,8}};

        System.out.println(solution(n,costs));
    }
}
