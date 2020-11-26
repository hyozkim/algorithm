package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P15686 {
	static int answer = 987654321;
	public static void main(String[] args) throws IOException {
		input();
		
		boolean[] visited = new boolean[chickens.size()];
		dfs(map, visited,0,0);
		
		System.out.println(answer);
	}
	
	private static void dfs(int[][] map, boolean[] visited, int index, int count) {
		int[][] newMap = new int[N][N];
		copyMap(newMap,map);
		
		if( count == M ) {
			// print(newMap);
			
			int cityChickenDist = 0;
			
			// 1 - 집
			for (int i = 0; i < houses.size(); i++) {
				Point housePos = houses.get(i);
				
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < chickens.size(); j++) {
					if( !visited[j] ) continue;
					
					// if( visited[j] ) { // 5 - 살아남은 치킨집
					Point chickenPos = chickens.get(j);
				
					int dist = Math.abs(housePos.x-chickenPos.x) + Math.abs(housePos.y-chickenPos.y);
					min = Math.min(min,  dist); // 집과 가장 가까운 거리의 치킨집 = 치킨 거리
					// }
				}
				cityChickenDist += min;
			}

			answer = Math.min(answer, cityChickenDist);
			
			return;
		}
				
		for (int i = index; i < chickens.size(); i++) {
			if( !visited[i] ) {
				visited[i] = true;
				newMap[chickens.get(i).x][chickens.get(i).y] = 5;
				dfs(newMap,visited,i+1, count+1);
				newMap[chickens.get(i).x][chickens.get(i).y] = 2;
				visited[i] = false;
			}
		}
	}
	
	private static void copyMap(int[][] target, int[][] origin) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				target[i][j] = origin[i][j];
			}
		}
	}
	
	static int N,M;
	static int[][] map;
	static List<Point> houses;
	static List<Point> chickens;
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		String[] inputs = br.readLine().split(" ");
		N = stoi(inputs[0]); M = stoi(inputs[1]);
		
		map = new int[N][N];
		chickens = new ArrayList<>();
		houses = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int e = stoi(st.nextToken());
				map[i][j] = e;
				if( e == 2 ) {
					chickens.add(new Point(i,j));
				} else if( e == 1 ) {
					houses.add(new Point(i,j));
				}
			}
		}
		
		// System.out.println(chickens.size());
		//print();
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	private static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x; 
			this.y = y;
		}
	}
}

