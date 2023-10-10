import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * N명의 사람이 살고 있다. 
 * 1~N 명의 사람 두 사람은 서로를 알고 있는 관계일 수도 있고, 아닐 수도 있다. 
 * 두 사람은 서로 아는 관계이거나 몇 사람을 거쳐서 알 수 있는 관계라면 이러한 사람들은 모두 다 묶어서 하나의 무리라고 한다. 
 * 창용마을에 몇개의 무리가 존재하는지
 *
 */
public class Solution {
	static int N, M;
	static List<Integer>[] adjList;
	static int[] parent;
	static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			adjList = new List[N + 1];
			parent = new int[N + 1];
			visit = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<>();
				parent[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				adjList[A].add(B);
				adjList[B].add(A);
			}
			HashSet<Integer> hs = new HashSet<>();
			for (int i = 1; i <= N; i++) {
				DFS(i);
			}
			for (int i = 1; i <= N; i++) {
				hs.add(parent[i]);
			}
			System.out.println("#" + tc + " " + hs.size());
		}
	}

	public static int find(int idx) {
		if (parent[idx] == idx)
			return idx;
		else
			return parent[idx] = find(parent[idx]);
	}

	public static void union(int a, int b) {
		parent[b] = a;
	}

	public static void DFS(int start) {

		for (int next : adjList[start]) {
			int rootS = find(start);
			int rootN = find(next);
			if (rootS != rootN) {
				union(rootS, rootN);
				DFS(next);
			}
		}

	}
}