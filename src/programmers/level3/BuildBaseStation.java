package programmers.level3;

/***
 * 기지국 설치
 * Greedy
 */
public class BuildBaseStation {
    public static void main(String[] args) {
        int n = 11; int[] stations = {4, 11}; int w = 1;
        // int n = 16; int[] stations = {9}; int w = 2;

        System.out.println(solution(n,stations,w));
    }

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;

        int index = 0;
        int location = 1;
        while( location <= n ) {
            if( index < stations.length && location >= stations[index]-w ) {
                location = stations[index]+w+1;
                index ++;
            } else {
                location += (2*w + 1);
                answer ++;
            }
        }

        return answer;
    }
}
