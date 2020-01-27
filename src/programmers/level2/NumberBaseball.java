package programmers.level2;

public class NumberBaseball {
    public static int[] toThreeDigit(int i) {
        return new int[] {i/100, (i%100)/10, i%10};
    }

    public static int[] judge(int[] tmp, int[] nums) {
        int strike = 0; int ball = 0;
        for(int x=0; x<3; x++) {
            for(int y=0; y<3; y++) {
                if( x == y && nums[x] == tmp[y] ) {
                    strike += 1;
                    continue;
                } else if( x != y && nums[x] == tmp[y] ) {
                    ball += 1;
                    continue;
                }
            } // end y
        } // end x

        return new int[] {0,strike,ball};
    }

    public static int solution(int[][] baseball) {
        int answer = 0;

        for (int i = 123; i <= 987 ; i++) {
            boolean flag = true;
            int[] tmp = toThreeDigit(i);
            if( tmp[0] == tmp[1] || tmp[1] == tmp[2] || tmp[0] == tmp[2]) continue;
            if( tmp[0] == 0 || tmp[1] == 0 || tmp[2] == 0 ) continue;

            for(int j = 0; j < baseball.length ; j++) {
                int[] nums = toThreeDigit(baseball[j][0]);

                int[] strikeNball = judge(tmp, nums);
                if (strikeNball[1] != baseball[j][1] || strikeNball[2] != baseball[j][2]) {
                    flag = false;
                    break;
                }
            }

            if( flag ) answer += 1;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[][] baseball =  {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};

        System.out.println(solution(baseball));
    }
}
