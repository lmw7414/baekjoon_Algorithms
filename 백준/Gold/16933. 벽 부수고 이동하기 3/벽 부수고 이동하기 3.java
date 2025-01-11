import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][][][] dp; // [벽][x][y]
    static char[][] arr;
    static final int INF = 987654321;
    static int[] dx = {-1, 1, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        dp = new int[2][K + 1][N][M];

        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            for (int m = 0; m < M; m++) {
                arr[n][m] = str.charAt(m);
            }
        }
        initDp();
        calc();
        System.out.println(answer());
    }

    /*
    1. 벽이 없는 경우 상하좌우
    2. 머무르는 경우
    3. 낮이어서 벽을 부수는 경우
     */
    public static void calc() {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0, 0, 0));
        //dp[0][0][0][0] = 1;
        fillDay(0, 0, 0, 0, 1);
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.x == N - 1 && cur.y == M - 1) break;
            int nextFlag = (cur.flag + 1) % 2;
            int curValue = dp[cur.flag][cur.k][cur.x][cur.y];

            if (cur.flag == 0) { // 다음 벽을 허물 수 있는 경우
                for (int d = 0; d < 5; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if (OOB(nx, ny)) continue;
                    if (arr[nx][ny] == '1') { // 벽인 경우
                        if (cur.k >= K) continue; // 벽 부술 수 있는 횟수를 초과한 경우
                        if (dp[nextFlag][cur.k + 1][nx][ny] <= curValue + 1) continue;
                        dp[nextFlag][cur.k + 1][nx][ny] = curValue + 1;
                        queue.add(new Point(nx, ny, cur.k + 1, nextFlag));
                    } else { // 벽이 아닌 경우
                        if (dp[nextFlag][cur.k][nx][ny] <= curValue + 1) continue;
                        fillDay(nextFlag, cur.k, nx, ny, curValue + 1);
                        //dp[nextFlag][cur.k][nx][ny] = curValue + 1;
                        queue.add(new Point(nx, ny, cur.k, nextFlag));
                    }
                }
            } else {
                for (int d = 0; d < 5; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    if (OOB(nx, ny)) continue;
                    if (arr[nx][ny] == '1' && d == 4)  {
                        fillDay(nextFlag, cur.k, nx, ny, curValue + 1);
                        queue.add(new Point(nx, ny, cur.k, nextFlag));
                        continue;
                    }
                    if (arr[nx][ny] == '1') continue;

                    if (dp[nextFlag][cur.k][nx][ny] <= curValue + 1) continue;
                    fillDay(nextFlag, cur.k, nx, ny, curValue + 1);
                    //dp[nextFlag][cur.k][nx][ny] = curValue + 1;
                    queue.add(new Point(nx, ny, cur.k, nextFlag));
                }
            }
        }
    }
    public static void fillDay(int flag, int curk, int x, int y, int val) {
        for(int k = curk; k <= K; k++) dp[flag][k][x][y] = val;
    }
    public static boolean OOB(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    public static void initDp() {
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k <= K; k++) {
                for (int n = 0; n < N; n++) {
                    for (int m = 0; m < M; m++) {
                        dp[i][k][n][m] = INF;
                    }
                }
            }
        }
    }

    public static int answer() {
        int result = INF;
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k <= K; k++) {
                result = Math.min(result, dp[i][k][N - 1][M - 1]);
            }
        }
        if (result == INF) return -1;
        return result;
    }


    static class Point {
        int x, y;
        int k;
        int flag;  // 0 : 낮 | 1 : 밤

        public Point(int x, int y, int k, int flag) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.flag = flag;
        }
    }
}