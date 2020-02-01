package programmers.level1;

// Greedy
// Programmers Lv.1
public class GymClothes {
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] student = new int[n];
        answer = n; // 빌려줄 사람이 없으면 -1

        /* 내껀 내가 입게 된다. */
        for(int l : lost )      { student[l-1] -= 1; }
        for( int r : reserve )  { student[r-1] += 1; }

        for(int i=0; i<n; i++) {
            if( student[i] == -1 ) {    // 체육복이 없으면
                if( i-1>=0 && student[i-1] == 1 ) { // 왼쪽 옆에 있는 친구가 빌려준다.
                    student[i] ++;
                    student[i-1] --;
                } else if( i+1 < n && student[i+1] == 1 ) { // 오른쪽 옆에 있는 친구가 빌려준다.
                    student[i] ++;
                    student[i+1] --;
                } else {    // 빌려줄 사람이 없으면 이친구는 체육복을 못입는다.
                    answer--;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        //int n = 5; int[] lost = {2,4}; int[] reserve = {1,3,5};
        //int n = 5; int[] lost = {2,4}; int[] reserve = {3};
        //int n = 3; int[] lost = {3}; int[] reserve = {1};
        //int n = 10; int[] lost = {3,9,10}; int[] reserve = {3,8,9}; // answer : 9
        int n = 6; int[] lost = {2,4,5}; int[] reserve = {2,3,4}; // answer : 5

        System.out.println(solution(n,lost,reserve));
    }
}
