package swsamsung.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/***
 * 선거 구역 문제 - 범위 설정 문제 문제(성공)
 * 
 * 복잡한 수식에 쫄지마라. 하나씩 뜯어보면 된다.
 * 어떻게 풀지나 생각하는게 훨씬 경제적.
 * 
 * @author sa833
 *
 */
public class P17779 {
	static int N, answer;
	static int[][] originMap;
	static int[][] newMap;
	static int[] size;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		answer = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());  // N x N
		originMap = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
				//System.out.print(originMap[i][j] + " ");
			}
			//System.out.println();
		}
		
		//print(originMap);
		
		solution();
	}
	
	private static void print(int[][] map) {
		for(int r = 1 ; r <= N ; ++r) {
			for(int c = 1 ; c <= N ; ++c) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
	}
	
	public static void solution() {
		for (int i = 1; i <= N; i++) { // 세로 축
			for (int j = 1; j <= N; j++) { // 가로 축
				for (int d1 = 1; d1 <= N; d1++) { // x(d1)
					for (int d2 = 1; d2 <= N; d2++) { // y(d2)
						
						if( (i + d1 + d2) <= N && 
								(j - d1) >= 1 && (j - d1) < j && 
								(j + d2) > j && (j + d2) <= N ) {
							newMap = new int[N+1][N+1];
							size = new int[6];
							
							setBoundary(i, j, d1, d2);
							setArea(i, j, d1, d2);
							calculate();
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}	
	
	private static void setBoundary(int x, int y, int d1, int d2) {
		// 다이아모든꼴 만들기다잉
		
		// 1구역: (x, y), (x+1, y-1), ..., (x+d1, y-d1)
		// 2구역: (x, y), (x+1, y+1), ..., (x+d2, y+d2)
		// 3구역: (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
		// 4구역: (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
		
		newMap[x][y] = 5;
		
		// 1. 5구역 경계선 그리기
		int adder1 = 0; int adder2 = 0;
		while( ++adder1 <= d1 ) newMap[x + adder1][y - adder1] = 5;  // 1구역 경계선
		while( ++adder2 <= d2 ) newMap[x + adder2][y + adder2] = 5;  // 2구역 경계선
		
		adder1 = 0; adder2 = 0;
		while( ++adder2 <= d2 ) newMap[x + d1 + adder2][y - d1 + adder2] = 5;  // 3구역 경계선
		while( ++adder1 <= d1 ) newMap[x + d2 + adder1][y + d2 - adder1] = 5;  // 4구역 경계선
		
		// 2. 5구역 내부 그리기
		for (int i = 1; i <= N; i++) {
			int left = -1;
			int right = -1;
			
			int idx = 1;
			while( idx <= N ) {
				if( newMap[i][idx] == 5 ) {
					left = idx;
					break;
				}
				idx ++;
			}
			
			idx = N;
			while( idx >= 0 ) {
				if( newMap[i][idx] == 5 ) {
					right = idx;
					break;
				}
				idx --;
			}
			
			if( left != right ) {
				for (int j = left; j < right; j++) {
					newMap[i][j] = 5;
				}
			}
		}
	}
	
	private static void setArea(int x, int y, int d1, int d2) {
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if( newMap[r][c] != 0 ) continue;
				
				if( r < (x + d1) && c <= y ) newMap[r][c] = 1;
				else if( r <= (x + d2) && y < c ) newMap[r][c] = 2;
				else if( (x + d1) <= r && c < (y-d1+d2) ) newMap[r][c] = 3;
				else if( (x + d2) < r && (y-d1+d2) <= c ) newMap[r][c] = 4;
				else newMap[r][c] = 5;
			}
		}
	}
	
	private static void calculate() {		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				size[newMap[i][j]] += originMap[i][j];
			}
		}
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i=1; i < 6; i++) {
			max = max < size[i] ? size[i] : max;
			min = min > size[i] ? size[i] : min;
		}
		
		int gap = max - min;
		answer = answer > gap ? gap : answer;
	}
	
}
