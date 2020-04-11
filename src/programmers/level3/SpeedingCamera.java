package programmers.level3;

import java.util.*;

// Greedy - 단속카메라
// Programmers Lv.3
public class SpeedingCamera {
    /*
    static class Route {
        int start;
        int end;
        public Route(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }

    static Comparator<Route> comp = new Comparator<Route>() {
        @Override
        public int compare(Route o1, Route o2) {
            if( o1.end == o2.end )
                return o1.start - o2.start;
            else
                return o1.end - o2.end;
        }
    };

    public static int solution(int[][] routes) {
        int answer = 1;

        List<Route> list = new ArrayList<>();
        for(int i=0; i<routes.length; i++) {
            list.add(new Route(routes[i][0], routes[i][1]));
        }

        list.sort(comp);

        Route now = list.get(0);
        for( int i=1; i<list.size(); i++ ) {
            if( now.end < list.get(i).start ) {
                answer++;
                now = list.get(i);
            }
        }

        return answer;
    }
    */

    static class Route implements Comparable<Route> {
        int s;
        int e;
        public Route(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Route o) {
            return this.e - o.e;
        }
    }

    public static int solution(int[][] routes) {
        int answer = 0;

        Route[] cRoute = new Route[routes.length];
        for (int i = 0; i < routes.length; i++) {
            cRoute[i] = new Route(routes[i][0], routes[i][1]);
        }
        Arrays.sort(cRoute);

        int last_camera = Integer.MIN_VALUE;
        for( Route route : cRoute ) {
            if( last_camera < route.s ) {
                // System.out.println(route.s + " " + route.e);
                ++answer;
                last_camera = route.e;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};

        System.out.println(solution(routes));
    }
}
