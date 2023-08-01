
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dp(N));
    }

    private static int dp(int N) {
        dp[1] = arr[1];
        if (N == 1)
            return dp[1];
        dp[2] = dp[1] + arr[2];
        int max = dp[2];
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(Math.max(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i]), dp[i - 1]);
            if (max < dp[i]) max = dp[i];
        }
        return max;
    }
}