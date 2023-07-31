import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int[][] arr = new int[19][19];
	public static boolean[][] visit = new boolean[19][19];
	public static int win = 0; // 결과 출력 0: 승부 X, 1: 검은색, 2: 흰색
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 결과 출력 0: 승부 X, 1: 검은색, 2: 흰색
		// 가장 왼쪽의 바둑알
		int[] result = calc();
		if(win == 0) {
			System.out.println(win);
		} else {
			System.out.println(win);
			System.out.println(result[0] + " " + result[1]);
		}
	}


	// 오목 정답 찾는 메서드
	// 정답일 경우 정답의 첫번째 바둑알 위치 반환
	public static int[] calc() {
		for(int i = 0; i< 19; i++) {
			for(int j = 0; j< 19; j++) {
				
				if(arr[i][j] == 1) {
					if(searchUpCross(1, i, j) || searchRight(1, i, j) || searchDownCross(1,  i,  j) || searchDown(1,  i,  j)) {
						win = 1;
						return new int[] {i + 1, j + 1};
						
					}
				} else if(arr[i][j] == 2) {
					if(searchUpCross(2, i, j) || searchRight(2, i, j) || searchDownCross(2,  i,  j) || searchDown(2,  i,  j)) {
						win = 2;
						return new int[] {i + 1, j + 1};
						
					}
				}
			}
		}
		
		return new int[] {0,0};
	}
	// 오른쪽 대각선 위로 탐색
	public static boolean searchUpCross(int key, int x, int y) {
		if(check(x + 1, y - 1))
			if(arr[x+1][y-1] == key) return false;
		for(int i = 0; i< 5; i++) {
			int nx = x - i;
			int ny = y + i;
			if(check(nx, ny)) {
				if(arr[nx][ny] != key) {
					return false;
				}
			} else return false;
		}
		if(!check(x-5, y + 5)) return true;  // 5개 계산되었고 마지막이 경계라인이라면
		else {
			if(arr[x - 5][y + 5] == key) return false;  // 6개 체크
		}
		return true;
	}
	
	// 오른쪽으로 탐색
	public static boolean searchRight(int key, int x, int y) {
		if(check(x, y - 1))
			if(arr[x][y-1] == key) return false;
		for(int i = 0; i< 5; i++) {
			int nx = x;
			int ny = y + i;
			if(check(nx, ny)) {
				if(arr[nx][ny] != key) {
					return false;
				}
			} else return false;
		}
		if(!check(x, y + 5)) return true;  // 5개 계산되었고 마지막이 경계라인이라면
		else {
			if(arr[x][y + 5] == key) return false;  // 6개 체크
		}
		return true;
	}
	// 오른쪽 대각선 아래로 탐색
	public static boolean searchDownCross(int key, int x, int y) {
		if(check(x - 1, y - 1))
			if(arr[x-1][y-1] == key) return false;
		for(int i = 0; i< 5; i++) {
			int nx = x + i;
			int ny = y + i;
			if(check(nx, ny)) {
				if(arr[nx][ny] != key) {
					return false;
				}
			} else return false;
		}
		if(!check(x + 5, y + 5)) return true;  // 5개 계산되었고 마지막이 경계라인이라면
		else {
			if(arr[x + 5][y + 5] == key) return false;  // 6개 체크
		}
		return true;
	}
	// 아래로 탐색
	public static boolean searchDown(int key, int x, int y) {
		if(check(x - 1, y))
			if(arr[x-1][y] == key) return false;
		for(int i = 0; i< 5; i++) {
			int nx = x + i;
			int ny = y;
			if(check(nx, ny)) {
				if(arr[nx][ny] != key) {
					return false;
				}
			} else return false;
		}
		if(!check(x + 5, y)) return true;  // 5개 계산되었고 마지막이 경계라인이라면
		else {
			if(arr[x + 5][y] == key) return false;  // 6개 체크
		}
		return true;
	}
	
	public static boolean check(int x, int y) {
		if(x < 0 || y < 0 || x >= 19 || y >=19) return false;
		return true;
	}
}
