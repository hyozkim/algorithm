package programmers.level3;

import java.util.*;

/***
 * 보석 쇼핑 - Lv.3
 * 2020 카카오 인턴십
 */
public class JewelShopping {
    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        //String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA", "RUBY", "EMERALD", "SAPPHIRE"};
        // String[] gems = {"AA", "AB", "AC", "AA", "AC"};
        //String[] gems = {"XYZ", "XYZ", "XYZ"};
        // String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};

        System.out.println(Arrays.toString(solution(gems)));
    }

    public static int[] solution(String[] gems) {
        int min = Integer.MAX_VALUE;

        Set<String> hs = new HashSet<>(); // 보석 size
        for(String s : gems) {
            hs.add(s);
        }

        Queue<String> q = new LinkedList<>(); // 보석함
        Map<String,Integer> hm = new HashMap<>(); // 적어도 1개를 갖는 보석 check Map
        int startPoint = 0;
        int start = 0;
        for (int i = 0; i < gems.length; i++) {
            hm.put(gems[i], hm.getOrDefault(gems[i],0)+1);

            q.add(gems[i]);

            while(true) {
                String currGem = q.peek();
                if( hm.get(currGem) > 1 ) {
                    hm.put(currGem, hm.get(currGem)-1);
                    q.poll();
                    startPoint++;
                } else {
                    break;
                }
            }

            if( hm.size() == hs.size() && min > q.size() ) {
                // System.out.println(q);
                start = startPoint;
                min = q.size();
            }
        }

        return new int[] {start+1, start+min};
    }
}
