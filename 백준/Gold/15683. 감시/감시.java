import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * 0 빈 칸
 * 6 벽
 * 1~5 CCTV 4 2 4 4 1
 * 1 우 하 좌 상
 * 2 가 로 세 로
 * 3 우 하 좌 상
 * 4 우 하 좌 상
 * 
 * 사각지대의 최소 크기 구하기
 */
public class Main {

	static int N, M;
	static int[][] arr;
	static List<CCTV> list = new ArrayList();
	static int[] cctvDir;  // cctv 방향 저장 배열
	static int answer = Integer.MAX_VALUE;
	static int[] dx = {-1, 0, 1, 0};  // 상우하좌
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int input = Integer.parseInt(st.nextToken());
				arr[i][j] = input;
				if(input >= 1 && input <= 5) list.add(new CCTV(input, i, j));
			}
		}

		cctvDir = new int[list.size()];
		DFS(0);
		System.out.println(answer);
	}

	public static void DFS(int depth) {
		if(depth == list.size()) {

			answer = Math.min(answer, countSpace(fillSpace()));
			return;
		}

		for(int i = 0; i < 4; i++) {
			cctvDir[depth] = i;
			DFS(depth + 1);
		}
	}


	public static int countSpace(int[][] copyArr) {
		int cnt = 0;
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyArr[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

	public static int[][] fillSpace() {
		int[][] copyArr = new int[N][M];
		// 원본 배열 복사하기
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < M; j++) {
				copyArr[i][j] = arr[i][j];
			}
		}

		for(int i = 0; i < list.size(); i++) {
			CCTV cctv = list.get(i);
			switch(cctv.type) {
			case 1:
				c1(copyArr, cctv, cctvDir[i]);
				break;
			case 2:
				c2(copyArr, cctv, cctvDir[i]);
				break;
			case 3:
				c3(copyArr, cctv, cctvDir[i]);
				break;
			case 4:
				c4(copyArr, cctv, cctvDir[i]);
				break;
			case 5:
				c5(copyArr, cctv);
				break;
			}
		}
		return copyArr;
	}


	// 한방향으로만 체크
	public static void c1(int[][] copyArr, CCTV cctv, int dir) {
		int curX = cctv.x;
		int curY = cctv.y;
		int idx = 0;
		while(true) {
			int nx = curX + dx[dir] * idx;
			int ny = curY + dy[dir] * idx;

			if(!check(nx, ny)) break;
			copyArr[nx][ny] = -1;
			idx++;
		}

	}

	// 상하, 좌우로만 체크
	public static void c2(int[][] copyArr, CCTV cctv, int dir) {
		int curX = cctv.x;
		int curY = cctv.y;
		int idx = 0;
		while(true) {
			int nx = curX + dx[dir] * idx;
			int ny = curY + dy[dir] * idx;

			if(!check(nx, ny)) break;
			copyArr[nx][ny] = -1;
			idx++;
		}

		dir += 2;
		dir %= 4;
		idx = 0;
		while(true) {
			int nx = curX + dx[dir] * idx;
			int ny = curY + dy[dir] * idx;

			if(!check(nx, ny)) break;
			copyArr[nx][ny] = -1;
			idx++;
		}

	}

	//  상우01, 우하12, 하좌23, 좌상30
	public static void c3(int[][] copyArr, CCTV cctv, int dir) {
		int curX = cctv.x;
		int curY = cctv.y;
		int idx = 0;
		while(true) {
			int nx = curX + dx[dir] * idx;
			int ny = curY + dy[dir] * idx;

			if(!check(nx, ny)) break;
			copyArr[nx][ny] = -1;
			idx++;
		}

		idx = 0;
		dir += 1;
		dir %= 4;
		while(true) {
			int nx = curX + dx[dir] * idx;
			int ny = curY + dy[dir] * idx;

			if(!check(nx, ny)) break;
			copyArr[nx][ny] = -1;
			idx++;
		}

	}

	//  좌상우301, 상우하012, 우하좌123, 하좌상230
	public static void c4(int[][] copyArr, CCTV cctv, int dir) {
		int curX = cctv.x;
		int curY = cctv.y;
		int idx = 0;
		while(true) {
			int nx = curX + dx[dir] * idx;
			int ny = curY + dy[dir] * idx;

			if(!check(nx, ny)) break;
			copyArr[nx][ny] = -1;
			idx++;
		}

		idx = 0;
		dir += 1;
		dir %= 4;
		while(true) {
			int nx = curX + dx[dir] * idx;
			int ny = curY + dy[dir] * idx;

			if(!check(nx, ny)) break;
			copyArr[nx][ny] = -1;
			idx++;
		}

		idx = 0;
		dir += 1;
		dir %= 4;
		while(true) {
			int nx = curX + dx[dir] * idx;
			int ny = curY + dy[dir] * idx;

			if(!check(nx, ny)) break;
			copyArr[nx][ny] = -1;
			idx++;
		}

	}

	// 네방향 모두 체크
	public static void c5(int[][] copyArr, CCTV cctv) {
		int curX = cctv.x;
		int curY = cctv.y;
		int idx = 0;
		
		for(int dir = 0; dir <4; dir++) {
			idx = 0;
			while(true) {
				int nx = curX + dx[dir] * idx;
				int ny = curY + dy[dir] * idx;

				if(!check(nx, ny)) break;
				copyArr[nx][ny] = -1;
				idx++;
			}
		}
	}


	// 범위 안에 있으면  true
	public static boolean check(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < M && arr[nx][ny] != 6;
	}

	static class CCTV {
		int type;
		int x;
		int y;
		int dir;  // 상하좌우
		public CCTV (int type, int x, int y) {
			this.type = type;
			this.x = x;
			this.y = y;
			dir = 0;
		}

	}

}