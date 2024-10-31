import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int INF = 987654321;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        // 배열 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp 배열 초기화(비트마스킹)
        dp = new int[N][(1 << N) - 1];
        for (int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
        System.out.println(dfs(0, 1));
        
    }

    public static int dfs(int now, int visit) {
        // 모든 점 방문 후 처음위치로
        if (visit == (1 << N) - 1) {
            if (arr[now][0] == 0) return INF;
            return arr[now][0];
        }
        if (dp[now][visit] != -1) return dp[now][visit]; // 이미 방문했을 경우

        dp[now][visit] = INF;
        for (int i = 0; i < N; i++) {
            if (arr[now][i] == 0) continue; // 경로 없음
            if ((visit & (1 << i)) != 0) continue; // 이미 방문
            int nextVisit = visit | (1 << i);
            dp[now][visit] = Math.min(dp[now][visit], dfs(i, nextVisit) + arr[now][i]);
        }
        return dp[now][visit];
    }


}