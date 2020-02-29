package leetcode.easy;

// Search Insert - Easy
public class leet_35 {
    public static int searchInsert(int[] nums, int target) {
        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            if( nums[i] == target )     return i;
            else if( nums[i] < target ) {
                answer = i+1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6}; int target = 5;
        //int[] nums = {1,3,5,6}; int target = 2;
        //int[] nums = {1,3,5,6}; int target = 7;
        //int[] nums = {1,3,5,6}; int target = 0;
        //int[] nums = {1,3,5,6}; int target = 4;

        System.out.println(searchInsert(nums,target));
    }
}
