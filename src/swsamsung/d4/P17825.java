package baekjoon.swacademy;

import java.io.*;
import java.util.Arrays;

/***
 * 주사위 윷놀이
 */
public class P17825 {
	static class Horse {
		int num;
		int lvl;
		int pos;
		boolean start;
		boolean end;
		public Horse(int num, int level, int pos, boolean start, boolean end) {
			this.num = num;
			this.lvl = level;
			this.pos = pos;
			this.start = start;
			this.end = end;
		}
	}
	
	static class Point {
		int score;
		int num;
		public Point(int s, int n) {
			this.score = s;
			this.num = n;
		}
	}
	
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		Horse[] horses = new Horse[4];
		Point[][] map = new Point[6][26];
		
		// 맵 초기화
		init(map, horses);
		//printMap(map);
		// for(int i=0; i<4; i++) System.out.println(horses[i].num + " ["+horses[i].lvl+","+horses[i].pos+"]");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] moves = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
		//System.out.println(Arrays.toString(moves));
		
		dfs(moves, horses, map, 0, 0);
		
		System.out.println(answer);
	}
	
	public static void dfs(int[] moves, Horse[] horses, Point[][] map, int m_index, int score) {
		if( m_index >= 10 ) {
			// System.out.println(m_index + " " + score);
			answer = Math.max(answer, score);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if( horses[i].end )
				continue;
			
			// 시작을 안한 말 -> 시작점에서 출발
			if( !horses[i].start ) {
				int curr_lvl = horses[i].lvl;
				int curr_pos = horses[i].pos;
				
				int move = moves[m_index];
				// 기본 이동
				int[] moveInfo = getCutLevel(curr_pos, curr_lvl, move);
				int next_pos = moveInfo[0]; // 다음 위치
				int next_lvl = moveInfo[1]; // 다음 지름길 레벨	
				
				// 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다. 단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.
				if( map[next_lvl][next_pos].num == 0 ) {
					horses[i].start = true;
					horses[i].lvl = next_lvl;
					horses[i].pos = next_pos;
					map[curr_lvl][curr_pos].num = 0;
					map[next_lvl][next_pos].num = horses[i].num;
					
					dfs(moves, horses, map, m_index+1, map[next_lvl][next_pos].score + score);
					
					horses[i].start = false;
					map[next_lvl][next_pos].num = 0;
					map[curr_lvl][curr_pos].num = 0;
					horses[i].lvl = curr_lvl;
					horses[i].pos = curr_pos;
				}
			}
			
			// 이미 시작한 말 -> 그 위치에서 출발
			else {
				int curr_lvl = horses[i].lvl;
				int curr_pos = horses[i].pos;
				
				int move = moves[m_index];
				
				int[] moveInfo = getCutLevel(curr_pos, curr_lvl, move);
				int next_pos = moveInfo[0]; // 다음 위치
				int next_lvl = moveInfo[1]; // 다음 지름길 레벨
				
				// 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다. 단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.
				//System.out.println(next_lvl + " " + next_pos);
				if( map[next_lvl][next_pos].num == 0 ) {
					horses[i].lvl = next_lvl;
					horses[i].pos = next_pos;
					map[curr_lvl][curr_pos].num = 0;
					map[next_lvl][next_pos].num = horses[i].num;
					
					if( next_lvl == 0 && next_pos > 20 ) {
						horses[i].end = true;
						map[next_lvl][next_pos].num = 0;
					} else if( next_lvl == 4 && next_pos > 1 ) {
						horses[i].end = true;
						map[next_lvl][next_pos].num = 0;
					} else if( next_lvl == 5 && next_pos > 4 ) {
						horses[i].end = true;
						map[next_lvl][next_pos].num = 0;
					} 
					
					dfs(moves, horses, map, m_index+1, map[next_lvl][next_pos].score + score);
					
					map[curr_lvl][curr_pos].num = horses[i].num;
					map[next_lvl][next_pos].num = 0;
					horses[i].lvl = curr_lvl;
					horses[i].pos = curr_pos;
					
					// 끝나는 지점
					if( curr_lvl == 0 && curr_pos <= 20 ) {
						horses[i].end = false;
					} else if( curr_lvl == 1 && curr_pos <= 8 ) {
						horses[i].end = false;
					} else if( curr_lvl == 2 && curr_pos <= 7 ) {
						horses[i].end = false;
					} else if( curr_lvl == 3 && curr_pos <= 8 ) {
						horses[i].end = false;
					} else if( curr_lvl == 5 && curr_pos <= 4 ) {
						horses[i].end = false;
					} else if( curr_lvl == 4 && curr_pos <= 1 ) {
						horses[i].end = false;
					}  
				}
			}
		}
	}
	
	public static int[] getCutLevel(int pos, int level, int move) {
		if( level == 0 ) {
			int n_pos = (pos+move);
			int n_level = level;
			if( (pos + move) == 5) {
				n_level = 1;
				n_pos = 1;
			} else if( n_pos == 10) {
				n_level = 2;
				n_pos  = 1;
			} else if( n_pos == 15) {
				n_level = 3;
				n_pos = 1;
			} else if( n_pos == 20 ) {
				n_level = 4;
				n_pos = 1;
			}
			
			return new int[] {n_pos, n_level};
		} else if( level > 0 && level < 4 ) {
			int n_pos = (pos+move);
			int n_level = level;
			
			if( n_level == 1 && n_pos >= 5 ) {
				if( n_pos == 8 ) {
					n_level = 4;
					n_pos = 1;
					return new int[] {n_pos, n_level};
				}
				if( n_pos >= 9 ) {
					n_level = 4;
					n_pos = 2;
					return new int[] {n_pos, n_level};
				} 
				
				n_level = 5;
				n_pos = (n_pos%5) + 1;
			} else if( n_level == 2 && n_pos >= 4 ) {
				if( n_pos == 7 ) {
					n_level = 4;
					n_pos = 1;
					return new int[] {n_pos, n_level};
				}
				if( n_pos >= 8 ) {
					n_level = 4;
					n_pos = 2;
					return new int[] {n_pos, n_level};
				}
				
				n_level = 5;
				n_pos = (n_pos%4) + 1;
			} else if( n_level == 3 && n_pos >= 5 ) {
				if( n_pos == 8 ) {
					n_level = 4;
					n_pos = 1;
					return new int[] {n_pos, n_level};
				}
				if( n_pos >= 9 ) {
					n_level = 4;
					n_pos = 2;
					return new int[] {n_pos, n_level};
				}
				
				n_level = 5;
				n_pos = (n_pos%5) + 1;
			}
			
			return new int[] {n_pos, n_level};
			
			
		} else if( level == 4 ) {
			int n_pos = (pos+move);
			int n_level = level;
			
			return new int[] {n_pos, n_level};
			
		} else {
			int n_pos = (pos+move);
			int n_level = level;
			
			if( n_level == 5 && n_pos == 4 ) { // 도착역
				n_level = 4;
				n_pos = 1;
			}
			
			return new int[] {n_pos, n_level};
		}
	}
	
	public static void printMap(Point[][] map) {
		for(int j=0; j<4; j++) {
			for(int i=0; i<=20; i++) {
				if( map[j][i] == null ) continue;
				
				System.out.print(map[j][i].score + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void init(Point[][] map, Horse[] horses) {
		for(int i=0; i<=20; i++) {
			map[0][i] = new Point(i*2, 0);
		}
		for(int i=21; i<=25; i++) {
			map[0][i] = new Point(0,0);
		}
		
		map[1][0] = new Point(0, 0);
		map[1][1] = new Point(10, 0);
		map[1][2] = new Point(13, 0);
		map[1][3] = new Point(16, 0);
		map[1][4] = new Point(19, 0);
		map[1][5] = new Point(25, 0);
		for(int i=6; i<=25; i++) {
			map[1][i] = new Point(0,0);
		}
			
		map[2][0] = new Point(0, 0);
		map[2][1] = new Point(20, 0);
		map[2][2] = new Point(22, 0);
		map[2][3] = new Point(24, 0);
		map[2][4] = new Point(25, 0);
		for(int i=5; i<=20; i++) {
			map[2][i] = new Point(0,0);
		}
		
		map[3][0] = new Point(0, 0);
		map[3][1] = new Point(30, 0);
		map[3][2] = new Point(28, 0);
		map[3][3] = new Point(27, 0);
		map[3][4] = new Point(26, 0);
		map[3][5] = new Point(25, 0);
		for(int i=6; i<=25; i++) {
			map[3][i] = new Point(0,0);
		}
		
		/*** 40 ***/
		map[4][1] = new Point(40,0);
		for(int i=2; i<=25; i++) {
			map[4][i] = new Point(0,0);
		}
		
		/*** 25 ***/
		map[5][0] = new Point(0,0);
		map[5][1] = new Point(25, 0);
		map[5][2] = new Point(30, 0);
		map[5][3] = new Point(35, 0);
		map[5][4] = new Point(40, 0);
		for(int i=5; i<=25; i++) {
			map[5][i] = new Point(0,0);
		}
		
		for(int i=0; i<4; i++) {
			horses[i] = new Horse(i+1,0,0,false,false);
		}
	}
}