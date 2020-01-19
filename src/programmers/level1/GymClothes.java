package programmers.level1;

// Greedy
// Programmers Lv.1
public class GymClothes {
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        int cnt = 0;

        for (int i = cnt; i < lost.length; i++) {
            if (cnt <= lost.length) {
                for(int j=0; j<reserve.length; j++) {
                    if (reserve[j] + 1 == lost[i] || reserve[j] - 1 == lost[i] || reserve[j] == lost[i]) {
                        //System.out.println("lost[i] " + lost[i] + ", cnt = " + cnt);
                        answer++;
                        cnt++;
                        //System.out.println("cnt = " + cnt);
                        break;
                    }
                }
            } else {
                break;
            }
        }

        return answer;
    }


    public static void main(String[] args) {
        //int n = 5; int[] lost = {2,4}; int[] reserve = {1,3,5};
        //int n = 5; int[] lost = {2,4}; int[] reserve = {3};
        //int n = 3; int[] lost = {3}; int[] reserve = {1};
        int n = 10; int[] lost = {3,9,10}; int[] reserve = {3,8,9}; // answer : 9

        System.out.println(solution(n,lost,reserve));
    }
}
