package swea.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/***
 * 이차원 배열과 연산 - 문제 설명 하... ( 행의 갯수가 행의 갯수인 줄 알았는데, 행에 있는 요소들의 갯수였다 - - )
 * 
 * Map 쓰지말고 -> 배열 index로 숫자별 갯수 구하자..!
 * 
 * @author sa833
 *
 */
public class P17140 {

	static class NumberFrequency implements Comparable<NumberFrequency> {
		int num;
		int times;
		public NumberFrequency(int num, int times) {
			this.num = num;
			this.times = times;
		}

		@Override
		public int compareTo(NumberFrequency o) {
			if( this.times == o.times )
				return this.num - o.num;
			return this.times - o.times;
			
		}
		
		/*
		@Override
        public int compareTo(NumberFrequency o) {
            if(this.times < o.times){
                return -1;
            }else if(this.times > o.times){
                return 1;   
            }else{
                //count가 동률인경우
                if(this.num < o.num){
                    return -1;
                }else if(this.num > o.num){
                    return 1;
                }
            }
            return 0;
        }
        */
	}
	
	static int[] countInfo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = stoi(st.nextToken());
		int c = stoi(st.nextToken());
		int k = stoi(st.nextToken());
		
		// System.out.println(r + " " + c + " " + k);
		int[][] arr = new int[101][101];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = stoi(st.nextToken());
			}
		}
		
		countInfo = new int[101];
		solution(r-1,c-1,k,arr);
	}
	
	
	private static void solution(int r, int c, int k, int[][] arr) {
		int t;
		int rSize = 3;
		int cSize = 3;
		
		for (t = 0; t <= 100; t++) {
			print(arr);
			System.out.println();
			
			if( arr[r][c] == k ) {
				break;
			}
			
			ArrayList<NumberFrequency> list = new ArrayList<>();
			
			if( cSize >= rSize ) { // 1. 행
				//System.out.println("행");
				
				int tmpSize = 0;
				for (int i = 0; i < rSize; i++) {
					for (int j = 0; j < cSize; j++) {
						countInfo[arr[i][j]] ++;
						arr[i][j] = 0;
					}
					
					for (int j = 1; j < countInfo.length; j++) {
						if( countInfo[j] == 0 ) continue;
						
						list.add(new NumberFrequency( j, countInfo[j] ));
					}
					
					Collections.sort(list);
					
					int index = 0;
					for( NumberFrequency n : list ) {
						arr[i][index++] = n.num;
						arr[i][index++] = n.times;
						System.out.println(Arrays.toString(arr[i]));
					}
					
					tmpSize = Math.max(tmpSize, index);
					
					list.clear();
					Arrays.fill(countInfo, 0);
				}
				rSize = tmpSize;

			} else { // 2. 열 
				//System.out.println("열");
				
				int tmpSize = 0; 
				for (int j = 0; j < cSize; j++) {
					for (int i = 0; i < rSize; i++) {
						countInfo[arr[j][i]] ++;
						arr[j][i] = 0;
					}
					
					for (int i = 1; i < countInfo.length; i++) {
						if( countInfo[i] == 0 ) continue;
						
						list.add(new NumberFrequency( i, countInfo[i] ));
					}
					
					Collections.sort(list);

					int index = 0;
					for( NumberFrequency n : list ) {
						arr[index++][j] = n.num;
						arr[index++][j] = n.times;
					}
					
					tmpSize = Math.max(tmpSize, index);
					
					list.clear();
					Arrays.fill(countInfo,0);
				}
				cSize = tmpSize;
			}
		}
		
		if( t == 101 )
			t = -1;
		System.out.println(t);
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}
