import java.io.*;
import java.util.*;

/**
 * dp[i][j] : 현재 지점에서부터 갈 수 있는 최대 거리
 */
public class Main {
    static int N;
    static int[][] arr, dp;
    static int[] dx = {-1, 1, 0 ,0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][N];

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                dp[i][j] = DFS(i, j);
                answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer);
    }

    public static int DFS(int x, int y) {
        if(dp[x][y] != 0) return dp[x][y];
        dp[x][y] = 1;
        for(int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(OOB(nx, ny)) continue;
            if(arr[x][y] >= arr[nx][ny]) continue;
            dp[x][y] = Math.max(dp[x][y], DFS(nx, ny) + 1);
        }
        return dp[x][y];
    }

    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

}