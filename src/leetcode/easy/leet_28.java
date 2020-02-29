package leetcode.easy;

// Implement strStr() - Easy
public class leet_28 {
    public static int strStr(String haystack, String needle) {
        if( haystack == null || needle == null ) return -1;
        if( needle.equals("") ) return 0;

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j;
            for (j = 0; j < needle.length(); j++) {
                if( haystack.charAt(i + j) != needle.charAt(j) )
                    break;
            }
            if( j == needle.length() )
                return i;
        }

        return -1;
//      return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        //String haystack = "hello"; String needle = "ll";
        //String haystack = "aaaaa"; String needle = "bba";
        //String haystack = "aaaaa"; String needle = "";
        String haystack = "a"; String needle = "a";
        //String haystack = "aaaa"; String needle = "aa";

        System.out.println(strStr(haystack,needle));
    }
}
