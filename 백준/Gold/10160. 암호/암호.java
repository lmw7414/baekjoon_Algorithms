import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp[n] = k ^ n - (dp[n - 5] * 2)
public class Main {
    static int N, K;
    static long MOD = 1_000_000_009;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new long[N + 1];

        dp[0] = 1;
        for (int i = 1; i <= 4; i++) dp[i] = (dp[i - 1] * K) % MOD;

        if (N < 5) {
            System.out.println(dp[N]);
            System.exit(0);
        }

        for (int i = 5; i <= N; i++) {
            dp[i] = ((dp[i - 1] * K + 2*MOD) - ((dp[i - 5] * 2))) % MOD;
            if(i >= 7) { // ABABCBC 인 경우
                dp[i] = (dp[i] + dp[i-7]) % MOD;
            }
        }
        System.out.println(dp[N]);
    }

}