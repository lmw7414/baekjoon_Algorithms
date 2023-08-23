import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static List<Integer>[] adjList;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new List[N];

		for(int i = 0; i < N; i++)
			adjList[i] = new ArrayList<>();

		for(int i = 0; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			adjList[s].add(e);
			adjList[e].add(s);
		}

		for(int i = 0; i < N; i++) {
			visit = new boolean[N];
			visit[i] = true;
			dfs(0,i);
		}

		System.out.println(0);
	}
	
	public static void dfs(int depth, int start) {
		if(depth == 4) {
			System.out.println(1);
			System.exit(0);
		}

		for(int next : adjList[start]) {
			if(visit[next]) continue;

			visit[next] = true;
			dfs(depth + 1, next);
			visit[next] = false;
		}
	}

}