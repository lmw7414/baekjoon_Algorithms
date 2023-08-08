import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int R;
	static int[][] board;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< R; i++) {
			int input = Integer.parseInt(st.nextToken());
			switch(input) {
			case 1:
				sol1();
				break;
			case 2:
				sol2();
				break;
			case 3:
				sol3();
				break;
			case 4:
				sol4();
				break;
			case 5:
				sol5();
				break;
			case 6:
				sol6();
				break;
			}
		}
		printBoard();
		
		
	}
	
	// 상하반전
	public static void sol1() {
		for(int i = 0; i< N/2; i++) {
			for(int j = 0; j < M; j++) {
				int temp = board[i][j];
				board[i][j] = board[N - i - 1][j];
				board[N - i - 1][j] = temp;
			}
		}
	}
	// 좌우 반전
	public static void sol2() {
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < M/2; j++) {
				int temp = board[i][j];
				board[i][j] = board[i][M - j - 1];
				board[i][M - j - 1] = temp;
			}
		}
		
	}
	// 오른쪽 90도
	public static void sol3() {
		int[][] temp = new int[M][N];
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < M; j++) {
				temp[j][N - i - 1] = board[i][j];
			}
		}
		int t = N;
		N = M;
		M = t;
		board = temp;
	}
	// 왼쪽 90도
	public static void sol4() {
		sol3();
		sol3();
		sol3();
//		int[][] temp = new int[M][N];
//		for(int i = 0; i< N; i++) {
//			for(int j = 0; j < M; j++) {
//				temp[i][N - j] = board[i][j];
//			}
//		}
//		int t = N;
//		N = M;
//		M = t;
//		board = temp;
	}
	// 4등분해서 시계방향 이동
	public static void sol5() {
		int[][] temp = new int[N][M];
		
		// 1을 2로
		for(int i = 0; i < N/2; i++) {
			for(int j = 0; j < M/2; j++) {
				temp[i][M/2 + j] = board[i][j];
			}
		}
		// 2를 3으로
		for(int i = 0; i < N/2; i++) {
			for(int j = M/2; j < M; j++) {
				temp[N/2 + i][j] = board[i][j];
			}
		}
		// 3을 4로
		for(int i = N/2; i < N; i++) {
			for(int j = M/2; j < M; j++) {
				temp[i][j - M/2] = board[i][j];
			}
		}
		// 4를 1로
		
		for(int i = N/2; i < N; i++) {
			for(int j = 0; j< M/2; j++) {
				temp[i - N/2][j] = board[i][j];
			}
		}
		board = temp;
		
	}
	// 4등분해서 반시계방향 이동
	public static void sol6() {
		int[][] temp = new int[N][M];
		// 1을 4로
		for(int i = 0; i< N/2; i++) {
			for(int j = 0; j < M/2; j++) {
				temp[N/2 + i][j] = board[i][j];
			}
		}
		
		// 4를 3으로
		for(int i = N/2; i < N; i++) {
			for(int j = 0; j< M/2; j++) {
				temp[i][M/2 + j] = board[i][j];
			}
		}
		// 3을 2로
		for(int i = N/2; i < N; i++) {
			for(int j = M/2; j < M; j++) {
				temp[i - N/2][j] = board[i][j];
			}
		}
		// 2를 1로
		for(int i = 0; i< N/2; i++) {
			for(int j = M/2; j < M; j++) {
				temp[i][j - M/2] = board[i][j];
			}
		}
		board = temp;
	}
	
	public static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}