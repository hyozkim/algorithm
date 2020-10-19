package baekjoon.swacademy;

import java.io.*;
import java.util.*;

/***
 * 어른상어
 */
public class P19237 {
	static class Point {
		int num;
		int k;
		public Point(int n, int k) {
			this.num = n;
			this.k = k;
		}
	}
	
	static class Shark {
		int num;
		int x;
		int y;
		int d;
		int[][] dir;
		public Shark(int n, int x, int y, int d, int [][] dir ) {
			this.num = n;
			this.x = x;
			this.y = y;
			this.d = d;
			this.dir = dir;
		}
	}
	
	static int N, M, k;
	static int[][] dMap;
	static Point[][] map; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		
		N = stoi(in[0]);
		M = stoi(in[1]);
		k = stoi(in[2]);
		
		// 0. 초기화
		dMap = new int[N][N];
		map = new Point[N][N];
		Shark[] sharks = new Shark[M+1];
		initMap();
		initShark(sharks);
		
		// 1. map 입력
		for(int i=0; i<N; i++) {
			dMap[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
			for(int j=0; j<N; j++) {
				if( dMap[i][j] != 0 ) {
					int num = dMap[i][j];
					
					map[i][j] = new Point(num, k);
					sharks[num] = new Shark(num, i, j, -1, new int[4][4]);
				}
			}
		}
		
		// 2. 상어 입력
		int[] curr_dir = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
		for(int i=1; i<=M; i++) {
			sharks[i].d = curr_dir[i-1]-1;
		}
		
		for(int i=1; i<=M; i++) {
			for(int j=0; j<4; j++) {
				sharks[i].dir[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
				
				for(int k=0; k<4; k++)  sharks[i].dir[j][k] -= 1;
			}
		}
		
		System.out.println(start(sharks));
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static int start(Shark[] sharks) {
		int time = 0;
		while( true ) {
			time ++; 
			if( time > 1000 ) {
				break;
			}
			
			//printMap();
			//printDMap();
			//printShark(sharks); 
			
			// 1. 1턴 돌기 [상어이동]
			for(int num=1; num <= M; num++) {
				Shark shark = sharks[num];
				int x = shark.x;
				int y = shark.y;
				int d = shark.d;
				int[][] dir = shark.dir;
				
				if( shark.num == 0 ) continue;
				
				dMap[x][y] = 0;
				
				// 공통적으로 특정한 우선순위를 따른다
				// 1) 먼저, 아무 냄새가 없는 칸 검색
				boolean moved = false;
				for(int e=0; e<4; e++) {
					int nd = dir[d][e];
					int nx = x + dx[nd];
					int ny = y + dy[nd];
					
					if( !boundary(nx,ny) ) 
						continue;
					
					// 빈칸(0)
					if( map[nx][ny].num == 0 ) { 
						moved = true;
						sharks[num].d = nd;
						
						dMap[nx][ny] = Math.min(dMap[nx][ny], num);
						if( dMap[nx][ny] == 0 ) {
							dMap[nx][ny] = num;
						}
						
						break;
					}
				}
				
				// 2) 그런 칸이 없다면 자신의 냄새가 있는 칸으로 이동
				if( !moved ) {
					for(int e=0; e<4; e++) {
						int nd = dir[d][e];
						int nx = x + dx[nd];
						int ny = y + dy[nd];
						
						if( !boundary(nx,ny) ) 
							continue;
						
						// 자신의 냄새가 있는 칸
						if( map[nx][ny].num == num ) { 
							sharks[num].d = nd;
							
							dMap[nx][ny] = Math.min(dMap[nx][ny], num);
							if( dMap[nx][ny] == 0 ) {
								dMap[nx][ny] = num;
							}
							
							break;
						}
					}
				}
			}
			
			// 2. 맵에 있는 전 k값 -1
			minusSmellTime(); 
			
			// 3. 턴 결과에 따라 상어 배치
			moveComplete(sharks);
			
			if( checkOnlyOneLeft() ) {
				return time;
			}
		}
		
		return -1;
	}
	
	public static boolean checkOnlyOneLeft() {
		int count =0; 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if( dMap[i][j] != 0 ) {
					count ++;
				}
			}
		}
		
		return count == 1 ? true : false;
	}
	
	public static void minusSmellTime() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if( map[i][j].k > 0 ) {
					map[i][j].k -= 1;
					if( map[i][j].k == 0 ) {
						map[i][j].num = 0;
					}
				}
			}
		}
	}
	
	public static void moveComplete(Shark[] sharks) {
		for(int i=1; i<=M; i++) {
			sharks[i].num = 0;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if( dMap[i][j] != 0 ) {
					int num = dMap[i][j];
					
					sharks[num].num = num;
					sharks[num].x = i;
					sharks[num].y = j;
					map[i][j] = new Point(num, k);
				}
			}
		}
	}
	
	public static boolean boundary(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	
	public static void printDMap() {
		System.out.println("Print DMap");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(dMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-----------------");
	}
	
	public static void printMap() {
		System.out.println("Print Map");
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print("[" + map[i][j].num + "," + map[i][j].k + "]");
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}
	
	public static void printShark(Shark[] s) {
		System.out.println("Print Shark");
		for(int i=1; i<=M; i++) {
			System.out.println(s[i].num + " [" + s[i].x + "," + s[i].y + "] dir - " + s[i].d);
		}
		System.out.println("==============================");
	}
	
	public static int[][] cloneDirection(int[][] d) {
		int[][] ret = new int[4][4];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				ret[i][j] = d[i][j];
			}
		}
		
		return ret;
	}
	
	public static void initMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = new Point(0,0);
			}
		}
	}
	
	public static void initShark(Shark[] s) {
		for(int i=0; i<=M; i++) {
			s[i] = new Shark(i,-1,-1,-1,new int[4][4]);
		}
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
