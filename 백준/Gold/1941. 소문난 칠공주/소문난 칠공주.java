import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 
 * 소문난 칠공주 규칙 1. 7명의 여학생들로 구성 2. 7명의 자리는 서로 가로나 세로로 반드시 인접해있어야 함 3. 반드시 이다솜파의
 * 학생들로만 구성될 필요는 없다. 4. 생존을 위해 이다솜파가 반드시 우위를 점해야한다. 따라서 7명의 학생 중 이다솜파의 학생이 적어도
 * 4명 이상은 반드시 포함되어야 한다.
 * 
 * [입력값] S : 이다솜파 Y : 임도연파
 * 
 * [문제 해결 프로세스] 1. 깊이는 7 2. 조합 -> 7자리 -> 길이 이어지지 않으면 가지치기 3. 연결되어있는지 확인하는 방법은
 * DFS or BFS y의 카운트가 3개 넘어가면 안됨
 */
public class Main {
	static char[][] arr = new char[5][5];
	static int[] list = new int[7];
	static boolean[][] visited = new boolean[5][5];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		calc(0, 0, 0);
		System.out.println(answer);

	}

	public static void calc(int depth, int idx, int yCnt) {
		if (yCnt > 3)
			return;
		if (depth == 7) {
			if (isConnected())
				answer++;
			return;
		}

		for (int i = idx; i < 25; i++) {
			list[depth] = i;
			int[] a = toArrIdx(i);
			visited[a[0]][a[1]] = true;
			if (arr[a[0]][a[1]] == 'Y')
				calc(depth + 1, i + 1, yCnt + 1);
			else
				calc(depth + 1, i + 1, yCnt);
			visited[a[0]][a[1]] = false;
		}
	}

	public static boolean isConnected() {
		boolean[][] visit = new boolean[5][5];
		Queue<int[]> queue = new ArrayDeque<>();
		int[] idx = toArrIdx(list[0]);
		queue.add(idx);
		visit[idx[0]][idx[1]] = true;
		int cnt = 1;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || !visited[nx][ny] || visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				queue.add(new int[] { nx, ny });
				cnt++;
			}
		}
		if (cnt == 7)
			return true;
		else
			return false;
	}

	public static int[] toArrIdx(int idx) {
		int[] result = new int[2];
		result[0] = idx / 5;
		result[1] = idx % 5;
		return result;
	}

}