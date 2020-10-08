package programmers.level3;

import java.util.*;

/***
 * 순위 - Lv.3
 * 그래프
 */
public class Rank {
    static class Player {
        int index;
        Set<Integer> win;
        Set<Integer> lose;

        public Player(int index, Set<Integer> win, Set<Integer> lose) {
            this.index = index;
            this.win = win;
            this.lose = lose;
        }
    }

    public static int solution(int n, int[][] edges) {
        int count = 0;

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            players.add(new Player(i, new HashSet<>(), new HashSet<>()));
        }

        for(int[] edge : edges) {
            players.get(edge[0]).win.add(edge[1]); // 내가 이긴 선수들 모음
            players.get(edge[1]).lose.add(edge[0]); // 내가 진 선수들 모음
        }

        int time = n;
        while( time-- > 0 ) { // 총 경기 횟수 5번
            for (int i = 1; i <= n; i++) {
                Player player = players.get(i);

                // 1. 이긴 선수들 모두 담기
                Set<Integer> winSet = new HashSet<>();
                for(Integer win : player.win ) {
                    for( Integer w : players.get(win).win ) {
                        winSet.add(w);
                    }
                }
                player.win.addAll(winSet);

                // 2. 진 선수들 모두 담기
                Set<Integer> loseSet = new HashSet<>();
                for(Integer lose : player.lose ) {
                    for( Integer l : players.get(lose).lose ) {
                        loseSet.add(l);
                    }
                }
                player.lose.addAll(loseSet);
            }
        }

        for(Player player : players) {
            if( (player.win.size() + player.lose.size()) == n-1 )
                count ++;
        }

        return count;
    }

    public static void main(String[] args) {
        int n = 5; int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};

        System.out.println(solution(n,results));
    }
}
