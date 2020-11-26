package baekjoon.swacademy;

import java.io.*;
import java.util.*;

/***
 * 모노미노도미노
 */
public class P19235 {
	
	static final int GREEN = 1;
	static final int BLUE = 2;
	
	static int[][] g = new int[6][4];
	static int[][] b = new int[4][6];
	
	static int N;
	static int removed_cnt  = 0;
	static int remained_cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		
		for(int i=1; i<=N; i++) {
			int[] in = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> stoi(s)).toArray();
			
			int t = in[0];
			int x = in[1];
			int y = in[2];
			
			// 1. new 블럭 move
			move(GREEN, t, 0, y, i);
			move(BLUE, t, x, 0, i);
			
			// 2. 꽉찬 행 지우기
			removeFullBlock(GREEN);
			removeFullBlock(BLUE);
			
			// 3. 오버된 행 지우기
			removeOverBlock(GREEN);
			removeOverBlock(BLUE);

			//print(GREEN);
			//print(BLUE);
		}
		
		calc(GREEN);
		calc(BLUE);
		
		System.out.println(removed_cnt);
		System.out.println(remained_cnt);
	}
	
	public static void removeOverBlock(int color) {
		int cnt = 0;
		
		if( color == GREEN ) {
			for(int i=0; i<2; i++) {
				for(int j=0; j<4; j++) {
					if( g[i][j] != 0 ) {
						cnt += 1;
						break;
					}
				}
			}
			
			while( cnt-- > 0 ) {
				for(int j=0; j<4; j++) {
					g[5][j] = 0;
					for(int i=5; i>=1; i--) {
						int temp = g[i][j];
						g[i][j] = g[i-1][j];
						g[i-1][j] = temp;
					}
				}
			}
			
		} else if( color == BLUE ) {
			for(int i=0; i<2; i++) {
				for(int j=0; j<4; j++) {
					if( b[j][i] != 0 ) {
						cnt += 1;
						break;
					}
				}
			}
			
			while( cnt-- > 0 ) {
				for(int j=0; j<4; j++) {
					b[j][5] = 0;
					for(int i=5; i>=1; i--) {
						int temp = b[j][i];
						b[j][i] = b[j][i-1];
						b[j][i-1] = temp;
					}
				}
			}
		}
	}
	
	/***
	 * 2. 꽉찬 행 지우고, 그다음 스텝 진행
	 * @param color
	 */
	public static void removeFullBlock(int color) {
		boolean removed = false;
		
		if( color == GREEN ) {
			 
			for(int row=0; row<6; row++) {
				if( isFull(GREEN, row) ) {
					removed_cnt += 1;
					removed = true;
					
					remove(GREEN, row);
					
					moveAgain(GREEN, row-1);
				}
			}
		}
		else if( color == BLUE ) {
			 
			for(int col=0; col<6; col++) {
				if( isFull(BLUE, col) ) {
					removed_cnt += 1;
					removed = true;
					
					remove(BLUE, col);
					
					moveAgain(BLUE, col-1);
				}
			}
		}
		
		if( removed ) {
			removeFullBlock(color);
		}
	}
	
	static int[] dx = {-1,0};
	static int[] dy = {0,+1};
	public static void moveAgain(int color, int i) {
		if( color == GREEN ) {
			for(; i>=0; i--) {
				for(int j=0; j<4; j++) {
					if( g[i][j] == 0 ) continue;

					int type = 1;
					int label = g[i][j];
					for(int d=0; d<2; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if( nx < 0 || ny >= 4 )
							continue;
						
						if( g[nx][ny] == g[i][j] ) {
							if( d == 0 ) {
								type = 3;
							} else if ( d == 1 ) {
								type = 2;
							}
						}
					}
					
					if( type == 1 ) {
						g[i][j] = 0;
						move_standard(color, i, j, label);
					} else if( type == 2 ) {
						g[i][j] = 0;
						g[i][j+1] = 0;
						move_horizon(color, i, j, label);
					} else if( type == 3 ) {
						g[i-1][j] = 0;
						g[i][j] = 0;
						move_vertical(color, i, j, label);
					}
				}
			}
			
		} else if( color == BLUE ) {
			for(; i>=0; i--) {
				for(int j=0; j<4; j++) {
					if( b[j][i] == 0 ) continue;

					int type = 1;
					int label = b[j][i];
					for(int d=0; d<2; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if( ny >= 4 || nx < 0 )
							continue;
						
						if( b[ny][nx] == b[j][i] ) {
							if( d == 0 ) {
								type = 2;
							} else if ( d == 1 ) {
								type = 3;
							}
						}
					}
					
					if( type == 1 ) {
						b[j][i] = 0;
						move_standard(color, j, i, label);
					} else if( type == 2 ) {
						b[j][i] = 0;
						b[j][i-1] = 0;
						move_vertical(color, j, i, label);
						
					} else if( type == 3 ) {
						b[j][i] = 0;
						b[j+1][i] = 0;
						move_horizon(color, j, i, label);
						
					}
				}
			}
		}
	}
	
	public static void remove(int color, int i) {
		if( color == GREEN ) {
			for(int j=0; j<4; j++) {
				g[i][j] = 0;
			}			
		} else if( color == BLUE ) {
			for(int j=0; j<4; j++) {
				b[j][i] = 0;
			}
		}
	}
	
	public static boolean isFull(int color, int i) {
		if( color == GREEN ) {
			for(int j=0; j<4; j++) {
				if( g[i][j] == 0 )
					return false;
			}
			
		} else if( color == BLUE ) {
			for(int j=0; j<4; j++) {
				if( b[j][i] == 0 )
					return false;
			}
			
		}
		
		return true;
	}
	
	/***
	 * 1. 블럭 움직임
	 */
	public static void move(int color, int t, int x, int y, int label) {
		if( t == 1 ) {
			move_standard(color, x, y, label);
		}
		else if( (color == GREEN && t == 2) || (color == BLUE && t == 3) ) {
			move_horizon(color, x, y, label);
		} 
		else if( (color == GREEN && t == 3) || (color == BLUE && t == 2) ) {
			move_vertical(color, x, y, label);
		}
	}
	
	public static void move_standard(int color, int x, int y, int label) {
		if( color == GREEN ) {
			for(; x<6; x++) {
				if( g[x][y] != 0 ) {
					break;
				}
			}
			x--;
			
			g[x][y] = label;
		} else if( color == BLUE ) {
			for(; y<6; y++) {
				if( b[x][y] != 0 ) {
					break;
				}
			}
			y--;
			
			b[x][y] = label;
		}
	}
	
	public static void move_horizon(int color, int x, int y, int label) {
		if( color == GREEN ) {
			for( ; x<6; x++) {
				if( g[x][y] != 0 || (y+1 < 4 && g[x][y+1] != 0) ) {
					break;
				}
			}
			x--;
			
			g[x][y] = label;
			g[x][y+1] = label;
		} else if( color == BLUE ) {
			for( ; y<6; y++) {
				if( b[x][y] != 0 || (x+1 < 4 && b[x+1][y] != 0) ) {
					break;
				}
			}
			y--;
			
			b[x][y] = label;
			b[x+1][y] = label;
		}
	}
	
	public static void move_vertical(int color, int x, int y, int label) {
		if( color == GREEN ) {
			for( ; x<6; x++) {
				if( g[x][y] != 0 ) {
					break;
				}
			}
			x--;
			
			g[x-1][y] = label;
			g[x][y] = label;
			
		} else if( color == BLUE ) {
			for( ; y<6; y++) {
				if( b[x][y] != 0 ) {
					break;
				}
			}
			y--;
			
			b[x][y-1] = label;
			b[x][y] = label;
		}
	}
	
	public static void calc(int color) {
		if( color == GREEN ) {
			for(int i=0; i<6; i++) {
				for(int j=0; j<4; j++) {
					if( g[i][j] != 0 ) {
						remained_cnt += 1; 
					}
				}
			}
		}
		else if( color == BLUE ) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<6; j++) {
					if( b[i][j] != 0 ) {
						remained_cnt += 1; 
					}
				}
			}
		}
	}
	
	public static void print(int color) {
		if( color == GREEN ) {
			for(int i=0; i<6; i++) {
				for(int j=0; j<4; j++) {
					System.out.print(g[i][j] + " ");
				}
				System.out.println();
			}
		}
		else if( color == BLUE ) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<6; j++) {
					System.out.print(b[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		System.out.println();
	}
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
