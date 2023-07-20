
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int[][] visit = new int[8][8];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String start = br.readLine();
		String end = br.readLine();
		for (int i = 0; i < 8; i++)
			Arrays.fill(visit[i], -1);

		int[] startPos = changePos(start);
		int[] endPos = changePos(end);
		BFS(startPos[0], startPos[1]);
		System.out.println(visit[endPos[0]][endPos[1]]);

	}

	public static int[] changePos(String pos) {
		int x = 8 - (pos.charAt(1) - '0'); // x값
		int y = pos.charAt(0) - 97; // y값
		return new int[] { x, y };
	}

	public static void BFS(int startX, int startY) {
		Queue<int[]> queue = new LinkedList<>();
		int[] dx = { -2, -2, -1, 1, 2, 2, 1, -1 };
		int[] dy = { -1, 1, 2, 2, 1, -1, -2, -2 };
		queue.add(new int[] { startX, startY });
		visit[startX][startY] = 0;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 8; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8)
					continue;

				if (visit[nx][ny] == -1) {
					visit[nx][ny] = visit[cur[0]][cur[1]] + 1;
					queue.add(new int[] { nx, ny });
				} else if (visit[nx][ny] > visit[cur[0]][cur[1]] + 1) {
					visit[nx][ny] = visit[cur[0]][cur[1]] + 1;
					queue.add(new int[] { nx, ny });
				}

			}
		}
	}

}
