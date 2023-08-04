import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	
	static int MAX_SIZE = 6;
	static int[] arr;
	static int[] result;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			if(size == 0) break;  // 입력값이 0일 경우 종료
			
			// 배열 초기화 후 데이터 저장
			arr = new int[size];
			visit = new boolean[size];
			result = new int[6];
			for(int i = 0; i< size; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			dfs(size, 0, 0);
			sb.append("\n");
		}
		System.out.println(sb);

	}
	
	// 조합
	public static void dfs(int N, int depth, int idx) {
		if(depth == MAX_SIZE) {
			for(int i = 0; i< 6; i++)
				sb.append(result[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i = idx; i < N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				result[depth] = arr[i];
				dfs(N, depth+1, i + 1);
				visit[i] = false;
			}
		}
		
	}

}