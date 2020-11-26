package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***
 * 낚시왕 - 성공
 * 
 * 시간 초과 문제 -> 속력 각 칸의 크기에 대해 나머지 연산 -> 제자리로 돌아오는 간격 구하기
 * 낚시왕이 잡을때 	  ->	catched = true;
 * 상어들끼리 잡아먹힐때  ->		catched = true;
 * 
 * @author sa833
 *
 */
public class P17143 {
	public static void main(String[] args) throws IOException {
		input();
		
		solution();
	}
	
	private static void solution() {
		int sum = 0;
		
		// 왼쪽 끝에서 오른쪽 끝까지 이동( for문 ) 
		for (int col = 1; col <= C; col++) {
			
			//System.out.println("낚시 전");
			//print();
			
			// 1. 낚시왕이 땅과 가장 가까운 상어를 잡는다
			sum += catchFish(col);
			
			//System.out.println("낚시 후, 상어 이동 전 ");
			//print();
			
			// 2. 상어 이동
			move();
			
			//System.out.println("상어 이동 후 양육 강식 서바이벌 전 ");
			//print();
			
			// 3. 양육 강식
			survive();
			
			//System.out.println("양육 강식 서바이벌 후 ");
			//print();
		}
		
		System.out.println(sum);
	}
	
	private static void survive() {
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if( map[r][c].size() > 1 ) {
					Collections.sort(map[r][c]);
					Shark biggestShark = map[r][c].get(0);
					
					for (int i = 1; i < map[r][c].size(); i++) {
						sharks.get(map[r][c].get(i).index).catched = true;
					}
					
					map[r][c].clear();
					map[r][c].add(biggestShark);
				}
			}
		}
	}
	
	static int[] dr = {0,-1,1,0,0}; // 상 하 
	static int[] dc = {0,0,0,1,-1}; // 좌 우
	private static void move() {
		for (Shark shark : sharks) {
			if( shark.catched ) 
				continue; // 잡혔으면 더이상 무브 X
			
			int index = shark.index;
			int r = shark.r;
			int c = shark.c;
			int s = shark.s;
			int d = shark.d;
			
			if( d == 1 || d == 2 ) {
				s %= ((R-1)*2);
			} else if( d == 3 || d == 4 ) {
				s %= ((C-1)*2);
			}
			
			// System.out.println(index + "[]" + r + " " + c + " " + s + " " + d);
			
			int nr = r;
			int nc = c;
			for (int i = 0; i < s; i++) { // 이동
				nr += dr[d];
				nc += dc[d];
				
				if( !boundary(nr,nc) ) {
					if( d == 1 ) d = 2;
					else if( d == 2 ) d = 1;
					else if( d == 3 ) d = 4;
					else if( d == 4 ) d = 3;
					
					nr += dr[d];
					nc += dc[d];
					
					nr += dr[d];
					nc += dc[d];
				}
			}
			
			//System.out.println("remove 전: " + map[r][c].size());
			for (int i = 0; i < map[r][c].size(); i++) {
				if( map[r][c].get(i).index == index ) {
					map[r][c].remove(i);
					break;
				}
			}
			//System.out.println("remove 후: " + map[r][c].size());
			
			//System.out.println(index + " " + nr + " " + nc + " " + s + " " + d);
			
			shark.r = nr;
			shark.c = nc;
			shark.d = d;
			
			map[nr][nc].add(shark);
		}
	}
	
	private static boolean boundary(int r, int c) {
		return r>=1 && c>=1 && r<=R && c<=C;
	}
	
	private static int catchFish(int col) {
		int sum = 0;
		
		for (int row = 1; row <= R; row++) {
			if( map[row][col].size() > 0 ) {
				Shark removed = map[row][col].remove(0);
				sum += removed.z; // 제거
				sharks.get(removed.index).catched = true;
				break;
			}
		}
		
		return sum;
	}
	
	private static void print() {
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				System.out.print(map[r][c].size() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static int R,C,M;
	static List<Shark>[][] map; 
	static List<Shark> sharks;
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		String[] inputs = br.readLine().split(" ");
		R = stoi(inputs[0]); C = stoi(inputs[1]); M = stoi(inputs[2]);
		
		map = new ArrayList[R+1][C+1];
		sharks = new ArrayList<>();
		
		init();
		for (int i = 0; i < M; i++) {
			int[] shark_info = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
			map[shark_info[0]][shark_info[1]].add(new Shark(i, shark_info[0],shark_info[1],shark_info[2], shark_info[3], shark_info[4], false));
			sharks.add(new Shark(i, shark_info[0],shark_info[1],shark_info[2], shark_info[3], shark_info[4], false));
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	private static void init() {
		for (int r = 0; r < R+1; r++) {
			for (int c = 0; c < C+1; c++) {
				map[r][c] = new ArrayList<>();
			}
		}
	}
	
	static class Shark implements Comparable<Shark> {
		int index;
		int r;
		int c;
		int s;
		int d;
		int z;
		boolean catched;
		public Shark(int index, int r, int c, int s, int d, int z, boolean catched) {
			this.index = index;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d; 
			this.z = z;
			this.catched = catched;
		}
		
		@Override
		public int compareTo(Shark o) {
			return o.z - this.z; // 내림차순
		}
		
		
	}
}
