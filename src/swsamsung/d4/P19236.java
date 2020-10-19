package baekjoon.swacademy;

import java.io.*;

/***
 * 청소년 상어
 */
public class P19236 {
	static class Fish {
		int x;
		int y;
		int d;
		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	static int max;
	public static void main(String[] args) throws IOException {
		max = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Fish[] fishes = new Fish[17];
		int[][] map = new int[4][4];
		
		for (int i = 0; i < 4; i++) {
			String[] in = br.readLine().split(" ");
			
			for(int j=0; j<8; j+=2) {
				map[i][j/2] = stoi(in[j]);
				fishes[map[i][j/2]] = new Fish(i, j/2, stoi(in[j+1])-1);
			}
		}
		
		// for(int i=1; i<=16; i++) { System.out.println(i + " - " + fishes[i].x + " " + fishes[i].y + " " + fishes[i].d ); }
		
		int d = fishes[map[0][0]].d;
		int s = map[0][0];
		
		fishes[map[0][0]].d = -1;
		fishes[0] = new Fish(0,0,-1);
		map[0][0] = -1;
		
		
		// map : 상어자리 -1, 빈칸 0, 나머지 - 물고리 번호
		// fish: 죽은 물고기 x 0 y 0 d -1
		dfs(map, fishes, 0,0,d,s);
		
		System.out.println(max);
	}
	
	public static void dfs(int[][] map, Fish[] fishes, int sx, int sy, int d, int sum) {
		int[][] t_map = cloneMap(map);
		Fish[] t_fish = cloneFish(fishes);
		
		max = Math.max(max, sum);
		
		// TODO 1. 물고기 움직임
		moveFish(t_map, t_fish, sx, sy); 
		
		// TODO 2. 상어 움직임
		moveShark(t_map, t_fish, sx, sy, d, sum);
	}
	
	// 2. 상어 움직임
	// 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다. 
	// 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 
	// 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다. 
	// 물고기가 없는 칸으로는 이동할 수 없다. 
	// 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다. 
	// 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 계속해서 반복된다.
	public static void moveShark(int[][] map, Fish[] fishes, int sx, int sy, int d, int sum) {
		int nx = sx;
		int ny = sy;
		
		while( true ) {
			nx += dx[d];
			ny += dy[d];
			
			if( !boundary(nx,ny) )
				break;
			
			int num = map[nx][ny];
			if( map[nx][ny] > 0 ) { // 빈칸이 아닐때
				
				int nd = fishes[num].d; 
				fishes[num] = new Fish(0,0,-1); // 상어한테 잡아먹힌 자리 
				map[nx][ny] = -1; // 상어 자리
				map[sx][sy] = 0; // 상어가 떠난 자리
				
				dfs(map, fishes, nx, ny, nd, sum+num);
				
				fishes[num] = new Fish(nx, ny, nd); // 상어한테 안잡아먹힌 자리
				map[nx][ny] = num;
				map[sx][sy] = -1;
				
			}
		}
	}
	
	// 1. 물고기 움직임
	// 번호가 작은 물고기부터 순서대로 이동한다. 
	// 물고기는 한 칸을 이동할 수 있다
	
	// 이동할 수 있는 칸은 빈 칸, 다른 물고기가 있는 칸
	// 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸
	
	// 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전 
	// 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다. 
	// 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다. 
	
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
	public static void moveFish(int[][] map, Fish[] fishes, int sx, int sy) {
		for(int i=1; i<=16; i++) {
			Fish f = fishes[i];
			if( f.d == -1 )
				continue;
			
			int nd = f.d;
			
			for(int d=0; d<8; d++) {
				nd %= 8;
				
				int nx = f.x + dx[nd];
				int ny = f.y + dy[nd];
				
				// 물고기가 갈수없는칸  - 경계 밖 or 상어가 있는 칸
				if( !boundary(nx,ny) || map[nx][ny] == -1 ) {
					nd ++;
					continue; 
				}
				
				// 물고기가 갈수있는칸 - 빈칸
				if( map[nx][ny] == 0 ) {
					map[f.x][f.y] = 0;
					fishes[i] = new Fish(nx,ny,nd); // 물고기
					
					fishes[map[nx][ny]] = new Fish(0,0,-1); // 빈칸으로
					map[nx][ny] = i;
				}
				
				// 물고기가 갈수있는칸 - 다른 물고기가 있는 칸
				else if( map[nx][ny] > 0 ) {
					fishes[i] = new Fish(nx,ny,nd);
					map[f.x][f.y] = map[nx][ny];
					
					fishes[map[nx][ny]] = new Fish(f.x, f.y, fishes[map[nx][ny]].d);
					map[nx][ny] = i;
				}
				break;
			}
		}
	}
	
	public static boolean boundary(int x, int y) {
		return x>=0 && x<4 && y>=0 && y<4;
	}
	
	
	public static int[][] cloneMap(int[][] map) {
		int[][] ret = new int[4][4];
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				ret[i][j] = map[i][j];
			}
		}
		
		return ret;
	}
	
	public static Fish[] cloneFish(Fish[] fish) {
		Fish[] ret = new Fish[17]; 
		
		for(int i=1; i<=16; i++) {
			ret[i] = new Fish(fish[i].x, fish[i].y, fish[i].d);
		}
		
		return ret;
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
