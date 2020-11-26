package baekjoon.swacademy;

import java.io.*;
import java.util.*;

/***
 * 스타트 택시
 */
public class P19238 {
	static class Taxi implements Comparable<Taxi> {
		int x;
		int y;
		int k;
		public Taxi(int x, int y, int k) {
			this.x = x;
			this.y = y;
			this.k = k;
		}
		
		@Override
		public int compareTo(Taxi taxi) {
			if( this.k == taxi.k ) {
				if( this.x == taxi.x ) {
					return this.y - taxi.y; // 3. 열 번호가 가장 작은 승객
				}
				
				return this.x - taxi.x; // 2. 행 번호가 가장 작은 승객
			}
			
			return taxi.k - this.k; // 1. 찾는데 연료가 가장 적게 쓰인 승객
		}
	}
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, k;
	static int[][] map;
	static Point[] dest;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		
		N = stoi(in[0]);
		M = stoi(in[1]);
		k = stoi(in[2]);
		map = new int[N+1][N+1];
		dest = new Point[M+2];
		
		for(int i=1; i<=N; i++) {
			int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
			for(int j=1; j<=N;j++) {
				map[i][j] = a[j-1]; 
			}
			//System.out.println(Arrays.toString(map[i]));
		}
		
		for(int i=0; i<=N; i++) {
			map[i][0] = 1;
			map[0][i] = 1;
		}
		
		// 택시 정보 입력
		in = br.readLine().split(" ");
		int r = stoi(in[0]);
		int c = stoi(in[1]);
		Taxi taxi = new Taxi(r,c,k);
		
		for(int i=1; i<=M; i++) {
			int[] g = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
			map[g[0]][g[1]] = i+1;
			dest[i+1] = new Point(g[2],g[3]);
		}
		
		//print(map);
		
		System.out.println(startTaxi(taxi));
	}
	
	public static int startTaxi(Taxi taxi) {
		
		for(int i=0; i<M;i ++) { // M명의 승객 처리
			
			// 1. 승객 찾기
			Taxi onTaxi = findPassenger(taxi);
			if( onTaxi == null )
				return -1;
			
			int pass_no = map[onTaxi.x][onTaxi.y];
			map[onTaxi.x][onTaxi.y] = 0;
			
			//print(map);
			
			// 2. 목적지까지 이동
			taxi = toDestination(onTaxi, dest[pass_no]);
			if( taxi == null ) 
				return -1;
		}
		
		return taxi.k;
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	/***
	 * 1. 승객 찾기 
	 */
	public static Taxi findPassenger(Taxi taxi) {
		if( map[taxi.x][taxi.y] > 1 ) {
			return taxi;
		}
		
		boolean[][] visit = new boolean[N+1][N+1];
		
		Queue<Taxi> q = new LinkedList<>();
		visit[taxi.x][taxi.y] = true;
		q.add(taxi);
		
		while( !q.isEmpty() ) {
			int size = q.size();
			PriorityQueue<Taxi> pq = new PriorityQueue<Taxi>();
					
			while( size -- > 0 ) { // 각 턴에 따라 탑승할 승객 있는지 확인하기 위한 처리
				Taxi t = q.poll();
				int x = t.x;
				int y = t.y;
				int fuel = t.k;

				for(int d=0; d<4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if( boundary(nx,ny) && !visit[nx][ny] && map[nx][ny] != 1 ) {
						visit[nx][ny] = true;
						if( map[nx][ny] > 1 ) {
							pq.add(new Taxi(nx,ny,fuel-1));
						} else {
							q.add(new Taxi(nx,ny,fuel-1));
						}
					}
				}
			}
			
			// 승객이 생겼다면 우선순위에 맞는 승객 return
			if( pq.size() > 0 ) {
				return pq.poll();
			}
		}
		
		return null;
	}
	
	/***
	 * 2. 목적지까지 가기
	 */
	public static Taxi toDestination(Taxi onTaxi, Point target) {
		Queue<Taxi> q = new LinkedList<>();
		boolean[][] visit = new boolean[N+1][N+1];
		
		visit[onTaxi.x][onTaxi.y] = true;
		q.add(onTaxi);
		
		int baseFuel = onTaxi.k;
		
		while( !q.isEmpty() ) {
			Taxi t = q.poll();
			int x = t.x;
			int y = t.y;
			int fuel = t.k;
			
			if( fuel == 0 )
				return null;
			
			for(int d=0; d<4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if( boundary(nx,ny) && !visit[nx][ny] && map[nx][ny] != 1 ) {
					visit[nx][ny] = true;
					
					if( nx == target.x && ny == target.y ) {
						fuel -= 1;
						fuel += (2 * (baseFuel - fuel));
						return new Taxi(nx,ny,fuel);
					}

					q.add(new Taxi(nx,ny,fuel-1));
				}
			}
		}
		
		return null;
	}
	
	public static void print(int[][] d) {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(d[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	} 
	
	public static boolean boundary(int x, int y) {
		return x>=0 && x<=N && y>=0 && y<=N;
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}