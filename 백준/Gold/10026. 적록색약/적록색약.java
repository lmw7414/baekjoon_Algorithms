import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


/**
 * 적록색약은 빨간색과 초록색 구별을 못함
 * 그림은 몇개의 구역으로 나눠져 있는데, 구역은 같은 색으로 이루어져 있다.
 * 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다.
 * 색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다.(빨간색, 초록색)
 *
 */

public class Main {
	static int N;
	static char[][] arr;
	static int[] dx = {-1, 1, 0, 0};  //상하좌우
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];

		for(int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		System.out.println(BFSforNormal() + " " + BFSforAbnormal());
	}

	//정상인
	public static int BFSforNormal() {
		int cnt = 0;
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(visit[i][j]) continue;
				cnt++;
				char target = arr[i][j];
				queue.offer(new int[] {i, j});
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					for(int k = 0; k < 4; k++) {
						int nx = cur[0] + dx[k];
						int ny = cur[1] + dy[k];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny] || arr[nx][ny] != target) continue;
						visit[nx][ny] = true;
						queue.offer(new int[] {nx, ny});
					}
				}
			}
		}

		return cnt;
	}

	//색맹 R과 G를 같은 색으로
	public static int BFSforAbnormal() {
		int cnt = 0;
		boolean flag;  // true -> 현재 탐색하려는 색깔이 R이거나 G이면 같은 색으로 본다.
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				flag = false;
				if(visit[i][j]) continue;
				cnt++;
				char target = arr[i][j];
				if(target == 'R' || target == 'G') flag = true;
				
				queue.offer(new int[] {i, j});
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					for(int k = 0; k < 4; k++) {
						int nx = cur[0] + dx[k];
						int ny = cur[1] + dy[k];
						
						if(flag) {  //R이나 G인경우
							if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny] || arr[nx][ny] == 'B') continue;
						} else {
							if(nx < 0 || ny < 0 || nx >= N || ny >= N || visit[nx][ny] || arr[nx][ny] != target) continue;
						}
						
						visit[nx][ny] = true;
						queue.offer(new int[] {nx, ny});
					}
				}
			}
		}

		return cnt;
	}

}