import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 7579번: 앱
public class Main {
static int N, M;
    static int[] memories, costs;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        memories = new int[N];
        costs = new int[N];
        dp = new int[N][10001]; // 앱번호, 비용, dp[앱번호][비용] = 최소 사용 메모리
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        for (int j = costs[0]; j < 10001; j++) {
            dp[0][j] = memories[0];
            if (M <= dp[0][j]) answer = Math.min(answer, j);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10001; j++) {
                if (j < costs[i]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - costs[i]] + memories[i]);
                if (M <= dp[i][j]) answer = Math.min(answer, j);
            }
        }
        System.out.println(answer);
    }

}
