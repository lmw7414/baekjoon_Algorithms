import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static int INF = 7654321;
    static int[][] arr;
    static int[][][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 0; k <= K; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j - 1) - '0';
            }
        }

        BFS(1, 1, 0);
        int answer = Integer.MAX_VALUE;
        for (int k = 0; k <= K; k++) answer = Math.min(answer, dp[N][M][k]);
        if (answer == INF) System.out.println(-1);
        else System.out.println(answer);
    }

    public static void BFS(int x, int y, int k) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y, k});
        dp[x][y][k] = 1;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx <= 0 || ny <= 0 || nx > N || ny > M) continue;

                if (arr[nx][ny] == 1) { // 부수고 가거나, 다른 길로 가거나
                    if (cur[2] < K) {
                        if (dp[nx][ny][cur[2] + 1] > dp[cur[0]][cur[1]][cur[2]] + 1) {
                            dp[nx][ny][cur[2] + 1] = dp[cur[0]][cur[1]][cur[2]] + 1;
                            queue.add(new int[]{nx, ny, cur[2] + 1});
                        }
                    }
                } else {
                    if (dp[nx][ny][cur[2]] > dp[cur[0]][cur[1]][cur[2]] + 1) {
                        dp[nx][ny][cur[2]] = dp[cur[0]][cur[1]][cur[2]] + 1;
                        queue.add(new int[]{nx, ny, cur[2]});
                    }
                }
            }
        }
    }

}