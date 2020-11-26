package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/***
 * 인구이동
 * 
 * dfs + bfs 심화문제 
 * dfs 안에서도 bfs가 두개 나눠져있을 경우... -> 까다롭다.. -> 해결방안 solution
 * 
 * @author sa833
 *
 */
public class P16234 {
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x; 
			this.y = y;
		}
	}
	
	static int N,L,R;
	static int[][] map;
	static int[][] open;
	
	public static void main(String[] args) throws IOException {
		input();
		
		solution();
	}	
	
	private static void solution() {
		int cnt = 0;
		
		while( true ) {
			if( !check() ) {
				cnt ++;
			} else {
				break;
			}
		}
		
		System.out.println(cnt);
	}
	
	static boolean[][] visited;
	private static boolean check() {
		visited = new boolean[N][N];
		
		boolean done = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if( !visited[i][j] ) {
					ArrayList<Point> points = new ArrayList<>();
					points.add(new Point(i,j));
					
					int sum = spread(i,j,points,0);
					
					if( points.size() > 1 ) {
						//System.out.println("before spread");
						//printMap();
						adjust(points,sum);
						//System.out.println("after spread");
						//printMap();
						
						done = false;
					}
				}
			}
		}
		
		return done;
	}
	
	private static void adjust(ArrayList<Point> points, int avg) {
		avg /= points.size();
		
		for (int k = 0; k < points.size(); k++) {
			map[points.get(k).x][points.get(k).y] = avg;
		}
	}
	
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	private static int spread(int x, int y, ArrayList<Point> points, int sum) {
		// System.out.println("dfs! ");
		visited[x][y] = true;
		sum = map[x][y];
				
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if( boundary(nx,ny) && !visited[nx][ny] ) {
				int diff = Math.abs(map[x][y] - map[nx][ny]);
				if( diff >= L && diff <= R ) {
					points.add(new Point(nx,ny));
					sum += spread(nx,ny,points,sum); // 후.. 이 발상 이해할 수 있을까
				}
			}
		}
		
		return sum;
	} // ***** 이 dfs 는  언젠가 끝이 난다. (if문을 설정해서 끝내주지 않아도)
	
	private static boolean boundary(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
	
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		String[] inputs = br.readLine().split(" ");
		N = stoi(inputs[0]);
		L = stoi(inputs[1]);
		R = stoi(inputs[2]);
		
		open = new int[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
			}
		}
		// printMap();
	}
	
	private static void printOpen() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(open[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void printMap() {
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
}
