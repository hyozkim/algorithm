package swsamsung.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 아기상어
 * 
 * @author sa833
 *
 */
public class P16236 {

	static int N;
	static int[][] map;
	static Baby shark;
	static List<Point> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		input();
		
		solution();
	}
	
	private static void solution() {
		
		while( true ) {
			// 1. 크기가 작은 물고기 스캔
			scan();
			
			// 먹을 수 있는 작은 물고기가 없다면 종료
			if( list.size() == 0 ) 
				break;
			
			// 2. 이동해서 먹기 ( + 크기 증가하는지 체크 )
			eat();
			list.clear();
		}
		
		System.out.println(shark.time);
	}
	
	private static void eat() {
		Collections.sort(list);
		
		int i;
		for (i = 0; i < list.size(); i++) {
			int x = list.get(i).x;
			int y = list.get(i).y;
			
			if( map[x][y] < shark.size )
				break;
		}
		
		Point next = list.get(i);
		//System.out.println("먹힌 물고기 위치: " + next.x + " " + next.y + " " + next.dist);
		
		map[shark.x][shark.y] = 0;
		
		shark.x = next.x;
		shark.y = next.y;
		shark.count+=1;
		shark.time += next.dist;
		
		if( shark.size == shark.count ) {
			shark.count = 0;
			shark.size += 1;
		}
			
	}
	
	private static void scan() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if( map[i][j] > 0 && map[i][j] < shark.size ) {
					imagineMove(i,j);
				}
			}
		}
	}
	
	static int[] dx = {-1,1,0,0}; // 상하
	static int[] dy = {0,0,-1,1}; // 좌우
	private static void imagineMove(int r, int c) {
		boolean[][] visited = new boolean[N][N];
		Queue<Point> q = new LinkedList<>();
		
		q.add(new Point(shark.x,shark.y,0));
		visited[shark.x][shark.y] = true;
		
		int max = 0;
		while( !q.isEmpty() ) {
			Point now = q.poll();
			int x = now.x;
			int y = now.y;
			int dist = now.dist;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if( boundary(nx,ny) && 
						shark.size >= map[nx][ny] && !visited[nx][ny] ) {
					Point newPoint = new Point(nx,ny,dist+1);
					q.add(newPoint);
					visited[nx][ny] = true;
					
					if( nx == r && ny == c ) {
						list.add(newPoint);
					}
				}
			}
		}
		
		// System.out.println("while문 종료!");
	}
	
	private static boolean boundary(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
	
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = stoi(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int e = stoi(st.nextToken());
				map[i][j] = e;
				if( e == 9 ) {
					shark = new Baby(i,j,2,0,0);
				}
			}
			//System.out.println(Arrays.toString(map[i]));
		}
	}
	
	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	
	static class Baby {
		int x;
		int y;
		int size; 
		int count;
		int time;
		public Baby(int x, int y, int size, int count, int time) {
			this.x = x;
			this.y = y; 
			this.size = size;
			this.count = count;
			this.time = time;
		}
	}
	
	static class Point implements Comparable<Point> {
		int x;
		int y; 
		int dist;
		public Point(int x, int y, int dist) {
			this.x = x; 
			this.y = y;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Point o) {
			if( this.dist == o.dist ) {
				if( this.x == o.x ) {
					return this.y - o.y;
				}
				return this.x - o.x;
			}
			return this.dist - o.dist;
		}
	}
}
