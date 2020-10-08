package programmers.level1;

/***
 * 다트 게임
 * 2017 카카오 블라인드 코딩테스트
 */
public class DartGame {
    public static int solution(String dartResult) {
        int answer = 0;

        char[] chArray = dartResult.toCharArray();

        int prev = 0;
        int curr = 0;
        for(int i =0; i< chArray.length; i++) {
            char c = chArray[i];

            if( Character.isDigit(c) ) {
                curr = (c -'0');
                if( Character.isDigit(chArray[i+1]) ) {
                    String digit = "" + curr + (chArray[i+1]-'0');
                    curr = Integer.parseInt(digit);
                    i++;
                }

                continue;

            } else {
                // Single, Double, Triple
                if( c == 'S' ) {
                    curr = (int) Math.pow(curr,1);
                } else if( c == 'D' ) {
                    curr = (int) Math.pow(curr,2);
                } else if( c == 'T' ) {
                    curr = (int) Math.pow(curr,3);
                }

                // 스타상 - 이전 값과 현재 값 2배, 아차상 - 마이너스
                if( i+1 < chArray.length && chArray[i+1] == '*' ) {
                    curr *= 2;
                    answer -= prev;
                    answer += (2*prev);
                    i++;

                } else if( i+1 < chArray.length && chArray[i+1] == '#' ) {
                    curr = curr * (-1);
                    i++;

                }
            }

            prev = curr;
            answer += curr;
            //System.out.println(answer);
        }

        return answer;
    }

    /* AS-IS 버전
    public int solution(String dartResult) {
		int answer = 0;
		int [] arr = new int[3];

		char [] toChar = new char[dartResult.length()];
		toChar = dartResult.toCharArray();

		int score = 0; int i = -1;
		int prevScore = 0;
		for( char c : toChar ) {
			if( Character.isDigit(c) ) {
				score = c-'0';
				if(prevScore == 1) {
					if( score == 0 ) {
						score = 10;
						i--;
					}
				}
				prevScore = score;
				i++;
			}

			switch(c) {
				case 'S' :
					score = (int) Math.pow(score, 1);
					arr[i] = score;
					break;
				case 'D' :
					score = (int) Math.pow(score, 2);
					arr[i] = score;
					break;
				case 'T' :
					score = (int) Math.pow(score, 3);
					arr[i] = score;
					break;
				case '*' :
					if( i > 0 )
						arr[i-1] = arr[i-1] * 2;
					arr[i] *= 2;
					break;
				case '#' :
					arr[i] = -arr[i];
					break;
			}
		}

		answer = arr[0]+arr[1]+arr[2];

		return answer;
	}
     */
    
    public static void main(String[] args) {
        //String dartResult = "1S2D*3T";
        //String dartResult = "1D2S#10S";
        //String dartResult = "1D2S0T";
        //String dartResult = "1S*2T*3S";
        //String dartResult = "1D#2S*3S";
        //String dartResult = "1T2D3D#";
        String dartResult = "1D2S3T*";


        System.out.println(solution(dartResult));
    }
}
