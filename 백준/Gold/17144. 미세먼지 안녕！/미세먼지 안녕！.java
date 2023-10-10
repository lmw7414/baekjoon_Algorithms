import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 공기 청정기는 항상 1번 열에 설치되어 있고, 크기는 두 행을 차지 다음 프로세스가 1초 동안 이루어진다. 
 * 1. 미세먼지가 확산된다.
 * 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다. 
 * - 미세먼지는 상하좌우 네방향으로 확산된다. 
 * - 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다. 
 * - 확산되는 양은 A / 5 이고 소수점은 버린다. 
 * - rc에 해당하는 자리는 A - ((A/5) X 확산된 방향의 개수) 
 * 2. 공기 청정기 작동 
 * - 위쪽 공기청정기의 바람은 반시계 방향 
 * - 아래쪽 공기청정기의 바람은 시계방향 
 * - 바람이 불면 미세먼지가 바람의 방향대로 모두 한칸씩 이동
 * 
 * [입력값] T : 주어진 시간
 */
public class Main {

	static int R, C, T;
	static int[][] arr;
	static Robot robot;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 공기 청정기 찾기
		findRobot();

		for (int t = 0; t < T; t++) {
			moveDust();
			// System.out.println("먼지이동");
			// printArr();
			purification();
			// System.out.println("공기순환");
			// printArr();
		}
		// 미세먼지 양 출력
		System.out.println(calc());
	}

	public static void findRobot() {
		for (int i = 0; i < R; i++) {
			if (arr[i][0] == -1) {
				robot = new Robot(i, i + 1);
				break;
			}
		}
	}

	// 미세먼지 이동시키기(1초 간격)
	public static void moveDust() {
		// 먼지 찾기
		int[][] copy = new int[R][C];
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != 0 && arr[i][j] != -1) {
					if (arr[i][j] < 5) {
						copy[i][j] = arr[i][j];
						continue; // 먼지가 5보다 작으면 퍼트려지지 않는다.
					}
					queue.add(new int[] { i, j });
				}
			}
		}

		// 복사배열 생성

		copy[robot.first[0]][robot.first[1]] = -1;
		copy[robot.second[0]][robot.second[1]] = -1;
		while (!queue.isEmpty()) {
			int[] dust = queue.poll();
			int childDust = arr[dust[0]][dust[1]] / 5;
			int dir = 0;
			for (int i = 0; i < 4; i++) {
				int nx = dust[0] + dx[i];
				int ny = dust[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C || arr[nx][ny] == -1)
					continue;
				dir++;
				copy[nx][ny] += childDust;
			}
			copy[dust[0]][dust[1]] += arr[dust[0]][dust[1]] - (childDust * dir);
		}

		// 복사배열을 원본으로 저장
		arr = copy;
	}

	public static void purification() {
		int[][] copy = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		// 상 청소
		int[] first = robot.first;
		// 왼쪽
		for (int i = first[0] - 1; i > 0; i--) {
			arr[i][0] = copy[i - 1][0];
		}
		// 위쪽
		for (int i = 0; i < C - 1; i++) {
			arr[0][i] = copy[0][i + 1];
		}
		// 오른쪽
		for (int i = 0; i < first[0]; i++) {
			arr[i][C - 1] = copy[i + 1][C - 1];
		}
		// 아래쪽
		for (int i = C - 1; i > 1; i--) {
			arr[first[0]][i] = copy[first[0]][i - 1];
		}
		arr[first[0]][first[1] + 1] = 0;

		// 하 청소
		int[] second = robot.second;
		// 왼쪽
		for (int i = second[0] + 1; i < R - 1; i++) {
			arr[i][0] = copy[i + 1][0];
		}
		// 아래쪽
		for (int i = 0; i < C - 1; i++) {
			arr[R - 1][i] = copy[R - 1][i + 1];
		}
		// 오른쪽
		for (int i = R - 1; i > second[0] - 1; i--) {
			arr[i][C - 1] = copy[i - 1][C - 1];
		}
		// 위쪽
		for (int i = C - 1; i > 1; i--) {
			arr[second[0]][i] = copy[second[0]][i - 1];
		}
		arr[second[0]][second[1] + 1] = 0;
	}

	public static int calc() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != 0 && arr[i][j] != -1) {
					cnt += arr[i][j];
				}
			}
		}
		return cnt;
	}

	public static void printArr() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Robot {
		int[] first;
		int[] second;

		public Robot(int f, int s) {
			first = new int[] { f, 0 };
			second = new int[] { s, 0 };
		}
	}
}