package programmers.level3;

import java.util.*;
public class TravelRoute {
    static ArrayList<String> list = new ArrayList<>();
    static String route = "";

    public static void dfs(String[][] tickets, boolean[] visit, String end, int cnt) {
        route += end+",";

        // System.out.println("cnt = " + cnt + ",tickets.length="+ tickets.length);
        if(cnt == tickets.length) {
            System.out.println(route);
            list.add(route);
            return ;
        }

        for(int i=0; i<tickets.length; i++) {
            if( !visit[i] && end.equals(tickets[i][0]) ) {
                visit[i] = true;
                dfs(tickets,visit,tickets[i][1],cnt+1);

                visit[i] = false;
                route = route.substring(0, route.length()-4);
            }
        }

    }

    public static String[] solution(String[][] tickets) {
        int n = tickets.length;

        // route += "ICN,";
        for(int i=0; i<n; i++) {
            boolean [] visit = new boolean[n];

            if( !visit[i] && tickets[i][0].equals("ICN") ) {
                visit[i] = true;
                route = tickets[i][0] + ",";
                dfs(tickets,visit,tickets[i][1],1);
            }
        }

        Collections.sort(list);

        return list.get(0).split(",");
    }

    public static void main(String[] args) {
        //String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};

        System.out.println(Arrays.toString(solution(tickets)));
    }
}
