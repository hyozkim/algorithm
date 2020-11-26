package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/***
 * 연구소 3 문제 - DFS, BFS 혼합 문제(성공)
 * 
 * @author sa833
 *
 */
public class P17142 {

	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Spread {
		int x;
		int y;
		int cur; 
		public Spread(int x, int y, int cur) {
			this.x = x; 
			this.y = y; 
			this.cur = cur;
		}
	}
	
	static int N, M;
	static int answer = 987654321;
	static List<Point> virusPos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		char[][] map = new char[N][N];
		virusPos = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String virus = br.readLine().replace(" ", "");
			// System.out.println(virus);
			
			for (int j = 0; j < map.length; j++) {
				map[i][j] = virus.charAt(j);
				if( map[i][j] == '2' ) {
					virusPos.add(new Point(i,j));
				}
				else if( map[i][j] == '1' ) {
					map[i][j] = '-';
				}
			}
		}
		
		//print(map);
		//System.out.println();
		
		boolean[] visit = new boolean[virusPos.size()];
		makeMap(map,visit,0,0);

		if( answer == 987654321 ) {
			answer = -1;
		} 
		
		System.out.println(answer);
	}
	
	private static boolean isContain(Point point) {
		for (int i = 0; i < virusPos.size(); i++) {
			Point newPoint = virusPos.get(i);
			if( newPoint.x == point.x && newPoint.y == point.y )
				return true;
		}
		
		return false;
	}

	private static void makeMap(char[][] map, boolean[] visit, int cnt, int idx) {
		char[][] currentMap = new char[N][N];
		copyMap(currentMap, map);
		
		if( cnt == M ) {
			//System.out.println("바이러스 번식 전");
			//print(currentMap);
			//System.out.println();
			
			/*if( !checkEmptyExist(currentMap) ) {
				answer = 0;
				return;
			}*/
			
			spreadVirus(currentMap, visit);
			
			//System.out.println("바이러스 번식 후");
			//print(currentMap);
			//System.out.println();
			
			int maxTime = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Point point = new Point(i,j);
					if( currentMap[i][j] == '0' && !isContain(point) ) { // 바이러스 위치인지 확인
						return;
					}
					
					else {
						maxTime = Math.max(maxTime, currentMap[i][j]-'0');
					}
				}
			}
			
			answer = Math.min(maxTime, answer);
			return;
		}
		
		for (int i = idx; i < virusPos.size(); i++) {
			Point point = virusPos.get(i);
			
			if( !visit[i] ) {
				visit[i] = true;
				currentMap[point.x][point.y] = '!'; // 활성 바이러스!
				makeMap(currentMap, visit, cnt+1, i+1);
				visit[i] = false;
				currentMap[point.x][point.y] = '2'; // 비활성 바이러스!
			}
		}
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	private static void spreadVirus(char[][] currentMap, boolean[] visit) {
		Queue<Spread> q = new LinkedList<>();
		boolean[][] virused = new boolean[N][N]; 
		
		for (int i = 0; i < virusPos.size(); i++) {
			Point point = virusPos.get(i);
			
			if( !visit[i] && currentMap[point.x][point.y] == '2' ) {
				currentMap[point.x][point.y] = '*';
			}
		}

		for (int i = 0; i < virusPos.size(); i++) {
			Point point = virusPos.get(i);
			
			if( currentMap[point.x][point.y] == '!' ) {
				currentMap[point.x][point.y] = '0';
				q.add(new Spread(point.x, point.y, 0));
			}
		}

		//System.out.println("번식 전.. ");
		//print(currentMap);
		//System.out.println();
		
		while( !q.isEmpty() ) {
			Spread now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				Point point = new Point(nx,ny);
				
				if( !boundary(nx,ny,currentMap.length) ) continue;
				if( currentMap[nx][ny] == '-' ) continue; 
				
				if( currentMap[nx][ny] == '*' && !virused[nx][ny] ) { // 끄트머리면 끝
					virused[nx][ny] = true;
					q.add(new Spread(nx,ny, (now.cur+1)));
				}
				
				else if( currentMap[nx][ny] == '0' && !isContain(point) && !virused[nx][ny] ) { // 빈칸, 바이러스 존, 지나간 길이 아니라면 keep going
					virused[nx][ny] = true;
					currentMap[nx][ny] = (char) (now.cur+1+'0');
					q.add(new Spread(nx,ny,(now.cur+1)));
				}
			}
		}
	}
	
	private static boolean checkEmptyExist(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if( map[i][j] == '0' )
					return true;
			}
		}
		return false;
	}
	
	private static boolean boundary(int x, int y, int n) {
		return x>=0 && y>=0 && x<n && y<n;
	}
	
	private static void copyMap(char[][] target, char[][] origin) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				target[i][j] = origin[i][j];				
			}
		}
	}
	
	private static void print(char[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
