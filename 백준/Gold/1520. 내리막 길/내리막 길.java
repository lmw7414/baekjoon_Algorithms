import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] data, dp;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        data = new int[M][];
        int count = 0;

        for (int i = 0; i < M; i++) {
            data[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }

    static int dfs(int x, int y) {
        if (x == M-1 && y == N-1) {
            return 1;
        }

        // 이미 탐색된 값인 경우
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;  // 한번도 방문하지 않은 위치에 대해서는 현재 위치에서 끝점까지 도달하는 경우의 수를 0으로 초기화
        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newY >= 0 && newX <= M-1 && newY <= N-1) {
                if (data[newX][newY] < data[x][y]) {
                    dp[x][y] += dfs(newX, newY);
                }
            }
        }

        return dp[x][y];
    }
}