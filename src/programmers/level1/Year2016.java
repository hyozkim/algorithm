package programmers.level1;

public class Year2016 {
    static String[] day = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
    static int[] dayOfMonth = {0, 31,29,31,30,31,30,31,31,30,31,30,31};
    public static String solution(int a, int b) {
        int init = 5;

        // calc month
        for (int mon = 1; mon <= (a-1); mon++) {
            init += dayOfMonth[mon];
            /*
            if( mon == 2 ) {
                init += 29;

            } else if( mon <= 7 ) {
                if( mon % 2 == 1 )  init += 31;
                else                init += 30;

            } else {
                if( mon % 2 == 1 )  init += 30;
                else                init += 31;

            }
            */
        }

        // calc day
        init += (b-1);

        return day[init % 7];
    }

    public static void main(String[] args) {
        int a = 5; int b = 24;
        //int a = 1; int b = 2;
        //int a = 2; int b = 3;


        System.out.println(solution(a,b));
    }
}
