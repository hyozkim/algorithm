package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P17142_2 {
	
	static int N,M;
	static List<Point> virus;
	
	static int answer = 987654321;
	public static void main(String[] args) throws IOException {
		// input();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);
		char[][] map = new char[N][N];
		
		// StringTokenizer st = null;
		virus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine().replace(" ","");
			for (int j = 0; j < N; j++) {
				char e = s.charAt(j);
				map[i][j] = e;
				if( e == '2' ) {
					virus.add(new Point(i,j));
				} else if( e == '1' ) {
					map[i][j] = '-';
				}
			}
		}
		
		boolean[] visit = new boolean[virus.size()];
		dfs(map,visit,0,0);
		
		if( answer == 987654321 ) {
			answer = -1;
		}
		
		System.out.println(answer);
	}
	
	private static boolean boundary(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
	
	private static void dfs(char[][] map, boolean[] visit, int cnt, int idx) {
		char[][] newMap = new char[N][N];
		copyMap(newMap,map);
		
		if( cnt == M ) {
			//print(newMap);
			// 바이러스 복제!
			bfs(newMap,visit);
			
			//System.out.println("바이러스 복제 후");
			//print(newMap);
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if( newMap[i][j] == '0' && !checkOriginPos(i,j) )
						return;
					else {
						max = Math.max(max, newMap[i][j]-'0');
					}
				}
			}
			
			//print(newMap);
			answer = Math.min(answer, max);
			return ;
		}
		
		for (int i = idx; i < virus.size(); i++) {
			Point point = virus.get(i);
			
			if( !visit[i] ) {
				visit[i] = true;
				newMap[point.x][point.y] = '!';
				dfs(newMap,visit,cnt+1,i+1);
				newMap[point.x][point.y] = '2';
				visit[i] = false;
			}
		}
	}
	
	private static boolean checkOriginPos(int x, int y) {
		for (int i = 0; i < virus.size(); i++) {
			if( virus.get(i).x == x && virus.get(i).y ==y )
				return true;
		}
		return false;
	}
	
	private static void print(char[][] newMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(newMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	private static void bfs(char[][] newMap, boolean[] visit ) {
		Queue<Spread> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		// 활성/비활성 바이러스 -> visit 배열로 분별
		for (int i = 0; i < virus.size(); i++) {
			Point point = virus.get(i);
			
			if( !visit[i] && newMap[point.x][point.y] == '2' ) { // 비활성
				newMap[point.x][point.y] = '*';
			}
		}
		
		for (int i = 0; i < virus.size(); i++) {
			Point point = virus.get(i);
			
			if( newMap[point.x][point.y] == '!') {
				q.add(new Spread(point.x,point.y, 0));
				newMap[point.x][point.y] = '0';
				
			}
		}
		
		while( !q.isEmpty() ) {
			Spread now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now .x + dx[i];
				int ny = now .y + dy[i];
			
				if( !boundary(nx,ny) )  continue;
				if( newMap[nx][ny] == '-' ) continue;
				
				if( newMap[nx][ny] == '*' && !visited[nx][ny] ) {
					visited[nx][ny] = true;
					q.add(new Spread(nx,ny,now.cur+1));
				}
					
				else if( newMap[nx][ny] == '0' && !checkOriginPos(nx,ny) && !visited[nx][ny] ) {
					visited[nx][ny] = true;
					q.add(new Spread(nx,ny,now.cur+1));
					newMap[nx][ny] = (char) (now.cur+1+'0');
				}
			}
		}
	}
	
	private static void copyMap(char[][] target, char[][] origin) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				target[i][j] = origin[i][j];				
			}
		}
	}
	/*
	private static char[][] copyMap(char[][] map) {
		char[][] newMap = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		
		return newMap;
	}*/
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
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
}
