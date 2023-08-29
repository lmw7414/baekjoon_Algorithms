import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		dp = new int[X + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 1;
		for(int i = 1; i <= X; i++) {
			if(i + 1 <= X && dp[i + 1] != 0) dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
			if(i * 2 <= X && dp[i * 2] != 0) dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
			if(i * 3 <= X && dp[i * 3] != 0) dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
		}
		System.out.println(dp[X] - 1);
	}

}