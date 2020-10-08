package programmers.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/***
 * 후보키 - Lv2
 * 2019 카카오 블라인드 코딩테스트
 */
public class CandidateKey {
    public static int solution(String[][] relation) {
        int answer = 0;

        int row_size = relation.length;
        int col_size = relation[0].length;

        List<Integer> candidates = new ArrayList<>();
        for (int i = 1; i < (1<<col_size); i++) {
            if( check(row_size, col_size, relation, i) ) {
                candidates.add(i);
            }
        }

        // 가장 작은 경우 제외 모두 삭제
        Collections.sort(candidates);
        while( candidates.size() > 0 ) {
            int n = candidates.remove(0);
            answer++;

            Iterator<Integer> iter = candidates.iterator();
            while (iter.hasNext()) {
                int c = iter.next();
                if( (n&c) == n ) // n&c -> ex. 1101 & 1001 -> 1001
                    iter.remove(); // remove c
            }
        }

        return answer;
    }

    // 최소성 무시한채 유일성에 맞는 경우 [완전탐색]
    private static boolean check(int row, int col, String[][] relation, int subset) {
        for (int i = 0; i < row-1; i++) {
            for (int j = i+1; j < row; j++) {
                boolean same = true;
                for (int k = 0; k < col; k++) {
                    if( ((1<<k) & subset) == 0 )
                        continue;

                    if( !relation[i][k].equals(relation[j][k]) ) {
                        same = false;
                        break;
                    }
                }
                if( same ) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[][] relation = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};

        System.out.println(solution(relation));
    }
}
