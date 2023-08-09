import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/**
 * 
 * 주어진 방에서 상하좌우 이동가능
 * 단 현재 방에서 딱 1 크기가 큰 값
 * 처음에 출발해야 하는 방 번호와 최대 몇개의 방을 이동할 수 있는지
 * 이동할 수 있는 방의 개수가 최대인 방이 여러개라면, 그 중에서 가작 작은 것 을 출력
 *
 */
public class Solution {

	static int[][] arr;
	static int startRoom;
	static int totalCnt;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			startRoom = Integer.MAX_VALUE;  // 작을 수록 좋음
			totalCnt = Integer.MIN_VALUE; // 클수록 좋음
			for(int i = 0; i< N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j< N; j++) 
					arr[i][j] = Integer.parseInt(st.nextToken());
			}
			dfs(N);
			printAnswer(tc, startRoom, totalCnt);
		}
		System.out.print(sb);
		

	}
	public static void dfs(int N) {
		int[] dx = {-1, 1, 0, 0};  // 상하좌우
		int[] dy = {0, 0, -1, 1};
		Stack<int[]> stack;
		int cnt = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				cnt = 1;
				stack = new Stack();
				stack.add(new int[] {i, j});
				while(!stack.isEmpty()) {
					int[] cur = stack.pop();
					
					for(int k = 0; k < 4; k++) {
						int nx = cur[0] + dx[k];
						int ny = cur[1] + dy[k];
						
						if(nx < 0 || ny < 0 || nx >= N || ny >= N)continue;
						if(arr[nx][ny] == arr[cur[0]][cur[1]] + 1) {  // 다음 위치의 값이 현재 위치보다 1 큰 경우에 해당 
							stack.add(new int[] {nx, ny});
							cnt++;
						}
					}
				}
				if(cnt > totalCnt) {  // cnt가 클 경우
					startRoom = arr[i][j];  // 시작 방의 값은 무조건 변경
					totalCnt = Math.max(totalCnt, cnt);
				} else if(cnt == totalCnt) {
					startRoom = Math.min(startRoom, arr[i][j]);  // 같을 때는 시작 방이 최소가 되는 게 좋다.
				}
			}
		}
	}
	
	public static void printAnswer(int tc, int a, int b) {
		sb.append("#").append(tc).append(" ").append(a).append(" ").append(b).append("\n");
	}

}
