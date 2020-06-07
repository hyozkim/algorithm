package swsamsung.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 주사위 윷놀이 - 실패
 * 
 * @author sa833
 *
 */
public class P17825 {
	
	static int ans;
	static int[] cmd;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		
		cmd = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
		
		map = new int[4][21];
		for (int i = 1; i <= 20; i++) { map[0][i] = i*2; }
		map[1][0] = 10; map[1][1] = 13; map[1][2] = 16; map[1][3] = 19; map[1][4] = 25; map[1][5] = 30; map[1][6] = 35; map[1][7] = 40;
		map[2][0] = 20; map[2][1] = 22; map[2][2] = 24; map[2][3] = 25; map[2][4] = 30; map[2][5] = 35; map[2][6] = 40; 
		map[3][0] = 30; map[3][1] = 28; map[3][2] = 27; map[3][3] = 26; map[3][4] = 25; map[3][5] = 30; map[3][6] = 35; map[3][7] = 40;
		
		//for (int i = 0; i < map.length; i++) { System.out.println(Arrays.toString(map[i]));	}
		//System.out.println(Arrays.toString(cmd));
		
		ans = 0;
		solution();
	}
	
	static int answer = 0;
	private static List<List<Integer>> comb;
	private static void solution() {
		Horse[] horse = new Horse[4];
		for (int i = 0; i < 4; i++) {
			horse[i] = new Horse(0,0,false,new ArrayList<>());
		}
		
		dfs(horse,0);
		
		System.out.println(answer);
	}	
	
	private static void dfs(Horse[] horses, int cnt) {
		if( cmd.length == cnt ) {
			
			System.out.println("1번말: " + horses[0].points);
			System.out.println("2번말: " + horses[1].points);
			System.out.println("3번말: " + horses[2].points);
			System.out.println("4번말: " + horses[3].points);
			
			int maxPoints = 0;
			for (int i = 0; i < horses.length; i++) {
				for (int j = 0; j < horses[i].points.size(); j++) {
					maxPoints += horses[i].points.get(j); // 점수 합
				}
			}
			
			answer = Math.max(answer, maxPoints);
			// System.out.println("answer = " + answer);
			
			return;
		}
		
		for (int i = cnt; i < cmd.length; i++) {
			int c = cmd[i];

			//System.out.println("1번말: " + horses[0].points);
			adjust(horses, 0, c);
			dfs(horses, cnt+1); // 1번말
			//if( !horses[0].finish )
			cancel(horses, 0,c);
			//System.out.println("1번말 끝: " + horses[0].points);
			

			//System.out.println("2번말: " + horses[1].points);
			adjust(horses, 1, c);
			dfs(horses,cnt+1); // 2번말
			//if( !horses[1].finish )
				cancel(horses, 1,c);
			//System.out.println("2번말 끝: " + horses[1].points);
			
			//System.out.println("3번말: " + horses[2].points);
			adjust(horses, 2, c);
			dfs(horses,cnt+1); // 3번말
			//if( !horses[2].finish )
				cancel(horses, 2,c);
			//System.out.println("3번말 끝: " + horses[2].points);
			
			//System.out.println("4번말: " + horses[3].points);
			adjust(horses, 3, c);
			dfs(horses,cnt+1); // 4번말
			//if( !horses[3].finish )
				cancel(horses, 3, c);
			//	System.out.println("4번말 끝: " + horses[3].points);
		}
	}
	
	private static void cancel(Horse[] horses, int index, int c) {
		int x = horses[index].x;
		int y = horses[index].y;
		
		if( horses[index].x == 1 && horses[index].y == 0 ) {
			horses[index].x = 0; 
			horses[index].y = 5 - c;
			
		} else if( horses[index].x == 2 && horses[index].y == 0 ) {
			horses[index].x = 1;
			horses[index].y = 10 - c;
			
		} else if( horses[index].x == 3 && horses[index].y == 0 ) {
			horses[index].x = 2;
			horses[index].y = 15 - c;
			
		} else {
			horses[index].y -= c;
			
		}
		
		if( (horses[index].x == 0 && horses[index].y <= 20) || (horses[index].x == 1 && horses[index].y <= 7 ) || 
				(horses[index].x == 2 && horses[index].y <= 6 ) || (horses[index].x == 3 && horses[index].y <= 7 ) ) {
			horses[index].finish = false;
		} else {
			horses[index].points.remove(horses[index].points.size()-1); // 전꺼 삭제 
		}
	}
	
	private static void adjust(Horse[] horses, int index, int c) {
		if( horses[index].finish ) 
			return;
		
		int x = horses[index].x;
		int y = horses[index].y;
		
		if( y + c == 5 ) {
			horses[index].x = 1;
			horses[index].y = 0;
			
		} else if( y + c == 10 ) {
			horses[index].x = 2;
			horses[index].y = 0;
			
		} else if( y + c == 15 ) {
			horses[index].x = 3;
			horses[index].y = 0;
			
		} else {
			horses[index].y += c;
		}
		
		//System.out.println("adjust!!");
		//System.out.println(index + " - " +  horses[index].x + " " + horses[index].y);
		
		// 그 다음 칸에 말이 있을 경우, 종료
		for (int i = 0; i < horses.length; i++) {
			if( i == index ) continue;
			
			if( horses[i].x == horses[index].x && horses[i].y == horses[index].y ) {
				return;
			}
		}
		
		if( (horses[index].x == 0 && horses[index].y > 20) || (horses[index].x == 1 && horses[index].y > 7 ) || 
				(horses[index].x == 2 && horses[index].y > 6 ) || (horses[index].x == 3 && horses[index].y > 7 ) ) {
			horses[index].finish = true;
		} else {
			horses[index].points.add(map[horses[index].x][horses[index].y]);
		}
		
	}
	
	private static int checkCount(Horse[] horses) {
		int sum = 0;
		for (int i = 0; i < horses.length; i++) {
			sum += horses[i].points.size();
		}
		return sum; 
	}
	
	static class Horse {
		int x;
		int y;
		boolean finish;
		ArrayList<Integer> points;
		public Horse(int x, int y, boolean finish, ArrayList<Integer> points) {
			this.x = x;
			this.y = y;
			this.finish = finish;
			this.points = points;
		}
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	private static void dfs(int[][] map, int[] cmd, int index, int sum, List<Horse> horses) {
		// System.out.println("sum: " + sum +", index: " + index);
		
		if( index == cmd.length ) {
			ans = Math.max(ans, sum);
			return;
		}
		
		//if( horses.size() < 4 ) {
			
		//}
		
		for (int i = index; i < cmd.length; i++) {
			Horse newHorse = new Horse(0, cmd[0], 1, false);
			horses.add(newHorse);
			dfs(map, cmd, 1, sum + map[newHorse.x][newHorse.y], horses);
			horses.remove(newHorse);
			
			for (int j = 0; j < horses.size(); j++) {
				Horse nextHorse = horses.get(j);
				
				if( nextHorse.finished ) 
					continue;
				
				// 1. 파란칸 -> 파란색 화살표, 그외 -> 빨간색 화살표 이동
				if( nextHorse.x == 0 && (nextHorse.y + cmd[i]) == 5 ) {
					nextHorse.x = 1;
					nextHorse.y = 0;
					
				} else if ( nextHorse.x == 0 && (nextHorse.y + cmd[i]) == 10 ) {
					nextHorse.x = 2;
					nextHorse.y = 0;
					
				} else if( nextHorse.x == 0 && (nextHorse.y + cmd[i]) == 15 ) {
					nextHorse.x = 3;
					nextHorse.y = 0;
					
				} else {
					nextHorse.y = nextHorse.y+cmd[i];
					
				}
				
				if( (nextHorse.x == 1 && nextHorse.y >= 7) || (nextHorse.x == 2 && nextHorse.y >= 6) ||
					(nextHorse.x == 3 && nextHorse.y >= 7) || (nextHorse.x == 0 && nextHorse.y >= 20) ) { // 끝난 말들
					
					nextHorse.finished = true;
					continue;
				}
				
				// 2. 이동 칸에 다른 말이 있는 경우
				for (int k = 0; k < horses.size(); k++) {
					Horse checkHorse = horses.get(k);
					// if( horses.size() != 1 && nextHorse.idx == checkHorse.idx ) continue;
					
					//if( horses.size() != 1 && nextHorse.x == checkHorse.x && nextHorse.y == checkHorse.y ) continue;
					
					// 3. 이동할때마다 점수 추가
					horses.add(nextHorse);
					dfs(map, cmd, i+1, sum + map[nextHorse.x][nextHorse.y], horses);
					horses.remove(nextHorse);
					
				}
			}
		}
	}
}

/*
//첫 말
		if( horses.size() < 4 ) {
			Horse horse = new Horse(0, cmd[0], 1, false);
			
			horses.add(horse);
			// 이동할때 점수 추가
			dfs(map, cmd, 1, sum+map[horse.x][horse.y], horses);
			// horses.remove(horse);
		} 
*/