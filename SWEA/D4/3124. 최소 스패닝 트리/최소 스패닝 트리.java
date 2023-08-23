import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			int[] parent = new int[V + 1];
			for(int idx = 1; idx <= V; idx++)
				parent[idx] = idx;
			PriorityQueue<Edge> pq = new PriorityQueue<>((a1, b1) -> a1.w - b1.w);
			for(int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				pq.add(new Edge(s, e, w));
			}
			System.out.println("#" + tc +" " + kruskal(pq, parent, V));
		}

	}
	
	public static long kruskal(PriorityQueue<Edge> pq, int[] parent, int V) {
		int cnt = 0;
		long result = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(union(parent, cur.s, cur.e)) {
				result += cur.w;
				if(++cnt == V - 1) break;
			}
		}
		return result;
	}
	
	public static int find(int[] parent, int child) {
		if(parent[child] == child) return child;
		return parent[child] = find(parent, parent[child]);
	}
	
	public static boolean union(int[] parent, int a, int b) {
		int aRoot = find(parent, a);
		int bRoot = find(parent, b);
		
		if(aRoot == bRoot) return false;
		parent[bRoot] = aRoot;
		return true;
	}
	
	

	static class Edge {
		int s;
		int e;
		int w;
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
}