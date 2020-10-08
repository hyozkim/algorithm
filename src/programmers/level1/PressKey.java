package programmers.level1;

// 키패드 누르기
// 2020 카카오 인턴십

// 문제 푸는데 40분 정도 걸렸는데, 이렇게 걸리면 안된다..ㅠ
// distance Math.sqrt 써서 double로 비교했는데, Math.abs 써서 int로만 비교해도 된다
public class PressKey {
    public static void main(String[] args) {
        //int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}; String hand = "right";
        //int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}; String hand = "left";
        //int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}; String hand = "right";
        //int[] numbers = {7, 0, 8, 2, 8, 3, 5, 5, 5, 6, 2}; String hand = "left";
        int[] numbers = {8}; String hand = "left";

        System.out.println(solution(numbers, hand));
    }

    public static String solution(int[] numbers, String hand) {
        String answer = "";

        int[][] keypad = {{1,2,3}, {4,5,6}, {7,8,9}, {-1,0,-1}};
        int n = keypad.length; // 4
        int m = keypad[0].length; // 3
        // System.out.println(n + " " + m);

        Hand leftHand = new Hand(n-1, 0);
        Hand rightHand = new Hand(n-1, m-1);

        for (int i = 0; i < numbers.length; i++) {
            int[] pos = findKeyPos(keypad,numbers[i]);
            if( pos[1] == 0 ) {
                leftHand.x = pos[0];
                leftHand.y = pos[1];
                answer += "L";
            } else if( pos[1] == m-1 ) {
                rightHand.x = pos[0];
                rightHand.y = pos[1];
                answer += "R";
            } else {
                double left_dist =  distance(leftHand, pos);
                double right_dist =  distance(rightHand, pos);

                if( left_dist > right_dist ) {
                    answer += "R";
                    rightHand.x = pos[0];
                    rightHand.y = pos[1];
                } else if( left_dist < right_dist ) {
                    answer += "L";
                    leftHand.x = pos[0];
                    leftHand.y = pos[1];

                } else {
                    if( hand.equals("right") ) {
                        answer += "R";
                        rightHand.x = pos[0];
                        rightHand.y = pos[1];
                    } else if( hand.equals("left") ) {
                        answer += "L";
                        leftHand.x = pos[0];
                        leftHand.y = pos[1];
                    }
                }
            }
        }

        return answer;
    }

    private static int distance(Hand hand, int[] curr_pos) {
        return Math.abs(hand.x-curr_pos[0]) + Math.abs(hand.y-curr_pos[1]);
    }

    private static int[] findKeyPos(int[][] keypad, int num) {
        int[] ret = new int[2];
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[i].length; j++) {
                if( keypad[i][j] == num ) {
                    ret = new int[]{i,j};
                }
            }
        }
        return ret;
    }

    static class Hand {
        int x;
        int y;
        public Hand(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
