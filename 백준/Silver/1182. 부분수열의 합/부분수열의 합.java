import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int S;
	static int[] arr;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		dfs(0, 0, 0);
		System.out.println(cnt);
		
	}
	
	public static void dfs(int depth, int sum, int selectedCnt) {
		if(depth == N) {
			if(selectedCnt == 0) return;
			if(sum == S) cnt++;
			return;
		}
		
		dfs(depth + 1, sum + arr[depth], selectedCnt + 1);
		dfs(depth + 1, sum, selectedCnt);
		
	}
}