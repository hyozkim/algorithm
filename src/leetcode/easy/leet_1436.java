package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class leet_1436 {
    /*public static void main(String[] args) {
        List<List<String>> paths = new ArrayList<>();

        List<String> city = new ArrayList<>();
        city.add("London");
        city.add("New York");
        paths.add(city);

        city.clear();
        city.add("London");
        city.add("Lima");
        paths.add(city);

        city.clear();
        city.add("Lima");
        city.add("Sao Paulo");
        paths.add(city);

        System.out.println(destCity(paths));
    }*/

    static String destination = "";
    public String destCity(List<List<String>> paths) {
        int n = paths.size();
        boolean[] visit = new boolean[n];

        for(int i=0; i<paths.size(); i++) {
            visit[i] = true;
            dfs(paths, paths.get(i).get(1), visit, 1, n);
            visit[i] = false;
        }

        return destination;
    }

    private static void dfs(List<List<String>> paths, String start, boolean[] visit, int cnt, int size) {
        if( cnt >= size ) {
            destination = start;
            return ;
        }

        for(int i=0; i<paths.size(); i++) {
            List<String> next = paths.get(i);
            if( !visit[i] && start.equals(next.get(0)) ) {
                visit[i] = true;
                dfs(paths, next.get(1), visit, cnt+1, size);
                visit[i] = false;
            }
        }
    }
}
