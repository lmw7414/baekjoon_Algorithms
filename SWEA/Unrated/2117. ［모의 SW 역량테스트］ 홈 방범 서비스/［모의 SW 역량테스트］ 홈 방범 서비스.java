import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("#").append(tc).append(" ").append(BFS()).append("\n");
		}
		System.out.print(sb);
	}

	public static int BFS() {
		Queue<int[]> queue;
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		int k = 1;
		int h = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int homeCnt = 0;
				if (arr[i][j] == 0)
					homeCnt = 0;
				else
					homeCnt = 1;
				k = 2;
				boolean[][] visit = new boolean[N][N];
				queue = new ArrayDeque<>();
				Queue<int[]> temp = new ArrayDeque<>();
				queue.add(new int[] { i, j });
				visit[i][j] = true;
				while (!queue.isEmpty()) {

					while (!queue.isEmpty())
						temp.add(queue.poll());

					while (!temp.isEmpty()) {
						int[] cur = temp.poll();
						for (int t = 0; t < 4; t++) {
							int nx = cur[0] + dx[t];
							int ny = cur[1] + dy[t];

							if (nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny])
								continue;
							if (arr[nx][ny] == 1) { // 집 발견
								homeCnt++;
							}
							queue.add(new int[] { nx, ny });
							visit[nx][ny] = true;
						}
					}
					if (getCost(k, homeCnt) >= 0) {
						h = Math.max(h, homeCnt);
					}
					k++;
				}
			}
		}

		return h;
	}

	public static int getCost(int k, int home) {
		return (home * M) - ((k * k) + (k - 1) * (k - 1));
	}

}