import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] degrees;  // 진입차수 저장
	static List<Integer>[] adjList;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		degrees = new int[N + 1];
		adjList = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			degrees[to]++;
		}
		BFS();
		System.out.println(sb);

	}
	
	public static void BFS() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i<= N; i++) {
			if(degrees[i] == 0)
				queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int from = queue.poll();
			sb.append(from + " ");
			
			for(int to : adjList[from]) {
				if(--degrees[to] == 0)
					queue.offer(to);
			}
		}
	}

}