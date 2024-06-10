import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 외판원 문제
 * 시작점은 어느 도시가 되든 비용이 동일하다는 것을 알고가자
 * 1 -> 2 -> 4 -> 3 -> 1
 * 2 -> 4 -> 3 -> 1 -> 2
 * [문제 해결 프로세스]
 * 비트마스크를 통해 방문했던 경로 표시
 */
public class Main {
    static int N;
    static int INF = 987654322;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        // 배열 초기화
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp 배열 초기화(비트마스킹)
        dp = new int[N][(1 << N) - 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 1));
    }

    static int dfs(int now, int visited) {
        // 모든 점 방문 후 처음으로 되돌아가기
        if (visited == (1 << N) - 1) {
            if (arr[now][0] == 0) return INF;
            return arr[now][0];
        }
        if (dp[now][visited] != -1) return dp[now][visited];  //이미 방문 했다면

        dp[now][visited] = INF;  // 방문 체크
        for (int i = 0; i < N; i++) {
            if (arr[now][i] == 0) continue; // 경로 없음
            if ((visited & (1 << i)) != 0) continue; // 이미 방문했음

            int nextVisited = visited | (1 << i);
            dp[now][visited] = Math.min(dp[now][visited], dfs(i, nextVisited) + arr[now][i]);
        }
        return dp[now][visited];
    }

}