import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제 설명] 모든 섬을 다리로 연결하려고 한다 다리는 바다에만 건설 다리의 길이는 다리가 격자에서 차지하는 칸의 수 한 다리의 방향이
 * 중간에 바뀌면 안된다. 다리의 길이는 2이상
 * 
 * [문제 해결 프로세스] 1. 섬 나누기 2. 모든 섬의 섬에서 섬 다리 길이 구하기 3. MST 찾기
 */
public class Main {

	static int N, M;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] arr;
	static int[][] route; // 길과 길 사이의 전체 경로
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int islandCnt = divideIsland();
		route = new int[islandCnt + 1][islandCnt + 1];
		parent = new int[islandCnt + 1];
		for (int i = 1; i <= islandCnt; i++)
			parent[i] = i;
		//printIsland();
		bridge();
		System.out.println(kruskal(islandCnt));
		
//		System.out.println(islandCnt);
		

	}

	// 섬 나누기
	public static int divideIsland() {
		boolean[][] visit = new boolean[N][M];
		int idx = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0)
					continue;
				if (visit[i][j])
					continue;

				Queue<int[]> queue = new ArrayDeque<>();
				queue.add(new int[] { i, j });
				visit[i][j] = true;
				arr[i][j] = idx;
				while (!queue.isEmpty()) {
					int[] cur = queue.poll();

					for (int k = 0; k < 4; k++) {
						int nx = cur[0] + dx[k];
						int ny = cur[1] + dy[k];
						if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny] || arr[nx][ny] == 0)
							continue;
						queue.add(new int[] { nx, ny });
						visit[nx][ny] = true;
						arr[nx][ny] = idx;
					}
				}
				idx++;
			}
		}
		return idx - 1;
	}

	// 모든 섬의 다리길이 구하기
	public static void bridge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0)
					continue;
				int islandIdx = arr[i][j];

				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (!check(nx, ny, islandIdx))
						continue;

					int cnt = 1;
					while (true) {
						nx += dx[k];
						ny += dy[k];
						if (!check(nx, ny, islandIdx))
							break; // 경계 체크
						cnt++;

						if (arr[nx][ny] != 0 && arr[nx][ny] != islandIdx) { // 다른 섬을 만난다면?
							if (cnt - 1 >= 2) {
								if (route[islandIdx][arr[nx][ny]] == 0) { // 아직 경로가 없는 경우
									route[islandIdx][arr[nx][ny]] = cnt - 1;
									route[arr[nx][ny]][islandIdx] = cnt - 1;
								} else if (route[islandIdx][arr[nx][ny]] > cnt - 1) { // 경로가 있으나 다 짧은 다리가 만들어질 경우
									route[islandIdx][arr[nx][ny]] = cnt - 1;
									route[arr[nx][ny]][islandIdx] = cnt - 1;
								}
							}
							break;
						}

					}
				}
			}
		}
	}

	public static boolean check(int nx, int ny, int islandIdx) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == islandIdx)
			return false;
		return true;
	}

	// MST 구하기
	public static int kruskal(int N) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
		for(int i= 1; i <= N; i++ ) {
			for(int j = 1; j <=N; j++) {
				//if(i == j) continue;
				if(route[i][j] == 0 ) continue;
				pq.add(new Edge(i, j, route[i][j]));
			}
		}
		
		int result = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int rootA = find(cur.v);
			int rootB = find(cur.e);
			
			if(rootA != rootB) {
				union(rootA, rootB);
				result += cur.w;
				cnt++;
			}
		}
		if(cnt != N - 1) return -1;
		return result;
	}

	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		parent[rootA] = rootB;
	}

	public static int find(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = find(parent[a]);
	}

	public static void printIsland() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Edge {
		int v;
		int e;
		int w;

		public Edge(int v, int e, int w) {
			this.v = v;
			this.e = e;
			this.w = w;
		}
	}

}