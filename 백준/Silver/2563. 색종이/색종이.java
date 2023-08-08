import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] board = new boolean[100][100];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			drawing(x, y);
		}
		System.out.println(solution());

	}
	
	public static void drawing(int x, int y) {
		for(int i = x; i< x + 10; i++) {
			for(int j = y; j < y + 10; j++) {
				board[i][j] = true;
			}
		}
	}
	
	public static int solution() {
		int answer = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++)
				if(board[i][j]) answer++;
		}
		return answer;
	}

}