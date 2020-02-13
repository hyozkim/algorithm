package leetcode.easy;

public class leet_14 {
    /* 분할 정복 */


    /* Binary Search 이분탐색 */
    public static boolean isCommonPrefix(String[] strs, int len) {
        String s = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++) {
            if( !strs[i].startsWith(s) )
                return false;
        }
        return true;
    }

    public static String longestCommonPrefix(String[] strs) {
        if( strs == null || strs.length == 0 ) return "";

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++)
            minLen = Math.min(minLen,strs[i].length());

        int low = 1;
        int high = minLen;
        while(low <= high) {
            int mid = (low+high)/2;
            if( isCommonPrefix(strs,mid) ) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return strs[0].substring(0,(low+high)/2);
    }

    /* Horizontal Scanning
    public static String longestCommonPrefix(String[] strs) {
        if( strs.length == 0 ) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while( strs[i].indexOf(prefix) != 0 ) {
                prefix = prefix.substring(0,prefix.length()-1);
                if( prefix.equals("") && prefix.length() == 0 ) return "";
            }
        }

        return prefix;
    }
    */

    public static void main(String[] args) {
        //String[] strs = {"flower","flow","flight"};
        //String[] strs = {"car","racecar","dog"};
        String[] strs = {"flower","flow","fog"};
        //String[] strs = {"aaa","aa","aaa"};

        System.out.println(longestCommonPrefix(strs));
    }
}
