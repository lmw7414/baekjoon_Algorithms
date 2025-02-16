import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr = new int[4];
    static int[][] dp;
    static int K;
    static int INF = 98765432;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 3; i++) arr[i] = Integer.parseInt(st.nextToken());
        //Arrays.sort(arr);
        K = Integer.parseInt(st.nextToken());
        dp = new int[7][K + 1];
        for (int i = 0; i < 7; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        int[] t = {0, arr[1] * 4, arr[2] * 4, arr[3] * 2, arr[1] + arr[2], arr[1] * 2 + arr[3], arr[2] * 2 + arr[3]};
        int[] cnt = {0, 4, 4, 2, 2, 3, 3};
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j <= K; j++) {
                if (dp[i - 1][j] != INF) dp[i][j] = dp[i - 1][j];
                if (j % t[i] == 0) dp[i][j] = Math.min(dp[i][j], j / t[i] * cnt[i]);
                if (j >= t[i]) dp[i][j] = Math.min(dp[i][j], dp[i][j - t[i]] + dp[i][t[i]]);
            }
        }

        if (dp[6][K] == INF) System.out.println(-1);
        else System.out.println(dp[6][K]);
    }
}