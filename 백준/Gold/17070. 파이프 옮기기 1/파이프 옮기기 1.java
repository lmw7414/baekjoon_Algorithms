import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] arr;
	static int[][][] dp;  //x좌표 0:-    1:/    2:|
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[3][N][N];
		
		// 배열 입력받기
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		//dp 초기 설정 
		dp[0][0][1] = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 2; j < N; j++) {
				if(arr[i][j] == 1) continue;  // 벽일경우
				dp[0][i][j] = dp[0][i][j - 1] + dp[1][i][j - 1];  //이전 가로, 대각선인 경우
				
				if(i == 0) continue;  // 첫번째 열의 경우 갈 수가 없다.
				dp[2][i][j] = dp[1][i-1][j] + dp[2][i - 1][j];  // 이전 세로, 대각선인 경우
				
				if(arr[i-1][j] == 1 || arr[i][j - 1] == 1) continue;
				dp[1][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
			}
		}
		System.out.println(dp[0][N - 1][N - 1] + dp[1][N - 1][N - 1] + dp[2][N - 1][N - 1]);
		
		
	}

}