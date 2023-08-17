import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static String[] strings;
	static boolean[] visit = new boolean[26];
	static int[] dx = {-1, 1, 0, 0};  //상하좌우
	static int[] dy = {0, 0, -1, 1};
	static int answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		strings = new String[R];
		for(int i = 0; i< R; i++) {
			strings[i] = br.readLine();
		}
		visit[charToInt(strings[0].charAt(0))] = true;
		dfs(0,0, 1);
		System.out.println(answer);

	}
	
	public static void dfs(int x, int y, int cnt) {
		answer = Math.max(answer, cnt);
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!check(nx, ny)) continue;
			
			visit[charToInt(strings[nx].charAt(ny))] = true;
			dfs(nx, ny, cnt + 1);
			visit[charToInt(strings[nx].charAt(ny))] = false;
		}
	}
	// 경계 체크, 방문한 곳인지 체크
	public static boolean check(int nx, int ny) {
		return (nx >=0 && ny >= 0 && nx < R && ny < C && !visit[charToInt(strings[nx].charAt(ny))]);
	}
	// 문자를 int로
	public static int charToInt(char c) {
		return c - 65;
	}

}