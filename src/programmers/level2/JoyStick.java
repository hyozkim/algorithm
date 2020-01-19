package programmers.level2;


import javax.crypto.spec.PSource;

// Greedy
// Programmers Lv.2
public class JoyStick {
    public static int solution(String name) {
        char[] nameToChar = name.toCharArray();

        // 오른쪽이 빠른가, 왼쪽이 빠른가
        int n = nameToChar.length;

        int updown = 0;
        int rightMin = n-1;

        // int min = n-1;
        for(int i=0; i<n; i++) {
            // 1. 알파벳 index 조이스틱 move 최소
            int startTo = nameToChar[i] - 'A';
            int endTo = 'Z' - nameToChar[i] + 1;

            if( startTo > endTo ) {
                updown += endTo;
            } else {
                updown += startTo;
            }

            // 2. 좌우 조이스틱 move 최소
            int next = i + 1;
            while( next < n && nameToChar[next] == 'A' ) next ++;


            //System.out.println("i = " + i + ", next = " + next + ", n = " + n + ", rightMin = " + rightMin);
            //System.out.println(i+n-next + " " + Math.min(i, n-next));
            // right -> 오른쪽으로 이동하며 바꿔나갔을때 최대 이동횟수

            rightMin = Math.min(rightMin, i + n - next + Math.min(i,n-next));
        }
        System.out.println(rightMin);

        return updown + rightMin;
    }

    public static void main(String[] args) {
        //String name = "JEROEN";
        // String name = "JAN";
        //String name = "BBAABB";
        String name = "ABAAABB";

        System.out.println(solution(name));
    }
}
