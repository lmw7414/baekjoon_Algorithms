import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제 해결 프로세스] 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 함 연구소의 크기는 N X M 연구소는 빈 칸, 벽으로
 * 이루어져 있음 0: 빈칸 1: 벽 2: 바이러스 일부 칸에 바이러스가 존재하며, 상하좌우로 인접한 빈칸으로 모두 퍼져나갈 수 있다. 세울
 * 수 있는 벽의 개수는 3개이며 꼭 3개를 세워야 함
 * 
 * -> 0의 개수 구하기 [입력 값 및 제한] 시간: 2초 3 <= N, M <= 8 [풀이] 1. 바이러스의 위치파악 2. 조합 3.
 * 바이러스 위치가 아니고 벽의 위치가 아닌 곳에 3개의 벽을 세운다. 4. 조합으로 만들었다면 해당 위치에 벽을 세우고 바이러스를 퍼트림
 * 5. 0의 개수 찾기
 */

public class Main {
	static int N, M;
	static int[][] arr;
	static int answer = 0;
	public static Point[] barriers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		barriers = new Point[3];
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		calc(0, 0);
		System.out.println(answer);

	}

	public static void calc(int depth, int idx) {
		if (depth == 3) {
			BFS();
//			for(int i = 0; i< 3; i++)
//				System.out.print(barriers[i] + " ");
//			System.out.println();
			return;
		}
		for (int i = idx; i < N * M; i++) {
			int x = i / M;
			int y = i % M;
			if (arr[x][y] == 0) {
				barriers[depth] = new Point(x, y);
				calc(depth + 1, i + 1);
			}
		}
	}

	public static void BFS() {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		int[][] copy = copyArr();
		boolean[][] visit = new boolean[N][M];

		Queue<Point> queue = new ArrayDeque();
		for (Point p : barriers) {
			copy[p.x][p.y] = 1; // 임시로 만든 벽 세우기
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 2 && !visit[i][j]) { // 현재 위치가 바이러스이고 아직 방문하지 않았다면
					visit[i][j] = true;
					queue.add(new Point(i, j));

					while (!queue.isEmpty()) {
						Point cur = queue.poll();

						for (int k = 0; k < 4; k++) {
							int nx = cur.x + dx[k];
							int ny = cur.y + dy[k];

							if (nx < 0 || ny < 0 || nx >= N || ny >= M || copy[nx][ny] == 1 || visit[nx][ny])
								continue;
							copy[nx][ny] = 2;
							visit[nx][ny] = true;
							queue.add(new Point(nx, ny));
						}
					}
				}
			}
		}

		int cleanArea = countCleanArea(copy);
		answer = Math.max(answer, cleanArea);
	}

	public static int countCleanArea(int[][] copy) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	public static int[][] copyArr() {
		int[][] copy = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}

	public static void printArr(int[][] copy) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(copy[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

}