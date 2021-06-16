package programmers.level1;

public class RecommendedID {
    public static void main(String[] args) {
        //String new_id = "...!@BaT#*..y.abcdefghijklm";
//        String new_id = "z-+.^.";
//        String new_id = "=.=";
//        String new_id = "123_.def";
        String new_id = "abcdefghijklmn.p";

        System.out.println(solution(new_id));
    }

    public static String solution(String new_id) {
        String answer = "";

        // 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
        new_id = new_id.toLowerCase();
        //System.out.println("1단계 : " + new_id);

        // 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
        new_id = removeElement(new_id.toCharArray());
        //System.out.println("2단계 : " + new_id);

        // 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        // 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        new_id = removeContinuesLetter(new_id.toCharArray());
        //System.out.println("3,4단계 : " + new_id);

        // 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if( "".equals(new_id) || new_id.length() == 0 ) {
            new_id = "a";
        }
        //System.out.println("5단계 : " + new_id);

        // 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
        //        만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        new_id = new_id.length() >= 16 ? new_id.substring(0,15) : new_id;
        if( new_id.charAt(new_id.length()-1) == '.' )
            new_id = new_id.substring(0,new_id.length()-1);
        //System.out.println("6단계 : " + new_id);

        //7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        answer = appendLastLetterTillLength3(new_id);
        //System.out.println("7단계 : " + answer);

        return answer;
    }

    private static String removeElement(char[] arr) {
        StringBuilder sb = new StringBuilder();
        for( char ch : arr ) {
            if( Character.isLetterOrDigit(ch) ) sb.append(ch);
            if( ch == '-' ) sb.append(ch);
            if( ch == '.' ) sb.append(ch);
            if( ch == '_' ) sb.append(ch);
        }
        return sb.toString();
    }

    private static String removeContinuesLetter(char[] arr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < arr.length-1; i++) {
            if( arr[i] == '.' && arr[i] == arr[i+1] ) continue;

            sb.append(arr[i]);
        }
        if( i < arr.length ) sb.append(arr[i]);

        if( sb.toString().charAt(0) == '.' )
            sb = new StringBuilder(sb.substring(1,sb.length()));
        if( sb.length() > 0 && sb.toString().charAt(sb.length()-1) == '.' )
            sb = new StringBuilder(sb.substring(0,sb.length()-1));

        return sb.toString();
    }

    private static String appendLastLetterTillLength3(String arr) {
        StringBuilder sb = new StringBuilder(arr);

        char last = arr.charAt(arr.length()-1);
        System.out.println("last : " + last);
        if( arr.length() <= 2 ) {
            while( sb.length() <= 2 ) {
                sb.append(last);
            }
        }

        return sb.toString();
    }



}
