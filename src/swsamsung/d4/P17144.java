package swsamsung.d4;

import java.io.*;
import java.util.*;

/***
 * 미세먼지 안녕 - 1시간 44분 소요
 * @author sa833
 *
 */
public class P17144 {
	static int R,C,T;
	static int[][] cleaner;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		
		input();
		
		solution();
	}
	
	private static void solution() {
		//System.out.println("확산전");
		//printMap();
		while( T-- > 0 ) {
			//System.out.println("확산 전");
			//printMap();
			
			// 1. 미세먼지 확산
			spreadDust();
			
			//System.out.println("확산 후 공기청정기 작동 전");
			//printMap();
			
			// 2. 공기청정기 작동
			onCleaner();
			
			//System.out.println("공기청정기 작동 후");
			//printMap();
		}
		
		System.out.println(calculateDust());
	}

	private static int calculateDust() {
		int sum = 0;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if( !cleanerBoundary(r,c) )
					sum += map[r][c];
			}
		}
		
		return sum;
	}
	
	private static boolean cleanerBoundary(int r, int c) {
		for (int i = 0; i < 2; i++) {
			if( cleaner[i][0] == r && cleaner[i][1] == c )
				return true;
		}
		
		return false;
	}
	
	private static void onCleaner() {
		/***
		 *  1. 위쪽 공기청정기 ( 반시계 방향 )
		 */
		int cleaner_r = cleaner[0][0]; int cleaner_c = cleaner[0][1];
		
		// 왼쪽 열
		int r = cleaner_r-1;
		while( r > 0 ) {
			map[r][cleaner_c] = map[r-1][cleaner_c];
			
			r--;
		}
		
		// 위쪽 행
		int c = 0;
		while( c < C-1 ) {
			map[0][c] = map[0][c+1];
			
			c++;
		}
			
		// 오른쪽 열
		r = 0;
		while( r < cleaner_r ) {
			map[r][C-1] = map[r+1][C-1];
			
			r++;
		}
		
		// 아래쪽 행
		c = C-1;
		while( c > 1 ) {
			map[cleaner_r][c] = map[cleaner_r][c-1];
			
			c--;
		}
		map[cleaner_r][c] = 0;
		
		
		/***
		 *  2. 아래쪽 공기청정기( 시계 방향 )
		 */
		cleaner_r = cleaner[1][0];  cleaner_c = cleaner[1][1];
		
		// 왼쪽 열
		r = cleaner_r+1;
		while( r < R-1 ) {
			map[r][0] = map[r+1][0];
			
			r++;
		}
		
		// 아래쪽 행
		c = 0;
		while( c < C-1 ) {
			map[R-1][c] = map[R-1][c+1];
			
			c++;
		}
		
		// 오른쪽 열
		r = R-1;
		while( r > cleaner_r ) {
			map[r][C-1] = map[r-1][C-1];
			
			r--;
		}
		
		// 위쪽 행
		c = C-1;
		while( c > 1 ) {
			map[cleaner_r][c] = map[cleaner_r][c-1];
			
			c--;
		}		
		map[cleaner_r][c] = 0;
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	private static void spreadDust() {
		Queue<Dust> q = new LinkedList<>();
		
		for (int r = 0; r < R; r++) { 
			for (int c = 0; c < C; c++) {
				if( !cleanerBoundary(r, c) && map[r][c] > 0 ) {
					// System.out.println("r= " + r + ", c= " + c);
					q.add(new Dust(r,c,map[r][c])); 
				}
			}
		}
		
		while( !q.isEmpty() ) {
			Dust now = q.poll();
			int x = now.x; int y = now.y;
			int dust = now.dust;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if( !cleanerBoundary(nx, ny) && boundary(nx,ny) ) {
					map[nx][ny] += dust/5;
					map[x][y] -= dust/5;
				}
			}
			
			// **** 확산 후 혹시나 0 미만의 미세먼지가 되면 제거 (문제에 없는 내용..)
			// if( map[x][y] < 0 ) map[x][y] = 0;
		}
		
	}
	
	private static boolean boundary(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}
	
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = br.readLine().split(" ");
		R = stoi(inputs[0]); C = stoi(inputs[1]); T = stoi(inputs[2]);

		// System.out.println("R: " + R + ", C: " + C + ", T: " + T);
		
		map = new int[R][C];
		cleaner = new int[2][2];
		// cleaner[0] = {r,c}; // 위쪽 공기청정기
		// cleaner[1] = {r,c}; // 아래쪽 공기청정기
		
		StringTokenizer st = null;
		
		int cleanerIdx = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				int e = stoi(st.nextToken());
				map[r][c] = e;
				
				if( e == -1 ) { // 공기청정기 입력
					cleaner[cleanerIdx][0] = r;
					cleaner[cleanerIdx++][1] = c;
				}
			}
			//System.out.println(Arrays.toString(map[r]));
		}
		
		//System.out.println("위쪽 공기청정기: " + Arrays.toString(cleaner[0]));
		//System.out.println("아래쪽 공기청정기: " + Arrays.toString(cleaner[1]));
	}
	
	private static void printMap() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	
	static class Dust {
		int x;
		int y;
		int dust;
		public Dust(int x, int y, int dust) {
			this.x = x; 
			this.y = y;
			this.dust = dust;
		}
	}
}
