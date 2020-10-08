package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class leet_46 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        //int[] nums = {1};

        System.out.println(permute(nums));
    }

    static List<List<Integer>> ans;
    public static List<List<Integer>> permute(int[] nums) {
        int N = nums.length;
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] visit = new boolean[nums.length];

        ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            visit[i] = true;
            list.add(nums[i]);
            dfs(nums, list,  N, visit);
            list.remove((Object) nums[i]);
            visit[i] = false;
        }

        return ans;
    }

    public static void dfs(int[] nums, List<Integer> list, int N, boolean[] visit) {
        if( N == list.size() ) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if( !visit[i] ) {
                visit[i] = true;
                list.add(nums[i]);
                dfs(nums, list, N, visit);
                visit[i] = false;
                list.remove((Object) nums[i]);
            }
        }
    }
}
