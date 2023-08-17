import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int R, C;
	static char[][] board;
	static int answer;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		for(int i = 0; i< R; i++) {
			board[i] = br.readLine().toCharArray();
		}

		for(int i = 0; i< R; i++) {
			if(dfs(i, 0)) answer++;
		}
		System.out.println(answer);
	}

	public static boolean dfs(int x, int y) {
		board[x][y] = '-';
		if(y == C - 1) return true;
		if(check(x-1, y+1)) { // 오른쪽 위 대각선으로 이동
			if( dfs(x - 1, y + 1)) 
				return true;  
		}
		if(check(x, y + 1)) { // 오른쪽으로 이동
			if(dfs(x, y + 1)) 
				return true; 
		}
		if(check(x + 1, y + 1)) { // 오른쪽 아래 대각선 이동
			if(dfs(x + 1, y + 1)) 
				return true; 
		}
		return false;

	}
	// 경계체크
	public static boolean check(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < R && ny < C && board[nx][ny] == '.';
	}

}