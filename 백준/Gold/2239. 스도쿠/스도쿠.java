import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * [문제 해결 프로세스] 1. 결과가 사전 순이므로 9~1 순으로 DFS 접근 2. 가지치기 해야함 - 가로에 해당 숫자가 있는지 - 세로에
 * 해당 숫자가 있는지 - 3 x 3에 해당 숫자가 있는지
 *
 */

public class Main {

	static int[][] board = new int[9][9];
	static List<Point> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				int val = line.charAt(j) - '0';
				board[i][j] = val;
				if (val == 0)
					list.add(new Point(i, j));
			}
		}
		DFS(0);
		printAnswer();
	}

	public static void DFS(int depth) {
		if (depth == list.size()) {
			printAnswer();
			System.exit(0);
		}

		for (int k = 1; k <= 9; k++) {
			if (!row(list.get(depth).x, k))
				continue;
			if (!col(list.get(depth).y, k))
				continue;
			if (!threeByThree(list.get(depth).x, list.get(depth).y, k))
				continue;
			board[list.get(depth).x][list.get(depth).y] = k;
			DFS(depth + 1);
			board[list.get(depth).x][list.get(depth).y] = 0;
		}
	}

	public static boolean row(int x, int value) {
		for (int i = 0; i < 9; i++)
			if (board[x][i] == value)
				return false;
		return true;
	}

	public static boolean col(int y, int value) {
		for (int i = 0; i < 9; i++)
			if (board[i][y] == value)
				return false;
		return true;
	}

	public static boolean threeByThree(int x, int y, int value) {
		int sx = startPoint(x);
		int sy = startPoint(y);

		for (int i = sx; i < sx + 3; i++) {
			for (int j = sy; j < sy + 3; j++) {
				if (board[i][j] == value)
					return false;
			}
		}
		return true;
	}

	public static int startPoint(int p) {
		switch (p) {
		case 0:
		case 1:
		case 2:
			return 0;
		case 3:
		case 4:
		case 5:
			return 3;
		case 6:
		case 7:
		case 8:
			return 6;
		}
		return -1;
	}

	public static void printAnswer() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}