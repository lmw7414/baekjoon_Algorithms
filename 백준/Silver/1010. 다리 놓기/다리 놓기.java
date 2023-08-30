import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] dp = new int[31][31];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= 30; i++) {
			dp[i][0] = 1;
			dp[i][i] = 1;
		}
		for(int i = 0; i< T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			sb.append(coef(n, r) + "\n");
		}
		System.out.print(sb);
		

		
	}
	
	public static int coef(int n, int r) {
		if(dp[n][r] != 0) return dp[n][r];
		return dp[n][r] = coef(n-1, r-1) + coef(n-1, r);
	}
	

}