package swsamsung.d4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/***
 * 나무 재테크 - 성공
 * 
 * 처음에 설계가 잘못되면 꼬여서 정신을 못차린다.
 * 처음에 설계를 잘하자ㅏㅏ.
 * 
 * PriorityQueue vs ArrayList + Collections.sort - 차이없음 
 * 
 * 전역 변수 vs 함수 내 변수 선언 - 차이 없음
 * 
 * @author sa833
 *
 */
public class P16235 {
	
	public static void main(String[] args) throws IOException {
		ArrayList<Tree> trees = new ArrayList<>();
		
		input(trees);
		
		trees = solution(trees);
		
		System.out.println(trees.size());
	}
	
	private static ArrayList<Tree> solution(ArrayList<Tree> trees) {
		List<Tree> die = new ArrayList<>();
		List<Tree> alive = new ArrayList<>();
		int[][] energies = new int[N+1][N+1];
		initEnerge(energies);
		
		while( K-- > 0 ) {
			
			//System.out.println("SPRING");
			trees = spring(trees, energies, die, alive);
			//printEnergies(energies);
			
			//System.out.println("SUMMER");
			summer(energies, die);
			die.clear();
			//printEnergies(energies);

			//System.out.println("FALL");
			trees = fall(trees, alive);
			alive.clear();
			//printEnergies(energies);
			
			//System.out.println("WINTER");
			winter(energies);
			//printEnergies(energies);
		}
		
		return trees;
	}
	
	private static void winter(int[][] energies) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				energies[i][j] += supplyEnergies[i][j];
			}
		}
	}
	
	static int[] dr = {-1,-1,-1,0,0,1,1,1};
	static int[] dc = {-1,0,1,-1,1,-1,0,1};
	private static ArrayList<Tree> fall(ArrayList<Tree> trees, List<Tree> alive) {
		
		for (Tree tree: alive) {
			int r = tree.r;
			int c = tree.c;
				
			for (int k = 0; k < 8; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				
				if( boundary(nr, nc) ) {
					trees.add(new Tree(nr,nc,1,false));
				}
			}
		}
		
		return trees;
	}
		
	private static boolean boundary(int r, int c) {
		return r>=1 && c>=1 && r<=N && c<=N;
	}
	
	private static void summer(int[][] energies, List<Tree> die) {
		
		for (Tree tree : die) {
			int i = tree.r;
			int j = tree.c;
			
			energies[i][j] += (tree.age/2);
		}
	}
	
	private static ArrayList<Tree> spring(ArrayList<Tree> trees, int[][] energies, List<Tree> die, List<Tree> alive) {
		int size = trees.size();
		
		//PriorityQueue<Tree> q = new PriorityQueue<>();
		ArrayList<Tree> q = new ArrayList<>();
		Collections.sort(trees);
		for (int i = 0; i < size; i++) {
			// Tree tree = trees.poll();
			Tree tree = trees.get(i);
			
			int r = tree.r;
			int c = tree.c;
			int age = tree.age;
			
			if( energies[r][c] - age < 0 ) {
				die.add(tree);
				continue;
			}
			
			energies[r][c] -= age;
			q.add(new Tree(r,c,age+1,false));
				
			if( (age+1) % 5 == 0 ) {
				alive.add(new Tree(r,c,age+1,false));
			}
		}
		
		//trees = new PriorityQueue<>(q);
		trees = new ArrayList<>(q);
		
		return trees;
	}
	
	private static void initEnerge(int[][] energies) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				energies[i][j] = 5;
			}
		}
	}
	
	static int N, M, K;
	static int[][] supplyEnergies;
	// static PriorityQueue<Tree> trees;
	private static void input(ArrayList<Tree> trees) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		
		N = stoi(inputs[0]);
		M = stoi(inputs[1]);
		K = stoi(inputs[2]);
		
		supplyEnergies = new int[N+1][N+1];
		
		init(br);
		
		setTree(br, trees);
		
		//printEnergies();
	}
	
	private static void printEnergies(int[][] energies) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(energies[i][j] + " "); 
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void setTree(BufferedReader br, ArrayList<Tree> trees) throws IOException {
		for (int i = 0; i < M; i++) {
			String[] inputs = br.readLine().split(" ");
			
			int r = stoi(inputs[0]); int c = stoi(inputs[1]); int age = stoi(inputs[2]);
			trees.add(new Tree(r,c,age,false));
		}
	}
	
	private static void init(BufferedReader br) throws IOException {
		// trees = new PriorityQueue();
		//trees = new ArrayList();
		
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				supplyEnergies[i][j] = stoi(st.nextToken());
			}
		}
	}
	
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static class Tree implements Comparable<Tree> {
		int r;
		int c;
		int age;
		boolean death;
		public Tree(int r, int c, int age, boolean death) {
			this.r = r;
			this.c = c;
			this.age = age;
			this.death = death;
		}
		
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
}