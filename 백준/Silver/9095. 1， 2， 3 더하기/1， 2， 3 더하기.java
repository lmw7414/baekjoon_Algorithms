import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dp = new int[12];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		for(int i = 4; i <= 11; i++) {
			dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
		}

		for(int tc = 0; tc < T; tc++) {
			int num = Integer.parseInt(br.readLine());
			sb.append(dp[num] + "\n");
		}
		System.out.print(sb);
	}

}