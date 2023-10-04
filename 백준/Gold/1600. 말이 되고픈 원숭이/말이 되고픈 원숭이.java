import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제 설명] 
 * 말은 격자판에서 체스의 나이트와 같은 이동방식을 가진다. 
 * 말은 장애물을 뛰어넘을 수 있다. 
 * 원숭이는 능력이 부족해서 총 k번만 위와 같이 움직일 수 있고, 그 외에는 그냥 인접한 칸으로만 움직일 수 있다. 
 * 대각선 방향은 인접한 칸에 포함되지 않는다.
 * 
 * 격자판 맨 왼쪽 위에서 시작해서 오른쪽 아래까지 가야한다. 
 * 인접한 네 방향으로 한번 움직이기, 말처럼 움직이기 모두 한번의 동작으로 침
 * 원숭이가 최소한의 동작으로 시작지점에서 도착지점까지 갈 수 있는 최소값을 구하여라
 * 
 * [입력값]
 * K 말처럼 이동할 수 있는 횟수, W 가로, H 세로 
 * 0 평지 1 장애물
 */

public class Main {

	static int K, W, H;
	static int[][] arr;
	static int[] hx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] hy = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Point monkey = new Point(0, 0, 0, 0);
	static boolean[][][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		visit = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visit[monkey.x][monkey.y][0] = true;

		System.out.println(BFS());
	}

	public static int BFS() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(monkey);

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			if (cur.x == H - 1 && cur.y == W - 1)
				return cur.cnt;

			if (cur.k < K) {
				for (int i = 0; i < 8; i++) {
					int nx = cur.x + hx[i];
					int ny = cur.y + hy[i];
					if (!check(nx, ny))
						continue;
					if (visit[nx][ny][cur.k + 1])
						continue;
					queue.add(new Point(nx, ny, cur.k + 1, cur.cnt + 1));
					visit[nx][ny][cur.k + 1] = true;
				}
			}
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if (!check(nx, ny))
					continue;
				if (visit[nx][ny][cur.k])
					continue;
				queue.add(new Point(nx, ny, cur.k, cur.cnt + 1));
				visit[nx][ny][cur.k] = true;
			}

		}
		return -1;

	}

	public static boolean check(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= H || ny >= W || arr[nx][ny] == 1)
			return false;
		return true;
	}

	static class Point {
		int x;
		int y;
		int k;
		int cnt;

		Point(int x, int y, int k, int cnt) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.cnt = cnt;
		}
	}
}