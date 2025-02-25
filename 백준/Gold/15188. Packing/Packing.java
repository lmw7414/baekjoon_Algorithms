import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 선물 개수
            int W1 = Integer.parseInt(st.nextToken()); // 배낭 1
            int W2 = Integer.parseInt(st.nextToken()); // 배낭 2

            int[] weights = new int[N];
            int[] values = new int[N];
            int[][] dp = new int[W1 + 1][W2 + 1];

            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) weights[n] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) values[n] = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= N; i++) {
                int weight = weights[i - 1];
                int value = values[i - 1];
                for (int j = W1; j >= 0; j--) {
                    for (int k = W2; k >= 0; k--) {
                        if (j - weight >= 0) dp[j][k] = Math.max(dp[j - weight][k] + value, dp[j][k]);
                        if (k - weight >= 0) dp[j][k] = Math.max(dp[j][k - weight] + value, dp[j][k]);
                    }
                }
            }
            System.out.printf("Problem %d: %d\n", t, dp[W1][W2]);
        }
    }
}