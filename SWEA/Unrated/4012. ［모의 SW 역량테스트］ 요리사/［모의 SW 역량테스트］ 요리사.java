import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] arr;
	static boolean[] visit;
	static int[] result1;
	static int[] result2;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N + 1][N + 1];
			visit = new boolean[N + 1];
			result1 = new int[N/2];
			result2 = new int[N/2];
			answer = Integer.MAX_VALUE;
			//answer2 = Integer.MAX_VALUE;
			for(int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			calc1(0, 1);
			System.out.println("#" + tc + " " + answer);
		}


	}
	public static int findBest() {
		int first = 0;
		int second = 0;
		for(int i = 0; i< result1.length; i++) {
			for(int j = i + 1; j < result1.length; j++) {
				first += arr[result1[i]][result1[j]] + arr[result1[j]][result1[i]];
			}
		}
		for(int i = 0; i< result2.length; i++) {
			for(int j = i + 1; j < result2.length; j++) {
				second += arr[result2[i]][result2[j]] + arr[result2[j]][result2[i]];
			}
		}
		
		return Math.abs(first - second);
	}
	public static void calc1(int depth, int idx) {
		if(depth == N/2) {
			calc2(0, 1);
			//System.out.println(res);
			return;
		}
		for(int i = idx; i <= N; i++) {
			if(!visit[i]) {
				result1[depth] = i;
				visit[i] = true;
				calc1(depth + 1, i + 1);
				visit[i] = false;
			}
		}
	}

	public static void calc2(int depth, int idx) {
		if(depth == N/2) {
			answer = Math.min(answer, findBest());
			//System.out.print("[" + result1[0] + "," + result1[1] + "]   ");
			//System.out.println("[" + result2[0] + "," + result2[1] + "]");
			return;
		}
		for(int i = idx; i <= N; i++) {
			if(!visit[i]) {
				result2[depth] = i;
				visit[i] = true;
				calc2(depth + 1, i + 1);
				visit[i] = false;
			}
		}
	}
	
	

}