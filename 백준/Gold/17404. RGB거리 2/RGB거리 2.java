import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static int[][] arr;
    static int[][] dp;
    static int INF = 1001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        dp = new int[N][3];

        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) dp[0][j] = arr[0][j];
                else dp[0][j] = INF;
            }

            for (int j = 1; j < N; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + arr[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + arr[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + arr[j][2];

                if (j == N - 1) {  // 마지막
                    if (i == 0) {
                        answer = Math.min(Math.min(dp[N - 1][1], dp[N - 1][2]), answer);
                    } else if (i == 1) {
                        answer = Math.min(Math.min(dp[N - 1][0], dp[N - 1][2]), answer);
                    } else if (i == 2) {
                        answer = Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), answer);
                    }
                }
            }
        }
        System.out.println(answer);
    }


}