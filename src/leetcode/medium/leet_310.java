package leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/***
 * Minimum Height Trees
 *
 */
public class leet_310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if( n == 0 ) {
            return result;
        }
        if( n == 1 || edges.length == 0 ) {
            result.add(0);
            return result;
        }

        List<HashSet<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n; i++) {
            graph.add(new HashSet<Integer>());
        }

        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new LinkedList<>();
        for(int i=0; i<n; i++) {
            if( graph.get(i).size() == 1 ) {
                leaves.add(i);
            }
        }

        if( leaves.size() == 0 )
            return result;

        while( n>2 ) {
            n = n - leaves.size();

            List<Integer> newLeaves = new LinkedList<>();
            for(int i : leaves) {
                int neighbor = graph.get(i).iterator().next();
                graph.get(neighbor).remove(i);
                if( graph.get(neighbor).size() == 1 ) {
                    newLeaves.add(neighbor);
                }
            }
            leaves = newLeaves;
        }

        return leaves;
    }
}
